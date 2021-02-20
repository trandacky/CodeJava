package qnu.cntt.dacky.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.service.AccountAuthorityService;
import qnu.cntt.dacky.service.dto.AccountAuthorityDTO;

@RestController

@RequestMapping("/api/auth")
public class AccountAuthorityResource {
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	/*
	 * private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections
	 * .unmodifiableList(Arrays.asList("about", "name", "phoneNumber", "account"));
	 */
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
}
