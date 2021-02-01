package qnu.cntt.dacky.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import qnu.cntt.dacky.config.Constants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @Column(name = "display_name", length = 30)
    private String displayName;
    
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
    
    @OneToOne(mappedBy = "account")
    private AccountDetails AccountDetail;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Avatar> avatars;
    
    @OneToOne(mappedBy = "account")
    private AccountAuthority accountAuthority;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public boolean getActivated() {
        return activated;
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

	public AccountDetails getAccountDetail() {
		return AccountDetail;
	}

	public void setAccountDetail(AccountDetails accountDetail) {
		AccountDetail = accountDetail;
	}



	public AccountAuthority getAccountAuthority() {
		return accountAuthority;
	}

	public void setAccountAuthority(AccountAuthority accountAuthority) {
		this.accountAuthority = accountAuthority;
	}

	public List<Avatar> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<Avatar> avatars) {
		this.avatars = avatars;
	}

	
   
  
}
