package qnu.cntt.dacky.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.Course;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.ClassRepository;
import qnu.cntt.dacky.repository.CourseAndDepartmentRepository;
import qnu.cntt.dacky.repository.CourseRepository;
import qnu.cntt.dacky.repository.DepartmentRepository;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentDTO;
import qnu.cntt.dacky.web.rest.AccountResource;

@Service
public class CourseAndDepartmentImpl implements CourseAndDepartmentService {
	private final Logger log = LoggerFactory.getLogger(CourseAndDepartmentImpl.class);
	@Autowired
	private CourseAndDepartmentRepository courseAndDepartmentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ClassRepository classRepository;

	@Override
	public List<CourseAndDepartment> getAllCourseAndDepartment() {
		return courseAndDepartmentRepository.findAll();
	}

	@Override
	public CourseAndDepartment getCourseAndDepartmentById(UUID uuid) {
		return courseAndDepartmentRepository.findById(uuid).get();
	}

	@Override
	public CourseAndDepartment addCourseAndDepartment(CourseAndDepartmentDTO courseAndDepartmentDTO) {
		CourseAndDepartment courseAndDepartment = new CourseAndDepartment();
		Optional<Department> department = departmentRepository.findById(courseAndDepartmentDTO.getDepartmentId());
		Optional<Course> course = courseRepository.findById(courseAndDepartmentDTO.getCourseId());
		if (department.isPresent() && course.isPresent()) {
			courseAndDepartment.setCourse(course.get());
			courseAndDepartment.setDepartment(department.get());
			return courseAndDepartmentRepository.save(courseAndDepartment);
		}
		return null;

	}

	@Override
	public CourseAndDepartment updateEnable(UUID uuid, boolean enable) {
		Optional<CourseAndDepartment> optional = courseAndDepartmentRepository.findById(uuid);
		if (optional.isPresent()) {
			CourseAndDepartment courseAndDepartment = optional.get();
			courseAndDepartment.setEnable(enable);
			return courseAndDepartmentRepository.save(courseAndDepartment);
		}
		return null;
	}

	@Override
	public Page<CourseAndDepartment> getPageable(Pageable paging) {
		return courseAndDepartmentRepository.findAll(paging);
	}

	@Override
	public CourseAndDepartment getCourseAndDepartmentByUsername(String username) {
		Optional<Account> optional = accountRepository.findByUsername(username);
		if (optional.isPresent()) {
			Account account = optional.get();
			Optional<ClaSs> claSs = classRepository.findById(account.getClass1().getUUID());
			if (claSs.isPresent()) {
				return claSs.get().getCourseAndDepartment();
			}
		}
		return null;
	}

	@Override
	public Page<CourseAndDepartment> getPageableEnable(Pageable paging) {
		return courseAndDepartmentRepository.findByEnableTrue(paging);
	}

	@Override
	public CourseAndDepartment deletedById(UUID uuid) {
		Optional<CourseAndDepartment> optional = courseAndDepartmentRepository.findById(uuid);
		if (optional.isPresent()) {
			CourseAndDepartment courseAndDepartment=optional.get();
			List<ClaSs> classes=classRepository.findByCourseAndDepartment(courseAndDepartment);
			if(classes.isEmpty())
			{
				courseAndDepartmentRepository.delete(courseAndDepartment);
				return null;
			}
		}
		return null;
	}

	@Override
	public List<ClaSs> getClassByCADId(UUID uuid) {
		Optional<CourseAndDepartment> optional=courseAndDepartmentRepository.findById(uuid);
		if(optional.isPresent())
		{
			return classRepository.findByCourseAndDepartment(optional.get());
		}
		return null;
	}
}
