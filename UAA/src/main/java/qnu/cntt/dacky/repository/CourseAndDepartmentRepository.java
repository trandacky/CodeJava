package qnu.cntt.dacky.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.CourseAndDepartment;


@Repository
public interface CourseAndDepartmentRepository extends JpaRepositoryImplementation<CourseAndDepartment, UUID> {

}
