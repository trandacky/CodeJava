package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.service.AccountDepartmentService;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.dto.AccountDTO;

@RestController
@RequestMapping("/api/admin")
public class AccountDepartmentController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountDepartmentService accountDepartmentService;
	
	@GetMapping("/get-all-account-khoa")
	public List<AccountDTO> getAllAccountKHOA(@RequestParam("uuid") UUID uuid) {
		List<Account> accounts= accountService.getAllAccountKHOA(uuid);
		List<AccountDTO> accountDTOs = new ArrayList<>();
		AccountDTO accountDTO;
		for (Account account : accounts) {
			accountDTO = new AccountDTO(account);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}
	@GetMapping("/account-khoa-not")
	public List<AccountDTO> accountRoleKhoaNot() {
		List<Account> accounts= accountService.accountRoleKhoaNot();
		List<AccountDTO> accountDTOs = new ArrayList<>();
		AccountDTO accountDTO;
		for (Account account : accounts) {
			accountDTO = new AccountDTO(account);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}
	@GetMapping("/account-sinhvien-not")
	public List<AccountDTO> accountRoleSVNot() {
		List<Account> accounts= accountService.accountRoleSVNot();
		List<AccountDTO> accountDTOs = new ArrayList<>();
		AccountDTO accountDTO;
		for (Account account : accounts) {
			accountDTO = new AccountDTO(account);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}
	@PostMapping("/create-account-khoa")
	private void createAccountKhoa(@RequestParam("username") String username, @RequestParam("departmentuuid") UUID departmentuuid)
	{
		accountDepartmentService.createAccountDepartment(username, departmentuuid);
	}
	@PostMapping("/create-account-sinhvien-khoa")
	public void createAccountSinhVienKhoa(@RequestParam("username") String username, @RequestParam("classUUID") UUID classUUID)
	{
		accountDepartmentService.createAccountClass(username, classUUID);
	}
	@DeleteMapping("/delete-account-khoa")
	private void deleteAccountKhoa(@RequestParam("username") String username, @RequestParam("departmentuuid") UUID departmentuuid)
	{
		accountDepartmentService.delete(username, departmentuuid);
	}
	@DeleteMapping("/delete-account-class")
	private void deleteAccountClass(@RequestParam("username") String username, @RequestParam("classUUID") UUID classUUID)
	{
		accountDepartmentService.deleteAccountClass(username, classUUID);
	}
}
