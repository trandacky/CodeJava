package qnu.cntt.dacky.service.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AuthorityRepository;

public class AuthorityDTO {
	@Autowired
	private AuthorityRepository authorityRepository;

	private String authorities;

	public Authority toEntity(AuthorityDTO authorityDTO) {

		Authority authority = authorityRepository.findByAuthorities(authorityDTO.getAuthorities()).get();
		return authority;
	}

	public Authority toNewEntity(AuthorityDTO authorityDTO) {
		Authority authority = new Authority();
		authority.setAuthorities(authorityDTO.getAuthorities());
		return authority;
	}

	

	public AuthorityDTO() {
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	

}
