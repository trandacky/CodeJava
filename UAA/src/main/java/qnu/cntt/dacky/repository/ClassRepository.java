package qnu.cntt.dacky.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.ClaSs;


@Repository
public interface ClassRepository extends JpaRepository<ClaSs, UUID> {

	Optional<ClaSs> findByName(String name);

	void deleteByName(String classname);

	//List<ClaSs> findByIsDeletedFalse();

}
