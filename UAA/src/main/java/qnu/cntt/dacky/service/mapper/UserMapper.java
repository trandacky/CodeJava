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

   
    public Account userDTOToUser(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        } else {
        	Account account = new Account();
    		account.setActivated(accountDTO.isActivated());
    		account.setUUID(accountDTO.getUUID());
    		account.setCreatedBy(accountDTO.getCreatedBy());
    		account.setCreatedDate(accountDTO.getCreatedDate());
    		account.setUpdateBy(accountDTO.getUpdateBy());
    		account.setUpdateDate(accountDTO.getUpdateDate());
    		account.setDisplayName(accountDTO.getDisplayName());
    		account.setEmail(accountDTO.getEmail());
    		account.setUsername(accountDTO.getUsername());
    		return account;
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
