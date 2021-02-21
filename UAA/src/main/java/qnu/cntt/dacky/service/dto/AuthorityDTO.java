package qnu.cntt.dacky.service.dto;

import org.springframework.beans.factory.annotation.Autowired;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AuthorityRepository;

public class AuthorityDTO {
	@Autowired
	private AuthorityRepository authorityRepository;

	private String authorities;

	private boolean enableAdmin = false;

	private boolean enableClient = false;

	private boolean isRoot = false;

	private String name;

	private String parentAuthorities;

	private String childAuthorities;

	public Authority toEntity(AuthorityDTO authorityDTO) {

		Authority authority = authorityRepository.findByAuthorities(authorityDTO.getAuthorities()).get();
		authority.setEnableAdmin(authorityDTO.isEnableAdmin());
		authority.setEnableClient(authorityDTO.isEnableClient());
		authority.setRoot(authorityDTO.isRoot());
		
		if (authorityDTO.getParentAuthorities() != null && !authorityDTO.getParentAuthorities().isEmpty())
			authority.setParentAuthority(
					authorityRepository.findByAuthorities(authorityDTO.getParentAuthorities()).get());
		if (authorityDTO.getChildAuthorities() != null && !authorityDTO.getChildAuthorities().isEmpty())
			authority
					.setChildAuthority(authorityRepository.findByAuthorities(authorityDTO.getChildAuthorities()).get());

		return authority;
	}

	public Authority toNewEntity(AuthorityDTO authorityDTO) {

		Authority authority = new Authority();
		authority.setEnableAdmin(authorityDTO.isEnableAdmin());
		authority.setEnableClient(authorityDTO.isEnableClient());
		authority.setRoot(authorityDTO.isRoot());
		authority.setAuthorities(authorityDTO.getAuthorities());

		if (authorityDTO.getParentAuthorities() != null && !authorityDTO.getParentAuthorities().isEmpty())
			authority.setParentAuthority(
					authorityRepository.findByAuthorities(authorityDTO.getParentAuthorities()).get());
		if (authorityDTO.getChildAuthorities() != null && !authorityDTO.getChildAuthorities().isEmpty())
			authority
					.setChildAuthority(authorityRepository.findByAuthorities(authorityDTO.getChildAuthorities()).get());

		return authority;
	}

	public AuthorityDTO(Authority authority) {
		this.authorities = authority.getAuthorities();
		this.enableAdmin = authority.isEnableAdmin();
		this.enableClient = authority.isEnableClient();
		this.isRoot = authority.isRoot();
		this.name = authority.getName();
		this.childAuthorities = authority.getChildAuthority().getAuthorities();
		this.parentAuthorities = authority.getParentAuthority().getAuthorities();
	}

	public AuthorityDTO() {
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public boolean isEnableAdmin() {
		return enableAdmin;
	}

	public void setEnableAdmin(boolean enableAdmin) {
		this.enableAdmin = enableAdmin;
	}

	public boolean isEnableClient() {
		return enableClient;
	}

	public void setEnableClient(boolean enableClient) {
		this.enableClient = enableClient;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentAuthorities() {
		return parentAuthorities;
	}

	public void setParentAuthorities(String parentAuthorities) {
		this.parentAuthorities = parentAuthorities;
	}

	public String getChildAuthorities() {
		return childAuthorities;
	}

	public void setChildAuthorities(String childAuthorities) {
		this.childAuthorities = childAuthorities;
	}

}
