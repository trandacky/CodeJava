package qnu.cntt.dacky.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountDepartment;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.repository.AccountDepartmentRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.ClassRepository;
import qnu.cntt.dacky.repository.CourseAndDepartmentRepository;
import qnu.cntt.dacky.repository.DepartmentRepository;
import qnu.cntt.dacky.service.AccountDepartmentService;
import qnu.cntt.dacky.service.exception.InvalidPasswordException;

@Service
public class AccountDepartmentImpl implements AccountDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AccountDepartmentRepository accountDepartmentRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private CourseAndDepartmentRepository courseAndDepartmentRepository;

	@Override
	public AccountDepartment createAccountDepartment(String username, UUID departmentuuid) {
		Optional<Account> account = accountRepository.findByUsername(username);
		Optional<Department> department = departmentRepository.findById(departmentuuid);
		if (account.isPresent() && department.isPresent()) {
			AccountDepartment accountDepartment = new AccountDepartment();
			accountDepartment.setAccount(account.get());
			accountDepartment.setDepartment(department.get());
			return accountDepartmentRepository.save(accountDepartment);
		}
		return null;
	}

	@Override
	public void delete(String username, UUID uuid) {
		Optional<Account> account = accountRepository.findByUsername(username);
		Optional<Department> department = departmentRepository.findById(uuid);
		if (account.isPresent() && department.isPresent()) {
			List<AccountDepartment> accountDepartment = accountDepartmentRepository
					.findByAccountAndDepartment(account.get(), department.get());
			accountDepartmentRepository.deleteAll(accountDepartment);
		} else
			throw new InvalidPasswordException();
	}

	@Override
	public Page<CourseAndDepartment> getByUsername(String username, Pageable paging) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent()) {
			List<AccountDepartment> accountDepartments = accountDepartmentRepository.findByAccount(account.get());

			List<Department> departments = new ArrayList<>();

			for (AccountDepartment accountDepartment : accountDepartments) {
				departments.add(accountDepartment.getDepartment());
			}

			return courseAndDepartmentRepository.findByDepartmentIn(departments, paging);

		}
		return null;
	}

	@Override
	public List<ClaSs> getCADByUsernameKhoa(String username) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent()) {
			List<AccountDepartment> accountDepartments = accountDepartmentRepository.findByAccount(account.get());

			List<Department> departments = new ArrayList<>();

			for (AccountDepartment accountDepartment : accountDepartments) {
				departments.add(accountDepartment.getDepartment());
			}

			List<CourseAndDepartment> courseAndDepartments = courseAndDepartmentRepository
					.findByDepartmentIn(departments);
			List<ClaSs> claSs=classRepository.findByCourseAndDepartmentIn(courseAndDepartments);
			return claSs;
		}
		return null;
	}

	@Override
	public Page<ClaSs> getCADByUsernameKhoaAndPaging(String username, Pageable paging) {
		
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent()) {
			List<AccountDepartment> accountDepartments = accountDepartmentRepository.findByAccount(account.get());

			List<Department> departments = new ArrayList<>();

			for (AccountDepartment accountDepartment : accountDepartments) {
				departments.add(accountDepartment.getDepartment());
			}

			List<CourseAndDepartment> courseAndDepartments = courseAndDepartmentRepository
					.findByDepartmentIn(departments);
			Page<ClaSs> claSs=classRepository.findByCourseAndDepartmentIn(courseAndDepartments,paging);
			return claSs;
		}
		return null;
	}

	@Override
	public void createAccountClass(String username, UUID classUUID) {
		
		Optional<Account> account= accountRepository.findByUsername(username);
		Optional<ClaSs> class1= classRepository.findById(classUUID);
		if(account.isPresent()&&class1.isPresent())
		{
			Account account2= account.get();
			account2.setClass1(class1.get());
			accountRepository.save(account2);
		}
	}

	@Override
	public void deleteAccountClass(String username, UUID classUUID) {
		Optional<Account> account = accountRepository.findByUsername(username);
		Optional<ClaSs> class1 = classRepository.findById(classUUID);
		if (account.isPresent() && class1.isPresent()) {
			Account account2= account.get();
			account2.setClass1(null);
			accountRepository.save(account2);
		} else
			throw new InvalidPasswordException();
	}

}
