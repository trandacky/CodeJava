package qnu.cntt.dacky.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "menu_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuClient extends AbstractAuditingEntity implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Column(name="description", length = 200)
	private String decription;
	
	@NotNull
    @Column(name="enable",nullable = false)
    private boolean enabled = false;
	
	@Column(name="icon", length = 200)
	private String icon;
	
	@Column(name="name", length = 200)
	private String name;
	
	@NotNull
	@Column(name="ordinal")
	private int ordinal;
	
	@Column(name="path", length = 255)
	private String path;
	
	@OneToOne(mappedBy = "parentMenuClient")
	private MenuClient childMenuClient;
	
	@OneToOne
	@JoinColumn(name="parent_uuid",referencedColumnName = "uuid")
	private MenuClient parentMenuClient;
	
	@OneToMany(mappedBy = "menuClient")
	private List<AuthorityMenuClient> authorityMenuClients;

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MenuClient getChildMenuClient() {
		return childMenuClient;
	}

	public void setChildMenuClient(MenuClient childMenuClient) {
		this.childMenuClient = childMenuClient;
	}

	public MenuClient getParentMenuClient() {
		return parentMenuClient;
	}

	public void setParentMenuClient(MenuClient parentMenuClient) {
		this.parentMenuClient = parentMenuClient;
	}

	public List<AuthorityMenuClient> getAuthorityMenuClients() {
		return authorityMenuClients;
	}

	public void setAuthorityMenuClients(List<AuthorityMenuClient> authorityMenuClients) {
		this.authorityMenuClients = authorityMenuClients;
	}
	
	
	
}
