package qnu.cntt.dacky.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountDepartment;
import qnu.cntt.dacky.domain.Department;

@Repository
public interface AccountDepartmentRepository extends JpaRepository<AccountDepartment, UUID>{

	List<AccountDepartment> findByAccount(Account account);

	List<AccountDepartment> findByDepartment(Department department);

	List<AccountDepartment> findByAccountAndDepartment(Account account, Department department);

}
