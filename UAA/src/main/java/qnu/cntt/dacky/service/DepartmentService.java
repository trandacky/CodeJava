package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.Department;


public interface DepartmentService {

	List<Department> getAllDepartment();

	Department getDepartmentById(UUID id);

	Optional<Department> getDepartmentByName(String name);

	Department addDepartment(Department department);
	
	Page<Department> getAllByIdAndPageable(Pageable paging);

	Department updateEnable(UUID id, boolean enable);

	Department updateDepartment(UUID id, String departmentName);

	List<Department> getDepartmentEnable();
}
