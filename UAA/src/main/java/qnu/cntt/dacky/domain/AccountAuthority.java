package qnu.cntt.dacky.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountAuthority extends AbstractAuditingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_uuid", referencedColumnName = "uuid")
	private Account account;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_uuid", referencedColumnName = "uuid")
	private Authority authority;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Authority getAuthority() {
		return authority;
	}



	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	
}
