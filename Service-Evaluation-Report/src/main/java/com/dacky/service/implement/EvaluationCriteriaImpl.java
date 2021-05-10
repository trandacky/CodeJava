package com.dacky.service.implement;
import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacky.entity.EvaluationCriteria;
import com.dacky.entity.TypeReport;
import com.dacky.repository.EvaluationCriteriaRepository;
import com.dacky.repository.TypeReportRepository;
import com.dacky.service.EvaluationCriteriaService;
import com.dacky.service.dto.EvaluationCriteriaDTO;

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

	@Override
	public List<EvaluationCriteria> getAllEnable() {
		return evaluationCriteriaRepository.findByEnableTrue();
	}

	@Override
	public List<EvaluationCriteria> getByTypeReportId(Long typeReportId) {
		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(typeReportId);
		if (typeReportOptional.isPresent()) {
			TypeReport typeReport = typeReportOptional.get();
			return typeReport.getEvaluationCriterias();
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
			evaluationCriteria.setContent(evaluationCriteriaDTO.getContent());
			evaluationCriteria.setMaxScore(evaluationCriteriaDTO.getMaxScore());
			evaluationCriteria.setEnable(evaluationCriteriaDTO.getEnable());
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
	public EvaluationCriteria updateEnableById(Long id, boolean enable) {

		Optional<EvaluationCriteria> evaluationCriteriaOptional = evaluationCriteriaRepository.findById(id);
		if (evaluationCriteriaOptional.isPresent()) {
			EvaluationCriteria evaluationCriteria = evaluationCriteriaOptional.get();
			evaluationCriteria.setEnable(enable);
			evaluationCriteria.setUpdateDate(Instant.now());
			return evaluationCriteriaRepository.save(evaluationCriteria);

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
}
