package com.dacky.service.dto;

public class EvaluationCriteriaDTO {

	

	public EvaluationCriteriaDTO(Long id, String content, Integer maxScore, Boolean enable, String typeReport,
			Long parentId) {
		super();
		this.id = id;
		this.content = content;
		this.maxScore = maxScore;
		this.enable = enable;
		this.typeReport = typeReport;
		this.parentId = parentId;
	}

	public EvaluationCriteriaDTO() {
		super();
	}

	private Long id;

	private String content;

	private Integer maxScore;

	private Boolean enable;

	private String typeReport;

	private Long parentId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
