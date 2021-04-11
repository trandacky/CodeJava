package qnu.cntt.dacky.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@Service
public class AuthorityImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Transactional
	public Authority createAuthority(AuthorityDTO authorityDTO) {

		return authorityRepository.save(authorityDTO.toNewEntity(authorityDTO));
	}

	@Transactional
	public Authority updateAuthority(AuthorityDTO authorityDTO) {

		return authorityRepository.save(authorityDTO.toEntity(authorityDTO));
	}

	@Transactional
	public void deleteAuthority(String authority) {
		authorityRepository.findByAuthorities(authority).ifPresent(authorityDelete -> {
			authorityRepository.delete(authorityDelete);
		});

	}

	@Transactional(readOnly = true)
	public List<Authority> getAllAuthority() {
		return authorityRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Authority> getAuthority(String authorities) {
		return authorityRepository.findByAuthorities(authorities);
	}

}
