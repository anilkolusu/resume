package com.rootservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class FlightController extends AbstractController {

	private FlightService getFlightService;
	
	public void setFlightService(FlightService getFlightService){
		this.getFlightService = getFlightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		String origin = request.getParameter("origin");
		String destin = request.getParameter("destin");
		String noofpg = request.getParameter("noofps");
		
		HistoryDetails history = new HistoryDetails();
		history.setOrigin(origin);
		history.setDestin(destin);
		history.setNumberofpassengers(Integer.parseInt(noofpg));
		mav = new ModelAndView();
		mav.setViewName("flighttiles");
		mav.addObject("history",history);
		return mav;
	}
}
