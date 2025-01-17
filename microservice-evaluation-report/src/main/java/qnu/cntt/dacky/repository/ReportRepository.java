package qnu.cntt.dacky.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import qnu.cntt.dacky.domain.Report;
import qnu.cntt.dacky.domain.TypeReport;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	Optional<Report> findByUsernameAndTypeReportLike(String username, Long typeReportId);

	List<Report> findByAccepted2True();

	List<Report> findByUsernameLike(String username);

	List<Report> findByAccepted2False();

	List<Report> findByAccepted2TrueAndAccepted3False();

	List<Report> findByAccepted3True();

	List<Report> findByAccepted3False();

	List<Report> findByUsernameOrClassIdOrYearOrSemesterLike(String username, Long classId, int year, int semester);

	List<Report> findByAccepted2FalseAndUsernameLike(String username);

	List<Report> findByAccepted2TrueAndUsernameLike(String username);

	@Query("select e from Report e")
	Page<Report> findAllByPageable(Pageable pageable);

	@Query("select e from Report e where e.accepted3=False")

	Page<Report> findAllAccepted3FalseByPageable(Pageable pageable);

	@Query("select e from Report e where e.accepted2=False and e.classId= :classId")
	Page<Report> findAllByClassIdAccepted2FalsePageable(@Param(value = "classId") UUID classId, Pageable pageable);

	@Query("select e from Report e where e.classId= :classId")
	Page<Report> findAllByClassIdPageable(@Param(value = "classId") UUID classId, Pageable pageable);

	@Query("select e from Report e where e.classId= :classId and e.accepted3=False")
	Page<Report> findByAccepted3FalseAndClassIdLike(@Param(value = "classId") UUID id, Pageable pageable);

	@Query("update Report e set e.accepted3=True where e.classId= :classId")
	List<Report> updateAccepted3TrueByClassIdLike(Long classId);

	@Query("update Report e set  e.accepted3=False where e.classId= :classId ")
	List<Report> updateAccepted3FalseByClassIdLike(Long classId);

	Optional<Report> findByTypeReportAndClassIdAndYearAndSemesterAndUsername(TypeReport typeReport, UUID classuuid,
			int year, int semester, String username);

	Page<Report> findByAccepted2FalseAndUsernameLike(String username, Pageable paging);

	Page<Report> findByAccepted2FalseAndAccepted3FalseAndUsernameLike(String username, Pageable paging);

	Optional<Report> findByIdAndUsername(Long id, String username);

	Page<Report> findByUsernameLike(String username, Pageable paging);

	List<Report> findAllByClassId(UUID classUUID);

	List<Report> findByClassId(UUID classId);

	@Query("SELECT COUNT(e.id) FROM Report e WHERE e.classId = :uuid AND e.year = :year AND e.semester = :semester "
			+ "AND CAST(e.typeReport.id AS string) LIKE %:type% ")
	int findCountReportByClass(@Param(value = "uuid") UUID uuid, @Param(value = "year") int year,
			@Param(value = "semester") int semester,@Param(value = "type") String type);

	@Query("SELECT e FROM Report e WHERE e.classId = :uuid AND e.year = :year AND e.semester = :semester "
			+ "AND CAST(e.typeReport.id AS string) LIKE %:type% ")
	Page<Report> findByCondition(@Param(value = "uuid") UUID uuid, @Param(value = "year") int year,
			@Param(value = "semester") int semester,@Param(value = "type") String type, Pageable paging);
	
	@Query("SELECT COUNT(e.id) FROM Report e WHERE e.classId = :uuid AND e.accepted3 = TRUE AND e.year = :year AND e.semester = :semester "
			+ "AND CAST(e.typeReport.id AS string) LIKE %:type% ")
	int findCountReportAccepted3TrueByClass(@Param(value = "uuid") UUID uuid, @Param(value = "year") int year,
			@Param(value = "semester") int semester,@Param(value = "type") String type);

	@Query("SELECT e FROM Report e WHERE e.classId = :uuid AND e.year = :year AND e.semester = :semester "
			+ "AND CAST(e.typeReport.id AS string) LIKE %:type% ")
	List<Report> findAllByCondition(@Param(value = "uuid") UUID uuid, @Param(value = "year") int year,
			@Param(value = "semester") int semester,@Param(value = "type") String type);

}
