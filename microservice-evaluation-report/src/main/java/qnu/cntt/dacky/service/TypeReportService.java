package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.service.dto.TypeReportDTO;

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
