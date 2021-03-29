package qnu.cntt.dacky.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course_department")
public class CourseAndDepartment extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "courseAndDepartment")
	private List<ClaSs> classes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@Column(name = "enable")
	private Boolean enable;

	

	public List<ClaSs> getClasses() {
		return classes;
	}

	public void setClasses(List<ClaSs> classes) {
		this.classes = classes;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
