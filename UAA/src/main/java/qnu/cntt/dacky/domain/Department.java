package qnu.cntt.dacky.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<CourseAndDepartment> courseAndDepartments= new ArrayList<>();

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "enable")
	private Boolean enable;
	
	@OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AccountDepartment> accountDepartments;
	

	public List<CourseAndDepartment> getCourseAndDepartments() {
		return courseAndDepartments;
	}

	public void setCourseAndDepartments(List<CourseAndDepartment> courseAndDepartments) {
		this.courseAndDepartments = courseAndDepartments;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<AccountDepartment> getAccountDepartments() {
		return accountDepartments;
	}

	public void setAccountDepartments(List<AccountDepartment> accountDepartments) {
		this.accountDepartments = accountDepartments;
	}
	
}
