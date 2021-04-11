package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import qnu.cntt.dacky.domain.Department;


public interface DepartmentService {

	List<Department> getAllDepartment();

	Department getDepartmentById(long id);

	Optional<Department> getDepartmentByName(String name);

	String addDepartment(Department department);

	String deleteDepartmentById(long id);

	String deleteDepartmentByName(String name);

	boolean isDepartmentExistedById(long id);
}
