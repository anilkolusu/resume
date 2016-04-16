package com.rootservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.CarDetails;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class FlightCarCompareController extends AbstractController {

	private FlightService getFlightService;
	
	public void setFlightService(FlightService getFlightService){
		this.getFlightService = getFlightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		
		
		
		HttpSession session = request.getSession();
		HistoryDetails history = (HistoryDetails)session.getAttribute("flightcosttimedetails");
		
		CarDetails cardetails = (CarDetails)session.getAttribute("cardetails");
		boolean result1 = getFlightService.saveDetails(history,cardetails);
		mav = new ModelAndView();
		mav.addObject("history", history);
		mav.addObject("cardetails", cardetails);
		mav.setViewName("comparetiles");

		return mav;
	}
}
