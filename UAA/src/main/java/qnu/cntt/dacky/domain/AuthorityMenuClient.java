package qnu.cntt.dacky.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "authority_menu_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AuthorityMenuClient extends AbstractAuditingEntity implements Serializable{
private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="authority_uuid", referencedColumnName = "uuid")
	private Authority authority;
	
	@ManyToOne
	@JoinColumn(name="menu_client_uuid", referencedColumnName = "uuid")
	private MenuClient menuClient;

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public MenuClient getMenuClient() {
		return menuClient;
	}

	public void setMenuClient(MenuClient menuClient) {
		this.menuClient = menuClient;
	}

}
