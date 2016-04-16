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
import com.rootservice.command.RegisterCommand;
import com.rootservice.service.RegisterService;

@SuppressWarnings("deprecation")
public class RegisterController extends SimpleFormController {

	private RegisterService registerService;
	
	public void setRegisterService(RegisterService registerService){
		this.registerService = registerService;
	}
	public RegisterController() {
		setCommandClass(RegisterCommand.class);
		setCommandName("register");
		setSessionForm(true);
	}
	@Override
	protected Map<String,String> referenceData(HttpServletRequest request) throws Exception {
		Map<String,String> dataMap = new HashMap<String,String>();

		return dataMap;
	}
	@Override
	protected Object formBackingObject(HttpServletRequest request)throws Exception {
		String fullname = request.getParameter("fullname");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		
		RegisterCommand rcommand = new RegisterCommand();
		rcommand.setEmailid(emailid);
		rcommand.setFullname(fullname);
		rcommand.setPassword(password);

		return rcommand;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command, BindException errors)throws Exception {
		ModelAndView mav = null;
		RegisterCommand rc = (RegisterCommand) command;
		String uuid = registerService.register(rc);
		mav = new ModelAndView();
		System.out.println(uuid);
		try{
			if(uuid!=null){
				Profile profile = new Profile(rc.getFullname(),rc.getEmailid(),uuid);
				HttpSession session = request.getSession();
				session.setAttribute("profile", profile);
				mav.setViewName("home");
			}else{
				mav.addObject("id", rc.getFullname());
				mav.setViewName("redirect:register.htm");
			}
		}catch(Exception e){
			mav.addObject("status","User already existed");
			mav.setViewName("redirect:register.htm");
		}
		return mav;
	}
}
