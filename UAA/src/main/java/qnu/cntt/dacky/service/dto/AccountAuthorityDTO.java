package qnu.cntt.dacky.service.dto;

import org.springframework.beans.factory.annotation.Autowired;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AccountAuthorityRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.AuthorityRepository;

public class AccountAuthorityDTO {
	@Autowired
	private AccountAuthorityRepository accountAuthorityRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	private java.util.UUID UUID;
	
	private String username;
	private String role;
	
	public AccountAuthorityDTO(AccountAuthority accountAuthority) {
		this.UUID = accountAuthority.getUUID();
		this.username = accountAuthority.getAccount().getUsername();
		this.role = accountAuthority.getAuthority().getAuthorities();
	}
	public AccountAuthority toEntity(AccountAuthorityDTO accountAuthorityDTO)
	{
		AccountAuthority accountAuthority = new AccountAuthority();
		Account account= accountRepository.findOneByUsername(accountAuthorityDTO.getUsername()).get();
		Authority authority= authorityRepository.findByAuthorities(accountAuthorityDTO.getRole()).get();
		
		accountAuthority.setAccount(account);
		accountAuthority.setAuthority(authority);
		
		return accountAuthority;
	}
	
	public AccountAuthorityDTO() {
	}
	public java.util.UUID getUUID() {
		return UUID;
	}
	public void setUUID(java.util.UUID uUID) {
		UUID = uUID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
