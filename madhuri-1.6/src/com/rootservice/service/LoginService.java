package com.rootservice.service;

import com.rootservice.bean.Profile;
import com.rootservice.command.LoginCommand;
import com.rootservice.dao.LoginDao;

public class LoginService {

	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao){
		this.loginDao = loginDao;
	}
	public Profile login(LoginCommand lc){
		return loginDao.login(lc);
	}
}
