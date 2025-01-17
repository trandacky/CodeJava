package qnu.cntt.dacky.repository;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.Authority;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

	Optional<Authority> findByAuthorities(String authority);
}
