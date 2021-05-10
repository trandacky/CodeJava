package com.dacky.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dacky.entity.EvaluationCriteria;
import com.dacky.entity.TypeReport;
@Repository
public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, Long>{

	List<EvaluationCriteria> findByEnableTrue();

	List<EvaluationCriteria> findByTypeReport(TypeReport typeReport);

	@Query("SELECT e FROM EvaluationCriteria e")
	List<EvaluationCriteria> findAllByPageable(Pageable pageable);

}
