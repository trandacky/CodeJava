package qnu.cntt.dacky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.DetailReport;
import qnu.cntt.dacky.domain.Report;

import org.springframework.data.domain.Pageable;

@Repository
public interface DetailReportRepository extends JpaRepository<DetailReport, Long>{
	  @Query("SELECT e FROM DetailReport e")
	  List<DetailReport> findDetailReports(Pageable pageable);

	List<DetailReport> findByReport(Report report);

}
