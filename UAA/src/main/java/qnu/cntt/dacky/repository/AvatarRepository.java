
package qnu.cntt.dacky.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, UUID> {

}
