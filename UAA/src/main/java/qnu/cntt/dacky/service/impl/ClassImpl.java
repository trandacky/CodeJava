package qnu.cntt.dacky.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.ClassRepository;
import qnu.cntt.dacky.service.ClassService;


@Service
public class ClassImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private AccountRepository accountRepository;
	
//	@Autowired
//	private CourseAndDepartmentRepository andDepartmentRepository;

	@Override
	public List<ClaSs> getAllClass() {
		return classRepository.findAll();
	}

	@Override
	public ClaSs getClassById(UUID id) {
		return classRepository.findById(id).get();
	}

	@Override
	public ClaSs getClassByName(String name) {
		return classRepository.findByName(name).get();
	}

	@Override
	public String addClass(ClaSs claSs) {
		if (isClassExistByName(claSs.getName()))
			return "This class has already existed!";
		ClaSs ss= new ClaSs();
		classRepository.save(claSs);
		return "Class added successfully!";
	}

	@Override
	public ClaSs deleteClassById(UUID id) {
		Optional<ClaSs> optional=classRepository.findById(id);
		if(optional.isPresent())
		{
			if(accountRepository.findByClass1(optional.get()).isEmpty())
			{
				classRepository.deleteById(id);
				return new ClaSs();
			}
		}
		return null;
	}

	@Override
	public String deleteClassByName(String classname) {
		if (!isClassExistByName(classname))
			return "Delete false!";
		classRepository.deleteByName(classname);
		return "Deleted successfully!";

	}
	
	public boolean isClassExistById(UUID id) {
		return classRepository.findById(id).isPresent();
	}

	private boolean isClassExistByName(String classname) {
		return classRepository.findByName(classname).isPresent();
	}

	@Override
	public Page<ClaSs> getAllByIdAndPageable(Pageable paging) {
		return classRepository.findAll(paging);
	}
}
