package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.service.dto.TypeReportDTO;

public interface TypeReportService {
	Optional<TypeReport> getTypeReportById(long id);

	List<TypeReport> getEnableTypeReport();
	
	List<TypeReport> getAllTypeReport();

	Page<TypeReport> getAllTypeReportPageable(Pageable pageable);
	
	TypeReport createTypeReportByTypeName(String typeName);
	
	TypeReport createTypeReport(TypeReportDTO typeReportDTO);

	TypeReport updateTypeReport(TypeReportDTO typeReportDTO);

	TypeReport updateEnableTypeReport(Long id, boolean enable);

	TypeReport updateContentTypeReport(Long id, String typeName);
}
