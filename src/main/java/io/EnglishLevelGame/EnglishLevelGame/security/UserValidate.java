package io.EnglishLevelGame.EnglishLevelGame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserAuth;

@Component
public class UserValidate implements Validator{

	@Autowired
	private UserService userService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserAuth.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAuth user = (UserAuth) target;
		int usernameLength = user.getUsername().length();
		int passwordLength = user.getPassword().length();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		
		if(usernameLength < 4 || usernameLength > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		
		if(userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}
		
		if (passwordLength < 3 || passwordLength > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		
	}
	

}
