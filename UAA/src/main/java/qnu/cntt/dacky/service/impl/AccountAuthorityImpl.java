package qnu.cntt.dacky.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AccountAuthorityRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.AuthorityRepository;
import qnu.cntt.dacky.service.AccountAuthorityService;
import qnu.cntt.dacky.service.dto.AccountAuthorityDTO;

@Service
public class AccountAuthorityImpl implements AccountAuthorityService{

	@Autowired
	private AccountAuthorityRepository accountAuthorityRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	@Override
	public AccountAuthority createAccountAuthority(AccountAuthorityDTO accountAuthorityDTO) {
		
		return accountAuthorityRepository.save(accountAuthorityDTO.toEntity(accountAuthorityDTO));
	}

	@Override
	public void deleteAccountAuthority(String username, String role) {
		Account account= accountRepository.findOneByUsername(username).get();
		Authority authority= authorityRepository.findByAuthorities(role).get();
		
		accountAuthorityRepository.findByAccountAndAuthorityLike(account, authority).ifPresent(accountAuthority -> {
			accountAuthorityRepository.delete(accountAuthority);
		});
	}

}
