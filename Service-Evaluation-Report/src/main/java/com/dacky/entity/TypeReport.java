package com.dacky.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_report")
public class TypeReport extends AbtractEntity {
	@Column(name = "type_name")
	private String typeName;
	
	@Column(name = "enable")
	private Boolean enable=true;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "typeReport")
	private List<Report> reports;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "typeReport")
	private List<EvaluationCriteria> evaluationCriterias;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<EvaluationCriteria> getEvaluationCriterias() {
		return evaluationCriterias;
	}

	public void setEvaluationCriterias(List<EvaluationCriteria> evaluationCriterias) {
		this.evaluationCriterias = evaluationCriterias;
	}

	
	
}
