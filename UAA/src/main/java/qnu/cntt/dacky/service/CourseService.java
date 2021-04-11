package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import qnu.cntt.dacky.domain.Course;


public interface CourseService {
	List<Course> getAllCourse();

	Course getCourseById(long id);

	Optional<Course> getCourseByName(String name);

	String addCourse(Course course);

	String deleteCourseById(long id);

	String deleteCourseByName(String name);

	boolean isCourseExistById(long id);
}
