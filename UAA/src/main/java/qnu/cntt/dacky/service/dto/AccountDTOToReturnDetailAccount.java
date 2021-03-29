package qnu.cntt.dacky.service.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import qnu.cntt.dacky.config.Constants;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;

public class AccountDTOToReturnDetailAccount {

	public AccountDTOToReturnDetailAccount(Account account) {
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
		this.enabled = account.isEnabled();
		this.locked = account.isLocked();
		this.email = account.getEmail();
		List<String> authority = new ArrayList<>();
		for(AccountAuthority accAuthor: account.getAccountAuthoritys())
		{
			authority.add(accAuthor.getAuthority().getAuthorities());
		}
		this.authorities = authority;
		this.accountDetailDTO = new AccountDetailDTO(account.getAccountDetail());
	}

	public AccountDTOToReturnDetailAccount() {
		super();
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

	private String firstName = "";

	private String lastName = "";

	private boolean enabled = true;

	private boolean locked = false;

	private String email;

	private List<String> authorities;

	private AccountDetailDTO accountDetailDTO;

	
	
	@Override
	public String toString() {
		return "AccountDTOToReturnDetailAccount [UUID=" + UUID + ", username=" + username + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", activated=" + activated + ", firstName=" + firstName + ", lastName=" + lastName + ", enabled="
				+ enabled + ", locked=" + locked + ", email=" + email + ", authorities=" + authorities
				+ ", accountDetailDTO=" + accountDetailDTO + "]";
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

	public AccountDetailDTO getAccountDetailDTO() {
		return accountDetailDTO;
	}

	public void setAccountDetailDTO(AccountDetailDTO accountDetailDTO) {
		this.accountDetailDTO = accountDetailDTO;
	}

}
