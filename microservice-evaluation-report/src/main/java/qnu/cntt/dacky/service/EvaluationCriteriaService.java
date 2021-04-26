package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.EvaluationCriteria;
import qnu.cntt.dacky.service.dto.EvaluationCriteriaDTO;
import qnu.cntt.dacky.service.dto.EvaluationCriteriaUpdateDTO;

public interface EvaluationCriteriaService {

List<EvaluationCriteria> getAll();
	
	List<EvaluationCriteria> getAllByPageable(Pageable pageable);
	

	List<EvaluationCriteria> getByTypeReportId(Long typeReportId);

	Optional<EvaluationCriteria> getById(Long id);

	EvaluationCriteria createEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaDTO);

	EvaluationCriteria updateEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaUpdateDTO);

	EvaluationCriteria updateContentById(Long id, String content);

	EvaluationCriteria updateMaxScore(Long id, int score);

	EvaluationCriteria updateParentId(Long id, Long parentId);


	EvaluationCriteria updateTypeReportId(Long id, Long typeReport);

	void deleteEvaluationReport(Long id);
}
