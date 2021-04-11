package qnu.cntt.dacky.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

	Optional<Department> findByDepartmentName(String name);

	void deleteByDepartmentName(String name);

}
