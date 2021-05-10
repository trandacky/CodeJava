package qnu.cntt.dacky.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import qnu.cntt.dacky.web.rest.dto.ClassKhoaAndCountDTO;
import qnu.cntt.dacky.web.rest.dto.ClassKhoaDTO;
import qnu.cntt.dacky.web.rest.dto.CommunicateAccountClassDTO;
import qnu.cntt.dacky.web.rest.dto.CreateReportDTO;
import qnu.cntt.dacky.web.rest.dto.DetailReportSVDTO;
import qnu.cntt.dacky.web.rest.dto.InitDetailReportDTO;
import qnu.cntt.dacky.web.rest.dto.PageYearSemester;
import qnu.cntt.dacky.web.rest.errors.BadRequestAlertException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/khoa")
public class KhoaRestController {
	private final int sizePage = 10;
	@Autowired
	private TypeReportService typeReportService;

	@Autowired
	private EvaluationCriteriaService evaluationCriteriaService;

	@Autowired
	private CallUAA callUAA;

	@Autowired
	private DetailReportService detailReportService;

	@Autowired
	private ReportService reportService;

	@GetMapping("/get-all-report-by-class-id")
	public ResponseEntity<Map<String, Object>> getReportInClass(@RequestParam UUID classid,
			@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createDate").ascending());
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
		for (EvaluationCriteria criteria : evaluationCriterias) {
			if (!criteria.getChildEvaluationCriterias().isEmpty()) {
				sort(criteria.getChildEvaluationCriterias());
			}
		}
		return evaluationCriterias.stream().sorted(Comparator.comparing(EvaluationCriteria::getCreateDate))
				.collect(Collectors.toList());
	}

