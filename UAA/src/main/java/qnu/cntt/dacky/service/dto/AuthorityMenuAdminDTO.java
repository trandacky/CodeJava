package qnu.cntt.dacky.service.dto;

import java.util.UUID;

import qnu.cntt.dacky.domain.AuthorityMenuAdmintration;

public class AuthorityMenuAdminDTO {
	private UUID UUID;
	private String authority;
	private String menu;
	
	public AuthorityMenuAdminDTO(AuthorityMenuAdmintration authorityMenuAdmintration) {
		this.UUID = authorityMenuAdmintration.getUUID(); 
		this.authority = authorityMenuAdmintration.getAuthority().getAuthorities();
		this.menu = authorityMenuAdmintration.getMenuAdmintration().getName();
		
	}
	
	public AuthorityMenuAdminDTO() {
	}
	public UUID getUUID() {
		return UUID;
	}
	public void setUUID(UUID uUID) {
		UUID = uUID;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
