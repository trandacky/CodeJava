package qnu.cntt.dacky.service.dto;

public class DepartmentDTO {

	private String departmentName;

	public DepartmentDTO() {
		super();
	}

	public DepartmentDTO(String departmentName) {
		super();
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
