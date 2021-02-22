package qnu.cntt.dacky.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.AuthorityMenuAdmintration;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.repository.AuthorityMenuAdmintrationRepository;
import qnu.cntt.dacky.service.AuthorityMenuAdminService;
import qnu.cntt.dacky.service.dto.AuthorityDTO;
@Service
public class AuthorityMenuAdminImpl implements AuthorityMenuAdminService {
	@Autowired
	private AuthorityMenuAdmintrationRepository authorityMenuAdmintrationRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void createAuthorityMenuAdmin(AuthorityMenuAdmintration authorityMenuAdmin) {
		authorityMenuAdmintrationRepository.save(authorityMenuAdmin);

	}

	@Override
	public void deleteAuthorityMenuAdmin(AuthorityMenuAdmintration authorityMenuAdmin) {

		authorityMenuAdmintrationRepository.findById(authorityMenuAdmin.getUUID()).ifPresent(authorMenuAdmin -> {
			authorityMenuAdmintrationRepository.delete(authorMenuAdmin);
		});
	}

	@Override
	public List<AuthorityMenuAdmintration> getAllAuthorityMenuAdmin() {
		// TODO Auto-generated method stub
		return authorityMenuAdmintrationRepository.findAll();
	}

	@Override
	public List<AuthorityMenuAdmintration> getAuthorityMenuAdmin(String username) {
		List<AuthorityMenuAdmintration> listAuthorityMenuAdmintrations = new ArrayList<>();
		List<AccountAuthority> accountAuthority = accountRepository.findByUsername(username).get()
				.getAccountAuthoritys();
		for (AccountAuthority accAutho : accountAuthority) {
			listAuthorityMenuAdmintrations.addAll(accAutho.getAuthority().getAuthorityMenuAdmintrations());
		}
		return listAuthorityMenuAdmintrations;
	}

}
