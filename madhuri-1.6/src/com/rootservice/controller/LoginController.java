package com.rootservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rootservice.bean.Profile;
import com.rootservice.command.LoginCommand;
import com.rootservice.service.LoginService;

@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController {

	private LoginService loginService;
	
	public void setLoginService(LoginService loginService){
		this.loginService = loginService;
	}
	public LoginController() {
		setCommandClass(LoginCommand.class);
		setCommandName("login");
		setSessionForm(true);
	}
	@Override
	protected Map<String,String> referenceData(HttpServletRequest request) throws Exception {
		Map<String,String> dataMap = new HashMap<String,String>();

		return dataMap;
	}
	@Override
	protected Object formBackingObject(HttpServletRequest request)throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginCommand lcommand = new LoginCommand();
		lcommand.setUsername(username);;
		lcommand.setPassword(password);;

		return lcommand;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command, BindException errors)throws Exception {
		ModelAndView mav = null;
		LoginCommand lc = (LoginCommand) command;
		Profile profile = loginService.login(lc);
		mav = new ModelAndView("login","status","Invalid");
		System.out.println("login");
		try{
			if(profile!=null){
				System.out.println("login success");
				mav.addObject("prfile", profile);
				HttpSession session = request.getSession();
				session.setAttribute("profile", profile);
				mav.setViewName("home");
			}else{
				System.out.println("login filed");
				errors.rejectValue("username", "login.message.error");
				
				mav.addObject("status", "Invalid username/password");
				mav.setViewName("redirect:login.htm");
			}
		}catch(Exception e){
			System.out.println("login exception");
			e.printStackTrace();
			errors.rejectValue("username", "login.message.error");
			
			mav.addObject("status", "Invalid username/password");
			mav.setViewName("redirect:login.htm");
		}
		return mav;
	}
}
