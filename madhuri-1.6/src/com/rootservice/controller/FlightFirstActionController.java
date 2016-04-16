package com.rootservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class FlightFirstActionController extends AbstractController {

	private FlightService getFlightService;
	
	public void setFlightService(FlightService getFlightService){
		this.getFlightService = getFlightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		String origin = request.getParameter("origin");
		String destin = request.getParameter("destination");
		String noofpg = request.getParameter("noofpsgrs");
		String bagfee = request.getParameter("bagfee");
		String checkin = request.getParameter("checkintime");
		String landing = request.getParameter("landingtime");
		
		HistoryDetails history = new HistoryDetails();
		history.setOrigin(origin);
		history.setDestin(destin);
		history.setNumberofpassengers(Integer.parseInt(noofpg));
		history.setCheckin(Double.parseDouble(checkin));
		history.setLanding(Double.parseDouble(landing));
		
		history.setParkingcostperday(20);
		history.setAirporttransportationcost(15);
		history.setEptc(320);
		history.setBagfee(10);
		history.setCheckedbagfee(20);
		
		
		mav = new ModelAndView();
		mav.setViewName("flightfirsttiles");
		mav.addObject("history",history);
		return mav;
	}
}
