package qnu.cntt.dacky.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String authority;

    @NotNull
    @Column(name="enable_admin",nullable = false)
    private boolean enableAdmin = false;
    
    @NotNull
    @Column(name="enable_client",nullable = false)
    private boolean enableClient = false;
    
    @NotNull
    @Column(name="is_root",nullable = false)
    private boolean isRoot = false;
    
    @Size(max = 50)
    @Column(length = 50)
    private String name;
    
    @OneToOne(mappedBy = "parentAuthority")
    private Authority childAuthority;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_uuid",referencedColumnName = "uuid")
    private Authority parentAuthority;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountAuthority> accountAuthorities;
    
    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private List<AuthorityMenuClient> authorityMenuClients;
    
    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private List<AuthorityMenuAdmintration> authorityMenuAdmintrations;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnableAdmin() {
		return enableAdmin;
	}

	public void setEnableAdmin(boolean enableAdmin) {
		this.enableAdmin = enableAdmin;
	}

	public boolean isEnableClient() {
		return enableClient;
	}

	public void setEnableClient(boolean enableClient) {
		this.enableClient = enableClient;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority getChildAuthority() {
		return childAuthority;
	}

	public void setChildAuthority(Authority childAuthority) {
		this.childAuthority = childAuthority;
	}

	public Authority getParentAuthority() {
		return parentAuthority;
	}

	public void setParentAuthority(Authority parentAuthority) {
		this.parentAuthority = parentAuthority;
	}

	public List<AccountAuthority> getAccountAuthorities() {
		return accountAuthorities;
	}

	public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) {
		this.accountAuthorities = accountAuthorities;
	}

	public List<AuthorityMenuClient> getAuthorityMenuClients() {
		return authorityMenuClients;
	}

	public void setAuthorityMenuClients(List<AuthorityMenuClient> authorityMenuClients) {
		this.authorityMenuClients = authorityMenuClients;
	}

	public List<AuthorityMenuAdmintration> getAuthorityMenuAdmintrations() {
		return authorityMenuAdmintrations;
	}

	public void setAuthorityMenuAdmintrations(List<AuthorityMenuAdmintration> authorityMenuAdmintrations) {
		this.authorityMenuAdmintrations = authorityMenuAdmintrations;
	}

	
    
    
}
