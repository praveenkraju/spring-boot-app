package com.uob.app.service;

import com.uob.app.dto.AppUserDTO;
import com.uob.app.dto.GlobalResponseDTO;
import com.uob.app.dto.UserDTO;
import com.uob.app.dto.UserLoginDTO;
import com.uob.app.dto.UserRespDTO;

public interface UserService {

	UserDTO authenticate(UserLoginDTO userLoginDTO);
	
	GlobalResponseDTO<UserRespDTO> createUser(AppUserDTO appUserDTO);
	
	GlobalResponseDTO<UserRespDTO> getUserDetails(String userID);
}
