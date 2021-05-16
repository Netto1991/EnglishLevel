package io.EnglishLevelGame.EnglishLevelGame.security;

public interface SecurityLoginService {
		String findLoginUsername();
		
		void autoLogin(String username, String password);
}
