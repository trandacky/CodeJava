package qnu.cntt.dacky.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "authority_menu_admintration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AuthorityMenuAdmintration extends AbstractAuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="authority_uuid", referencedColumnName = "uuid")
	private Authority authority;
	
	@ManyToOne
	@JoinColumn(name="menu_admintration_uuid", referencedColumnName = "uuid")
	private MenuAdmintration menuAdmintration;

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public MenuAdmintration getMenuAdmintration() {
		return menuAdmintration;
	}

	public void setMenuAdmintration(MenuAdmintration menuAdmintration) {
		this.menuAdmintration = menuAdmintration;
	}
	
	
}
