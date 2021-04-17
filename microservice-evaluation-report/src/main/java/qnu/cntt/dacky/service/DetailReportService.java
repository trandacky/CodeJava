package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.service.dto.DetailReportDTO;
import qnu.cntt.dacky.service.dto.NewDetailReportDTO;

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
