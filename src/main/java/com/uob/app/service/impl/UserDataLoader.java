package com.uob.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.uob.app.dto.AppUserDTO;
import com.uob.app.dto.UserAddressDTO;
import com.uob.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserDataLoader implements ApplicationRunner {

	private final UserService userService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		AppUserDTO user = new AppUserDTO();
		user.setUserID("APPUSER01");
		user.setUserName("App User 1");
		user.setPassword("password");
		user.setAge(30);
		user.setPhoneNo("+6031242323");
		
		UserAddressDTO addr1 = new UserAddressDTO();
		addr1.setAddressType("HOME");
		addr1.setFirstLine("162, Avantas Residences");
		addr1.setSecondLine("Jalan Klang Lama");
		addr1.setState("Kuala Lumpur");
		addr1.setCountry("MYS");
		addr1.setPostcode("58100");

		UserAddressDTO addr2 = new UserAddressDTO();
		addr2.setAddressType("OFFICE");
		addr2.setFirstLine("Menara Maybank");
		addr2.setSecondLine("100, Jalan Tun Perak");
		addr2.setState("Kuala Lumpur");
		addr2.setCountry("MYS");
		addr2.setPostcode("50050");
		
		List<UserAddressDTO> addresses = new ArrayList<>();
		addresses.add(addr1);
		addresses.add(addr2);
		
		user.setAddresses(addresses);
		
		userService.createUser(user);
		
		AppUserDTO user2 = new AppUserDTO();
		user2.setUserID("APPUSER02");
		user2.setUserName("App User 2");
		user2.setPassword("password");
		user2.setAge(35);
		user2.setPhoneNo("+60128243434");
		
		UserAddressDTO addr3 = new UserAddressDTO();
		addr3.setAddressType("HOME");
		addr3.setFirstLine("Pantai HillPark");
		addr3.setSecondLine("Jalan Pantai Murni");
		addr3.setState("Kuala Lumpur");
		addr3.setCountry("MYS");
		addr3.setPostcode("59200");
		
		List<UserAddressDTO> addresses2 = new ArrayList<>();
		addresses2.add(addr3);
		user2.setAddresses(addresses2);
		
		userService.createUser(user2);
	}
}
