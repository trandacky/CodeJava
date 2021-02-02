package qnu.cntt.dacky.service.dto;

import qnu.cntt.dacky.config.Constants;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.Account;

import javax.validation.constraints.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class AccountDTO {

	private java.util.UUID UUID;

	
	private String createdBy;

    private Instant createdDate;

    private String updateBy;

    private Instant updateDate;
	
	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 6, max = 128)
	private String userName;

	private boolean activated = false;

	private String displayName;

	private boolean enabled = false;

	private boolean locked = false;

	private String email;
	
	private List<String> authorities;
	
	public AccountDTO() {
		// Empty constructor needed for Jackson.
	}

	public AccountDTO(Account user) {
		this.UUID = user.getUUID();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.updateBy = user.getUpdateBy();
		this.updateDate = user.getUpdateDate();
		this.userName = user.getUserName();
		this.activated = user.getActivated();
		
		this.activated = user.getActivated();
		this.displayName = user.getDisplayName();
		this.enabled = user.isEnabled();
		this.locked = user.isLocked();
		this.email = user.getEmail();
		this.authorities = user.getAccountAuthoritys().stream().map(authority ->authority.getAuthority().getName()) .collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "AccountDTO [UUID=" + UUID + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", userName=" + userName + ", activated=" + activated
				+ ", displayName=" + displayName + ", enabled=" + enabled + ", locked=" + locked + ", email=" + email
				+ ", authorities=" + authorities + "]";
	}

	

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	
	
}
