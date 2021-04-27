package qnu.cntt.dacky.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentDTO;


public interface CourseAndDepartmentService {

	List<CourseAndDepartment> getAllCourseAndDepartment();

	CourseAndDepartment getCourseAndDepartmentById(UUID uuid);

	CourseAndDepartment addCourseAndDepartment(CourseAndDepartmentDTO courseAndDepartmentDTO);

	CourseAndDepartment updateEnable(UUID uuid, boolean enable);

	Page<CourseAndDepartment> getPageable(Pageable paging);

	CourseAndDepartment getCourseAndDepartmentByUsername(String username);

	Page<CourseAndDepartment> getPageableEnable(Pageable paging);
}
