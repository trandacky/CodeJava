package qnu.cntt.dacky.web.rest.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import qnu.cntt.dacky.domain.DetailReport;

public class InitDetailReportDTO {
	private long id;
	private String content;
	private boolean isChild=false;
	private int maxScore;
	private int score1;
	private int score2;
	private int score3;
	private String note;
	@JsonIgnore
	private Instant createdDate;
	
	public InitDetailReportDTO(DetailReport detailReport)
	{
		this.id=detailReport.getId();
		this.content=detailReport.getEvaluationCriteria().getContent();
		if(detailReport.getChildDetailReport().isEmpty())
		{
			this.isChild=true;
		}
		this.maxScore=detailReport.getEvaluationCriteria().getMaxScore();
		this.score1=detailReport.getScore1();
		this.score2=detailReport.getScore2();
		this.score3=detailReport.getScore3();
		this.note=detailReport.getNote();
		this.createdDate=detailReport.getEvaluationCriteria().getCreateDate();
	}
	
	
	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getIsChild() {
		return isChild;
	}
	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getScore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public int getScore3() {
		return score3;
	}
	public void setScore3(int score3) {
		this.score3 = score3;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
