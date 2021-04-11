package qnu.cntt.dacky.service.dto;

public class CourseAndDepartmentDTO {

	private long courseId;
	private long departmentId;

	public CourseAndDepartmentDTO() {
		super();
	}

	public CourseAndDepartmentDTO(long courseId, long departmentId) {
		super();
		this.courseId = courseId;
		this.departmentId = departmentId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

}
