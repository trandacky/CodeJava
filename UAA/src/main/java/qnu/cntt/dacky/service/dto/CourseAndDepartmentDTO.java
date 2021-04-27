package qnu.cntt.dacky.service.dto;

import java.util.UUID;

public class CourseAndDepartmentDTO {

	private UUID courseId;
	private UUID departmentId;

	public CourseAndDepartmentDTO() {
		super();
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public UUID getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(UUID departmentId) {
		this.departmentId = departmentId;
	}

	
}
