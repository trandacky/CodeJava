package qnu.cntt.dacky.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.MenuAdmintration;
@Repository
public interface MenuAdmintrationRepository extends JpaRepository<MenuAdmintration, UUID>{

}
