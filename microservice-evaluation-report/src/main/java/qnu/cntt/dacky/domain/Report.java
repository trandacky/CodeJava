package qnu.cntt.dacky.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "report")
public class Report extends AbtractEntity {
	@Column(name = "year")
	private Integer year;

	/* Hoc Ky */
	@Column(name = "semester")
	private Integer semester;

	@Column(name = "total_score1")
	private Integer totalScore1 = 0;

	@Column(name = "total_score2")
	private Integer totalScore2 = 0;

	@Column(name = "total_score3")
	private Integer totalScore3 = 0;

	@Column(name = "accepted2")
	private Boolean accepted2 = false;

	@Column(name = "accepted3")
	private Boolean accepted3 = false;
	
	@Column(name="username")
	private String username;
	
	@Column(name="class_id")
	private Long classId;
	
	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
	private List<DetailReport> detailReports;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "type_report_id", nullable = false)
    private TypeReport typeReport;


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getSemester() {
		return semester;
	}


	public void setSemester(Integer semester) {
		this.semester = semester;
	}


	public Integer getTotalScore1() {
		return totalScore1;
	}


	public void setTotalScore1(Integer totalScore1) {
		this.totalScore1 = totalScore1;
	}


	public Integer getTotalScore2() {
		return totalScore2;
	}


	public void setTotalScore2(Integer totalScore2) {
		this.totalScore2 = totalScore2;
	}


	public Integer getTotalScore3() {
		return totalScore3;
	}


	public void setTotalScore3(Integer totalScore3) {
		this.totalScore3 = totalScore3;
	}


	public Boolean getAccepted2() {
		return accepted2;
	}


	public void setAccepted2(Boolean accepted2) {
		this.accepted2 = accepted2;
	}


	public Boolean getAccepted3() {
		return accepted3;
	}


	public void setAccepted3(Boolean accepted3) {
		this.accepted3 = accepted3;
	}


	public List<DetailReport> getDetailReports() {
		return detailReports;
	}


	public void setDetailReports(List<DetailReport> detailReports) {
		this.detailReports = detailReports;
	}


	public TypeReport getTypeReport() {
		return typeReport;
	}


	public void setTypeReport(TypeReport typeReport) {
		this.typeReport = typeReport;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}


    
    
  
}
