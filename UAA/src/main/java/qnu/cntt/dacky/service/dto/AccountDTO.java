package qnu.cntt.dacky.service.dto;

import qnu.cntt.dacky.config.Constants;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class AccountDTO {
	public AccountDTO(Account account) {
		super();
		this.UUID = account.getUUID();
		this.username = account.getUsername();
		this.createdBy = account.getCreatedBy();
		this.createdDate = account.getCreatedDate();
		this.updateBy = account.getUpdateBy();
		this.updateDate = account.getUpdateDate();
		this.activated = account.getActivated();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.email = account.getEmail();
		this.phoneNumber=account.getPhoneNumber();
		List<String> authority = new ArrayList<>();
		for(AccountAuthority accAuthor: account.getAccountAuthoritys())
		{
			authority.add(accAuthor.getAuthority().getAuthorities());
		}
		this.authorities = authority;
	}

	private java.util.UUID UUID;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 6, max = 128)
	private String username;
	
	private String createdBy;

	private Instant createdDate;

	private String updateBy;

	private Instant updateDate;
	
	private boolean activated = false;

	private String firstName="";
	
	private String lastName="";

	private boolean enabled = true;

	private boolean locked = false;

	private String email;
	
	private String phoneNumber;

	private List<String> authorities;

	public AccountDTO() {
		// Empty constructor needed for Jackson.
	}


	@Override
	public String toString() {
		return "AccountDTO [UUID=" + UUID + ", username=" + username + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + ", activated=" + activated
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", locked=" + locked
				+ ", email=" + email + ", authorities=" + authorities + "]";
	}


//	public Account toEntity(AccountDTO accountDTO) {
//		Account account = new Account();
//		account.setActivated(accountDTO.isActivated());
//		account.setUUID(accountDTO.getUUID());
//		account.setCreatedBy(accountDTO.getCreatedBy());
//		account.setCreatedDate(accountDTO.getCreatedDate());
//		account.setUpdateBy(accountDTO.getUpdateBy());
//		account.setUpdateDate(accountDTO.getUpdateDate());
//		account.setFirstName(accountDTO.getFirstName());
//		account.setLastName(accountDTO.getLastName());
//		account.setUsername(accountDTO.getUsername());
//		if (accountDTO.getEmail() != null) {
//			account.setEmail(accountDTO.getEmail().toLowerCase());
//		}
//		List<AccountAuthority> accountAuthoritys = new ArrayList<>();
//		for (String acc : accountDTO.getAuthorities()) {
//			Authority authority = authorityRepository.findByAuthorities(acc).get();
//
//			AccountAuthority accountAuthority = new AccountAuthority();
//			accountAuthority.setAuthority(authority);
//			accountAuthoritys.add(accountAuthority);
//		}
//		account.setAccountAuthoritys(accountAuthoritys);
//		return account;
//	}

	public java.util.UUID getUUID() {
		return UUID;
	}

	public void setUUID(java.util.UUID uUID) {
		UUID = uUID;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
