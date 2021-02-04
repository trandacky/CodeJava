package qnu.cntt.dacky.repository;

import qnu.cntt.dacky.domain.Account;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.Instant;

/**
 * Spring Data JPA repository for the {@link Account} entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

	String USERS_BY_USERNAME_CACHE = "usersByUsername";

	String USERS_BY_EMAIL_CACHE = "usersByEmail";

	Optional<Account> findOneByActivationKey(String activationKey);

	List<Account> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

	Optional<Account> findOneByResetKey(String resetKey);

	Optional<Account> findOneByEmailIgnoreCase(String email);

	Optional<Account> findOneByUserName(String login);
	
//	@EntityGraph(attributePaths = "authorities")
	@Cacheable(cacheNames = USERS_BY_USERNAME_CACHE)
	@Query("SELECT a ,aaa.authorities "
			+ "FROM Account a, AccountAuthority aa , Authority aaa where a.UUID = aa.account and aa.authority = aaa.UUID and a.userName = :username")
	Optional<Account> _findOneWithAuthorityByUserName( @Param("username") String userName);

	/*
	 * @EntityGraph(attributePaths = "authorities")
	 * 
	 * @Cacheable(cacheNames = USERS_BY_USERNAME_CACHE) Optional<Account>
	 * findOneWithAuthoritiesByUserName(String userName);
	 */

//	@EntityGraph(attributePaths = "authorities")
	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	@Query("SELECT a, aaa.authorities "
			+ "FROM Account a, AccountAuthority aa , Authority aaa where a.UUID = aa.account and aa.authority = aaa.UUID and a.email = :email")
	Optional<Account> _findOneWithAuthorityByEmailIgnoreCase(@Param("email") String email);
//	Optional<Account> findOneWithAuthoritiesByEmailIgnoreCase(String email);

	Page<Account> findAllByUserNameNot(Pageable pageable, String login);
}
