
package qnu.cntt.dacky.service;

import qnu.cntt.dacky.config.Constants;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.repository.AccountAuthorityRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.dto.AccountDTO;

import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */

@Service

@Transactional
public class AccountService {

	private final Logger log = LoggerFactory.getLogger(AccountService.class);

	private final AccountRepository accountRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthorityRepository authorityRepository;

	private final CacheManager cacheManager;

	@Autowired
	private AccountAuthorityRepository accountAuthorityRepository;

	public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
			AuthorityRepository authorityRepository, CacheManager cacheManager) {
		this.accountRepository = accountRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepository = authorityRepository;
		this.cacheManager = cacheManager;
	}

	public Optional<Account> activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		return accountRepository.findOneByActivationKey(key).map(user -> {
			// activate given user for the registration key.
			user.setActivated(true);
			user.setActivationKey(null);
			this.clearUserCaches(user);
			log.debug("Activated user: {}", user);
			return user;
		});
	}

	public Optional<Account> completePasswordReset(String newPassword, String key) {
		log.debug("Reset user password for reset key {}", key);
		return accountRepository.findOneByResetKey(key)
				.filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400))).map(user -> {
					user.setPassword(passwordEncoder.encode(newPassword));
					user.setResetKey(null);
					user.setResetDate(null);
					this.clearUserCaches(user);
					return user;
				});
	}

	public Optional<Account> requestPasswordReset(String mail) {
		return accountRepository.findOneByEmailIgnoreCase(mail).filter(Account::getActivated).map(user -> {
			user.setResetKey(RandomUtil.generateResetKey());
			user.setResetDate(Instant.now());
			this.clearUserCaches(user);
			return user;
		});
	}

	public Account registerUser(AccountDTO userDTO, String password) {
		accountRepository.findOneByUserName(userDTO.getUserName().toLowerCase()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new UsernameAlreadyUsedException();
			}
		});
		accountRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new EmailAlreadyUsedException();
			}
		});
		Account newUser = new Account();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setUserName(userDTO.getUserName().toLowerCase()); // new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setDisplayName(userDTO.getDisplayName());
		if (userDTO.getEmail() != null) {
			newUser.setEmail(userDTO.getEmail().toLowerCase());
		}
