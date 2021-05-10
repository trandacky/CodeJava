package qnu.cntt.dacky.web.rest.dto;

import java.time.Instant;
import java.util.UUID;

public class ClassKhoaDTO {
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
	private String name;
	private int count=0;
	private int countReport=0;
	private int countAccepted3=0;
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCountReport() {
		return countReport;
	}
	public void setCountReport(int countReport) {
		this.countReport = countReport;
	}
	public int getCountAccepted3() {
		return countAccepted3;
	}
	public void setCountAccepted3(int countAccepted3) {
		this.countAccepted3 = countAccepted3;
	}
	
}
