package qnu.cntt.dacky.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.ClassRepository;
import qnu.cntt.dacky.repository.CourseAndDepartmentRepository;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.dto.ClassDTO;
import qnu.cntt.dacky.service.dto.ClassDTOUpdate;
import qnu.cntt.dacky.web.rest.errors.BadRequestAlertException;

@Service
public class ClassImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CourseAndDepartmentRepository courseAndDepartmentRepository;

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
	public ClaSs deleteClassById(UUID id) {
		Optional<ClaSs> optional = classRepository.findById(id);
		if (optional.isPresent()) {
			if (accountRepository.findCountByClass1(optional.get()) == 0) {
				classRepository.deleteById(id);
				return new ClaSs();
			}
		}
		throw new BadRequestAlertException("class have account", "classManagement", "idexists");
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

	@Override
	public ClaSs addClass(ClassDTO classDTO) {
		Optional<CourseAndDepartment> optional = courseAndDepartmentRepository
				.findById(classDTO.getCourseAndDepartmentId());
		if (optional.isPresent()) {
			ClaSs ss = new ClaSs();
			ss.setCourseAndDepartment(optional.get());
			ss.setName(classDTO.getClassName());
			return classRepository.save(ss);
		}
		return null;
	}

	@Override
	public ClaSs updateEnable(UUID uuid, boolean enable) {
		Optional<ClaSs> optional = classRepository.findById(uuid);
		if (optional.isPresent()) {
			ClaSs ss = optional.get();
			ss.setEnable(enable);
			return classRepository.save(ss);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Account> getAccountByClass(Pageable paging, UUID uuid) {
		Optional<ClaSs> ss = classRepository.findById(uuid);
		if (ss.isPresent()) {
			return accountRepository.findAllByClass1(ss.get(), paging);
		}
		return null;
	}

	@Override
	public ClaSs updateName(ClassDTOUpdate dtoUpdate) {
		Optional<ClaSs> optional=classRepository.findById(dtoUpdate.getUuid());
		if(optional.isPresent())
		{
			ClaSs ss= optional.get();
			ss.setName(dtoUpdate.getClassName());
			return classRepository.save(ss);
		}
		throw new BadRequestAlertException("uuid does not exist", "classManagement", "idexists");
	}
}
