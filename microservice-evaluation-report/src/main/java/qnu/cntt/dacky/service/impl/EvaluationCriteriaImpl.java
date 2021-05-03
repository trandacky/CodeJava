package qnu.cntt.dacky.service.impl;

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
		Optional<EvaluationCriteria> parentEvaluationCriteriaOptional = evaluationCriteriaRepository
				.findById(evaluationCriteriaDTO.getParentId());
		Optional<TypeReport> optional = typeReportRepository.findById(evaluationCriteriaDTO.getTypeReport());

		EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
		evaluationCriteria.setContent(evaluationCriteriaDTO.getContent());
		evaluationCriteria.setMaxScore(evaluationCriteriaDTO.getMaxScore());

		if (parentEvaluationCriteriaOptional.isPresent()) {
			evaluationCriteria.setParentEvaluationCriteria(parentEvaluationCriteriaOptional.get());
		}
		if (optional.isPresent()) {
			evaluationCriteria.setTypeReport(optional.get());
			TypeReport typeReport=optional.get();
			typeReportRepository.save(typeReport);
		}
		return evaluationCriteriaRepository.save(evaluationCriteria);
	}

	@Override
	public EvaluationCriteria updateEvaluaCriteria(EvaluationCriteriaDTO evaluationCriteriaDTO) {

		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository
				.findById(evaluationCriteriaDTO.getId());
		if (evaluationCriteriaOptional.isPresent())

		{
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
//			TypeReport typeReport=evaluationCriteria.getTypeReport();
//			typeReportRepository.save(typeReport);
			evaluationCriteria.setContent(evaluationCriteriaDTO.getContent());
			evaluationCriteria.setMaxScore(evaluationCriteriaDTO.getMaxScore());
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
			TypeReport typeReport=evaluationCriteria.getTypeReport();
			typeReportRepository.save(typeReport);
			evaluationCriteria.setContent(content);
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
			TypeReport typeReport=evaluationCriteria.getTypeReport();
			typeReportRepository.save(typeReport);
			evaluationCriteria.setMaxScore(score);
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
				TypeReport typeReport=evaluationCriteria.getTypeReport();
				typeReportRepository.save(typeReport);
				evaluationCriteria.setParentEvaluationCriteria(evaluationCriteriaParentOptional.get());
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
		Optional<EvaluationCriteria> criteria = evaluationCriteriaRepository.findById(id);
		EvaluationCriteria evaluationCriteria;
		if (criteria.isPresent()) {
			evaluationCriteria=criteria.get();
			TypeReport typeReport=evaluationCriteria.getTypeReport();
			typeReportRepository.save(typeReport);
			evaluationCriteria.setTypeReport(null);
			evaluationCriteria.setParentEvaluationCriteria(null);;
			evaluationCriteriaRepository.save(evaluationCriteria);
			if(!evaluationCriteria.getChildEvaluationCriterias().isEmpty())
			{
				delete(evaluationCriteria.getChildEvaluationCriterias());
			}
			evaluationCriteriaRepository.delete(evaluationCriteria);
		}
	}
	private void delete(List<EvaluationCriteria> evaluationCriterias)
	{
		for(EvaluationCriteria evaluationCriteria:evaluationCriterias)
		{
			if(evaluationCriteria.getChildEvaluationCriterias().isEmpty())
			{
//				evaluationCriteria.setParentEvaluationCriteria(null);
//				evaluationCriteriaRepository.save(evaluationCriteria);
				evaluationCriteriaRepository.delete(evaluationCriteria);
			}
			else
			{
				delete(evaluationCriteria.getChildEvaluationCriterias());
			}
		}
	}
}
