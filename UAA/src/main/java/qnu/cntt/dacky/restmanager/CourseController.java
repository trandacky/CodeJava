package qnu.cntt.dacky.restmanager;

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
import qnu.cntt.dacky.service.CourseService;
import qnu.cntt.dacky.service.dto.CourseDTO;

@RestController
@RequestMapping("/api/admin")
public class CourseController {
	private final int sizePage = 10;
	@Autowired
	private CourseService courseService;

	@GetMapping("/get-all-course")
	private List<Course> getAllCourse() {
		return courseService.getAllCourse();
	}

	@GetMapping("/get-course-enable")
	private List<Course> getCourseEnable() {
		return courseService.getCourseEnable();
	}

	@GetMapping("/get-department-by-course")
	private ResponseEntity<Map<String, Object>> getDepartmentByCourse(@RequestParam("uuid") UUID uuid) {
		try {
			Map<String, Object> response = new HashMap<>();
			response.put("course", courseService.getCourseById(uuid));
			response.put("departments", courseService.getDepartmentByCourse(uuid));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-paging-course")
	private ResponseEntity<Map<String, Object>> getPagingDepartment(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<Course> pageable = courseService.getPageable(paging);
			List<Course> departments = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("courses", departments);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/get-course-by-id")
	private Course getCourseById(@RequestParam("uuid") UUID uuid) {
		return courseService.getCourseById(uuid);
	}

	@PostMapping("/get-course-by-name")
	private Optional<Course> getCourseByName(@RequestParam("course-name") String name) {
		return courseService.getCourseByName(name);
	}

	@PostMapping("/create-course")
	private Course addCourseByName(@RequestBody CourseDTO courseDTO) {
		Course course = new Course();
		course.setCourse(courseDTO.getCourse());
		course.setEnable(true);
		return courseService.addCourse(course);
	}

	@PutMapping("/update-enable-course")
	private Course updateEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable) {
		return courseService.updateEnable(uuid, enable);
	}

	@PutMapping("/update-course")
	private Course updateCourse(@RequestParam("uuid") UUID uuid, @RequestParam("course") String course) {
		return courseService.updateCourse(uuid, course);
	}
}
