package qnu.cntt.dacky.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import qnu.cntt.dacky.service.DetailReportService;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.dto.UpdateDetailReportScore1AndNoteDTO;
import qnu.cntt.dacky.service.dto.UpdateDetailReportScore23DTO;
import qnu.cntt.dacky.service.dto.UpdateReportScore1DTO;
import qnu.cntt.dacky.service.dto.UpdateReportScore23DTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/class/president")
public class ClassPresidentRestController {
	private final int sizePage = 20;

	@Autowired
	private DetailReportService detailReportService;

	@Autowired
	private ReportService reportService;

	@GetMapping("/get-all-report-by-classid")
	public ResponseEntity<Map<String, Object>> getReportInClass(@RequestParam Long classid,
			@RequestParam(defaultValue = "0") int page) {

		try {
			Pageable paging = PageRequest.of(page, sizePage);
			Page<Report> pageable = reportService.getAllByClassIdAndPageable(classid, paging);
			List<Report> reports = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("reports", reports);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-report-by-id")
	public Optional<Report> getReportById(@RequestParam Long id) {
		return reportService.getReportById(id);
	}
	@GetMapping("/get-detail-report-by-report-id")
	public List<DetailReport> getDetailReportByReportId(@RequestParam Long id)
	{
		return detailReportService.getDetailReportByReportId(id);
	}

	@GetMapping("/get-all-report-by-classid-accepted2-false")
	public ResponseEntity<Map<String, Object>> getReportFalseInClass(@RequestParam Long classid,
			@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage);
			Page<Report> pageable = reportService.getAllByClassIdAndPageable(classid, paging);
			List<Report> reports = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("reports", reports);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/* begin do submit report */

	@PutMapping("/submit-report-score1")
	public void updateReportScore1(@RequestBody UpdateReportScore1DTO reportScore1DTO) {
		if (!reportScore1DTO.getDetailReportScore1AndNoteDTO().isEmpty()) {
			int totalScore1 = functionUpdateReportScore1AndCountTotalScore(
					reportScore1DTO.getDetailReportScore1AndNoteDTO());
			reportService.updateTotalScore1(reportScore1DTO.getId(), totalScore1);
		}
	}

	@PutMapping("/submit-report-score2")
	public void updateReportScore2(@RequestBody UpdateReportScore23DTO reportScore23DTO) {
		if (!reportScore23DTO.getDetailReportScore23DTOs().isEmpty()) {
			int totalScore2 = functionUpdateReportScore2AndCountTotalScore2(
					reportScore23DTO.getDetailReportScore23DTOs());
			reportService.updateTotalScore1(reportScore23DTO.getId(), totalScore2);
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

	private int functionUpdateReportScore2AndCountTotalScore2(
			List<UpdateDetailReportScore23DTO> listDetailReportScore23DTOs) {
		int total = 0;
		for (UpdateDetailReportScore23DTO detailReport : listDetailReportScore23DTOs) {
			if (detailReport.getDetailReportScore23DTOs().isEmpty()) {
				detailReportService.updateScore2(detailReport.getId(), detailReport.getScore());
				return detailReport.getScore();
			} else {
				int totalPiece = (0
						+ functionUpdateReportScore2AndCountTotalScore2(detailReport.getDetailReportScore23DTOs()));
				total += totalPiece;
				detailReportService.updateScore2(detailReport.getId(), totalPiece);
			}
		}
		return total;
	}

}