//		newUser.setCreatedBy();
		newUser.setCreatedDate(Instant.now());
		newUser.setUpdateDate(Instant.now());
		newUser.setActivated(false); // new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		accountRepository.save(newUser);
		// them quyen cho user
		Optional<Authority> authorities = authorityRepository.findByAuthority(AuthoritiesConstants.USER);
		AccountAuthority accountAuthority = new AccountAuthority();
		accountAuthority.setAccount(newUser);
		accountAuthority.setAuthority(authorities.get());
		accountAuthorityRepository.save(accountAuthority);
		this.clearUserCaches(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	private boolean removeNonActivatedUser(Account existingUser) {
		if (existingUser.getActivated()) {
			return false;
		}
		accountRepository.delete(existingUser);
		accountRepository.flush();
		this.clearUserCaches(existingUser);
		return true;
	}

	public Account createUser(AccountDTO userDTO) {
		Account user = new Account();
		user.setUserName(userDTO.getUserName().toLowerCase());
		user.setDisplayName(userDTO.getDisplayName());
		if (userDTO.getEmail() != null) {
			user.setEmail(userDTO.getEmail().toLowerCase());
		}

		String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
		user.setPassword(encryptedPassword);
		user.setResetKey(RandomUtil.generateResetKey());
		user.setResetDate(Instant.now());
		user.setActivated(true);
		Set<Authority> authorities = new HashSet<>();
		if (userDTO.getAuthorities() != null) {
			authorities = userDTO.getAuthorities().stream().map(authorityRepository::findByAuthority)
					.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
		}

		accountRepository.save(user);
		for (Authority author : authorities) {
			AccountAuthority accAuthor = new AccountAuthority();
			accAuthor.setAccount(user);
			accAuthor.setAuthority(author);
			accountAuthorityRepository.save(accAuthor);
		}
		// luu user truoc khi luu quyen do tinh chat database
		this.clearUserCaches(user);
		log.debug("Created Information for User: {}", user);
		return user;
	}

	/**
	 * Update all information for a specific user, and return the modified user.
	 *
	 * @param userDTO user to update.
	 * @return updated user.
	 */

	/*
	 * public Optional<AccountDTO> updateUser(AccountDTO userDTO) { return
	 * Optional.of(accountRepository.findById(userDTO.getUUID())).filter(Optional::
	 * isPresent).map(Optional::get) .map(user -> { this.clearUserCaches(user);
	 * user.setUserName(userDTO.getUserName().toLowerCase());
	 * user.setDisplayName(userDTO.getDisplayName()); if (userDTO.getEmail() !=
	 * null) { user.setEmail(userDTO.getEmail().toLowerCase()); }
	 * user.setActivated(userDTO.isActivated()); Set<Authority> managedAuthorities =
	 * user.getAccountAuthoritys(); managedAuthorities.clear();
	 * userDTO.getAuthorities().stream().map(authorityRepository::findByAuthority).
	 * filter(Optional::isPresent)
	 * .map(Optional::get).forEach(managedAuthorities::add);
	 * this.clearUserCaches(user); log.debug("Changed Information for User: {}",
	 * user); return user; }).map(AccountDTO::new); }
	 */

	public void deleteUser(String login) {
		accountRepository.findOneByUserName(login).ifPresent(user -> {
			accountRepository.delete(user);
			this.clearUserCaches(user);
			log.debug("Deleted User: {}", user);
		});
	}

	/**
	 * Update basic information (first name, last name, email, language) for the
	 * current user.
	 *
	 * @param firstName first name of user.
	 * @param lastName  last name of user.
	 * @param email     email id of user.
	 * @param langKey   language key.
	 * @param imageUrl  image URL of user.
	 */

	/*
	 * public void updateUser(String firstName, String lastName, String email,
	 * String langKey, String imageUrl) {
	 * SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneByLogin
	 * ).ifPresent(user -> { user.setFirstName(firstName);
	 * user.setLastName(lastName); if (email != null) {
	 * user.setEmail(email.toLowerCase()); } user.setLangKey(langKey);
	 * user.setImageUrl(imageUrl); this.clearUserCaches(user);
	 * log.debug("Changed Information for User: {}", user); }); }
	 */

	@Transactional
	public void changePassword(String currentClearTextPassword, String newPassword) {
		SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneByUserName).ifPresent(user -> {
			String currentEncryptedPassword = user.getPassword();
			if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
				throw new InvalidPasswordException();
			}
			String encryptedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(encryptedPassword);
			this.clearUserCaches(user);
			log.debug("Changed password for User: {}", user);
		});
	}

	@Transactional(readOnly = true)
	public Page<AccountDTO> getAllManagedUsers(Pageable pageable) {
		return accountRepository.findAllByUserNameNot(pageable, Constants.ANONYMOUS_USER).map(AccountDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthoritiesByLogin(String userName) {
		return accountRepository.findOneWithAuthoritiesByUserName(userName);
	}

	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneWithAuthoritiesByUserName);
	}

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 */

	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		accountRepository.findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(
				Instant.now().minus(3, ChronoUnit.DAYS)).forEach(user -> {
					log.debug("Deleting not activated user {}", user.getUserName());
					accountRepository.delete(user);
					this.clearUserCaches(user);
				});
	}

	/**
	 * Gets a list of all the authorities.
	 * 
	 * @return a list of all the authorities.
	 */
	@Transactional(readOnly = true)
	public List<String> getAuthorities() {
		return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
	}

	private void clearUserCaches(Account user) {
		Objects.requireNonNull(cacheManager.getCache(AccountRepository.USERS_BY_USERNAME_CACHE))
				.evict(user.getUserName());
		if (user.getEmail() != null) {
			Objects.requireNonNull(cacheManager.getCache(AccountRepository.USERS_BY_EMAIL_CACHE))
					.evict(user.getEmail());
		}
	}
}