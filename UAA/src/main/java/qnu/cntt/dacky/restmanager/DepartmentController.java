package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Course;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.service.DepartmentService;
import qnu.cntt.dacky.service.dto.DepartmentDTO;

@RestController
@RequestMapping("/api/admin")
public class DepartmentController {
	private final int sizePage = 10;
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/get-all-department")
	private List<Department> getAllDepartment() {
		return departmentService.getAllDepartment();
	}

	@GetMapping("/get-course-by-department")
	private ResponseEntity<Map<String, Object>> getCourseByDepartment(@RequestParam(defaultValue = "0") int page,
			@RequestParam("uuid") UUID uuid) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<CourseAndDepartment> pageable = departmentService.getCourseByDepartment(uuid, paging);
			List<CourseAndDepartment> courseAndDepartments = pageable.getContent();
			List<Course> courses = new ArrayList<Course>();
			for (CourseAndDepartment courseAndDepartment : courseAndDepartments) {
				courses.add(courseAndDepartment.getCourse());
			}
			Map<String, Object> response = new HashMap<>();
			response.put("courses", courses);
			response.put("department", departmentService.getDepartmentById(uuid));
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-department-enable")
	private List<Department> getDepartmentEnable() {
		return departmentService.getDepartmentEnable();
	}

	@GetMapping("/get-paging-department")
	private ResponseEntity<Map<String, Object>> getPagingDepartment(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<Department> pageable = departmentService.getAllByIdAndPageable(paging);
			List<Department> departments = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("departments", departments);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/get-department-by-id")
	private Department getDepartmentById(@RequestParam("id") UUID id) {
		return departmentService.getDepartmentById(id);
	}

	@PutMapping("/update-enable-department")
	private Department updateEnable(@RequestParam("uuid") UUID id, @RequestParam("enable") boolean enable) {
		return departmentService.updateEnable(id, enable);
	}

	@PutMapping("/update-department")
	private Department updateDepartment(@RequestParam("uuid") UUID id,
			@RequestParam("departmentName") String departmentName) {
		return departmentService.updateDepartment(id, departmentName);
	}

	@PostMapping("/get-department-by-name")
	private Optional<Department> getDepartmentByName(@RequestParam("department-name") String name) {
		return departmentService.getDepartmentByName(name);
	}

	@PostMapping("/add-department-by-name")
	private Department addDepartmentByName(@RequestBody DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setDepartmentName(departmentDTO.getDepartmentName());
		return departmentService.addDepartment(department);
	}
}
