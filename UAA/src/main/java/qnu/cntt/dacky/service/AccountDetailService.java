package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.service.dto.AccountDetailDTO;

public interface AccountDetailService {

	public AccountDetails createAccountDetail(AccountDetailDTO accountDetailDTO);
	public AccountDetailDTO getAccountDetailByUsername(String username);
	public Optional<AccountDetails> updateAccountDetail(AccountDetailDTO accountDetailDTO);
	public void delete(String username);
	public List<AccountDetails> getAllAccountDetail();
}
