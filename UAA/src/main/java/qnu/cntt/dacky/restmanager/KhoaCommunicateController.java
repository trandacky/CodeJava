package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import qnu.cntt.dacky.service.dto.ClassAndCountCommunicateDTO;
import qnu.cntt.dacky.service.dto.ClassReturnDTO;
import qnu.cntt.dacky.service.dto.CommunicateAccountClassDTO;

@RestController
//@FeignClient(name = "uaa")
@RequestMapping("/api/khoa")

public class KhoaCommunicateController {
	private final int sizePage = 10;
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
		List<ClaSs> claSs = accountDepartmentService.getCADByUsernameKhoa(username);
		List<Account> accounts = new ArrayList<Account>();
		for (ClaSs ss : claSs) {
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

	@GetMapping("/get-all-class-khoa")
	public ClassAndCountCommunicateDTO getClassPagingByUserName(@RequestParam(defaultValue = "0") int page) {
		Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
		Optional<String> username = SecurityUtils.getCurrentUserLogin();
		Page<ClaSs> pageable=accountDepartmentService.getCADByUsernameKhoaAndPaging(username.get(),paging);
		List<ClassReturnDTO> classReturnDTOs = new ArrayList<>();
		ClassReturnDTO classReturnDTO;
		for (ClaSs ss : pageable.getContent()) {
			classReturnDTO = new ClassReturnDTO(ss);
			classReturnDTO.setCount(accountService.getCount(ss));
			classReturnDTOs.add(classReturnDTO);
		}
		ClassAndCountCommunicateDTO countCommunicateDTO= new ClassAndCountCommunicateDTO();
		countCommunicateDTO.setClassKhoaDTOs(classReturnDTOs);
		countCommunicateDTO.setTotalItems(pageable.getTotalElements());
		return countCommunicateDTO;
	}

}
