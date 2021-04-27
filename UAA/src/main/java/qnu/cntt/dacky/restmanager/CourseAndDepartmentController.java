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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentDTO;

@RestController
@RequestMapping("/api/admin")
public class CourseAndDepartmentController {
	private final int sizePage = 10;
	@Autowired
	private CourseAndDepartmentService courseAndDepartmentService;

	@GetMapping("/get-all-course-and-department")
	private List<CourseAndDepartment> getAllCourseAndDepartment() {
		return courseAndDepartmentService.getAllCourseAndDepartment();
	}
	@GetMapping("/get-course-and-department-by-username")
	private CourseAndDepartment getCourseAndDepartmentByUsername() {
		String username=SecurityUtils.getCurrentUserLogin().get();
		return courseAndDepartmentService.getCourseAndDepartmentByUsername(username);
	}
	@GetMapping("/get-paging-course-and-department")
	public ResponseEntity<Map<String, Object>> getPagingClass(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<CourseAndDepartment> pageable = courseAndDepartmentService.getPageable(paging);
			List<CourseAndDepartment> clasS = pageable.getContent();
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
	@GetMapping("/get-paging-course-and-departmen-enable")
	public ResponseEntity<Map<String, Object>> getPagingCourseAndDepartmentEnable(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<CourseAndDepartment> pageable = courseAndDepartmentService.getPageableEnable(paging);
			List<CourseAndDepartment> clasS = pageable.getContent();
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

	@PostMapping("/get-course-and-department-by-id")
	private CourseAndDepartment getCourseAndDepartmentById(@RequestParam("uuid") UUID uuid) {
		return courseAndDepartmentService.getCourseAndDepartmentById(uuid);
	}

	@PostMapping("/create-course-and-department")
	private CourseAndDepartment addCourseAndDepartment(@RequestBody CourseAndDepartmentDTO courseAndDepartmentDTO) {
		return courseAndDepartmentService.addCourseAndDepartment(courseAndDepartmentDTO);
	}
	@PutMapping("/update-course-and-department-enable")
	private CourseAndDepartment updateEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable)
	{
		return courseAndDepartmentService.updateEnable(uuid,enable);
	}

}
