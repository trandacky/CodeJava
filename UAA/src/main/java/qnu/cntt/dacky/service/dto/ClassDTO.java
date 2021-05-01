package qnu.cntt.dacky.service.dto;

import java.util.UUID;

public class ClassDTO {

	private String className;
	private UUID courseAndDepartmentId;

	public ClassDTO() {
		super();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public UUID getCourseAndDepartmentId() {
		return courseAndDepartmentId;
	}

	public void setCourseAndDepartmentId(UUID courseAndDepartmentId) {
		this.courseAndDepartmentId = courseAndDepartmentId;
	}

	

}
