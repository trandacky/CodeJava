package qnu.cntt.dacky.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.ClaSs;

public interface ClassService {

	List<ClaSs> getAllClass();

	ClaSs getClassById(UUID id);

	ClaSs getClassByName(String className);

	String addClass(ClaSs claSs);


	String deleteClassByName(String className);

	boolean isClassExistById(UUID id);

	String deleteClassById(UUID id);


	Page<ClaSs> getAllByIdAndPageable(Pageable paging);
}
