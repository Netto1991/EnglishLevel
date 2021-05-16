package io.EnglishLevelGame.EnglishLevelGame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserAuth;
import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public void save(UserAuth user) {
		if(user != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(ApplicationUserRole.STUDENT);
			userRepo.save(user);
		}
		
	}

	@Override
	public UserAuth findByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}

}
