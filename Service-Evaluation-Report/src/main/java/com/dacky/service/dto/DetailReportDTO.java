package com.dacky.service.dto;

public class DetailReportDTO {

	public DetailReportDTO(Long id, Integer score1, Integer score2, Integer score3, String note, Long idReport,
			Long id_evaluation_criteria, Long parentIdDetailReport) {
		super();
		this.id = id;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		this.note = note;
		this.idReport = idReport;
		this.id_evaluation_criteria = id_evaluation_criteria;
		this.parentIdDetailReport = parentIdDetailReport;
	}

	public DetailReportDTO() {
		super();
	}

	private Long id;

	private Integer score1 = 0;

	private Integer score2 = 0;

	private Integer score3 = 0;

	private String note;

	private Long idReport;

	private Long id_evaluation_criteria;

	private Long parentIdDetailReport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public Integer getScore3() {
		return score3;
	}

	public void setScore3(Integer score3) {
		this.score3 = score3;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getIdReport() {
		return idReport;
	}

	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}

	public Long getId_evaluation_criteria() {
		return id_evaluation_criteria;
	}

	public void setId_evaluation_criteria(Long id_evaluation_criteria) {
		this.id_evaluation_criteria = id_evaluation_criteria;
	}

	public Long getParentIdDetailReport() {
		return parentIdDetailReport;
	}

	public void setParentIdDetailReport(Long parentIdDetailReport) {
		this.parentIdDetailReport = parentIdDetailReport;
	}

	
}
