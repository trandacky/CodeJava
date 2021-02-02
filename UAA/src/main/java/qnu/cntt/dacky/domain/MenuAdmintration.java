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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "menu_admintration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuAdmintration extends AbstractAuditingEntity implements Serializable{

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
	
	@OneToOne(mappedBy = "parentMenuAdmintration")
	private MenuAdmintration childMenuAdmintration;
	
	@OneToOne
	@JoinColumn(name="parent_uuid",referencedColumnName = "uuid")
	private MenuAdmintration parentMenuAdmintration;

	@OneToMany(mappedBy = "menuAdmintration")
	private List<AuthorityMenuAdmintration> authorityMenuAdmintrations;
	
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

	public MenuAdmintration getChildMenuAdmintration() {
		return childMenuAdmintration;
	}

	public void setChildMenuAdmintration(MenuAdmintration childMenuAdmintration) {
		this.childMenuAdmintration = childMenuAdmintration;
	}

	public MenuAdmintration getParentMenuAdmintration() {
		return parentMenuAdmintration;
	}

	public void setParentMenuAdmintration(MenuAdmintration parentMenuAdmintration) {
		this.parentMenuAdmintration = parentMenuAdmintration;
	}

	public List<AuthorityMenuAdmintration> getAuthorityMenuAdmintrations() {
		return authorityMenuAdmintrations;
	}

	public void setAuthorityMenuAdmintrations(List<AuthorityMenuAdmintration> authorityMenuAdmintrations) {
		this.authorityMenuAdmintrations = authorityMenuAdmintrations;
	}
	
}
