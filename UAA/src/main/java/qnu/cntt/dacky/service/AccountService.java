package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountDTOToReturnDetailAccount;
import qnu.cntt.dacky.service.dto.AccountSVDTO;
import qnu.cntt.dacky.web.rest.vm.ManagedUserVM;

public interface AccountService {
	public Account createUser(ManagedUserVM userDTO);
	public Account registerUser(AccountDTO userDTO, String password);
	public Optional<AccountDTO> updateUser(AccountDTO userDTO);
	public void deleteUser(String login);
	public void updateUser(String firstName, String lastName, String string);
	
	
	public Optional<Account> activateRegistration(String key);
	public Optional<Account> completePasswordReset(String newPassword, String key);
	public Optional<Account> requestPasswordReset(String mail);
	
	@Transactional
	public void changePassword(String currentClearTextPassword, String newPassword);
	@Transactional(readOnly = true)
	public Page<AccountDTO> getAllManagedUsers(Pageable pageable);
	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthoritiesByUserName(String userName);
	@Transactional(readOnly = true)
	public Optional<Account> getUserWithAuthorities();
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers();
	@Transactional(readOnly = true)
	public List<String> getAuthorities();
	@Transactional
	public void registerAdmin();
	public int getCount(ClaSs ss);
	public void updateActivated(String username, boolean activated);
	public Account getAccountByUsername(String string);
	public List<Account> getAllAccountKHOA(UUID uuid);
	public List<Account> accountRoleKhoaNot();
	public Account createAccountOfClass(AccountSVDTO accountSVDTO);
	void deleteUserKhoa(String login);
	public List<Account> accountRoleSVNot();
	public Account createAccountToClass(UUID userUUID, UUID classUUID);
}
