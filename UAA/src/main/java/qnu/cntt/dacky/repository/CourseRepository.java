package qnu.cntt.dacky.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

	Optional<Course> findByCourse(String name);

	void deleteByCourse(String name);

}
