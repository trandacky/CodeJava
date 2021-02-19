package qnu.cntt.dacky.web.rest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.PaginationUtil;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.service.AccountDetailService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountDetailDTO;
import qnu.cntt.dacky.web.rest.errors.InvalidPasswordException;
import qnu.cntt.dacky.web.rest.vm.KeyAndPasswordVM;

@RestController

@RequestMapping("/api/auth")
public class AccountDetailResource {
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections
			.unmodifiableList(Arrays.asList("about", "name", "phoneNumber","account"));

	@Autowired
	private AccountDetailService accountDetailService;
	@PostMapping("/change-account-detail")
	public void changeAccountDetail(AccountDetailDTO accountDetailDTO) {
		accountDetailService.updateAccountDetail(accountDetailDTO);
		log.debug("changed account detail");
	}
	@GetMapping("/get-all-account-detail")
	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<List<AccountDetailDTO>> getAllAccountDetails(Pageable pageable) {
		if (!onlyContainsAllowedProperties(pageable)) {
			return ResponseEntity.badRequest().build();
		}

		final Page<AccountDetailDTO> page = accountDetailService.getAllAccountDetail(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	private boolean onlyContainsAllowedProperties(Pageable pageable) {
		return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
	}
}
