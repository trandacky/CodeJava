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
import qnu.cntt.dacky.repository.CourseAndDepartmentRepository;
import qnu.cntt.dacky.repository.CourseRepository;
import qnu.cntt.dacky.service.CourseService;

@Service
public class CourseImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseAndDepartmentRepository courseAndDepartmentRepository;

	@Override
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(UUID id) {
		Optional<Course> optional=courseRepository.findById(id);
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}

	@Override
	public Optional<Course> getCourseByName(String name) {
		return courseRepository.findByCourse(name);
	}

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}


	@Override
	public boolean isCourseExistById(UUID id) {
		return courseRepository.findById(id).isPresent();
	}

	@Override
	public Page<Course> getPageable(Pageable paging) {
		
		return courseRepository.findAll(paging);
	}

	@Override
	public List<Course> getCourseEnable() {
		return courseRepository.findByEnableTrue();
	}

	@Override
	public Course updateEnable(UUID uuid, boolean enable) {
		Optional<Course> optional=courseRepository.findById(uuid);
		if(optional.isPresent()) 
		{
			Course course=optional.get();
			course.setEnable(enable);
			return courseRepository.save(course);
		}
		return null;
	}

	@Override
	public Course updateCourse(UUID uuid, String string) {
		Optional<Course> optional=courseRepository.findById(uuid);
		if(optional.isPresent()) 
		{
			Course course=optional.get();
			course.setCourse(string);
			return courseRepository.save(course);
		}
		return null;
	}

	@Override
	public List<CourseAndDepartment> getDepartmentByCourse(UUID uuid) {
		Optional<Course> optional=courseRepository.findById(uuid);
		if(optional.isPresent())
		{
			Course course = optional.get();
			return courseAndDepartmentRepository.findByCourse(course);
		}
		return null;
	}
}
