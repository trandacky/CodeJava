package qnu.cntt.dacky.restmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.ClassDTO;

@RestController
@RequestMapping("/api/admin")
public class ClassController {
	private final int sizePage = 10;
	@Autowired
	private ClassService classService;

//	@Autowired
//	private CourseAndDepartmentService courseAndDepartmentService;

	@GetMapping("/get-all-class")
	private List<ClaSs> getAllClass() {
		return classService.getAllClass();
	}
	
	@GetMapping("/get-paging-class")
	public ResponseEntity<Map<String, Object>> getPagingClass(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<ClaSs> pageable = classService.getAllByIdAndPageable(paging);
			List<ClaSs> clasS = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("classList", clasS);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/get-class-by-id")
	private ClaSs getClassById(@RequestParam("id") UUID id) {
		return classService.getClassById(id);
	}

	@PostMapping("/get-class-by-name")
	private ClaSs getClassByName(@RequestParam("classname") String name) {
		return classService.getClassByName(name);
	}

//	@PostMapping("/add-class")
//	private String addClass(@RequestBody ClassDTO classDTO) {
//		ClaSs claSs = new ClaSs();
//		claSs.setName(classDTO.getClassName());
//		claSs.setCourseAndDepartment(
//				courseAndDepartmentService.getCourseAndDepartmentById(classDTO.getCourseAndDepartmentId()));
//		return classService.addClass(claSs);
//	}

	@DeleteMapping("/delete-class-by-id/{id}")
	private ClaSs deleteClassById(@PathVariable UUID id) {
		return classService.deleteClassById(id);
	}

	@DeleteMapping("/delete-class-by-name/{classname}")
	private String deleteClassByName(@PathVariable String className) {
		return classService.deleteClassByName(className);
	}
}