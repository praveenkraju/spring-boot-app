package com.uob.app.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uob.app.dto.AppUserDTO;
import com.uob.app.dto.GlobalResponseDTO;
import com.uob.app.dto.StatusEnum;
import com.uob.app.dto.UserDTO;
import com.uob.app.dto.UserLoginDTO;
import com.uob.app.dto.UserRespDTO;
import com.uob.app.entity.AppUser;
import com.uob.app.mapper.UserMapper;
import com.uob.app.repository.UserRepository;
import com.uob.app.security.JwtTokenProvider;
import com.uob.app.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepo;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO authenticate(UserLoginDTO userLoginDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginDTO.getUserID(), userLoginDTO.getPassword()));
		String token = jwtTokenProvider.generateToken(userLoginDTO.getUserID());
		return new UserDTO(userLoginDTO.getUserID(), token);
	}
	
	@Override
	public GlobalResponseDTO<UserRespDTO> createUser(AppUserDTO appUserDTO) {
		try {
			appUserDTO.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
			AppUser user = userMapper.userDTOToUser(appUserDTO);
			if (user.getAddresses() != null) {
				user.getAddresses().forEach(address -> address.setAppUser(user));
			}
			return new GlobalResponseDTO<>(userMapper.userToUserRespDTO(userRepo.save(user)));
		} catch (Exception e) {
			log.error("", e);
			return new GlobalResponseDTO<>(null, StatusEnum.FAIL, "Error while persisting user: " + e.getMessage());
		}
	}

	@Override
	public GlobalResponseDTO<UserRespDTO> getUserDetails(String userID) {
		log.info("Fetch User Details: {}", userID);
		AppUser user = userRepo.fetchUserDetailsByID(userID);
		if (user == null) {
			return new GlobalResponseDTO<>(null, StatusEnum.ERROR, "User not found");
		}
		return new GlobalResponseDTO<>(userMapper.userToUserRespDTO(user));
	}
}
