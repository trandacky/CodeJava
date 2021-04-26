package qnu.cntt.dacky.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.repository.DepartmentRepository;
import qnu.cntt.dacky.service.DepartmentService;

@Service
public class DepartmentImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(UUID id) {
		return departmentRepository.findById(id).get();
	}

	@Override
	public Optional<Department> getDepartmentByName(String name) {
		return departmentRepository.findByDepartmentName(name);
	}

	@Override
	public Department addDepartment(Department department) {
		department.setCreatedDate(Instant.now());
		department.setEnable(true);
		return departmentRepository.save(department);
	}

	@Override
	public Page<Department> getAllByIdAndPageable(Pageable paging) {
		return departmentRepository.findAll(paging);
	}

	@Override
	public Department updateEnable(UUID id, boolean enable) {
		Optional<Department> optional=departmentRepository.findById(id);
		if(optional.isPresent())
		{
			Department department=optional.get();
			department.setEnable(enable);
			department.setUpdateDate(Instant.now());
			return departmentRepository.save(department);
		}
		return null;
	}

	@Override
	public Department updateDepartment(UUID id, String departmentName) {
		Optional<Department> optional=departmentRepository.findById(id);
		if(optional.isPresent())
		{
			Department department=optional.get();
			department.setDepartmentName(departmentName);;
			department.setUpdateDate(Instant.now());
			return departmentRepository.save(department);
		}
		return null;
	}

	@Override
	public List<Department> getDepartmentEnable() {
		return departmentRepository.findByEnableTrue();
	}

}
