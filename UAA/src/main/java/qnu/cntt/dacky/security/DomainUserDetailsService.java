package qnu.cntt.dacky.security;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.AccountAuthority;
import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.repository.AccountRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

	private final AccountRepository userRepository;

	public DomainUserDetailsService(AccountRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String userName) {
		log.debug("Authenticating {}", userName);

		if (new EmailValidator().isValid(userName, null)) {
			return userRepository._findOneWithAuthorityByEmailIgnoreCase(userName)
//					return userRepository.findOneByEmailIgnoreCase(userName)
					.map(user -> createSpringSecurityUser(userName, user))
					.orElseThrow(() -> new UsernameNotFoundException(
							"User with email " + userName + " was not found in the database"));
		}

		String lowercaseLogin = userName.toLowerCase(Locale.ENGLISH);
		return userRepository._findOneWithAuthorityByUserName(lowercaseLogin)
//		return userRepository.findOneByUserName(lowercaseLogin)
				.map(user -> createSpringSecurityUser(lowercaseLogin, user))
				.orElseThrow(() -> new UsernameNotFoundException(
						"User " + lowercaseLogin + " was not found in the database"));

	}

	private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin,
			Account user) {

		if (!user.getActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
		}
		List<AccountAuthority> accountAuthority = user.getAccountAuthoritys();
		List<GrantedAuthority> grantedAuthorities = accountAuthority.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}
}
