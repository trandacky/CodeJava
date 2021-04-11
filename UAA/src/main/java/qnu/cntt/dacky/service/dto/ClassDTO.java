package qnu.cntt.dacky.service.dto;

public class ClassDTO {

	private String className;
	private long courseAndDepartmentId;

	public ClassDTO() {
		super();
	}

	public ClassDTO(String className, long courseAndDepartmentId) {
		super();
		this.className = className;
		this.courseAndDepartmentId = courseAndDepartmentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getCourseAndDepartmentId() {
		return courseAndDepartmentId;
	}

	public void setCourseAndDepartmentId(long courseAndDepartmentId) {
		this.courseAndDepartmentId = courseAndDepartmentId;
	}

}
