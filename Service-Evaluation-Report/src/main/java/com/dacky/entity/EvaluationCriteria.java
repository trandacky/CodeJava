package com.dacky.entity;

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
@Table(name = "evaluation_criteria")
public class EvaluationCriteria extends AbtractEntity{

    @Column(name = "content")
    private String content;

    @Column(name = "max_score")
    private Integer maxScore;

    @Column(name = "enable")
    private Boolean enable=true;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "type_report_id", nullable = true)
    private TypeReport typeReport;
    
    @OneToMany(mappedBy = "evaluationCriteria", cascade = CascadeType.ALL)
    private List<DetailReport> detailReports;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private EvaluationCriteria parentEvaluationCriteria;
    
    @OneToMany(mappedBy = "parentEvaluationCriteria", cascade = CascadeType.ALL)
    private List<EvaluationCriteria> childEvaluationCriterias;

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

	public TypeReport getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(TypeReport typeReport) {
		this.typeReport = typeReport;
	}


	public List<DetailReport> getDetailReports() {
		return detailReports;
	}

	public void setDetailReports(List<DetailReport> detailReports) {
		this.detailReports = detailReports;
	}



	public EvaluationCriteria getParentEvaluationCriteria() {
		return parentEvaluationCriteria;
	}

	public void setParentEvaluationCriteria(EvaluationCriteria parentEvaluationCriteria) {
		this.parentEvaluationCriteria = parentEvaluationCriteria;
	}

	public List<EvaluationCriteria> getChildEvaluationCriterias() {
		return childEvaluationCriterias;
	}

	public void setChildEvaluationCriterias(List<EvaluationCriteria> childEvaluationCriterias) {
		this.childEvaluationCriterias = childEvaluationCriterias;
	}
    
    
    
    
    

}
