package qnu.cntt.dacky.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "account_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountDetails extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	@Column(name = "about", length = 50)
	private String about;

	@Size(max = 50)
	@Column(name = "name", length = 50)
	private String name;

	@Size(min = 3, max = 14)
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_uuid", referencedColumnName = "uuid")
	private Account account;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "class_id") private ClaSs class1;
	 */

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
