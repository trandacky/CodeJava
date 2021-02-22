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

import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.service.AccountDetailService;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AccountDetailDTO;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
	/*
	 *  insert detail when create account, delete
	 * detail when delete account
	 */
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	/*
	 * private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections
	 * .unmodifiableList(Arrays.asList("about", "name", "phoneNumber", "account"));
	 */
	@Autowired
	private AccountDetailService accountDetailService;

	@PostMapping("/change-account-detail")
	public void changeAccountDetail(AccountDetailDTO accountDetailDTO) {
		accountDetailService.updateAccountDetail(accountDetailDTO);
		log.debug("changed account detail");
	}

	@GetMapping("/get-detail")
	public AccountDetailDTO getDetail(String username) {
		return accountDetailService.getAccountDetailByUsername(username);
	}

	/*
	 * private boolean onlyContainsAllowedProperties(Pageable pageable) { return
	 * pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(
	 * ALLOWED_ORDERED_PROPERTIES::contains); }
	 */
}
