package qnu.cntt.dacky.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import qnu.cntt.dacky.domain.AbtractEntity;
import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;
import qnu.cntt.dacky.service.dto.ReportDTO;
import qnu.cntt.dacky.web.rest.dto.CreateReportDTO;

public interface ReportService {
	/* get */
	/* Hoi Dong Khoa */
	List<Report> getAll();

	Page<Report> getAllByPageable(Pageable pageable);
	
	Page<Report> getAllAccepted3FalseByPageable(Pageable pageable);
	
	List<Report> getAllByAccepted3True();

	List<Report> getAllByAccepted3False();

	
	List<Report> getAllByAccepted2False();

	List<Report> getAllByAccepted2True();

	List<Report> getAllByClassId(UUID uuid);
	
	Page<Report> getAllByClassIdAndPageable(UUID classid,Pageable pageable);
	
	Page<Report> getAllByClassIdAccepted2FalseAndPageable(UUID classid,Pageable pageable);

	List<Report> seachReport(String username, Long classId, int year, int semester);
	
	Page<Report> getAllByAccepted3FalseByClassIdByPagable(UUID classid, Pageable pageable);

	/* create */

	Report createReport(String username, Long typeReportId);

	// Sinh Vien

	List<Report> getAllByUsername(String username);

	List<Report> getByUsernameAndAccepted2False(String username);

	List<Report> getByUsernameAndAccepted2True(String username);

	Optional<Report> getByUsernameAndTypeReportId(String username, Long typeReportId);
	/* update */
	Report updateReport(ReportDTO reportDTO);

	Report updateAccepted2(Long id, boolean accepted2);

	Report updateAccepted3(Long id, boolean accepted3);

	Report updateTotalScore1(Long id);

	Report updateTotalScore2(Long id);

		Report updateTotalScore3(Long id, int totalScore3);
/*admin*/
	List<Report> updateAllReportAccepted3TrueByClassId(Long classId);
	
	List<Report> updateAllReportAccepted3FalseByClassId(Long classId);

	Report createReportByDTO(CreateReportDTO createReportDTO, String username);

	boolean checkReport(CreateReportDTO createReportDTO, String username);

	Page<Report> getAllReportByUsername(String username, Pageable paging);

	boolean checkReportByUsername(Long id, String username);

	Optional<Report> getReportByIdAndUserName(Long id, String username);

	Optional<Report> getReportById(Long id);

	List<Report> updateAllAccepted2ByClass(UUID classUUID);

}
