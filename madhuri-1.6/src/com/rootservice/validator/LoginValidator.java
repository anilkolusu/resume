package com.rootservice.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rootservice.command.LoginCommand;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> classType) {
		if (classType.isAssignableFrom(LoginCommand.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {
		LoginCommand lc = (LoginCommand) command;

		if (lc.getUsername() == null || lc.getUsername().equals("")) {
			errors.reject("login.username.blank");
		}
		else
		if (lc.getPassword() == null || lc.getPassword().equals("")) {
			errors.reject("login.password.blank");
		}
	}

}

