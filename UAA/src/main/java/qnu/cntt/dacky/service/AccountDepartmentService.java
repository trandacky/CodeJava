package qnu.cntt.dacky.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.AccountDepartment;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;

public interface AccountDepartmentService {
	AccountDepartment createAccountDepartment(String username, UUID departmentuuid);
	void delete(String username, UUID departmentuuid);
	Page<CourseAndDepartment> getByUsername(String username, Pageable paging);
	List<ClaSs> getCADByUsernameKhoa(String username);
	Page<ClaSs> getCADByUsernameKhoaAndPaging(String string, Pageable paging);
}
