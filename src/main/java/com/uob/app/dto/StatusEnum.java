package com.uob.app.dto;

public enum StatusEnum {

	SUCCESS("SUCCESS"), FAIL("FAIL"), ERROR("ERROR"), INFO("INFO");
	
	private final String status;
	
	private StatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
