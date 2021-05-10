package com.dacky.service.dto;

public class TypeReportDTO {
	
	private Long id;
	
	private boolean enable;
	
	private String typeName;
	
	public TypeReportDTO() {
		super();
	}

	public TypeReportDTO(Long id, boolean enable, String typeName) {
		super();
		this.id = id;
		this.enable = enable;
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
}
