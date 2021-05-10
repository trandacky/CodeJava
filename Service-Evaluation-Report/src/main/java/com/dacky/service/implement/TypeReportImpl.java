package com.dacky.service.implement;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacky.entity.TypeReport;
import com.dacky.repository.TypeReportRepository;
import com.dacky.service.TypeReportService;
import com.dacky.service.dto.TypeReportDTO;

@Service
public class TypeReportImpl implements TypeReportService {

	@Autowired
	private TypeReportRepository typeReportRepository;

	@Override
	public Optional<TypeReport> getTypeReportById(long id) {
		return typeReportRepository.findById(id);
	}

	@Override
	public List<TypeReport> getEnableTypeReport() {
		return typeReportRepository.findByEnableTrue();
	}

	@Override
	public TypeReport updateEnableTypeReport(Long id, boolean enable) {
		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(id);
		if (typeReportOptional.isPresent()) {
			TypeReport typeReport = typeReportOptional.get();
			typeReport.setUpdateDate(Instant.now());
			typeReport.setEnable(enable);
			return typeReportRepository.save(typeReport);
		}
		return null;
	}

	@Override
	public TypeReport updateContentTypeReport(Long id, String typeName) {
		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(id);
		if (typeReportOptional.isPresent()) {
			TypeReport typeReport = typeReportOptional.get();
			typeReport.setUpdateDate(Instant.now());
			typeReport.setTypeName(typeName);
			return typeReportRepository.save(typeReport);
		}
		return null;
	}

	@Override
	public TypeReport createTypeReport(TypeReportDTO typeReportDTO) {
		TypeReport typeReport = new TypeReport();
		typeReport.setTypeName(typeReportDTO.getTypeName());
		return typeReportRepository.save(typeReport);
	}

	@Override
	public TypeReport updateTypeReport(TypeReportDTO typeReportDTO) {

		Optional<TypeReport> typeReportOptional = typeReportRepository.findById(typeReportDTO.getId());

		if (typeReportOptional.isPresent()) {
			TypeReport typeReport = typeReportOptional.get();
			typeReport.setUpdateDate(Instant.now());
			typeReport.setTypeName(typeReportDTO.getTypeName());
			typeReport.setEnable(typeReportDTO.isEnable());
			return typeReportRepository.save(typeReport);
		}
		return null;
	}

	@Override
	public List<TypeReport> getAllTypeReport() {
		return typeReportRepository.findAll();
	}

	@Override
	public TypeReport createTypeReportByTypeName(String typeName) {
		TypeReport typeReport = new TypeReport();
		typeReport.setEnable(true);
		typeReport.setTypeName(typeName);
		return typeReportRepository.save(typeReport);
	}

}
