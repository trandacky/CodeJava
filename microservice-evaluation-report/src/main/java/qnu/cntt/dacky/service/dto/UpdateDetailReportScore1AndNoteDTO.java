package qnu.cntt.dacky.service.dto;

import java.util.List;

public class UpdateDetailReportScore1AndNoteDTO {


	public UpdateDetailReportScore1AndNoteDTO(Long id, int score1, String note,
			List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO) {
		super();
		this.id = id;
		this.score1 = score1;
		this.note = note;
		this.detailReportScore1AndNoteDTO = detailReportScore1AndNoteDTO;
	}

	public UpdateDetailReportScore1AndNoteDTO() {
		super();
	}

	private Long id;
	private int score1;
	private String note;
	private List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<UpdateDetailReportScore1AndNoteDTO> getDetailReportScore1AndNoteDTO() {
		return detailReportScore1AndNoteDTO;
	}

	public void setDetailReportScore1AndNoteDTO(List<UpdateDetailReportScore1AndNoteDTO> detailReportScore1AndNoteDTO) {
		this.detailReportScore1AndNoteDTO = detailReportScore1AndNoteDTO;
	}

	

}
