package qnu.cntt.dacky.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.AbtractEntity;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.repository.ReportRepository;
import qnu.cntt.dacky.repository.TypeReportRepository;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.dto.ReportDTO;
import qnu.cntt.dacky.web.rest.dto.CreateReportDTO;

@Service
public class ReportImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;

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
			report.setUpdateDate(Instant.now());
			report.setYear(reportDTO.getYear());
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Optional<Report> getReportById(Long reportId) {

		return reportRepository.findById(reportId);
	}

	@Override
	public Report updateAccepted2(Long id, boolean accepted2) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setAccepted2(accepted2);
			report.setUpdateDate(Instant.now());
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
			report.setUpdateDate(Instant.now());
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Report updateTotalScore1(Long id, int totalScore1) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setTotalScore1(totalScore1);
			report.setUpdateDate(Instant.now());
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Report updateTotalScore2(Long id, int totalScore2) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setTotalScore1(totalScore2);
			report.setUpdateDate(Instant.now());
			return reportRepository.save(report);
		}
		return null;
	}

	@Override
	public Report updateTotalScore3(Long id, int totalScore3) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			Report report = reportOptional.get();
			report.setTotalScore1(totalScore3);
			report.setUpdateDate(Instant.now());
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
	public List<Report> getAllByClassId(Long classId) {
		return reportRepository.findByClassId(classId);
	}

	@Override
	public List<Report> getAllByClassIdAndAccepted2False(Long classId) {
		return reportRepository.findByAccepted2FalseAndClassId(classId);
	}

	@Override
	public List<Report> getAllByClassIdAndAccepted2True(Long classId) {
		return reportRepository.findByAccepted2TrueAndClassId(classId);
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
	public Page<Report> getAllByClassIdAccepted2FalseAndPageable(Long classId, Pageable pageable) {

		return reportRepository.findAllByClassIdAccepted2FalsePageable(classId, pageable);
	}

	@Override
	public Page<Report> getAllByAccepted3FalseByClassIdByPagable(Long id, Pageable pageable) {
		return reportRepository.findByAccepted3FalseAndClassIdLike(id, pageable);
	}

	@Override
	public List<Report> updateAllReportAccepted3TrueByClassId(Long classId) {

		return reportRepository.updateAccepted3TrueByClassIdLike(classId, Instant.now());
	}

	@Override
	public List<Report> updateAllReportAccepted3FalseByClassId(Long classId) {

		return reportRepository.updateAccepted3FalseByClassIdLike(classId, Instant.now());
	}

	@Override
	public Report createReportByDTO(CreateReportDTO createReportDTO, String username) {
		Report report = new Report();

		Optional<TypeReport> typeReport = typeReportRepository.findById(createReportDTO.getTypeReportId());
		if (typeReport.isPresent()) {
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
		return reportRepository.findByAccepted2FalseAndAccepted3FalseAndUsernameLike(username,paging);
	}
}
