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
@Table(name = "class")
public class ClaSs extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "class1")
	private List<Account> accounts = new ArrayList<>();

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "course_department_id")
	private CourseAndDepartment courseAndDepartment;

	@Column(name = "enable")
	private Boolean enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseAndDepartment getCourseAndDepartment() {
		return courseAndDepartment;
	}

	public void setCourseAndDepartment(CourseAndDepartment courseAndDepartment) {
		this.courseAndDepartment = courseAndDepartment;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
 
	
	
}
