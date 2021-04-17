package qnu.cntt.dacky.service.dto;

public class NewDetailReportDTO {

	/* detail report need this info */

	private Long parentId;
	private Long reportId;
	private Long evaluationCriteriaId;

	public NewDetailReportDTO(Long parentId, Long reportId, Long evaluationCriteriaId) {
		super();
		this.parentId = parentId;
		this.reportId = reportId;
		this.evaluationCriteriaId = evaluationCriteriaId;
	}

	public NewDetailReportDTO() {
		super();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getEvaluationCriteriaId() {
		return evaluationCriteriaId;
	}

	public void setEvaluationCriteriaId(Long evaluationCriteriaId) {
		this.evaluationCriteriaId = evaluationCriteriaId;
	}

}
