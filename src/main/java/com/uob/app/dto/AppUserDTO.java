package com.uob.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer userPk;
	
	private String userID;
	
	private String password;
	
	private String userName;
	
	private Integer age;
	
	private String phoneNo;
	
	private List<UserAddressDTO> addresses;
}
