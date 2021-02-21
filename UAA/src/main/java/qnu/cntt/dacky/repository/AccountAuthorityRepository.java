package qnu.cntt.dacky.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.Authority;
@Repository
public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, UUID>{

	Optional<AccountAuthority> findByAccountAndAuthorityLike(Account account, Authority authority);
	
}
