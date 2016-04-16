package com.rootservice.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.FlightStatus;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class FlightSecondActionController extends AbstractController {

	private FlightService getFlightService;
	
	public void setFlightService(FlightService getFlightService){
		this.getFlightService = getFlightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		
		double cost = getRandomNumber(new Random(),100, 500);
		double crental = 20;
		double checkbagfee = 30;
		double airporttransptcost = 25;
		double parkingcostperday = 10;
		double mdwparkingcost = 0;
		double mdwtransportationcost = 0;
		double lgatransportationcost = 0;
		long drivingtimetomdw = 24*60;
		long estimatedtime = 200*60;
		long dringtimefromsja = 45*60;
		
		
		String origin = request.getParameter("origin");
		String destin = request.getParameter("destination");
		String noofpg = request.getParameter("noofpsgrs");
		String bagfee = request.getParameter("bagfee");
		String caryear = request.getParameter("caryear");
		String carmake = request.getParameter("carmake");
		String carmodel = request.getParameter("carmodel");
		double checkin = Double.parseDouble(request.getParameter("checkintime"));
		double landing = Double.parseDouble(request.getParameter("landingtime"));
		
		int tbsm = Integer.parseInt(request.getParameter("tbsm"));
		double eptc = Double.parseDouble(request.getParameter("eptc"));
		double atc = Double.parseDouble(request.getParameter("atc"));
		double checkedbagfee = Double.parseDouble(request.getParameter("checkedbagfee"));
		double pcpd = Double.parseDouble(request.getParameter("pcpd"));
		String twf = request.getParameter("twf");
		int tbsd = Integer.parseInt(request.getParameter("tbsd"));
		long n = (long)checkin*15*60;
		long m = (long)landing*15*60;
		
		long totaltime = drivingtimetomdw+estimatedtime+dringtimefromsja+n+m;
				
		String a = getFlightService.getDurationString(n);
		String b = getFlightService.getDurationString(m);
		
		String totaltimetext = getFlightService.getDurationString(totaltime);
		
		HistoryDetails history = new HistoryDetails();
		history.setOrigin(origin);
		history.setDestin(destin);
		history.setNumberofpassengers(Integer.parseInt(noofpg));
		history.setCheckintext(a);
		history.setLandingtext(b);
		history.setCaryear(caryear);
		history.setCarmake(carmake);
		history.setCarmodel(carmodel);
		
		history.setParkingcostperday(parkingcostperday);
		history.setAirporttransportationcost(airporttransptcost);
		history.setEptc(cost);
		history.setBagfee(Double.parseDouble(bagfee));
		history.setCheckedbagfee(checkbagfee);
		
		String drivingtimetomdwtext = getFlightService.getDurationString(drivingtimetomdw);
		history.setDrivingtimetomdwtext(drivingtimetomdwtext);
		String dringtimefromsjatext = getFlightService.getDurationString(dringtimefromsja);
		history.setDrivingtimefromsjctext(dringtimefromsjatext);
		String estimatedtimetext = getFlightService.getDurationString(estimatedtime);
		history.setFlyingtimetext(estimatedtimetext);
		history.setTotaltimetext(totaltimetext);
		double t = Integer.parseInt(noofpg) * cost;
		history.setTotalticketcosttext("$ "+t);
		history.setBaggagefeetext("$ "+history.getBagfee());
		history.setCarrentalcosttext("$ "+crental);
		history.setMdwtransportationcosttext("$ "+mdwtransportationcost);
		history.setMdwparkingcosttext("$ "+mdwparkingcost);
		history.setLgatransportationcost("$ "+lgatransportationcost);
		double totaltrip = t+history.getBagfee()+crental+mdwparkingcost+lgatransportationcost;
		history.setTotalonewaytripcost("$ "+totaltrip);
		
		HttpSession session = request.getSession();
		session.setAttribute("flightcosttimedetails", history);
		
		List<FlightStatus> flights = getFlightService.getFlightDetailsService(history);
		
		
		mav = new ModelAndView();
		mav.setViewName("flightsecondtiles");
		mav.addObject("history",history);
		mav.addObject("flights",flights);
		return mav;
	}
	private int getRandomNumber(Random r,int low, int high){
		return r.nextInt(high-low) + low;
	}
}
