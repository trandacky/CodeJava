package com.dacky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.dacky.entity.DetailReport;
@Repository
public interface DetailReportRepository extends JpaRepository<DetailReport, Long>{
	  @Query("SELECT e FROM DetailReport e")
	  List<DetailReport> findDetailReports(Pageable pageable);

}
