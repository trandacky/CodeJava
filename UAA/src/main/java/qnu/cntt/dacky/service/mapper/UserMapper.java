package qnu.cntt.dacky.service.mapper;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.service.dto.AccountDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Account} and its DTO called {@link AccountDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    public List<AccountDTO> usersToUserDTOs(List<Account> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public AccountDTO userToUserDTO(Account user) {
        return new AccountDTO(user);
    }

    public List<Account> userDTOsToUsers(List<AccountDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public Account userDTOToUser(AccountDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            Account user = new Account();
			/*
			 * user.setId(userDTO.getId()); user.setUsername(userDTO.getLogin());
			 * user.setFirstName(userDTO.getFirstName());
			 * user.setLastName(userDTO.getLastName()); user.setEmail(userDTO.getEmail());
			 * user.setImageUrl(userDTO.getImageUrl());
			 * user.setActivated(userDTO.isActivated());
			 * user.setLangKey(userDTO.getLangKey()); Set<Authority> authorities =
			 * this.authoritiesFromStrings(userDTO.getAuthorities());
			 * user.setAuthorities(authorities);
			 */
            return user;
        }
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }

    public Account userFromId(UUID UUID) {
        if (UUID == null) {
            return null;
        }
        Account account = new Account();
        account.setUUID(UUID);
        return account;
    }
}
