package qnu.cntt.dacky.web.rest.dto;

import java.util.UUID;

public class CreateReportDTO {
	private UUID classuuid;
	private int semester;
	private int year;
	private Long typeReportId;
	
	public UUID getClassuuid() {
		return classuuid;
	}
	public void setClassuuid(UUID classuuid) {
		this.classuuid = classuuid;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Long getTypeReportId() {
		return typeReportId;
	}
	public void setTypeReportId(Long typeReportId) {
		this.typeReportId = typeReportId;
	}
	
}
