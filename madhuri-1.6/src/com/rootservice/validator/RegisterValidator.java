package com.rootservice.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rootservice.command.RegisterCommand;

public class RegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> classType) {
		if (classType.isAssignableFrom(RegisterCommand.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {
		RegisterCommand lc = (RegisterCommand) command;

		if (lc.getFullname() == null || lc.getFullname().equals("")) {
			errors.reject("register.fullname.blank");
		}
		else
		if (lc.getEmailid() == null || lc.getEmailid().equals("")) {
			errors.reject("register.emailid.blank");
		}
		else
		if (lc.getPassword() == null || lc.getPassword().equals("")) {
			errors.reject("register.password.blank");
		}
		else
		if (lc.getRepassword() == null || lc.getRepassword().equals("")) {
			errors.reject("register.repassword.blank");
		}
	}

}

