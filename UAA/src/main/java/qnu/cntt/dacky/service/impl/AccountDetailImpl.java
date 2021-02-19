package qnu.cntt.dacky.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import qnu.cntt.dacky.domain.AccountDetails;
import qnu.cntt.dacky.repository.AccountDetailRepository;
import qnu.cntt.dacky.repository.AccountRepository;
import qnu.cntt.dacky.service.AccountDetailService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountDetailDTO;

public class AccountDetailImpl implements AccountDetailService{
	private final Logger log = LoggerFactory.getLogger(AccountDetailService.class);
	@Autowired
	private AccountDetailRepository accountDetailRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountDetails createAccountDetail(AccountDetailDTO accountDetailDTO)
	{
		return accountDetailRepository.save(accountDetailDTO.toEntity(accountDetailDTO));
	}
	
	
	public AccountDetails getAccountDetailByUsername(String username)
	{
		return accountRepository.findOneByUsername(username).get().getAccountDetail();
	}
	
	public Optional<AccountDetails> updateAccountDetail(AccountDetailDTO accountDetailDTO)
	{
		AccountDetails accountDetails=accountDetailDTO.toEntity(accountDetailDTO);
		return accountDetailRepository.findById(accountDetails.getAccount().getAccountDetail().getUUID()).map(accountDetailDB -> {
			accountDetails.setUpdateDate(Instant.now());
			accountDetailDB = accountDetails;
			return accountDetailRepository.save(accountDetailDB);
		});
	}
	
	public void delete(String username)
	{
		accountRepository.findOneByUsername(username).ifPresent(user -> {
			accountDetailRepository.delete(user.getAccountDetail());
			log.debug("deleted user "+ user.getUsername());
			accountRepository.delete(user);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AccountDetailDTO> getAllAccountDetail(Pageable pageable) {
	return	accountDetailRepository.findAll(pageable).map(AccountDetailDTO::new);

	}
	
}
