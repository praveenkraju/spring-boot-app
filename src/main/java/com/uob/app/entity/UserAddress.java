package com.uob.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ADDRESS")
public class UserAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ADDR_PK")
	private Integer userAddrPk;
	
	@Column(name = "ADDR_TYPE")
	private String addressType;
	
	@Column(name = "FIRST_LINE")
	private String firstLine;
	
	@Column(name = "SECOND_LINE")
	private String secondLine;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "POSTCODE")
	private String postcode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_PK")
	private AppUser appUser;
	
}
