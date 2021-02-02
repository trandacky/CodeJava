package qnu.cntt.dacky.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import qnu.cntt.dacky.domain.AccountAuthority;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, UUID>{

}
