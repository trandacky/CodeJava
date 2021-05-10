package qnu.cntt.dacky.service.dto;

import java.util.List;

public class ClassAndCountCommunicateDTO {
	private List<ClassReturnDTO> classKhoaDTOs;
	private long totalItems;
	public List<ClassReturnDTO> getClassKhoaDTOs() {
		return classKhoaDTOs;
	}
	public void setClassKhoaDTOs(List<ClassReturnDTO> classReturnDTOs) {
		this.classKhoaDTOs = classReturnDTOs;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	
	
}
