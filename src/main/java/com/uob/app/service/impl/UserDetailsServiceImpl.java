package com.uob.app.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uob.app.entity.AppUser;
import com.uob.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		AppUser user = userRepo.findByUserID(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User '%s' not found", username));
		}
		return new User(username, user.getPassword(), new ArrayList<>());
	}


}
