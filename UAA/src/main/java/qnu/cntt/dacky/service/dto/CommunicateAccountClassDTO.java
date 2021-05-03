package qnu.cntt.dacky.service.dto;

import java.util.UUID;

import qnu.cntt.dacky.domain.Account;

public class CommunicateAccountClassDTO {
	private String username;
	private UUID classuuid;
	
	public CommunicateAccountClassDTO(Account account)
	{
		super();
		this.username=account.getUsername();
		this.classuuid=account.getClass1().getUUID();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UUID getClassuuid() {
		return classuuid;
	}
	public void setClassuuid(UUID classuuid) {
		this.classuuid = classuuid;
	}
	
}
