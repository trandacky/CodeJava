package qnu.cntt.dacky.web.rest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.service.DetailReportService;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.web.rest.dto.DetailReportSVDTO;
import qnu.cntt.dacky.web.rest.dto.InitDetailReportDTO;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/canbolop")
public class CBLRestController {
	private final int sizePage = 10;

	@Autowired
	private DetailReportService detailReportService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private CallUAA callUAA;

	@GetMapping("/get-all-report-by-classid")
	public ResponseEntity<Map<String, Object>> getReportInClass(@RequestParam(defaultValue = "0") int page) {

		try {
			UUID classid = callUAA.getClassUUID();
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("updateDate").descending());
			Page<Report> pageable = reportService.getAllByClassIdAccepted2FalseAndPageable(classid, paging);
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

//
//	@GetMapping("/get-report-by-id")
//	public Optional<Report> getReportById(@RequestParam Long id) {
//		return reportService.getReportById(id);
//	}

	@GetMapping("/get-detail-report-by-report-id")
	public ResponseEntity<Map<String, Object>> getDetailReportByReportId(@RequestParam Long id) {

		Optional<Report> optional = reportService.getReportById(id);
		if (optional.isPresent()) {
			if (optional.get().getClassId().equals(callUAA.getClassUUID())) {
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
//		Collections.reverse(detailReportSVDTOs);
				for (DetailReportSVDTO detailReportSVDTO : detailReportSVDTOs) {
					Collections.reverse(detailReportSVDTO.getDetailReportDTOs());
					detailReportSVDTO.getDetailReportDTOs()
							.sort(Comparator.comparing(InitDetailReportDTO::getCreatedDate));
				}

				Map<String, Object> response = new HashMap<>();
				response.put("detailReports", detailReportSVDTOs);
				response.put("report", optional.get());
				response.put("infoAccount", callUAA.getAccountInfoClass(optional.get().getUsername()));
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update-score2")
	public Instant updateScore2(@RequestParam("id") Long id,
			@RequestParam("score2") int score2) {
		return detailReportService.updateScore2(id, score2);		
	}

	@PutMapping("/update-total-score2")
	public ResponseEntity<Map<String, Object>> updateTotalScore2(@RequestParam("id") Long id) {
		try {

			Optional<Report> report = reportService.getReportById(id);
			if (report.isPresent()) {
				if (report.get().getClassId().equals(callUAA.getClassUUID())) {
					Map<String, Object> response = new HashMap<>();
					response.put("detailReports", reportService.updateTotalScore2(id));
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
}
