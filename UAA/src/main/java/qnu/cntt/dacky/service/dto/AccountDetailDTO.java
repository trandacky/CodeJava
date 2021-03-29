package qnu.cntt.dacky.service.dto;

import org.springframework.beans.factory.annotation.Autowired;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.repository.AccountDetailRepository;
import qnu.cntt.dacky.repository.AccountRepository;

public class AccountDetailDTO {
	@Autowired
	private AccountDetailRepository accountDetailRepository;
	@Autowired
	private AccountRepository accountRepository;
	private java.util.UUID UUID;
	private String about;

	private String name;

	private String phoneNumber;
	
	private String account;
	
	public AccountDetails toEntity(AccountDetailDTO accountDetailDTO)
	{
		Account account =  accountRepository.findOneByUsername(accountDetailDTO.getAccount()).get();
		AccountDetails accountDetails = account.getAccountDetail();
		accountDetails.setAbout(accountDetailDTO.getAbout());
		accountDetails.setPhoneNumber(accountDetailDTO.getPhoneNumber());
		accountDetails.setName(accountDetailDTO.getName());
		accountDetails.setAccount(account);
		return accountDetails;
		
	}
	
	
	public AccountDetailDTO(AccountDetails accountDetails) {
		this.about= accountDetails.getAbout();
		this.name = accountDetails.getName();
		this.phoneNumber = accountDetails.getPhoneNumber();
		this.account = accountDetails.getAccount().getUsername();
		this.UUID= accountDetails.getUUID();
	}
	
	@Override
	public String toString() {
		return "AccountDetailDTO [UUID=" + UUID + ", about=" + about + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", account=" + account + "]";
	}


	public AccountDetailDTO() {
	}

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public java.util.UUID getUUID() {
		return UUID;
	}

	public void setUUID(java.util.UUID uUID) {
		UUID = uUID;
	}
	
	
}
