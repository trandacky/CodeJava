package qnu.cntt.dacky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import qnu.cntt.dacky.config.Constants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private boolean activated = false;

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	@JsonIgnore
	private String activationKey;

	@Size(max = 30)
	@Column(name = "first_name", length = 30)
	private String firstName;

	@Size(max = 30)
	@Column(name = "last_name", length = 30)
	private String lastName;

	@NotNull
	@Column(nullable = false)
	private boolean enabled = false;

	@NotNull
	@Column(nullable = false)
	private boolean locked = false;

	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	@JsonIgnore
	private String resetKey;

	@Column(name = "reset_date")
	@JsonIgnore
	private Instant resetDate;

	@NotNull
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String username;

	@JsonIgnore
	@NotNull
	@Size(min = 6, max = 128)
	@Column(name = "password", length = 128, nullable = false)
	private String password;

	@Email
	@Size(min = 5, max = 254)
	@Column(length = 254, unique = true)
	private String email;
	
	@Size(max = 14)
	@Column(name = "phone_number",length = 14)
	private String phoneNumber;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Avatar> avatars;

	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	private List<AccountAuthority> accountAuthoritys;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "class_id")
	private ClaSs class1;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AccountDepartment>  accountDepartments;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActivated() {
		return activated;
	}
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public List<Avatar> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<Avatar> avatars) {
		this.avatars = avatars;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public List<AccountAuthority> getAccountAuthoritys() {
		return accountAuthoritys;
	}

	public void setAccountAuthoritys(List<AccountAuthority> accountAuthoritys) {
		this.accountAuthoritys = accountAuthoritys;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public ClaSs getClass1() {
		return class1;
	}

	public void setClass1(ClaSs class1) {
		this.class1 = class1;
	}

	public List<AccountDepartment> getAccountDepartments() {
		return accountDepartments;
	}

	public void setAccountDepartments(List<AccountDepartment> accountDepartments) {
		this.accountDepartments = accountDepartments;
	}
	
	

}
