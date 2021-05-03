package qnu.cntt.dacky.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;


@Repository
public interface ClassRepository extends JpaRepository<ClaSs, UUID> {

	Optional<ClaSs> findByName(String name);

	void deleteByName(String classname);

	@Query(value="select c from ClaSs c where c.courseAndDepartment= :courseAndDepartment")
	List<ClaSs> findByCourseAndDepartment(@Param(value = "courseAndDepartment")CourseAndDepartment courseAndDepartment);

	@Query(value="select c from ClaSs c where c.courseAndDepartment IN :courseAndDepartment")
	List<ClaSs> findByCourseAndDepartmentIn(@Param(value = "courseAndDepartment")List<CourseAndDepartment> courseAndDepartments);

	@Query(value="select c from ClaSs c where c.courseAndDepartment IN :courseAndDepartment AND c.enable = TRUE")
	Page<ClaSs> findByCourseAndDepartmentIn(@Param(value = "courseAndDepartment") List<CourseAndDepartment> courseAndDepartments, Pageable paging);

	//List<ClaSs> findByIsDeletedFalse();

}
