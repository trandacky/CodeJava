package qnu.cntt.dacky.web.rest.dto;

import java.util.Calendar;
import java.util.UUID;

public class PageYearSemester {
	private int page=0;
	private int year=Calendar.getInstance().get(Calendar.YEAR);
	private int semester=1;
	private UUID classUUID;
	private String typeReportId="";
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public UUID getClassUUID() {
		return classUUID;
	}
	public void setClassUUID(UUID classUUID) {
		this.classUUID = classUUID;
	}
	public String getTypeReportId() {
		return typeReportId;
	}
	public void setTypeReportId(String typeReportId) {
		this.typeReportId = typeReportId;
	}
	
	
	
}
