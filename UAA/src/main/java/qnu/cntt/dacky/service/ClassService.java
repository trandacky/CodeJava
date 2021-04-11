package qnu.cntt.dacky.service;

import java.util.List;

import qnu.cntt.dacky.domain.ClaSs;

public interface ClassService {

	List<ClaSs> getAllClass();

	ClaSs getClassById(long id);

	ClaSs getClassByName(String className);

	String addClass(ClaSs claSs);

	String deleteClassById(long id);

	String deleteClassByName(String className);

	boolean isClassExistById(long id);
}
