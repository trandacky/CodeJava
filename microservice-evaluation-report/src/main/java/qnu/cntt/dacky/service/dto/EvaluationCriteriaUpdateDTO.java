package qnu.cntt.dacky.service.dto;

import java.util.List;

public class EvaluationCriteriaUpdateDTO {
	
	public EvaluationCriteriaUpdateDTO(Long id, String content, int maxScore,
			List<EvaluationCriteriaUpdateDTO> childEvaluationCriterias) {
		super();
		this.id = id;
		this.content = content;
		this.maxScore = maxScore;
		this.childEvaluationCriterias = childEvaluationCriterias;
	}
	
	private Long id;
	private String content;
	private int maxScore;
	private List<EvaluationCriteriaUpdateDTO> childEvaluationCriterias;
	
	
	
	public EvaluationCriteriaUpdateDTO() {
		super();
	}
	
	
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
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public List<EvaluationCriteriaUpdateDTO> getChildEvaluationCriterias() {
		return childEvaluationCriterias;
	}
	public void setChildEvaluationCriterias(List<EvaluationCriteriaUpdateDTO> childEvaluationCriterias) {
		this.childEvaluationCriterias = childEvaluationCriterias;
	}
	
	
}