	private void sort(List<EvaluationCriteria> evaluationCriterias) {
		for (EvaluationCriteria criteria : evaluationCriterias) {
			if (!criteria.getChildEvaluationCriterias().isEmpty()) {
				sort(criteria.getChildEvaluationCriterias());
				criteria.getChildEvaluationCriterias().sort(Comparator.comparing(EvaluationCriteria::getCreateDate));
			}
		}
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
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createDate").ascending());
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
	@PostMapping("/create-report-and-detail-report-by-type-report-and-username")
	public boolean createReportAndDetail(@RequestParam String username, @RequestParam Long typereportid) {
		return createReportAndDetailReportByUsername(username, typereportid);
	}

	@PostMapping("/create-report-and-detail-report-by-type-report-and-classuuid")
	public boolean createReportAndDetailByClass(@RequestBody CreateReportDTO createReportDTO) {
		try {

			List<CommunicateAccountClassDTO> accountClassDTOs = callUAA.getAccountClass(createReportDTO.getClassuuid());
			for (CommunicateAccountClassDTO classDTO : accountClassDTOs) {
				createReportAndDetailReportByCreateReportDTO(createReportDTO, classDTO.getUsername());
			}
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create or connect", "exception");
		}
		return true;
	}

	@PostMapping("/create-report-and-detail-report-by-type-report-all-account")
	public boolean createReportAndDetailAll(@RequestBody CreateReportDTO createReportDTO) {
		try {

			List<CommunicateAccountClassDTO> accountClassDTOs = callUAA.getAllAccountKhoa();
			for (CommunicateAccountClassDTO classDTO : accountClassDTOs) {
				createReportDTO.setClassuuid(classDTO.getClassuuid());
				createReportAndDetailReportByCreateReportDTO(createReportDTO, classDTO.getUsername());
			}
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create or connect", "exception");
		}
		return true;
	}

	/* end create detail */

	@PostMapping("/get-all-class-by-year-and-semester")
	public ResponseEntity<Map<String, Object>> getAllReportByYearAndSemester(
			@RequestBody PageYearSemester pageYearSemester) {
		try {
			ClassKhoaAndCountDTO khoaAndCountDTO = callUAA.getAllClassKhoa(pageYearSemester.getPage());
			List<ClassKhoaDTO> classReturnDTOs = khoaAndCountDTO.getClassKhoaDTOs();
			for (ClassKhoaDTO classReturnDTO : classReturnDTOs) {
				classReturnDTO.setCountReport(reportService.getCountReportByClass(classReturnDTO.getUuid(),
						pageYearSemester.getYear(), pageYearSemester.getSemester(),pageYearSemester.getTypeReportId()));
				classReturnDTO.setCountAccepted3(reportService.getCountReportAccepted3TrueByClass(classReturnDTO.getUuid(),
						pageYearSemester.getYear(), pageYearSemester.getSemester(),pageYearSemester.getTypeReportId()));
			}
			Map<String, Object> response = new HashMap<>();
			response.put("classList", classReturnDTOs);
			response.put("totalItems", khoaAndCountDTO.getTotalItems());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/get-all-report-by-class-year-and-semester")
	public ResponseEntity<Map<String, Object>> getAllReportByClassYearAndSemester(
			@RequestBody PageYearSemester pageYearSemester) {
		try {
			Pageable paging = PageRequest.of(pageYearSemester.getPage(), sizePage, Sort.by("createDate").descending());
			Page<Report> pageable = reportService.getAllByCondition(pageYearSemester,paging);
			List<Report> reports = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("reports", reports);
			response.put("totalItems", pageable.getTotalElements());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-detail-report-by-report-id")
	public ResponseEntity<Map<String, Object>> getDetailReportByReportId(@RequestParam Long id) {
		Optional<Report> optional = reportService.getReportById(id);
		if (optional.isPresent()) {
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
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("update-evaluation-criteria")
	public EvaluationCriteria updateEvaluationCriteria(@RequestBody EvaluationCriteriaDTO evaluationCriteriaUpdateDTO) {
		return evaluationCriteriaService.updateEvaluaCriteria(evaluationCriteriaUpdateDTO);
	}

	@PutMapping("/update-all-accepted3")
	public List<Report> updateAllAccepted3(@RequestBody PageYearSemester pageYearSemester) {
		return reportService.updateAllReportAccepted3(pageYearSemester);
	}

	@PutMapping("/dis-update-all-accepted3-by-classid")
	public List<Report> updateAllAccepted3FalseByClassId(@RequestParam Long classid) {
		try {
			return reportService.updateAllReportAccepted3TrueByClassId(classid);
		} catch (Exception e) {
			return null;
		}
	}
	@PutMapping("/update-total-score3")
	public Report updateTotalScore3(@RequestParam Long id) {
		return reportService.updateTotalScore3(id);
	}
	@PutMapping("/update-accepted3")
	public Report updateAccepted3(@RequestParam Long id,@RequestParam boolean accepted3) {
		return reportService.updateAccepted3(id, accepted3);
	}
	@PutMapping("/update-detail-score3")
	public DetailReport updateDetailScore3(@RequestParam Long id,@RequestParam int score3) {
		return detailReportService.updateScore3(id, score3);
	}

	@DeleteMapping("/delete-evaluation-report/{id}")
	public void deleteEvaluationReport(@PathVariable Long id) {
		evaluationCriteriaService.deleteEvaluationReport(id);
	}

	private boolean createReportAndDetailReportByCreateReportDTO(CreateReportDTO createReportDTO, String username) {
		try {
			if (!reportService.checkReport(createReportDTO, username)) {
				Long reportId = (long) 0;
				Optional<TypeReport> typeReportOptional = typeReportService
						.getTypeReportById(createReportDTO.getTypeReportId());
				if (typeReportOptional.isPresent()) {
					reportId = reportService.createReportByDTO(createReportDTO, username).getId();
					createDetailReport(reportId, typeReportOptional.get());
				}
			}
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create", "exception");
		}
		return true;
	}

	private boolean createReportAndDetailReportByUsername(String username, Long typereportid) {
		try {

			Long reportId = (long) 0;
			Optional<TypeReport> typeReportOptional = typeReportService.getTypeReportById(typereportid);
			if (typeReportOptional.isPresent()) {
				reportId = reportService.createReport(username, typereportid).getId();
				createDetailReport(reportId, typeReportOptional.get());
			}
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create", "exception");
		}
		return true;
	}

	private void createDetailReport(Long reportId, TypeReport typeReport) {
		try {
			NewDetailReportDTO newDetailReportDTO = new NewDetailReportDTO();

			newDetailReportDTO.setReportId(reportId);

			List<EvaluationCriteria> evaluationCriterias = evaluationCriteriaService
					.getByTypeReportId(typeReport.getId());

			for (EvaluationCriteria evaluationCriteria : evaluationCriterias) {
				if (evaluationCriteria.getParentEvaluationCriteria() == null) {

					newDetailReportDTO.setParentId(null);

					newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());

					DetailReport detailReport = createDetailToDatabase(newDetailReportDTO);

					newDetailReportDTO.setParentId(detailReport.getId());

					createDetailTree(newDetailReportDTO, evaluationCriteria, detailReport, true);
				}
			}
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create", "exception");
		}
	}

	private DetailReport createDetailToDatabase(NewDetailReportDTO newDetailReportDTO) {
		return detailReportService.createDetailReport(newDetailReportDTO);
	}

	private void createDetailTree(NewDetailReportDTO newDetailReportDTO, EvaluationCriteria evaluationCriteria,
			DetailReport detailReport, boolean isParent) {
		try {
			if (!evaluationCriteria.getChildEvaluationCriterias().isEmpty()) {

				for (EvaluationCriteria evaluationCriteria2 : evaluationCriteria.getChildEvaluationCriterias()) {
					newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());

					if (evaluationCriteria2.getChildEvaluationCriterias().isEmpty()) {
						newDetailReportDTO.setParentId(detailReport.getId());
						newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria2.getId());
						createDetailToDatabase(newDetailReportDTO);
					} else {
						newDetailReportDTO.setParentId(detailReport.getId());
						newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria2.getId());
						createDetailTree(newDetailReportDTO, evaluationCriteria2,
								createDetailToDatabase(newDetailReportDTO), false);
					}
				}
			} /*
				 * else {
				 * 
				 * newDetailReportDTO.setParentId(detailReport.getId());
				 * 
				 * newDetailReportDTO.setEvaluationCriteriaId(evaluationCriteria.getId());
				 * 
				 * createDetailToDatabase(newDetailReportDTO); }
				 */
		} catch (Exception e) {
			throw new BadRequestAlertException("error", "Create", "exception");
		}
	}
}
