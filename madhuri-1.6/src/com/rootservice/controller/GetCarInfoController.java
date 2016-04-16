package com.rootservice.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.service.GetCarInfoService;

public class GetCarInfoController extends AbstractController {

	private GetCarInfoService getCarInfoService;
	
	public void setGetCarInfoService(GetCarInfoService getCarInfoService){
		this.getCarInfoService = getCarInfoService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		PrintWriter out = response.getWriter();
		String cyear = request.getParameter("year");
		String cmake = request.getParameter("make");
		List<String> listof_makes = null;
		try{
			if(cyear!=null && cmake!=null){
				listof_makes = getCarInfoService.getCarModelInfoService(cyear,cmake);
			}else{
				if(cyear!=null){
					listof_makes = getCarInfoService.getCarMakeInfoService(cyear);
				}else{
					if(cmake!=null){
						listof_makes = getCarInfoService.getCarModelInfoService(cmake);
					}
				}
			}
			StringBuffer sb = new StringBuffer();
			for(String make:listof_makes){
				sb.append("{'make':'"+make+"'},");
			}
			out.println("{'s':'1','makes':["+sb.toString()+"]}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
}
