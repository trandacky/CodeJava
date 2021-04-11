package qnu.cntt.dacky.service.dto;

public class CourseDTO {

	private String course;

	public CourseDTO() {
		super();
	}

	public CourseDTO(String course) {
		super();
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
