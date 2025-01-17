package qnu.cntt.dacky.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course extends AbstractAuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "course")
	@JsonIgnore
	private List<CourseAndDepartment> courseAndDepartment=new ArrayList<>();

	@Column(name = "course")
	private String course;

	@Column(name = "enable")
	private Boolean enable;

	
	public List<CourseAndDepartment> getCourseAndDepartment() {
		return courseAndDepartment;
	}

	public void setCourseAndDepartment(List<CourseAndDepartment> courseAndDepartment) {
		this.courseAndDepartment = courseAndDepartment;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
