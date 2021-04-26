package qnu.cntt.dacky.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.service.dto.EvaluationCriteriaUpdateDTO;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.EvaluationCriteria;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.service.DetailReportService;
import qnu.cntt.dacky.service.EvaluationCriteriaService;
import qnu.cntt.dacky.service.ReportService;
import qnu.cntt.dacky.service.TypeReportService;
import qnu.cntt.dacky.service.dto.EvaluationCriteriaDTO;
import qnu.cntt.dacky.service.dto.NewDetailReportDTO;
import qnu.cntt.dacky.service.dto.TypeReportDTO;
import qnu.cntt.dacky.service.dto.UpdateDetailReportScore23DTO;
import qnu.cntt.dacky.service.dto.UpdateReportScore23DTO;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class AdminRestController {
	private final int sizePage = 10;
	@Autowired
	private TypeReportService typeReportService;

	@Autowired
	private EvaluationCriteriaService evaluationCriteriaService;

	@Autowired
	private DetailReportService detailReportService;

	@Autowired
	private ReportService reportService;

	@GetMapping("/get-all-report-by-class-id")
	public ResponseEntity<Map<String, Object>> getReportInClass(@RequestParam Long classid,
			@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("id").ascending());
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

	@GetMapping("/get-report-example-by-type-id/{id}")
	public List<EvaluationCriteria> getReportExampleByTypeId(@PathVariable Long id) {

		List<EvaluationCriteria> evaluationCriterias = evaluationCriteriaService.getByTypeReportId(id);

		for (int i = 0; i < evaluationCriterias.size(); i++) {

			if (evaluationCriterias.get(i).getParentEvaluationCriteria() != null) {
				evaluationCriterias.remove(i);
				i--;
			}

		}
		return evaluationCriterias.stream().sorted(Comparator.comparing(EvaluationCriteria::getCreateDate))
				.collect(Collectors.toList());
	}

	@GetMapping("/get-all-type-report-enable")
	public List<TypeReport> getAllTypeReportEnable() {
		return typeReportService.getEnableTypeReport();
	}
	@GetMapping("/get-type-report-by-id/{id}")
	public Optional<TypeReport> getTypeReportById(@PathVariable Long id) {
		return typeReportService.getTypeReportById(id);
	}

	@GetMapping("/get-all-type-report")
	public ResponseEntity<Map<String, Object>> getAllTypeReport(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("id").ascending());
			Page<TypeReport> pageable = typeReportService.getAllTypeReportPageable(paging);
			List<TypeReport> typeReports = pageable.getContent();
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

	@PutMapping("/update-type-report")
	public TypeReport updateTypeReport(@RequestBody TypeReportDTO typeReportDTO) {

		return typeReportService.updateTypeReport(typeReportDTO);
	}

	@PutMapping("/update-type-name-type-report")
	public TypeReport updateContentTypeReport(@RequestParam(required = true) Long id, @RequestParam String typeName) {

		return typeReportService.updateContentTypeReport(id, typeName);
	}

	@PutMapping("/update-enable-type-report")
	public TypeReport updateEnableTypeReport(@RequestParam(required = true) Long id, @RequestParam boolean enable) {

		return typeReportService.updateEnableTypeReport(id, enable);
	}

	@PostMapping("create-type-report")
	public TypeReport createTypeReport(@RequestParam(required = true) String typename) {
		return typeReportService.createTypeReportByTypeName(typename);
	}

	@PostMapping("create-evaluation-criteria")
	public EvaluationCriteria createEvaluationCriteria(@RequestBody EvaluationCriteriaDTO evaluationCriteriaDTO) {
		return evaluationCriteriaService.createEvaluaCriteria(evaluationCriteriaDTO);
	}

	/* create detail report */
	@PostMapping("/create-report-and-detail-report-by-type-report-by-username")
	public void createReportAndDetailReportByUsername(@RequestParam String username, @RequestParam Long typereportid) {
		Long reportId = (long) 0;
		Optional<TypeReport> typeReportOptional = typeReportService.getTypeReportById(typereportid);
		if (typeReportOptional.isPresent()) {
			reportId = reportService.createReport(username, typereportid).getId();
			createDetailReport(reportId, typeReportOptional.get());
		}
	}

	/* end create detail */

	@GetMapping("/get-all-report-accepted3-false-by-class-id")
	public ResponseEntity<Map<String, Object>> getReportAccepted3FalseInClass(@RequestParam Long classid,
			@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("id").descending());
			Page<Report> pageable = reportService.getAllByAccepted3FalseByClassIdByPagable(classid, paging);
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

	@GetMapping("/get-all-report")
	public ResponseEntity<Map<String, Object>> getAllReport(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("id").descending());
			Page<Report> pageable = reportService.getAllByPageable(paging);
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

	@GetMapping("/get-detail-report-by-report-id")
	public List<DetailReport> getDetailReportByReportId(@RequestParam Long id) {
		return detailReportService.getDetailReportByReportId(id);
	}

	@PutMapping("update-evaluation-criteria")
	public EvaluationCriteria updateEvaluationCriteria(@RequestBody EvaluationCriteriaDTO evaluationCriteriaUpdateDTO) {
		return evaluationCriteriaService.updateEvaluaCriteria(evaluationCriteriaUpdateDTO);
	}

	@PutMapping("/update-all-accepted3-by-classid")
	public List<Report> updateAllAccepted3TrueByClassId(@RequestParam Long classid) {
		try {
			return reportService.updateAllReportAccepted3TrueByClassId(classid);
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/dis-update-all-accepted3-by-classid")
	public List<Report> updateAllAccepted3FalseByClassId(@RequestParam Long classid) {
		try {
			return reportService.updateAllReportAccepted3TrueByClassId(classid);
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/update-accepted3")
	public Report updateAccepted3(@RequestParam Long id, boolean accepted3) {
		return reportService.updateAccepted3(id, accepted3);
	}
	/* begin do submit report */

	@PutMapping("/submit-report-score3")
	public void updateReportScore3(@RequestBody UpdateReportScore23DTO reportScore23DTO) {
		if (!reportScore23DTO.getDetailReportScore23DTOs().isEmpty()) {
			int totalScore3 = functionUpdateReportScore3AndCountTotalScore3(
					reportScore23DTO.getDetailReportScore23DTOs());
			reportService.updateTotalScore1(reportScore23DTO.getId(), totalScore3);
		}

	}

	@DeleteMapping("/delete-evaluation-report/{id}")
	public void deleteEvaluationReport(@PathVariable Long id) {
		evaluationCriteriaService.deleteEvaluationReport(id);
	}

	private int functionUpdateReportScore3AndCountTotalScore3(
			List<UpdateDetailReportScore23DTO> listDetailReportScore23DTOs) {
		int total = 0;
		for (UpdateDetailReportScore23DTO detailReport : listDetailReportScore23DTOs) {
			if (detailReport.getDetailReportScore23DTOs().isEmpty()) {
				detailReportService.updateScore2(detailReport.getId(), detailReport.getScore());
				return detailReport.getScore();
			} else {
				int totalPiece = (0
						+ functionUpdateReportScore3AndCountTotalScore3(detailReport.getDetailReportScore23DTOs()));
				total += totalPiece;
				detailReportService.updateScore3(detailReport.getId(), totalPiece);
			}
		}
		return total;
	}

	private void createDetailReport(Long reportId, TypeReport typeReport) {

		NewDetailReportDTO newDetailReportDTO = new NewDetailReportDTO();

		newDetailReportDTO.setReportId(reportId);

		List<EvaluationCriteria> evaluationCriterias = typeReport.getEvaluationCriterias();

		for (EvaluationCriteria evaluationCriteria : evaluationCriterias) {
			if (evaluationCriteria.getParentEvaluationCriteria() == null) {

				newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());

				DetailReport detailReport = createDetailToDatabase(newDetailReportDTO);

				newDetailReportDTO.setParentId(detailReport.getId());

				createDetailTree(newDetailReportDTO, evaluationCriteria, detailReport, true);
			}
		}
	}

	private DetailReport createDetailToDatabase(NewDetailReportDTO newDetailReportDTO) {
		return detailReportService.createDetailReport(newDetailReportDTO);
	}

	private void createDetailTree(NewDetailReportDTO newDetailReportDTO, EvaluationCriteria evaluationCriteria,
			DetailReport detailReport, boolean isParent) {
		if (!evaluationCriteria.getChildEvaluationCriterias().isEmpty()) {
			newDetailReportDTO.setParentId(detailReport.getId());

			for (EvaluationCriteria evaluationCriteria2 : evaluationCriteria.getChildEvaluationCriterias()) {
				newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());
				if (isParent) {
					createDetailTree(newDetailReportDTO, evaluationCriteria2, detailReport, false);
				} else
					createDetailTree(newDetailReportDTO, evaluationCriteria2,
							createDetailToDatabase(newDetailReportDTO), false);
			}
		} else {

			newDetailReportDTO.setParentId(detailReport.getId());

			newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());

			createDetailToDatabase(newDetailReportDTO);
		}
	}
}
