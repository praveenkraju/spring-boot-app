package com.uob.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer userPk;
	
	private String userID;
	
	private String userName;
	
	private Integer age;
	
	private String phoneNo;
	
	private List<UserAddressDTO> addresses;
}
