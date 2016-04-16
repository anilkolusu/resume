package com.rootservice.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.FlightStatus;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.FlightService;

public class FlightActionController extends AbstractController {

	private FlightService flightService;
	
	public void setFlightService(FlightService flightService){
		this.flightService = flightService;
	}
	public FlightService getFlightService(){
		return flightService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();

		String origin = request.getParameter("origin");
		String destin = request.getParameter("destin");
		String noofpg = request.getParameter("noofpsgrs");
		String caryear = request.getParameter("caryear");
		String carmake = request.getParameter("carmake");
		String carmodel = request.getParameter("carmodel");
		
		HistoryDetails history = new HistoryDetails();
		history.setOrigin(origin);
		history.setDestin(destin);
		history.setNumberofpassengers(Integer.parseInt(noofpg));
		history.setCaryear(caryear);
		history.setCarmake(carmake);
		history.setCarmodel(carmodel);
		
		List<FlightStatus> flight_details = flightService.getFlightDetailsService(history);
		if(flight_details != null && flight_details.size() != 0){
			String sourcearray[] = origin.split(",");
			String destinarray[] = destin.split(",");
			
			StringBuffer rootsdata = new StringBuffer();
			rootsdata.append("{'n':'"+noofpg+"','st':'"+sourcearray[0]+"','et':'"+destinarray[0]+"','sst':'"+sourcearray[1]+","+sourcearray[2]+"','dst':'"+destinarray[1]+","+destinarray[2]+"','srt':'"+origin+"','end':'"+destin+"','dis':'','dit':'','dur':'','dut':'','noofstops':'','cost':'','days':'','d':''}");
			StringBuffer flights = new StringBuffer();
			for(FlightStatus f:flight_details){
				flights.append("{'fid':'1','fname':'Flight Name','aname':'Airport Name','origin':'From Name','destin':'Destination','starttime':'Start Time','endtime':'End Time'},");
			}
			out.println("{'s':'1','flights':["+flights.toString()+"],'root':["+rootsdata+"]}");
		}else{
			out.println("{'s':'0','message':''}");
		}
		return null;
	}
}
