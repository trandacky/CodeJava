package qnu.cntt.dacky.service.dto;

import java.time.Instant;
import java.util.UUID;

import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;

public class CourseAndDepartmentReturnDTO {
	private UUID uuid;
	private UUID courseUUID;
	private UUID departmentUUID;
	private String course;
	private String departmentName;
	private Instant createdDate;
	private Instant updateDate;
	private boolean enable;
	private String createBy;
	private String updateBy;
	
	public CourseAndDepartmentReturnDTO(CourseAndDepartment courseAndDepartment) {
		super();
		this.uuid = courseAndDepartment.getUUID();
		this.course = courseAndDepartment.getCourse().getCourse();
		this.departmentName = courseAndDepartment.getDepartment().getDepartmentName();
		this.createdDate = courseAndDepartment.getCreatedDate();
		this.updateDate = courseAndDepartment.getUpdateDate();
		this.enable = courseAndDepartment.getEnable();
		this.createBy = courseAndDepartment.getCreatedBy();
		this.updateBy = courseAndDepartment.getUpdateBy();
		this.courseUUID = courseAndDepartment.getCourse().getUUID();
		this.departmentUUID= courseAndDepartment.getCourse().getUUID();
	}
	

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public UUID getCourseUUID() {
		return courseUUID;
	}

	public void setCourseUUID(UUID courseUUID) {
		this.courseUUID = courseUUID;
	}

	public UUID getDepartmentUUID() {
		return departmentUUID;
	}

	public void setDepartmentUUID(UUID departmentUUID) {
		this.departmentUUID = departmentUUID;
	}
	
}
