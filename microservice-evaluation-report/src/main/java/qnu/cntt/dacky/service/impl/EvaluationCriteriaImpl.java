package qnu.cntt.dacky.service.impl;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qnu.cntt.dacky.domain.EvaluationCriteria;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.repository.EvaluationCriteriaRepository;
import qnu.cntt.dacky.repository.TypeReportRepository;
import qnu.cntt.dacky.service.EvaluationCriteriaService;
import qnu.cntt.dacky.service.dto.EvaluationCriteriaDTO;
import qnu.cntt.dacky.service.dto.EvaluationCriteriaUpdateDTO;

@Service
public class EvaluationCriteriaImpl implements EvaluationCriteriaService {

	@Autowired
	private EvaluationCriteriaRepository evaluationCriteriaRepository;

	@Autowired
	private TypeReportRepository typeReportRepository;

	@Override
	public List<EvaluationCriteria> getAll() {
		return evaluationCriteriaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<EvaluationCriteria> getByTypeReportId(Long typeReportId) {
		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(typeReportId);
		if (typeReportOptional.isPresent()) {
			TypeReport typeReport = typeReportOptional.get();
			return evaluationCriteriaRepository.findByTypeReport(typeReport);
		}
		return null;
	}

	@Override
	public Optional<EvaluationCriteria> getById(Long id) {
		return evaluationCriteriaRepository.findById(id);
	}

	@Override
	public EvaluationCriteria createEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaDTO) {
		Optional<EvaluationCriteria> parentEvaluationCriteriaOptional= evaluationCriteriaRepository.findById(evaluationCriteriaDTO.getParentId());
		EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
		evaluationCriteria.setContent(evaluationCriteriaDTO.getContent());
		evaluationCriteria.setMaxScore(evaluationCriteriaDTO.getMaxScore());
		if(parentEvaluationCriteriaOptional.isPresent())
		{
			evaluationCriteria.setParentEvaluationCriteria(parentEvaluationCriteriaOptional.get());
			evaluationCriteria.setTypeReport(parentEvaluationCriteriaOptional.get().getTypeReport());
		}
		return evaluationCriteriaRepository.save(evaluationCriteria);
	}

	@Override
	public EvaluationCriteria updateEvaluaCriteria(EvaluationCriteriaUpdateDTO evaluationCriteriaDTO) {

		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository
				.findById(evaluationCriteriaDTO.getId());
		if (evaluationCriteriaOptional.isPresent())

		{
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
			evaluationCriteria.setContent(evaluationCriteriaDTO.getContent());
			evaluationCriteria.setMaxScore(evaluationCriteriaDTO.getMaxScore());
			evaluationCriteria.setUpdateDate(Instant.now());
			return evaluationCriteriaRepository.save(evaluationCriteria);
		}

		return null;
	}

	@Override
	public EvaluationCriteria updateContentById(Long id, String content) {
		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository.findById(id);
		if (evaluationCriteriaOptional.isPresent())

		{
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
			evaluationCriteria.setContent(content);
			evaluationCriteria.setUpdateDate(Instant.now());
			return evaluationCriteriaRepository.save(evaluationCriteria);
		}

		return null;
	}

	@Override
	public EvaluationCriteria updateMaxScore(Long id, int score) {
		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository.findById(id);
		if (evaluationCriteriaOptional.isPresent())

		{
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
			evaluationCriteria.setMaxScore(score);
			evaluationCriteria.setUpdateDate(Instant.now());
			return evaluationCriteriaRepository.save(evaluationCriteria);
		}

		return null;
	}

	@Override
	public EvaluationCriteria updateParentId(Long id, Long parentId) {
		Optional<EvaluationCriteria> evaluationCriteriaParentOptional = evaluationCriteriaRepository.findById(parentId);
		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository.findById(id);
		if (evaluationCriteriaOptional.isPresent()) {
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
			if (evaluationCriteriaParentOptional.isPresent()) {
				evaluationCriteria.setParentEvaluationCriteria(evaluationCriteriaParentOptional.get());
				evaluationCriteria.setUpdateDate(Instant.now());
				return evaluationCriteriaRepository.save(evaluationCriteria);
			}
		}
		return null;
	}

	@Override
	public EvaluationCriteria updateTypeReportId(Long id, Long idTypeReport) {

		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(idTypeReport);

		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository.findById(id);
		if (evaluationCriteriaOptional.isPresent()) {

			if (typeReportOptional.isPresent()) {
				TypeReport typeReport = typeReportOptional.get();
				EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
				evaluationCriteria.setUpdateDate(Instant.now());
				evaluationCriteria.setTypeReport(typeReport);
				return evaluationCriteriaRepository.save(evaluationCriteria);
			}
		}
		return null;
	}

	@Override
	public List<EvaluationCriteria> getAllByPageable(Pageable pageable) {
		
		return evaluationCriteriaRepository.findAllByPageable(pageable);
	}

	@Override
	public void deleteEvaluationReport(Long id) {
		 evaluationCriteriaRepository.deleteById(id);
	}
}
