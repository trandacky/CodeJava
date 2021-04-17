package qnu.cntt.dacky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.TypeReport;

@Repository
public interface TypeReportRepository extends JpaRepository<TypeReport, Long>{

	List<TypeReport> findByEnableTrue();

}
