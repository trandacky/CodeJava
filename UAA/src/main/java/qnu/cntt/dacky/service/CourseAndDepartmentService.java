package qnu.cntt.dacky.service;

import java.util.List;

import qnu.cntt.dacky.domain.CourseAndDepartment;


public interface CourseAndDepartmentService {

	List<CourseAndDepartment> getAllCourseAndDepartment();

	CourseAndDepartment getCourseAndDepartmentById(long id);

	String addCourseAndDepartment(CourseAndDepartment courseAndDepartment);

	String deleteCourseAndDepartment(long id);

	boolean isCourseAndDepartmentExist(long id);
}
