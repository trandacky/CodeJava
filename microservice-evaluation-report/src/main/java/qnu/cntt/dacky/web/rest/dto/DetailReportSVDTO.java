package qnu.cntt.dacky.web.rest.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import qnu.cntt.dacky.domain.DetailReport;

public class DetailReportSVDTO {
	private long id;
	private String content;
	private boolean isChild = false;
	private int maxScore;
	private int score1;
	private int score2;
	private int score3;
	private String note;
	private Instant createdDate;
	private List<InitDetailReportDTO> detailReportDTOs= new ArrayList<>();

	public DetailReportSVDTO(DetailReport detailReport) {
		this.id = detailReport.getId();
		if (detailReport.getChildDetailReport().isEmpty()) {
			this.isChild = true;
		}
		else
		{
			for (DetailReport report : detailReport.getChildDetailReport()) {
				this.detailReportDTOs.add(initDetailReportDTO(report));
			}
		}
		this.content = detailReport.getEvaluationCriteria().getContent();
		this.maxScore = detailReport.getEvaluationCriteria().getMaxScore();
		this.score1 = detailReport.getScore1();
		this.score2 = detailReport.getScore2();
		this.score3 = detailReport.getScore3();
		this.note = detailReport.getNote();
		this.createdDate=detailReport.getEvaluationCriteria().getCreateDate();
	}

	private InitDetailReportDTO initDetailReportDTO(DetailReport detailReport) {
		InitDetailReportDTO detailReportDTO = new InitDetailReportDTO(detailReport);
		if (!detailReport.getChildDetailReport().isEmpty()) {
			for (DetailReport report : detailReport.getChildDetailReport()) {
				this.detailReportDTOs.add(initDetailReportDTO(report));
			}
		}
		return detailReportDTO;

	}

	public boolean getIsChild() {
		return isChild;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
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

	public List<InitDetailReportDTO> getDetailReportDTOs() {
		return detailReportDTOs;
	}

	public void setDetailReportDTOs(List<InitDetailReportDTO> detailReportDTOs) {
		this.detailReportDTOs = detailReportDTOs;
	}

}
