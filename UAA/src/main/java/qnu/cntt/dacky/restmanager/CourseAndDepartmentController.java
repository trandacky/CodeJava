package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.ClassReturnDTO;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentDTO;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentReturnDTO;

@RestController
@RequestMapping("/api/admin")
public class CourseAndDepartmentController {
	private final int sizePage = 10;
	@Autowired
	private CourseAndDepartmentService courseAndDepartmentService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/get-all-course-and-department")
	private List<CourseAndDepartment> getAllCourseAndDepartment() {
		return courseAndDepartmentService.getAllCourseAndDepartment();
	}

	@GetMapping("/get-course-and-department-by-username")
	private CourseAndDepartment getCourseAndDepartmentByUsername() {
		String username = SecurityUtils.getCurrentUserLogin().get();
		return courseAndDepartmentService.getCourseAndDepartmentByUsername(username);
	}

	@GetMapping("/get-paging-course-and-department")
	public ResponseEntity<Map<String, Object>> getPagingClass(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<CourseAndDepartment> pageable = courseAndDepartmentService.getPageable(paging);
			List<CourseAndDepartment> list = pageable.getContent();
			List<CourseAndDepartmentReturnDTO> returnDTOs = new ArrayList<>();
			for (CourseAndDepartment courseAndDepartment : list) {
				returnDTOs.add(new CourseAndDepartmentReturnDTO(courseAndDepartment));
			}
			Map<String, Object> response = new HashMap<>();
			response.put("courseAndDepartment", returnDTOs);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-paging-course-and-departmen-enable")
	public ResponseEntity<Map<String, Object>> getPagingCourseAndDepartmentEnable(
			@RequestParam(defaultValue = "0") int page) {
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

	@GetMapping("/get-class-by-cad-id")
	private ResponseEntity<Map<String, Object>> getClassByCADId(@RequestParam("uuid") UUID uuid) {
		try {
			List<ClaSs> sses = courseAndDepartmentService.getClassByCADId(uuid);
			List<ClassReturnDTO> classReturnDTOs= new ArrayList<>();
			ClassReturnDTO classReturnDTO;
			for(ClaSs ss:sses)
			{
				classReturnDTO= new ClassReturnDTO(ss);
				classReturnDTO.setCount(accountService.getCount(ss));
				classReturnDTOs.add(classReturnDTO);
			} 
			classReturnDTOs.sort(Comparator.comparing(ClassReturnDTO::getCreatedDate));
			Map<String, Object> response = new HashMap<>();
			response.put("classList", classReturnDTOs); 
			response.put("courseAndDepartment",
					new CourseAndDepartmentReturnDTO(courseAndDepartmentService.getCourseAndDepartmentById(uuid)));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/create-course-and-department")
	private CourseAndDepartment addCourseAndDepartment(@RequestParam UUID courseId,@RequestParam UUID departmentId ) {
		CourseAndDepartmentDTO dto= new CourseAndDepartmentDTO();
		dto.setCourseId(courseId);
		dto.setDepartmentId(departmentId);
		return courseAndDepartmentService.addCourseAndDepartment(dto);
	}

	@PutMapping("/update-course-and-department-enable")
	private CourseAndDepartment updateEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable) {
		return courseAndDepartmentService.updateEnable(uuid, enable);
	}

	@DeleteMapping("/delete-course-and-department/{uuid}")
	private CourseAndDepartment deletedById(@PathVariable("uuid") UUID uuid) {
		return courseAndDepartmentService.deletedById(uuid);
	}
}
