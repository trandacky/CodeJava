package qnu.cntt.dacky.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Course;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.repository.CourseAndDepartmentRepository;
import qnu.cntt.dacky.repository.DepartmentRepository;
import qnu.cntt.dacky.service.DepartmentService;

@Service
public class DepartmentImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CourseAndDepartmentRepository courseAndDepartmentRepository;

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(UUID id) {
		Optional<Department> optional=departmentRepository.findById(id);
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}

	@Override
	public Optional<Department> getDepartmentByName(String name) {
		return departmentRepository.findByDepartmentName(name);
	}

	@Override
	public Department addDepartment(Department department) {
		department.setEnable(true);
		return departmentRepository.save(department);
	}

	@Override
	public Page<Department> getAllByIdAndPageable(Pageable paging) {
		return departmentRepository.findAll(paging);
	}

	@Override
	public Department updateEnable(UUID id, boolean enable) {
		Optional<Department> optional = departmentRepository.findById(id);
		if (optional.isPresent()) {
			Department department = optional.get();
			department.setEnable(enable);
			return departmentRepository.save(department);
		}
		return null;
	}

	@Override
	public Department updateDepartment(UUID id, String departmentName) {
		Optional<Department> optional = departmentRepository.findById(id);
		if (optional.isPresent()) {
			Department department = optional.get();
			department.setDepartmentName(departmentName);
			;
			return departmentRepository.save(department);
		}
		return null;
	}

	@Override
	public List<Department> getDepartmentEnable() {
		return departmentRepository.findByEnableTrue();
	}

	@Override
	public List<Course> getCourseByDepartment(UUID uuid) {
		Optional<Department> optional= departmentRepository.findById(uuid);
		if(optional.isPresent())
		{
			Department department= optional.get();
			List<Course> courses= new ArrayList<Course>();
			List<CourseAndDepartment> courseAndDepartments=courseAndDepartmentRepository.findByDepartment(department);
			for(CourseAndDepartment courseAndDepartment: courseAndDepartments)
			{
				courses.add(courseAndDepartment.getCourse());
			}
			return courses;
		}
		return null;
	}

}
