
package com.dacky.service.implement;

import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacky.entity.DetailReport;
import com.dacky.entity.EvaluationCriteria;
import com.dacky.entity.Report;
import com.dacky.repository.DetailReportRepository;
import com.dacky.repository.EvaluationCriteriaRepository;
import com.dacky.repository.ReportRepository;
import com.dacky.service.DetailReportService;
import com.dacky.service.dto.DetailReportDTO;
import com.dacky.service.dto.NewDetailReportDTO;

@Service
public class DetailReportImpl implements DetailReportService {
	@Autowired
	ReportRepository reportRepository;

	@Autowired
	private DetailReportRepository detailReportRepository;

	@Autowired
	private EvaluationCriteriaRepository evaluationCriteriaRepository;

	@Override
	public Optional<DetailReport> getById(Long id) {
		return detailReportRepository.findById(id);
	}

	@Override
	public List<DetailReport> getAll() {

		return detailReportRepository.findAll();
	}

	@Override
	public List<DetailReport> getDetailReportByReportId(Long id) {
		Optional<Report> reportOptional = reportRepository.findById(id);
		if (reportOptional.isPresent()) {
			return reportOptional.get().getDetailReports();
		}
		return null;
	}

	@Override
	public DetailReport updateScore1AndNote(Long idDetailReport, int score1, String note) {
		Optional<DetailReport> detailReportOptional = detailReportRepository.findById(idDetailReport);
		if (detailReportOptional.isPresent()) {
			DetailReport detailReport = detailReportOptional.get();
			detailReport.setNote(note);
			detailReport.setScore1(score1);
			detailReport.setUpdateDate(Instant.now());
			return detailReportRepository.save(detailReport);
		}
		return null;
	}

	@Override
	public DetailReport updateScore2(Long idDetailReport, int score2) {
		Optional<DetailReport> detailReportOptional = detailReportRepository.findById(idDetailReport);
		if (detailReportOptional.isPresent()) {
			DetailReport detailReport = detailReportOptional.get();
			detailReport.setScore2(score2);
			detailReport.setUpdateDate(Instant.now());
			return detailReportRepository.save(detailReport);
		}
		return null;
	}

	@Override
	public DetailReport updateScore3(Long idDetailReport, int score3) {
		Optional<DetailReport> detailReportOptional = detailReportRepository.findById(idDetailReport);
		if (detailReportOptional.isPresent()) {
			DetailReport detailReport = detailReportOptional.get();
			detailReport.setScore3(score3);
			detailReport.setUpdateDate(Instant.now());
			return detailReportRepository.save(detailReport);
		}
		return null;
	}

	@Override
	public DetailReport updateDetailReport(DetailReportDTO detailReportDTO) {

		Optional<DetailReport> detailReportOptional = detailReportRepository.findById(detailReportDTO.getId());
		if (detailReportOptional.isPresent()) {
			DetailReport detailReport = detailReportOptional.get();
			detailReport.setScore1(detailReportDTO.getScore1());
			detailReport.setScore2(detailReportDTO.getScore2());
			detailReport.setScore3(detailReportDTO.getScore3());
			detailReport.setUpdateDate(Instant.now());
			return detailReportRepository.save(detailReport);
		}
		return null;

	}

	@Override
	public DetailReport createDetailReport(NewDetailReportDTO detailReportDTO) {
		DetailReport detailReport = new DetailReport();
		if (detailReportDTO.getParentId() != null) {
			Optional<DetailReport> parentDetailReportOptional = detailReportRepository
					.findById(detailReportDTO.getParentId());
			if (parentDetailReportOptional.isPresent()) {
				detailReport.setParentDetailReport(parentDetailReportOptional.get());
			} else
				return null;
		}

		if (detailReportDTO.getEvaluationCriteriaId() != null) {
			Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository
					.findById(detailReportDTO.getEvaluationCriteriaId());
			if (evaluationCriteriaOptional.isPresent()) {
				detailReport.setEvaluationCriteria(evaluationCriteriaOptional.get());
			} else
				return null;
		}

		if (detailReportDTO.getReportId() != null) {
			Optional<Report> reportOptional = reportRepository.findById(detailReportDTO.getReportId());
			if (reportOptional.isPresent()) {
				detailReport.setReport(reportOptional.get());
			} else
				return null;
		}
		return detailReportRepository.save(detailReport);
	}

	/*
	 * @Override public List<DetailReport> getByPageable(Pageable pageable) {
	 * 
	 * return detailReportRepository.findDetailReports(pageable); }
	 */

}
