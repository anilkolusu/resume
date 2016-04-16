package com.rootservice.service;

import com.rootservice.command.RegisterCommand;
import com.rootservice.dao.RegisterDao;

public class RegisterService {

	private RegisterDao registerDao;
	
	public void setRegisterDao(RegisterDao registerDao){
		this.registerDao = registerDao;
	}
	public String register(RegisterCommand rc) {
		return registerDao.register(rc);
	}
}
