package qnu.cntt.dacky.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Course;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;


@Repository
public interface CourseAndDepartmentRepository extends JpaRepositoryImplementation<CourseAndDepartment, UUID> {

	Page<CourseAndDepartment> findByEnableTrue(Pageable paging);

	List<CourseAndDepartment> findByCourse(Course course);

	Page<CourseAndDepartment> findByDepartment(Department department, Pageable paging);

	Page<CourseAndDepartment> findByCourse(Course course, Pageable paging);

	List<CourseAndDepartment> findByDepartment(Department department);

}
