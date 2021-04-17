package qnu.cntt.dacky.service.dto;

import java.util.List;

public class UpdateDetailReportScore23DTO {

	
	public UpdateDetailReportScore23DTO() {
		super();
	}
	public UpdateDetailReportScore23DTO(Long id, int score,
			List<UpdateDetailReportScore23DTO> detailReportScore23DTOs) {
		super();
		this.id = id;
		this.score = score;
		this.detailReportScore23DTOs = detailReportScore23DTOs;
	}
	private Long id;
	private int score;
	private List<UpdateDetailReportScore23DTO> detailReportScore23DTOs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<UpdateDetailReportScore23DTO> getDetailReportScore23DTOs() {
		return detailReportScore23DTOs;
	}
	public void setDetailReportScore23DTOs(List<UpdateDetailReportScore23DTO> detailReportScore23DTOs) {
		this.detailReportScore23DTOs = detailReportScore23DTOs;
	}
	
	
	
	
}
