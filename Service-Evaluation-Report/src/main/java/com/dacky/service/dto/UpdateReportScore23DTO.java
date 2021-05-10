package com.dacky.service.dto;

import java.util.List;

public class UpdateReportScore23DTO {

	public UpdateReportScore23DTO(Long id, List<UpdateDetailReportScore23DTO> detailReportScore23DTOs) {
		super();
		this.id = id;
		this.detailReportScore23DTOs = detailReportScore23DTOs;
	}

	public UpdateReportScore23DTO() {
		super();
	}

	private Long id;
	private List<UpdateDetailReportScore23DTO> detailReportScore23DTOs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UpdateDetailReportScore23DTO> getDetailReportScore23DTOs() {
		return detailReportScore23DTOs;
	}

	public void setDetailReportScore23DTOs(List<UpdateDetailReportScore23DTO> detailReportScore23DTOs) {
		this.detailReportScore23DTOs = detailReportScore23DTOs;
	}

}
