package com.uob.app.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalResponseDTO<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String EMPTY = "";
	private static final String DT_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	private String timestamp;
	private String status;
	private String message;
	private T data;
	
	public GlobalResponseDTO() {
		super();
	}
	
	public GlobalResponseDTO(T data) {
		this(data, StatusEnum.SUCCESS.getStatus(), EMPTY);
	}
	
	public GlobalResponseDTO(StatusEnum status, T data) {
		this(data, status.getStatus(), EMPTY);
	}

	public GlobalResponseDTO(T data, StatusEnum status, String message) {
		this(data, status.getStatus(), message);
	}

	public GlobalResponseDTO(T data, String status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
		this.timestamp = getTime();
	}
	
	private static String getTime() {
		return new SimpleDateFormat(DT_FORMAT_YYYYMMDDHHMMSS).format(Calendar.getInstance().getTime());
	}
	

}
