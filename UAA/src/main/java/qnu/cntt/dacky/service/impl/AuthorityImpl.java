package qnu.cntt.dacky.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@Service
public class AuthorityImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority createAuthority(AuthorityDTO authorityDTO) {

		return authorityRepository.save(authorityDTO.toNewEntity(authorityDTO));
	}

	@Override
	public Authority updateAuthority(AuthorityDTO authorityDTO) {

		return authorityRepository.save(authorityDTO.toEntity(authorityDTO));
	}

	@Override
	public void deleteAuthority(String authority) {
		authorityRepository.findByAuthorities(authority).ifPresent(authorityDelete -> {
			authorityRepository.delete(authorityDelete);
		});

	}

	@Override
	public List<Authority> getAllAuthority() {
		return authorityRepository.findAll();
	}

	@Override
	public Optional<Authority> getAuthority(String authorities) {
		return authorityRepository.findByAuthorities(authorities);
	}

}
