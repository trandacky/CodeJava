package qnu.cntt.dacky.service;

import java.util.List;

import qnu.cntt.dacky.domain.AuthorityMenuAdmintration;

public interface AuthorityMenuAdminService {
	public void createAuthorityMenuAdmin(AuthorityMenuAdmintration authorityMenuAdmin);
	public void deleteAuthorityMenuAdmin(AuthorityMenuAdmintration authorityMenuAdmin);
	public List<AuthorityMenuAdmintration> getAllAuthorityMenuAdmin();
	public List<AuthorityMenuAdmintration> getAuthorityMenuAdmin(String username);
	
}
