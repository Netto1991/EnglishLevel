package io.EnglishLevelGame.EnglishLevelGame.security;

import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserAuth;

public interface UserService {
	void save(UserAuth user);
	
	UserAuth findByUsername(String username);
	
}
