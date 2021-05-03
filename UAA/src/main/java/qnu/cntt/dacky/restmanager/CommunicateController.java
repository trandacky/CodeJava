package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountDepartmentService;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.CommunicateAccountClassDTO;

@RestController
//@FeignClient(name = "uaa")
@RequestMapping("/api/khoa")

public class CommunicateController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ClassService classService;

	@Autowired
	private CourseAndDepartmentService courseAndDepartmentService;
	@Autowired
	private AccountDepartmentService accountDepartmentService;

	@GetMapping("/get-list-account/{uuid}")
	public List<CommunicateAccountClassDTO> getAccountByClassId(@PathVariable("uuid") UUID uuid) {
		List<Account> accounts = classService.getAllAccountByClass(uuid);
		List<CommunicateAccountClassDTO> list = new ArrayList<>();
		List<AccountAuthority> authorities = new ArrayList<>();
		for (Account account : accounts) {
			authorities.clear();
			authorities.addAll(account.getAccountAuthoritys());
			for (AccountAuthority accountAuthority : authorities) {
				if (accountAuthority.getAuthority().getAuthorities().equals(AuthoritiesConstants.SV)
						&& account.getActivated()) {
					list.add(new CommunicateAccountClassDTO(account));
				}
			}
		}
		return list;
	}

	@GetMapping("/get-list-account-by-khoa")
	public List<CommunicateAccountClassDTO> getAccountByLogin() {
		String username = SecurityUtils.getCurrentUserLogin().get();
		List<ClaSs> claSs=accountDepartmentService.getCADByUsernameKhoa(username);
		List<Account> accounts = new ArrayList<Account>();
		for(ClaSs ss:claSs)
		{
			accounts.addAll(classService.getAllAccountByClass(ss.getUUID()));
		}
		List<CommunicateAccountClassDTO> list = new ArrayList<>();
		List<AccountAuthority> authorities = new ArrayList<>();
		for (Account account : accounts) {
			authorities.clear();
			authorities.addAll(account.getAccountAuthoritys());
			for (AccountAuthority accountAuthority : authorities) {
				if (accountAuthority.getAuthority().getAuthorities().equals(AuthoritiesConstants.SV)
						&& account.getActivated()) {
					list.add(new CommunicateAccountClassDTO(account));
				}
			}
		}
		return list;
	}

}
