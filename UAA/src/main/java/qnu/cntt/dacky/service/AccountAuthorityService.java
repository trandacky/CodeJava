package qnu.cntt.dacky.service;


import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.service.dto.AccountAuthorityDTO;

public interface AccountAuthorityService {
	public AccountAuthority createAccountAuthority(AccountAuthorityDTO accountAuthorityDTO);
	public void deleteAccountAuthority(String username, String role);
}
