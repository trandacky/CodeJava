package com.dacky.service.dto;

import java.util.List;

public class UpdateReportScore1DTO {

	public UpdateReportScore1DTO(Long id, List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO) {
		super();
		this.id = id;
		this.detailReportScore1AndNoteDTO = detailReportScore1AndNoteDTO;
	}

	public UpdateReportScore1DTO() {
		super();
	}

	private Long id;
	private List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UpdateDetailReportScore1AndNoteDTO> getDetailReportScore1AndNoteDTO() {
		return detailReportScore1AndNoteDTO;
	}

	public void setDetailReportScore1AndNoteDTO(List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO) {
		this.detailReportScore1AndNoteDTO = detailReportScore1AndNoteDTO;
	}

}
