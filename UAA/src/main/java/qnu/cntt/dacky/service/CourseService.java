package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.Course;


public interface CourseService {
	List<Course> getAllCourse();

	Course getCourseById(UUID id);

	Optional<Course> getCourseByName(String name);

	Course addCourse(Course course);

	boolean isCourseExistById(UUID id);

	Page<Course> getPageable(Pageable paging);

	List<Course> getCourseEnable();

	Course updateEnable(UUID uuid, boolean enable);

	Course updateCourse(UUID uuid, String course);
}
