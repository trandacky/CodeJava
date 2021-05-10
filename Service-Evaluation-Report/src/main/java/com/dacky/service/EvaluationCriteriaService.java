package com.dacky.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import com.dacky.entity.EvaluationCriteria;
import com.dacky.service.dto.EvaluationCriteriaDTO;

public interface EvaluationCriteriaService {

	List<EvaluationCriteria> getAll();
	
	List<EvaluationCriteria> getAllByPageable(Pageable pageable);
	
	List<EvaluationCriteria> getAllEnable();

	List<EvaluationCriteria> getByTypeReportId(Long typeReportId);

	Optional<EvaluationCriteria> getById(Long id);

	EvaluationCriteria createEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaDTO);

	EvaluationCriteria updateEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaDTO);

	EvaluationCriteria updateContentById(Long id, String content);

	EvaluationCriteria updateMaxScore(Long id, int score);

	EvaluationCriteria updateParentId(Long id, Long parentId);

	EvaluationCriteria updateEnableById(Long id, boolean enable);

	EvaluationCriteria updateTypeReportId(Long id, Long typeReport);
}
