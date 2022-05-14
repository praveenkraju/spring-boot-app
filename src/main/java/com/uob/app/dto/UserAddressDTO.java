package com.uob.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userAddrPk;
	
	private Integer userPk;
	
	private String addressType;
	
	private String firstLine;
	
	private String secondLine;
	
	private String state;
	
	private String country;
	
	private String postcode;
	
}
