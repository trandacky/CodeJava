package qnu.cntt.dacky.service.dto;

public class EvaluationCriteriaUpdateDTO {
	
	public EvaluationCriteriaUpdateDTO(Long id, String content, int maxScore) {
		super();
		this.id = id;
		this.content = content;
		this.maxScore = maxScore;
	}
	
	private Long id;
	private String content;
	private int maxScore;
	
	
	
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
	
	
}
