package qnu.cntt.dacky.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.service.DetailReportService;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.dto.UpdateDetailReportScore1AndNoteDTO;
import qnu.cntt.dacky.service.dto.UpdateReportScore1DTO;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/class/student")
public class StudentRestControler {
	@Autowired
	private ReportService reportService;

	@Autowired
	private DetailReportService detailReportService;

	@GetMapping("/get-all-report-by-username")
	public List<Report> getAllReportByUsername(@RequestParam String username) {
		return reportService.getAllByUsername(username);
	}
	
	@GetMapping("/get-detail-report-by-reportid")
	public List<DetailReport> getDetailReportByReportId(@RequestParam Long id)
	{
		return detailReportService.getDetailReportByReportId(id);
	}
	
	@GetMapping("/get-report-by-id")
	public Optional<Report> getReportById(@RequestParam Long id) {
		return reportService.getReportById(id);
	}

	@GetMapping("/get-report-accepted2-false-by-username")
	public List<Report> getReportAccepted2False(@RequestParam String username) {
		return reportService.getByUsernameAndAccepted2False(username);
	}

	@GetMapping("/get-report-by-username-and-type-report-id")
	public Optional<Report> getAllReportByUsernameAndTypeReportId(@RequestParam String username,
			@RequestParam Long idtype) {
		return reportService.getByUsernameAndTypeReportId(username, idtype);
	}

	@PutMapping("/update-report-total-score1")
	public Report updateReportTotalScore1(@RequestParam Long idreport, @RequestParam int totalscore1) {
		return reportService.updateTotalScore1(idreport, totalscore1);
	}

	/* detail report */
	@PutMapping("/update-detail-report-scrore1-and-note")
	public DetailReport updateDetailReportScore1(@RequestParam Long id, @RequestParam int score1,
			@RequestParam String note) {
		return detailReportService.updateScore1AndNote(id, score1, note);
	}

	@PutMapping("/submit-report-score1")
	public void updateReportScore1(@RequestBody UpdateReportScore1DTO reportScore1DTO) {
		if (!reportScore1DTO.getDetailReportScore1AndNoteDTO().isEmpty()) {

			int totalScore1 = functionUpdateReportScore1AndCountTotalScore(
					reportScore1DTO.getDetailReportScore1AndNoteDTO());

			reportService.updateTotalScore1(reportScore1DTO.getId(), totalScore1);
		}

	}

	private int functionUpdateReportScore1AndCountTotalScore(
			List<UpdateDetailReportScore1AndNoteDTO> listUpdateDetailReportDTO) {
		int total = 0;
		for (UpdateDetailReportScore1AndNoteDTO detailReport : listUpdateDetailReportDTO) {
			if (detailReport.getDetailReportScore1AndNoteDTO().isEmpty()) {
				detailReportService.updateScore1AndNote(detailReport.getId(), detailReport.getScore1(),
						detailReport.getNote());
				return detailReport.getScore1();
			} else {
				int totalPiece = (0
						+ functionUpdateReportScore1AndCountTotalScore(detailReport.getDetailReportScore1AndNoteDTO()));
				total += totalPiece;
				detailReportService.updateScore1AndNote(detailReport.getId(), totalPiece, detailReport.getNote());
			}
		}
		return total;

	}

}
