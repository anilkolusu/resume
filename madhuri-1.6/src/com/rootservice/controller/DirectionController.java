package com.rootservice.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.Bounds;
import com.rootservice.bean.CarDetails;
import com.rootservice.bean.Distance;
import com.rootservice.bean.Duration;
import com.rootservice.bean.EndLocation;
import com.rootservice.bean.GeocodedWaypoints;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.bean.Legs;
import com.rootservice.bean.Roots;
import com.rootservice.bean.StartLocation;
import com.rootservice.bean.Step;
import com.rootservice.properties.GetPropertyByKey;
import com.rootservice.service.GetCarInfoService;
import com.rootservice.service.GetDirectionJsonParserService;

public class DirectionController extends AbstractController {

	private GetDirectionJsonParserService getDirectionJsonParserService;
	private GetCarInfoService getCarInfoService;
	
	public void setGetDirectionJsonParserService(GetDirectionJsonParserService getDirectionJsonParserService){
		this.getDirectionJsonParserService = getDirectionJsonParserService;
	}
	public void setGetCarInfoService(GetCarInfoService getCarInfoService){
		this.getCarInfoService = getCarInfoService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		
		GetPropertyByKey getkey = new GetPropertyByKey();
		
		PrintWriter out = response.getWriter();
		String origin = request.getParameter("origin");
		String destin = request.getParameter("destin");
		String noofpsgrs = request.getParameter("noofpsgrs");
		int n = Integer.parseInt(noofpsgrs);
		String caryear = request.getParameter("caryear");
		String carmake = request.getParameter("carmake");
		String carmodel = request.getParameter("carmodel");
		int hours = Integer.parseInt(request.getParameter("hours"));
		double cost_per_litter = Double.parseDouble(getkey.getProperty("getcostindollers"));
		int hotelcost = Integer.parseInt(request.getParameter("hotelcost"));
		int tolls = Integer.parseInt(request.getParameter("tollscost"));
		try{
			
			int mileage = getCarInfoService.getCarMileageService(caryear,carmake,carmodel);
			String data = getDirectionJsonParserService.getDirectionsBySourceAndDestination(origin, destin);
			GeocodedWaypoints gwp = getDirectionJsonParserService.parse(data,mileage,cost_per_litter,n);
			
			
			//String place_id = gwp.getPlace_id();
			boolean status = gwp.getGeocoder_status();
			if(status){
				
				int wearandtear = Integer.parseInt(getDirectionJsonParserService.getWeareAndTear());
				//int tolls = 
						//Integer.parseInt(getDirectionJsonParserService.getTolls());
				
				/*HistoryDetails history = new HistoryDetails();
				history.setOrigin(origin);
				history.setDestin(destin);
				history.setNumberofpassengers(n);
				history.setCaryear(caryear);
				history.setCarmake(carmake);
				history.setCarmodel(carmodel);
				history.setHours(hours);
				boolean result = getDirectionJsonParserService.saveDetails(history);*/
				
				
				//gwp = getDirectionJsonParserService.setAddressToSteps(gwp);
				
				Roots roots = gwp.getRoots();
				Bounds bounds = roots.getBounds();
				System.out.println("---------------  Bounds -----------------------");
				StringBuffer boundrs = new StringBuffer();
				boundrs.append("{'nelat':'"+bounds.getNortheast_lat()+"','nelng':'"+bounds.getNortheast_lng()+"','swlat':'"+bounds.getSouthwest_lat()+"','swlat':'"+bounds.getSouthwest_lng()+"'}");
				
				Legs legs = roots.getLegs();
				System.out.println("------------------- -------------  Legs -------------");
				
				StringBuffer rootsdata = new StringBuffer();
				Distance distance = legs.getDistance();
				Duration duration = legs.getDuration();
				String sourcearray[] = legs.getStart_address().split(",");
				String destinarray[] = legs.getEnd_address().split(",");
				
				List<Step> listofsteps = legs.getSteps().getListofsteps();
				int h = (int)(duration.getDuration_value()/3600)/hours;
				int k = (int)(duration.getDuration_value()/3600)%hours;
				int days = 0;
				if(k>0){
					days = h+1;
				}else{
					days = h;
				}
				String d = h+" days ,"+k+" hours";
				
				listofsteps = getDirectionJsonParserService.reducedStepsService(listofsteps);
				
				listofsteps = getDirectionJsonParserService.setAddressToSteps(listofsteps);
				double totalcost = distance.getCost()+wearandtear+tolls+hotelcost;
				
				HttpSession session = request.getSession();
				CarDetails cardetails = new CarDetails();
				cardetails.setDistance(distance.getDistance_text()+" ( "+distance.getDistance_value()+" )");
				cardetails.setTime(duration.getDuration_text());
				cardetails.setDuration(""+duration.getDuration_value());
				cardetails.setTravel(d);
				cardetails.setTotalfuelcost(""+distance.getCost());
				cardetails.setWeartear(""+wearandtear);
				cardetails.setHotelcost(""+hotelcost);
				cardetails.setEstimatedtolls(""+tolls);
				cardetails.setTotalroundtripcost(""+totalcost);
				session.setAttribute("cardetails", cardetails);
				rootsdata.append("{'n':'"+n+"','st':'"+sourcearray[0]+"','et':'"+destinarray[0]+"','sst':'"+sourcearray[1]+","+sourcearray[2]+"','dst':'"+destinarray[1]+","+destinarray[2]+"','srt':'"+legs.getStart_address()+"','end':'"+legs.getEnd_address()+"','dis':'"+distance.getDistance_value()+"','dit':'"+distance.getDistance_text()+"','dur':'"+duration.getDuration_value()+"','dut':'"+duration.getDuration_text()+"','noofstops':'"+listofsteps.size()+"','cost':'"+distance.getCost()+"','days':'"+days+"','d':'"+d+"','caryear':'"+caryear+"','carmodel':'"+carmodel+"','carmake':'"+carmake+"','mileage':'"+mileage+"','wt':'"+wearandtear+"','tolls':'"+tolls+"','hotelcost':'"+hotelcost+"','totalcost':'"+totalcost+"'}");
				
				StringBuffer noofstops = new StringBuffer();
				int i = 1;
				for(Step step:listofsteps){
					
					StartLocation startlocation = step.getStart_location();
					EndLocation endlocation = step.getEnd_location();
					Distance stepDistance = step.getDistance();
					Duration stepDuration = step.getDuration();
					
					noofstops.append("{'sno':'"+i+"','srt':'"+startlocation.getAddress()+"','srtlat':'"+startlocation.getLatitude()+"','srtlng':'"+startlocation.getLongitude()+"','end':'"+endlocation.getAddress()+"','endlat':'"+startlocation.getLatitude()+"','endlng':'"+startlocation.getLongitude()+"','dis':'"+stepDistance.getDistance_value()+"','dit':'"+stepDistance.getDistance_text()+"','dur':'"+stepDuration.getDuration_value()+"','dut':'"+stepDuration.getDuration_text()+"','cost':'"+stepDistance.getCost()+"'},");
					i++;
				}
				out.println("{'s':'1','bounds':["+boundrs.toString()+"],'root':["+rootsdata+"],'noofstops':["+noofstops.toString()+"]}");
			}else{
				out.println("{'s':'0','message':'Sorry !'}");
			}
			
			return mav;
		}catch(Exception e){
			e.printStackTrace();
			out.println("{'s':'0','message':'Sorry !'}");
		}
		return mav;
	}
}
