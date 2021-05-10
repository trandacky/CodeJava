package qnu.cntt.dacky.web.rest.dto;

import java.util.List;

public class ClassKhoaAndCountDTO {
	private List<ClassKhoaDTO> classKhoaDTOs;
	private long totalItems;
	public List<ClassKhoaDTO> getClassKhoaDTOs() {
		return classKhoaDTOs;
	}
	public void setClassKhoaDTOs(List<ClassKhoaDTO> classKhoaDTOs) {
		this.classKhoaDTOs = classKhoaDTOs;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	
	
}
