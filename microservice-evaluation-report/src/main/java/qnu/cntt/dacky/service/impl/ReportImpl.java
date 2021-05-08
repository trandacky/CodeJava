package qnu.cntt.dacky.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.repository.DetailReportRepository;
import qnu.cntt.dacky.repository.ReportRepository;
import qnu.cntt.dacky.repository.TypeReportRepository;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.dto.ReportDTO;
import qnu.cntt.dacky.web.rest.dto.CreateReportDTO;
import qnu.cntt.dacky.web.rest.errors.BadRequestAlertException;

@Service
public class ReportImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private DetailReportRepository detailReportRepository;

	@Autowired
	private TypeReportRepository typeReportRepository;

	@Override
	public Optional<Report> getByUsernameAndTypeReportId(String username, Long typeReportId) {
		return reportRepository.findByUsernameAndTypeReportLike(username, typeReportId);
	}

	@Override
	public Report createReport(String username, Long typeReportId) {

		Report report = new Report();
		report.setUsername(username);

		Optional<TypeReport> typeReport = typeReportRepository.findById(typeReportId);
		if (typeReport.isPresent())
			report.setTypeReport(typeReport.get());
		else
			return null;
		return reportRepository.save(report);
	}

	@Override
	public Report updateReport(ReportDTO reportDTO) {
		Optional<Report> reportOptional = reportRepository.findById(reportDTO.getId());
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setAccepted2(reportDTO.getAccepted2());
			report.setAccepted3(reportDTO.getAccepted3());
			report.setSemester(reportDTO.getSemester());
			report.setTotalScore1(reportDTO.getTotalScore1());
			report.setTotalScore2(reportDTO.getTotalScore2());
			report.setTotalScore3(reportDTO.getTotalScore3());
			report.setYear(reportDTO.getYear());
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Optional<Report> getReportByIdAndUserName(Long id, String username) {
		Optional<Report> reportCheck = reportRepository.findByIdAndUsername(id, username);
		if (!reportCheck.isPresent()) {
			throw new BadRequestAlertException(username, username, username);
		}
		return reportRepository.findById(id);
	}

	@Override
	public Report updateAccepted2(Long id, boolean accepted2) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setAccepted2(accepted2);
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Report updateAccepted3(Long id, boolean accepted3) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setAccepted2(accepted3);
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Report updateTotalScore1(Long id) {
		String username = SecurityUtils.getCurrentUserLogin().get();
		int totalScore1 = 0;
		int sumScore1 = 0;
		Optional<Report> reportCheck = reportRepository.findByIdAndUsername(id, username);
		if (!reportCheck.isPresent()) {
			throw new BadRequestAlertException(username, username, username);
		}
		Report report = reportCheck.get();
		List<DetailReport> detailReports = detailReportRepository.findByReport(report);
		for (int i = 0; i < detailReports.size(); i++) {

			if (detailReports.get(i).getParentDetailReport() != null) {
				detailReports.remove(i);
				i--;
			}
		}
		for (DetailReport detailReport : detailReports) {
			if (detailReport.getChildDetailReport().isEmpty()) {
				int score = detailReport.getScore1();
				if (score > detailReport.getEvaluationCriteria().getMaxScore()) {
					score = detailReport.getEvaluationCriteria().getMaxScore();
				}
				totalScore1 = totalScore1 + score;
			} else {
				sumScore1 = sumScore1(detailReport.getChildDetailReport(), 0);
				if (sumScore1 > detailReport.getEvaluationCriteria().getMaxScore()) {
					sumScore1 = detailReport.getEvaluationCriteria().getMaxScore();
				}
				totalScore1 = totalScore1 + sumScore1;
				detailReport.setScore1(sumScore1);
				detailReportRepository.save(detailReport);
			}
		}
		report.setTotalScore1(totalScore1);
		return reportRepository.save(report);
	}

	private int sumScore1(List<DetailReport> detailReports, int sum) {
		for (DetailReport detailReport : detailReports) {
			if (detailReport.getChildDetailReport().isEmpty()) {
				int score = detailReport.getScore1();
				if (score > detailReport.getEvaluationCriteria().getMaxScore()) {
					score = detailReport.getEvaluationCriteria().getMaxScore();
				}
				sum = sum + score;
			} else {
				sum = sumScore1(detailReport.getChildDetailReport(), sum);
			}
		}
		return sum;
	}

	@Override
	public Report updateTotalScore2(Long id) {
		int totalScore2 = 0;
		int sumScore2 = 0;
		Report report;
		Optional<Report> optional = reportRepository.findById(id);
		if (optional.isPresent()) {
			report = optional.get();
			List<DetailReport> detailReports = detailReportRepository.findByReport(report);
			for (int i = 0; i < detailReports.size(); i++) {

				if (detailReports.get(i).getParentDetailReport() != null) {
					detailReports.remove(i);
					i--;
				}

			}
			for (DetailReport detailReport : detailReports) {
				if (detailReport.getChildDetailReport().isEmpty()) {
					int score = detailReport.getScore2();
					if (score > detailReport.getEvaluationCriteria().getMaxScore()) {
						score = detailReport.getEvaluationCriteria().getMaxScore();
					}
				} else {
					sumScore2 = sumScore2(detailReport.getChildDetailReport(), 0);
					if (sumScore2 > detailReport.getEvaluationCriteria().getMaxScore()) {
						sumScore2 = detailReport.getEvaluationCriteria().getMaxScore();
					}
					totalScore2 = totalScore2 + sumScore2;
					detailReport.setScore2(sumScore2);
					detailReportRepository.save(detailReport);
				}
			}

			report.setTotalScore2(totalScore2);
			return reportRepository.save(report);
		}
		return null;

	}

	private int sumScore2(List<DetailReport> detailReports, int sum) {
		for (DetailReport detailReport : detailReports) {
			if (detailReport.getChildDetailReport().isEmpty()) {
				int score = detailReport.getScore2();
				if (score > detailReport.getEvaluationCriteria().getMaxScore()) {
					score = detailReport.getEvaluationCriteria().getMaxScore();
				}
				sum = sum + score;
			} else {
				sum = sumScore2(detailReport.getChildDetailReport(), sum);
			}
		}
		return sum;
	}

	@Override
	public Report updateTotalScore3(Long id, int totalScore3) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setTotalScore1(totalScore3);
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public List<Report> getAll() {
		return reportRepository.findAll();
	}

	@Override
	public List<Report> getAllByUsername(String username) {
		return reportRepository.findByUsernameLike(username);
	}

	@Override
	public List<Report> getAllByAccepted2False() {
		return reportRepository.findByAccepted2False();
	}

	@Override
	public List<Report> getAllByAccepted2True() {
		return reportRepository.findByAccepted2True();
	}

	@Override
	public List<Report> getAllByAccepted3True() {
		return reportRepository.findByAccepted3True();
	}

	@Override
	public List<Report> getAllByAccepted3False() {
		return reportRepository.findByAccepted3False();
	}

	@Override
	public List<Report> getAllByClassId(UUID classId) {
		return reportRepository.findByClassId(classId);
	}
	@Override
	public List<Report> seachReport(String username, Long classId, int year, int semester) {

		if (username != "")
			username = "%" + username + "%";
		else
			username = "";

		return reportRepository.findByUsernameOrClassIdOrYearOrSemesterLike(username, classId, year, semester);
	}

	@Override
	public List<Report> getByUsernameAndAccepted2False(String username) {
		return reportRepository.findByAccepted2FalseAndUsernameLike(username);
	}

	@Override
	public List<Report> getByUsernameAndAccepted2True(String username) {
		return reportRepository.findByAccepted2TrueAndUsernameLike(username);
	}

	@Override
	public Page<Report> getAllByPageable(Pageable pageable) {

		return reportRepository.findAllByPageable(pageable);
	}

	@Override
	public Page<Report> getAllAccepted3FalseByPageable(Pageable pageable) {

		return reportRepository.findAllAccepted3FalseByPageable(pageable);
	}

	@Override
	public Page<Report> getAllByClassIdAndPageable(UUID classId, Pageable pageable) {

		return reportRepository.findAllByClassIdPageable(classId, pageable);
	}

	@Override
	public Page<Report> getAllByClassIdAccepted2FalseAndPageable(UUID classId, Pageable pageable) {

		return reportRepository.findAllByClassIdAccepted2FalsePageable(classId, pageable);
	}

	@Override
	public Page<Report> getAllByAccepted3FalseByClassIdByPagable(UUID id, Pageable pageable) {
		return reportRepository.findByAccepted3FalseAndClassIdLike(id, pageable);
	}

	@Override
	public List<Report> updateAllReportAccepted3TrueByClassId(Long classId) {

		return reportRepository.updateAccepted3TrueByClassIdLike(classId);
	}

	@Override
	public List<Report> updateAllReportAccepted3FalseByClassId(Long classId) {

		return reportRepository.updateAccepted3FalseByClassIdLike(classId);
	}

	@Override
	public Report createReportByDTO(CreateReportDTO createReportDTO, String username) {
		Report report = new Report();

		Optional<TypeReport> typeReport = typeReportRepository.findById(createReportDTO.getTypeReportId());
		if (typeReport.isPresent() && !ObjectUtils.isEmpty(createReportDTO.getClassuuid())
				&& !ObjectUtils.isEmpty(username)) {
			report.setUsername(username);
			report.setTypeReport(typeReport.get());
			report.setClassId(createReportDTO.getClassuuid());
			report.setSemester(createReportDTO.getSemester());
			report.setYear(createReportDTO.getYear());
			return reportRepository.save(report);
		}
		return null;

	}

	@Override
	public boolean checkReport(CreateReportDTO createReportDTO, String username) {
		Optional<TypeReport> typeReport = typeReportRepository.findById(createReportDTO.getTypeReportId());
		if (typeReport.isPresent()) {
			Optional<Report> optional = reportRepository.findByTypeReportAndClassIdAndYearAndSemesterAndUsername(
					typeReport.get(), createReportDTO.getClassuuid(), createReportDTO.getYear(),
					createReportDTO.getSemester(), username);
			if (optional.isPresent()) {
				return true;
			}
		}
		return false;

	}

	@Override
	public Page<Report> getAllReportByUsername(String username, Pageable paging) {
		return reportRepository.findByUsernameLike(username, paging);
	}

	@Override
	public boolean checkReportByUsername(Long id, String username) {
		Optional<Report> report = reportRepository.findByIdAndUsername(id, username);
		if (report.isPresent())
			return true;
		return false;
	}

	@Override
	public Optional<Report> getReportById(Long id) {
		return reportRepository.findById(id);
	}

	@Override
	public List<Report> updateAllAccepted2ByClass(UUID classUUID) {
		List<Report> reports= reportRepository.findByClassId(classUUID);
		for(Report report:reports)
		{
			report.setAccepted2(true);
		}
		return reportRepository.saveAll(reports);
	}

}
