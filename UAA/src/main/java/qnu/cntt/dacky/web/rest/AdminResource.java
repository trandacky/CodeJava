package qnu.cntt.dacky.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.AuthorityMenuAdmintration;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.service.AccountAuthorityService;
import qnu.cntt.dacky.service.AccountDetailService;
import qnu.cntt.dacky.service.AuthorityMenuAdminService;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AccountAuthorityDTO;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AuthorityMenuAdminService authorityMenuAdminService;

	@Autowired
	private AccountDetailService accountDetailService;

	@GetMapping("/get-all-authority")
	public List<Authority> getAllAuthority() {
		return authorityService.getAllAuthority();
	}

	@Autowired
	private AccountAuthorityService accountAuthorityService;

	@PostMapping("/create-account-authority")
	public void createAccountAuthority(AccountAuthorityDTO accountAuthorityDTO) {
		accountAuthorityService.createAccountAuthority(accountAuthorityDTO);
	}

	@DeleteMapping("/delete-account-authority")
	public void deleteAccountAuthority(String username, String role) {
		accountAuthorityService.deleteAccountAuthority(username, role);
	}

	@GetMapping("/get-authority")
	public Authority getAuthority(String authorities) {
		return authorityService.getAuthority(authorities).get();
	}

	@PostMapping("/create-authority")
	public void createAuthorites(AuthorityDTO authorityDTO) {
		authorityService.createAuthority(authorityDTO);
	}

	@DeleteMapping("/delete-authority")
	public void deleteAuthority(String authorities) {
		authorityService.deleteAuthority(authorities);
	}

	@GetMapping("/get-all-account-detail")
	public List<AccountDetails> getAllAccountDetails() {
		return accountDetailService.getAllAccountDetail();
	}

	@GetMapping("/get-all-authority-menu-admin")
	public List<AuthorityMenuAdmintration> getAllauthorityAdminMenu() {
		return authorityMenuAdminService.getAllAuthorityMenuAdmin();
	}
	
	// delete authority admin menu do sometime

	@GetMapping("/get-authority-menu-admin")
	public List<AuthorityMenuAdmintration> getauthorityMenuAdmin() {
		return authorityMenuAdminService.getAuthorityMenuAdmin("admin");
		// change it
	}
	@PostMapping("/create-authority-menu-admin")
	public void createAuthorityMenuAdmin()
	{
		
	}
	// create do it with DTO
	
}
