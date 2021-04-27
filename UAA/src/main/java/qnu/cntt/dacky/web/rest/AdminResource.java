package qnu.cntt.dacky.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.service.AccountAuthorityService;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AccountAuthorityDTO;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {
	@Autowired
	private AuthorityService authorityService;


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

	@DeleteMapping("/delete-authority/{authorities}")
	public void deleteAuthority(@PathVariable String authorities) {
		authorityService.deleteAuthority(authorities);
	}

}
