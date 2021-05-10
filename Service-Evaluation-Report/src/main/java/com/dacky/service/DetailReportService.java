package com.dacky.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import com.dacky.entity.DetailReport;
import com.dacky.service.dto.DetailReportDTO;
import com.dacky.service.dto.NewDetailReportDTO;

public interface DetailReportService {

//	List<DetailReport> getByPageable(Pageable pageable);
	
	Optional<DetailReport> getById(Long id);
	
	DetailReport createDetailReport(NewDetailReportDTO newDetailReportDTO);

	List<DetailReport> getAll();

	List<DetailReport> getDetailReportByReportId(Long id);

	DetailReport updateDetailReport(DetailReportDTO detailReportDTO);
	
	
	

	DetailReport updateScore1AndNote(Long idDetailReport, int score1, String note);

	DetailReport updateScore2(Long idDetailReport, int score2);

	DetailReport updateScore3(Long idDetailReport, int score3);

}
