package qnu.cntt.dacky.web.rest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import qnu.cntt.dacky.web.rest.dto.DetailReportSVDTO;
import qnu.cntt.dacky.web.rest.dto.InitDetailReportDTO;

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
			String username = SecurityUtils.getCurrentUserLogin().get();

			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createDate").ascending());
			Page<Report> pageable = reportService.getAllReportByUsername(username, paging);
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

	@GetMapping("/get-detail-report-by-reportid")
	public ResponseEntity<Map<String, Object>> getDetailReportByReportId(@RequestParam Long id) {
		String username = SecurityUtils.getCurrentUserLogin().get();
		if (reportService.checkReportByUsername(id, username)) {
			List<DetailReport> detailReports = detailReportService.getDetailReportByReportId(id);
			for (int i = 0; i < detailReports.size(); i++) {

				if (detailReports.get(i).getParentDetailReport() != null) {
					detailReports.remove(i);
					i--;
				}

			}
			Map<String, Object> response = new HashMap<>();
			response.put("detailReports", detailReports);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/get-detail-report")
	public ResponseEntity<Map<String, Object>> getDetailReportTESTTTTTTTT(@RequestParam Long id) {
		String username = SecurityUtils.getCurrentUserLogin().get();
		if (reportService.checkReportByUsername(id, username)) {
			List<DetailReport> detailReports = detailReportService.getDetailReportByReportId(id);
			for (int i = 0; i < detailReports.size(); i++) {

				if (detailReports.get(i).getParentDetailReport() != null) {
					detailReports.remove(i);
					i--;
				}

			}
			List<DetailReportSVDTO> detailReportSVDTOs = new ArrayList<>();
			for (int i = 0; i < detailReports.size(); i++) {

				detailReportSVDTOs.add(new DetailReportSVDTO(detailReports.get(i)));

			}
			detailReportSVDTOs.sort(Comparator.comparing(DetailReportSVDTO::getCreatedDate));
//			Collections.reverse(detailReportSVDTOs);
			for (DetailReportSVDTO detailReportSVDTO : detailReportSVDTOs) {
				Collections.reverse(detailReportSVDTO.getDetailReportDTOs());
				detailReportSVDTO.getDetailReportDTOs().sort(Comparator.comparing(InitDetailReportDTO::getCreatedDate));
			} 
			Optional<Report> optional = reportService.getReportByIdAndUserName(id,username);
			Map<String, Object> response = new HashMap<>();
			response.put("detailReports", detailReportSVDTOs);
			response.put("report", optional.get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/get-report-by-id")
	public Optional<Report> getReportById(@RequestParam Long id) {
		String username = SecurityUtils.getCurrentUserLogin().get();
		return reportService.getReportByIdAndUserName(id,username);
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
	public Report updateReportTotalScore1(@RequestParam Long idreport) {
		return reportService.updateTotalScore1(idreport);
	}

	/* detail report */
	@PutMapping("/update-detail-report-scrore1-and-note")
	public Instant updateDetailReportScore1(@RequestParam Long id, @RequestParam int score1,
			@RequestParam String note) {
		return detailReportService.updateScore1AndNote(id, score1, note);
	}
}
