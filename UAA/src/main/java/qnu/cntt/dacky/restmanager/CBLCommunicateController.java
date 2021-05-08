package qnu.cntt.dacky.restmanager;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountDepartmentService;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.AccountInfoDTO;

@RestController
@RequestMapping("/api/canbolop")
public class CBLCommunicateController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ClassService classService;

	@Autowired
	private CourseAndDepartmentService courseAndDepartmentService;
	@Autowired
	private AccountDepartmentService accountDepartmentService;
	
	
	@GetMapping("/get-account-info-class")
	private AccountInfoDTO getInfoAccount(@RequestParam String username) {
		String canbo = SecurityUtils.getCurrentUserLogin().get();
		Account account = accountService.getAccountByUsername(canbo);
		Account accountInfo = accountService.getAccountByUsername(username);
		if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CBL)
				&& account.getClass1().getUUID().equals(accountInfo.getClass1().getUUID())) {
			return new AccountInfoDTO(accountInfo);
		}
		return null;
	}
}
