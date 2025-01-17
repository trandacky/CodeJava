
package qnu.cntt.dacky.web.rest;

import qnu.cntt.dacky.config.Constants;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.security.AuthoritiesConstants;

import org.springframework.data.domain.Sort;
import java.util.Collections;

import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountDTOToReturnDetailAccount;
import qnu.cntt.dacky.service.MailService;
import qnu.cntt.dacky.web.rest.errors.BadRequestAlertException;
import qnu.cntt.dacky.web.rest.errors.EmailAlreadyUsedException;
import qnu.cntt.dacky.web.rest.errors.LoginAlreadyUsedException;
import qnu.cntt.dacky.web.rest.vm.ManagedUserVM;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.logstash.logback.encoder.org.apache.commons.lang3.ObjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link Account} entity, and needs to fetch its
 * collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no View Model and DTO, a lot less code, and an outer-join which
 * would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our users' application
 * because of this use-case.</li>
 * <li>Not having an outer join causes n+1 requests to the database. This is not
 * a real issue as we have by default a second-level cache. This means on the
 * first HTTP call we do the n+1 requests, but then all authorities come from
 * the cache, so in fact it's much better than doing an outer join (which will
 * get lots of data from the database, for each HTTP call).</li>
 * <li>As this manages users, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.
 */

@RestController

@RequestMapping("/api")
public class UserResource {
	private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
			Arrays.asList("UUID", "username", "firstName", "lastName", "email", "activated", "langKey"));

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final AccountService userService;

	private final AccountRepository userRepository;

	private final MailService mailService;

	public UserResource(AccountService userService, AccountRepository userRepository, MailService mailService) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.mailService = mailService;
	}

	/**
	 * {@code POST  /users} : Creates a new user.
	 * <p>
	 * Creates a new user if the login and email are not already used, and sends an
	 * mail with an activation link. The user needs to be activated on creation.
	 *
	 * @param userDTO the user to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new user, or with status {@code 400 (Bad Request)} if the
	 *         login or email is already in use.
	 * @throws URISyntaxException       if the Location URI syntax is incorrect.
	 * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or
	 *                                  email is already in use.
	 */

	@PostMapping("/users")

	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<Account> createUser(@Valid @RequestBody ManagedUserVM userDTO) throws URISyntaxException {
		log.debug("REST request to save User : {}", userDTO);

		if (!ObjectUtils.isEmpty(userDTO.getUUID())) {
			throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
			// Lowercase the user login before comparing with database
		} else if (userRepository.findOneByUsername(userDTO.getUsername().toLowerCase()).isPresent()) {
			throw new LoginAlreadyUsedException();
		} else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyUsedException();
		} else {
			Account newUser = userService.createUser(userDTO);
			if (ObjectUtils.isEmpty(userDTO.getPassword())) {
				mailService.sendCreationEmail(newUser);
			}
			return ResponseEntity.created(new URI("/api/users/" + newUser.getUsername()))
					.headers(HeaderUtil.createAlert(applicationName,
							"A user is created with identifier " + newUser.getUsername(), newUser.getUsername()))
					.body(newUser);
		}
	}

	/**
	 * {@code PUT /users} : Updates an existing User.
	 *
	 * @param userDTO the user to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated user.
	 * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is
	 *                                   already in use.
	 * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is
	 *                                   already in use.
	 */

	@PutMapping("/users")

	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<AccountDTO> updateUser(@Valid @RequestBody AccountDTO userDTO) {
		log.debug("REST request to update User : {}", userDTO);
		Optional<Account> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());

		if (existingUser.isPresent() && (!existingUser.get().getUUID().equals(userDTO.getUUID()))) {
			throw new EmailAlreadyUsedException();
		}

		existingUser = userRepository.findOneByUsername(userDTO.getUsername().toLowerCase());

		if (existingUser.isPresent() && (!existingUser.get().getUUID().equals(userDTO.getUUID()))) {
			throw new LoginAlreadyUsedException();
		}

		Optional<AccountDTO> updatedUser = userService.updateUser(userDTO);

		return ResponseUtil.wrapOrNotFound(updatedUser, HeaderUtil.createAlert(applicationName,
				"A user is updated with identifier " + userDTO.getUsername(), userDTO.getUsername()));
	}

	/**
	 * {@code GET /users} : get all users.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         all users.
	 */

	@GetMapping("/users")
	public ResponseEntity<List<AccountDTO>> getAllUsers(Pageable pageable) {
		if (!onlyContainsAllowedProperties(pageable)) {
			return ResponseEntity.badRequest().build();
		}

		final Page<AccountDTO> page = userService.getAllManagedUsers(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	private boolean onlyContainsAllowedProperties(Pageable pageable) {
		return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
	}

	/**
	 * Gets a list of all roles.
	 * 
	 * @return a string list of all roles.
	 */

	@GetMapping("/users/authorities")

	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public List<String> getAuthorities() {
		return userService.getAuthorities();
	}

	/**
	 * {@code GET /users/:login} : get the "login" user.
	 *
	 * @param login the login of the user to find.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the "login" user, or with status {@code 404 (Not Found)}.
	 */

	@GetMapping("/users/{username:" + Constants.LOGIN_REGEX + "}")
	public ResponseEntity<AccountDTOToReturnDetailAccount> getUser(@PathVariable String username) {
		log.debug("REST request to get User : {}", username);
		return ResponseUtil.wrapOrNotFound(
				userService.getUserWithAuthoritiesByUserName(username).map(AccountDTOToReturnDetailAccount::new));
	}

	/**
	 * {@code DELETE /users/:login} : delete the "login" User.
	 *
	 * @param login the login of the user to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")

	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<Void> deleteUser(@PathVariable String login) {
		log.debug("REST request to delete User: {}", login);
		userService.deleteUser(login);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + login, login))
				.build();
	}
	@PutMapping("/admin/account")
	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<Void> updateEnable(@RequestParam("username") String username,@RequestParam("activated") boolean activated ) {
		log.debug("REST request to delete User: {}", username);
		userService.updateActivated(username,activated);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + username, username))
				.build();
	}
	@DeleteMapping("/admin/account")
	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<Void> deletedRole(@RequestParam("username") String username) {
		log.debug("REST request to delete User: {}", username);
		userService.deleteUser(username);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + username, username))
				.build();
	}
}
