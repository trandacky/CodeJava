package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

public interface AuthorityService {
	public Authority createAuthority(AuthorityDTO authorityDTO);
	public Authority updateAuthority(AuthorityDTO authorityDTO);
	public List<Authority> getAllAuthority();
	public Optional<Authority> getAuthority(String authorities);
	public void deleteAuthority(String authority);
}
