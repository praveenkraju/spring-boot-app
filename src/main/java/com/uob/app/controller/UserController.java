package com.uob.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.app.dto.AppUserDTO;
import com.uob.app.dto.GlobalResponseDTO;
import com.uob.app.dto.StatusEnum;
import com.uob.app.dto.UserDTO;
import com.uob.app.dto.UserLoginDTO;
import com.uob.app.dto.UserRespDTO;
import com.uob.app.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<UserDTO> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
		log.info("Authenticating: {}", userLoginDTO.getUserID());
		UserDTO userDTO = userService.authenticate(userLoginDTO);
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, userDTO.getJwt()).body(userDTO);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<GlobalResponseDTO<UserRespDTO>> signup(@RequestBody AppUserDTO appUserDTO) {
		log.info("Creating a user");
		GlobalResponseDTO<UserRespDTO> userRespDTO = userService.createUser(appUserDTO);
		if (!userRespDTO.getStatus().equals(StatusEnum.SUCCESS.getStatus())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userRespDTO);
		}
		return ResponseEntity.ok(userRespDTO);
	}
	
	@GetMapping("/users/{userID}")
	public ResponseEntity<GlobalResponseDTO<UserRespDTO>> getUserDetails(@PathVariable String userID) {
		log.info("GET user details: {}", userID);
		GlobalResponseDTO<UserRespDTO> userRespDTO = userService.getUserDetails(userID);
		if (!userRespDTO.getStatus().equals(StatusEnum.SUCCESS.getStatus())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userRespDTO);
		}
		return ResponseEntity.ok(userRespDTO);
	}
}
