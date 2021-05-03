package qnu.cntt.dacky.web.rest.dto;

import java.util.UUID;

public class CommunicateAccountClassDTO {
	private String username;
	private UUID classuuid;
	
	
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
