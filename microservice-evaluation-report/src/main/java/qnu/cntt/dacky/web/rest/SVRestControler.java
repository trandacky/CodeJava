package qnu.cntt.dacky.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.DetailReportService;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.TypeReportService;
import qnu.cntt.dacky.service.dto.UpdateDetailReportScore1AndNoteDTO;
import qnu.cntt.dacky.service.dto.UpdateReportScore1DTO;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/sinhvien")
public class SVRestControler {
	
	private final int sizePage = 10;
	@Autowired
	private TypeReportService typeReportService;
	@Autowired
	private ReportService reportService;

	@Autowired
	private DetailReportService detailReportService;

	@GetMapping("/get-all-report-by-username")
	public ResponseEntity<Map<String, Object>> getAllReportByUsername(@RequestParam(defaultValue = "0") int page) {
		
		try {
			String username=SecurityUtils.getCurrentUserLogin().get();
			
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createDate").ascending());
			Page<Report> pageable = reportService.getAllReportByUsername(username,paging);
			List<Report> typeReports = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("reports", typeReports);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
