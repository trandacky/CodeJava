package qnu.cntt.dacky.domain;

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
@Table(name = "detail_report")
public class DetailReport extends AbtractEntity {

	@Column(name = "score1")
	private Integer score1 = 0;

	@Column(name = "score2")
	private Integer score2 = 0;

	@Column(name = "score3")
	private Integer score3 = 0;

	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "report_id", nullable = false)
	private Report report;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "evaluation_criteria_id", nullable = false)
	private EvaluationCriteria evaluationCriteria;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private DetailReport parentDetailReport;

	@OneToMany(mappedBy = "parentDetailReport", cascade = CascadeType.ALL)
	private List<DetailReport> childDetailReport;

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

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public EvaluationCriteria getEvaluationCriteria() {
		return evaluationCriteria;
	}

	public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
		this.evaluationCriteria = evaluationCriteria;
	}

	public DetailReport getParentDetailReport() {
		return parentDetailReport;
	}

	public void setParentDetailReport(DetailReport parentDetailReport) {
		this.parentDetailReport = parentDetailReport;
	}

	public List<DetailReport> getChildDetailReport() {
		return childDetailReport;
	}

	public void setChildDetailReport(List<DetailReport> childDetailReport) {
		this.childDetailReport = childDetailReport;
	}
	
	

}
