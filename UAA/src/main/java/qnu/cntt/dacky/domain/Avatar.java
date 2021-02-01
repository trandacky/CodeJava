package qnu.cntt.dacky.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "avatar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Avatar extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	@Column(name = "description", length = 50)
	private String description;

	@Column(name = "external")
	private boolean external;

	@Size(max = 50)
	@Column(name = "picture", length = 50)
	private String picture;

	@Column(name = "default")
	private boolean _default;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "account_uuid", referencedColumnName = "uuid")
	private Account account;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean is_default() {
		return _default;
	}

	public void set_default(boolean _default) {
		this._default = _default;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	

}
