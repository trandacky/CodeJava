package com.dacky.service;

import java.util.List;
import java.util.Optional;

import com.dacky.entity.TypeReport;
import com.dacky.service.dto.ReportDTO;
import com.dacky.service.dto.TypeReportDTO;

public interface TypeReportService {
	Optional<TypeReport> getTypeReportById(long id);

	List<TypeReport> getEnableTypeReport();
	
	List<TypeReport> getAllTypeReport();

	TypeReport createTypeReportByTypeName(String typeName);
	
	TypeReport createTypeReport(TypeReportDTO typeReportDTO);

	TypeReport updateTypeReport(TypeReportDTO typeReportDTO);

	TypeReport updateEnableTypeReport(Long id, boolean enable);

	TypeReport updateContentTypeReport(Long id, String typeName);
}
