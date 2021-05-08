
package qnu.cntt.dacky.service.impl;

import qnu.cntt.dacky.config.Constants;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.domain.Department;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.AccountDepartment;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.repository.ClassRepository;
import qnu.cntt.dacky.repository.DepartmentRepository;
import qnu.cntt.dacky.repository.AccountAuthorityRepository;
import qnu.cntt.dacky.repository.AccountDepartmentRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.security.AuthoritiesConstants;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountDTOToReturnDetailAccount;
import qnu.cntt.dacky.service.dto.AccountSVDTO;
import qnu.cntt.dacky.service.exception.EmailAlreadyUsedException;
import qnu.cntt.dacky.service.exception.InvalidPasswordException;
import qnu.cntt.dacky.service.exception.UsernameAlreadyUsedException;
import qnu.cntt.dacky.web.rest.vm.ManagedUserVM;
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
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */

@Service

@Transactional
public class AccountImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private AccountAuthorityRepository accountAuthorityRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private AccountDepartmentRepository accountDepartmentRepository;

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
		boolean testUsername = true;
		try {
			Long.parseLong(userDTO.getUsername());
		} catch (Exception e) {
			testUsername = false;
		}
		if (testUsername) {
			throw new UsernameAlreadyUsedException();
		}
		accountRepository.findOneByUsername(userDTO.getUsername().toLowerCase()).ifPresent(existingUser -> {
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
		newUser.setUsername(userDTO.getUsername().toLowerCase()); // new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		if (userDTO.getEmail() != null) {
			newUser.setEmail(userDTO.getEmail().toLowerCase());
		}
		newUser.setCreatedBy("user");
		newUser.setUpdateBy("user");
		newUser.setActivated(false); // new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		accountRepository.save(newUser);
		// them quyen cho user
		Optional<Authority> authorities = authorityRepository.findByAuthorities(AuthoritiesConstants.USER);
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
		accountAuthorityRepository.deleteAll(existingUser.getAccountAuthoritys());
		accountRepository.delete(existingUser);
		accountRepository.flush();
		this.clearUserCaches(existingUser);
		return true;
	}

	private void removeUser(Account existingUser) {
		accountAuthorityRepository.deleteAll(existingUser.getAccountAuthoritys());
//		accountDetailRepository.delete(existingUser.getAccountDetail());
		accountRepository.delete(existingUser);
		accountRepository.flush();
		this.clearUserCaches(existingUser);
	}

	public Account createUser(ManagedUserVM userDTO) {
		String encryptedPassword;
		Account user = new Account();
		user.setUsername(userDTO.getUsername().toLowerCase());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		if (userDTO.getEmail() != null) {
			user.setEmail(userDTO.getEmail().toLowerCase());
		}
		if (ObjectUtils.isEmpty(userDTO.getPassword())) {
			encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
			user.setResetKey(RandomUtil.generateResetKey());
		} else {
			encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
		}
		user.setPassword(encryptedPassword);

		user.setResetDate(Instant.now());
		user.setActivated(true);
		user.setCreatedBy("user");
		user.setUpdateBy("user");
		Set<Authority> authorities = new HashSet<>();
		if (userDTO.getAuthorities() != null) {
			authorities = userDTO.getAuthorities().stream().map(authorityRepository::findByAuthorities)
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

	public Optional<AccountDTO> updateUser(AccountDTO userDTO) {
		return Optional.of(accountRepository.findById(userDTO.getUUID())).filter(Optional::isPresent).map(Optional::get)
				.map(user -> {
					this.clearUserCaches(user);
					user.setUsername(userDTO.getUsername().toLowerCase());
					user.setFirstName(userDTO.getFirstName());
					user.setLastName(userDTO.getLastName());
					if (userDTO.getEmail() != null) {
						user.setEmail(userDTO.getEmail().toLowerCase());
					}
					user.setActivated(userDTO.isActivated());
					List<AccountAuthority> accAuthor = user.getAccountAuthoritys();
					List<Authority> authority = new ArrayList<>();
					for (AccountAuthority author : accAuthor) {
						authority.add(author.getAuthority());
					}
					List<Authority> managedAuthorities = authority;
					Set<Authority> authorities = new HashSet<>();
					if (userDTO.getAuthorities() != null) {
						authorities = userDTO.getAuthorities().stream().map(authorityRepository::findByAuthorities)
								.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
					}
					accountRepository.save(user);
					for (Authority author : authorities) {
						if (!managedAuthorities.contains(author)) {
							AccountAuthority accAuthor2 = new AccountAuthority();
							accAuthor2.setAccount(user);
							accAuthor2.setAuthority(author);
							accountAuthorityRepository.save(accAuthor2);
						}
					}
					managedAuthorities.clear();
					userDTO.getAuthorities().stream().map(authorityRepository::findByAuthorities)
							.filter(Optional::isPresent).map(Optional::get).forEach(managedAuthorities::add);
					this.clearUserCaches(user);
					log.debug("Changed Information for User: {}", user);
					return user;
				}).map(AccountDTO::new);
	}

	public void deleteUser(String login) {
		accountRepository.findOneByUsername(login).ifPresent(user -> {
			AccountDTOToReturnDetailAccount account = new AccountDTOToReturnDetailAccount(user);
			if (account.getAuthorities().contains(AuthoritiesConstants.GV)) {
				user.setClass1(null);
			} else {
				accountAuthorityRepository.deleteAll(user.getAccountAuthoritys());
//			if(!user.getActivated()&&user.isLocked())
//			{
//				accountRepository.delete(user);
//			}
				if (!user.getActivated()) {
					user.setLocked(true);
				}
				user.setActivated(false);
			}
			this.clearUserCaches(user);
			log.debug("Deleted User: {}", user);
		});
	}

	@Transactional
	@Override
	public void deleteUserKhoa(String login) {
		accountRepository.findOneByUsername(login).ifPresent(user -> {
			AccountDTOToReturnDetailAccount account = new AccountDTOToReturnDetailAccount(user);
			if (account.getAuthorities().contains(AuthoritiesConstants.GV)) {
				user.setClass1(null);
			} else {
				accountAuthorityRepository.deleteAll(user.getAccountAuthoritys());
				if (!user.getActivated()) {
					user.setLocked(true);
				}
				user.setActivated(false);

			}
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
	@Transactional
	public void updateUser(String firstName, String lastName, String email) {
		SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneByUsername).ifPresent(user -> {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			if (email != null) {
				user.setEmail(email.toLowerCase());
			}
			this.clearUserCaches(user);
			log.debug("Changed Information for User: {}", user);
		});
	}

	@Transactional
	public void changePassword(String currentClearTextPassword, String newPassword) {
		SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneByUsername).ifPresent(user -> {
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
		return accountRepository.findAllByUsernameNot(pageable, Constants.ANONYMOUS_USER).map(AccountDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthoritiesByUserName(String userName) {
		return accountRepository.findOneWithAuthoritiesByUsername(userName);
//		return accountRepository.findOneByUsername(userName);
	}

	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneWithAuthoritiesByUsername);
//		return SecurityUtils.getCurrentUserLogin().flatMap(accountRepository::findOneByUsername);
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
					log.debug("Deleting not activated user {}", user.getUsername());
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
		return authorityRepository.findAll().stream().map(Authority::getAuthorities).collect(Collectors.toList());
	}

	public void registerAdmin() {
		Authority authority = new Authority();
		authority.setAuthorities("ROLE_ADMIN");
		authority.setCreatedBy("Anonymus");
		if (authorityRepository.findByAuthorities(authority.getAuthorities()).isEmpty()) {
			authorityRepository.save(authority);
		}

		String username = "admin", password = "admin", email = "trandackydbz2@gmail.com";
		Optional<Account> user = accountRepository.findByUsername(username);
		if (user.isPresent()) {
			accountRepository.findOneByEmailIgnoreCase(email).ifPresent(existingUser -> {
				if (existingUser.getUsername() != user.get().getUsername())
					removeUser(existingUser);
			});
		}
		Account newUser = new Account();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setUsername(username.toLowerCase()); // new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(username);
		newUser.setLastName(username);
		newUser.setEmail(email.toLowerCase());
		newUser.setCreatedBy("user");
		newUser.setUpdateBy("user");
		newUser.setActivated(true);
		accountRepository.save(newUser);
		// them quyen cho user
		Optional<Authority> authorities = authorityRepository.findByAuthorities(AuthoritiesConstants.ADMIN);
		AccountAuthority accountAuthority = new AccountAuthority();
		accountAuthority.setAccount(newUser);
		accountAuthority.setAuthority(authorities.get());
		accountAuthorityRepository.save(accountAuthority);
		this.clearUserCaches(newUser);
		log.debug("Created Information for User: {}", newUser);
	}

	private void clearUserCaches(Account user) {
		Objects.requireNonNull(cacheManager.getCache(AccountRepository.USERS_BY_USERNAME_CACHE))
				.evict(user.getUsername());
		if (user.getEmail() != null) {
			Objects.requireNonNull(cacheManager.getCache(AccountRepository.USERS_BY_EMAIL_CACHE))
					.evict(user.getEmail());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public int getCount(ClaSs ss) {
		return accountRepository.findCountByClass1(ss);
	}

	@Override
	@Transactional
	public void updateActivated(String username, boolean activated) {
		accountRepository.findOneByUsername(username).ifPresent(user -> {
			// accountRepository.delete(user);

			if (activated && user.getAccountAuthoritys().isEmpty()) {
				AccountAuthority accountAuthority = new AccountAuthority();
				accountAuthority.setAccount(user);
				accountAuthority.setAuthority(authorityRepository.findByAuthorities(AuthoritiesConstants.SV).get());
				accountAuthorityRepository.save(accountAuthority);
			}
			user.setActivated(activated);
			this.clearUserCaches(user);
			log.debug("Deleted User: {}", user);
		});
	}

	@Override
	public Account getAccountByUsername(String string) {
		Optional<Account> optional = accountRepository.findByUsername(string);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public List<Account> getAllAccountKHOA(UUID uuid) {
		Optional<Department> optional = departmentRepository.findById(uuid);
		if (optional.isPresent()) {
			Department department = optional.get();
			List<AccountDepartment> accountDepartments = accountDepartmentRepository.findByDepartment(department);
			List<Account> accounts = new ArrayList<>();
			for (AccountDepartment accountDepartment : accountDepartments) {
				accounts.add(accountDepartment.getAccount());
			}
			return accounts;
		}
		return null;
	}

	@Override
	public List<Account> accountRoleKhoaNot() {
		List<AccountDepartment> accountDepartments = accountDepartmentRepository.findAll();
		List<Account> accounts = new ArrayList<Account>();
		for (AccountDepartment accountDepartment : accountDepartments) {
			accounts.add(accountDepartment.getAccount());
		}
		List<Account> accountKhoa = accountRepository.findByAuthorities(AuthoritiesConstants.KHOA);
		for (Account account : accounts) {
			for (int i = 0; i < accountKhoa.size(); i++) {
				if (account.getUUID().equals(accountKhoa.get(i).getUUID())) {
					accountKhoa.remove(i);
					i--;
				}
			}
		}
		return accountKhoa;
	}

	@Override
	@Transactional
	public Account createAccountOfClass(AccountSVDTO accountSVDTO) {
		Optional<ClaSs> optional = classRepository.findById(accountSVDTO.getClassuuid());
		if (optional.isPresent()) {
			Optional<Account> accOptional = accountRepository.findByUsername(accountSVDTO.getUsername());
			Account account = new Account();
			if (accOptional.isPresent() && accOptional.get().isLocked()) {
				accountRepository.delete(accOptional.get());
			}
			account.setActivated(true);
			account.setLocked(false);
			account.setUsername(accountSVDTO.getUsername().toLowerCase());
			account.setClass1(optional.get());
			account.setFirstName(accountSVDTO.getFirstName());
			account.setLastName(accountSVDTO.getLastName());
			account.setEmail(accountSVDTO.getEmail().toLowerCase());
			account.setPhoneNumber(accountSVDTO.getPhoneNumber());

			String encryptedPassword = passwordEncoder.encode(accountSVDTO.getPassword());
			account.setPassword(encryptedPassword);

			account = accountRepository.save(account);
			Authority authority = authorityRepository.findByAuthorities(AuthoritiesConstants.USER).get();
			AccountAuthority accountAuthority = new AccountAuthority();
			accountAuthority.setAccount(account);
			accountAuthority.setAuthority(authority);
			accountAuthorityRepository.save(accountAuthority);
			return account;
		}
		return null;
	}
}
