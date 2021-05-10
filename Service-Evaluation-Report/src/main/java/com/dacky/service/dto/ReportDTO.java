package com.dacky.service.dto;


public class ReportDTO {
	
	public ReportDTO(Long id, Integer year, Integer semester, Integer totalScore1, Integer totalScore2,
			Integer totalScore3, Boolean accepted2, Boolean accepted3, String username, Long classId) {
		super();
		this.id = id;
		this.year = year;
		this.semester = semester;
		this.totalScore1 = totalScore1;
		this.totalScore2 = totalScore2;
		this.totalScore3 = totalScore3;
		this.accepted2 = accepted2;
		this.accepted3 = accepted3;
		this.username = username;
		this.classId = classId;
	}

	private Long id;

	private Integer year;

	private Integer semester;

	private Integer totalScore1 = 0;

	private Integer totalScore2 = 0;

	private Integer totalScore3 = 0;

	private Boolean accepted2 = false;

	private Boolean accepted3 = false;
	
	private String username;
	
	private Long classId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
