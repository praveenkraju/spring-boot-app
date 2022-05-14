package com.uob.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userID;
	
	private String password;

}
