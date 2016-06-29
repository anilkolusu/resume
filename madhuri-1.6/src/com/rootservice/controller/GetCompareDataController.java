package com.rootservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.CarDetails;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class GetCompareDataController extends AbstractController {

	private FlightService getFlightService;
	
	public void setFlightService(FlightService getFlightService){
		this.getFlightService = getFlightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		String historyid = request.getParameter("hid");
		HistoryDetails history = getFlightService.getFlightComapreDetails(historyid);
		
		CarDetails cardetails = getFlightService.getCarComapreDetails(historyid);
	
		mav = new ModelAndView();
		mav.addObject("history", history);
		mav.addObject("cardetails", cardetails);
		mav.setViewName("comparetiles");

		return mav;
	}
}
