package qnu.cntt.dacky.service.dto;

import java.util.UUID;

public class ClassDTOUpdate {
	private String className;
	private UUID uuid;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
