package qnu.cntt.dacky.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	Optional<Report> findByUsernameAndTypeReportLike(String username, Long typeReportId);

	List<Report> findByAccepted2True();

	List<Report> findByUsernameLike(String username);

	List<Report> findByClassId(long classId);

	List<Report> findByAccepted2False();

	List<Report> findByAccepted2TrueAndAccepted3False();

	List<Report> findByAccepted3True();

	List<Report> findByAccepted3False();

	List<Report> findByAccepted2FalseAndClassId(Long classId);

	List<Report> findByAccepted2TrueAndClassId(Long classId);

	List<Report> findByUsernameOrClassIdOrYearOrSemesterLike(String username, Long classId, int year, int semester);

	List<Report> findByAccepted2FalseAndUsernameLike(String username);

	List<Report> findByAccepted2TrueAndUsernameLike(String username);

	@Query("select e from Report e")
	Page<Report> findAllByPageable(Pageable pageable);

	@Query("select e from Report e where e.accepted3=False")

	Page<Report> findAllAccepted3FalseByPageable(Pageable pageable);

	@Query("select e from Report e where e.accepted2=False and e.classId= :classId")
	Page<Report> findAllByClassIdAccepted2FalsePageable(Long classId, Pageable pageable);

	@Query("select e from Report e where e.classId= :classId")
	Page<Report> findAllByClassIdPageable(UUID classId, Pageable pageable);

	@Query("select e from Report e where e.classId= :classId and e.accepted3=False")
	Page<Report> findByAccepted3FalseAndClassIdLike(Long classId, Pageable pageable);
	
	@Query("update Report e set e.accepted3=True, e.updateDate= :now where e.classId= :classId")
	List<Report> updateAccepted3TrueByClassIdLike(Long classId, Instant now);
	
	@Query("update Report e set  e.accepted3=False, e.updateDate = :now where e.classId= :classId ")
	List<Report> updateAccepted3FalseByClassIdLike(Long classId, Instant now);

	Optional<Report> findByTypeReportAndClassIdAndYearAndSemesterAndUsername(TypeReport typeReport, UUID classuuid,
			int year, int semester, String username);

	Page<Report> findByAccepted2FalseAndUsernameLike(String username, Pageable paging);

	Page<Report> findByAccepted2FalseAndAccepted3FalseAndUsernameLike(String username, Pageable paging);

}
