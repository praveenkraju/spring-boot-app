package com.uob.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USERS")
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_PK")
	private Integer userPk;
	
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userID;
	
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "PASSWORD", unique = true, nullable = false)
	private String password;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "USER_PK", referencedColumnName = "USER_PK")
	private List<UserAddress> addresses;
	
}
