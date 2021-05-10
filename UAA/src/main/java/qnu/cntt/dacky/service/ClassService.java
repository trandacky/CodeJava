package qnu.cntt.dacky.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.ClassDTO;
import qnu.cntt.dacky.service.dto.ClassDTOUpdate;

public interface ClassService {

	List<ClaSs> getAllClass();

	ClaSs getClassById(UUID id);

	ClaSs getClassByName(String className);

	ClaSs addClass(ClassDTO classDTO);


	String deleteClassByName(String className);

	boolean isClassExistById(UUID id);

	ClaSs deleteClassById(UUID id);


	Page<ClaSs> getAllByIdAndPageable(Pageable paging);

	ClaSs updateEnable(UUID uuid, boolean enable);

	Page<Account> getAccountByClass(Pageable paging, UUID uuid);
	
	List<Account> getAllAccountByClass(UUID uuid);

	ClaSs updateName(ClassDTOUpdate dtoUpdate);

	List<ClaSs> getClassByUuids(List<UUID> uuids);
}
