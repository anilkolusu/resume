package com.rootservice.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





//import com.google.android.gms.maps.model.LatLng;
import com.rootservice.bean.Bounds;
import com.rootservice.bean.Distance;
import com.rootservice.bean.Duration;
import com.rootservice.bean.EndLocation;
import com.rootservice.bean.GeocodedWaypoints;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.bean.Legs;
import com.rootservice.bean.Roots;
import com.rootservice.bean.StartLocation;
import com.rootservice.bean.Step;
import com.rootservice.bean.Steps;
import com.rootservice.dao.HistoryDetailsDao;
import com.rootservice.properties.GetPropertyByKey;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class GetDirectionJsonParserService {
	/** Receives a JSONObject and returns a list of lists containing latitude and longitude 
	 * @throws JSONException */
	
	private GetPropertyByKey getkey = new GetPropertyByKey();
	
	private HistoryDetailsDao historyDao;
	
	
	public HistoryDetailsDao getHistoryDao() {
		return historyDao;
	}
	public void setHistoryDao(HistoryDetailsDao historyDao) {
		this.historyDao = historyDao;
	}
	public boolean saveDetails(HistoryDetails history){
		return historyDao.saveDetails(history);
	}
	public GeocodedWaypoints parse(String data,int mileage,double cost_per_litter,int noofpsgrs) throws JSONException{

		GeocodedWaypoints gwp = new GeocodedWaypoints();
		
		JSONObject obj = new JSONObject(data);
		
		JSONArray jGeocoder = null;
		JSONArray jRoutes = null;
		//JSONArray jBounds = null;
		JSONArray jLegs = null;
		JSONArray jSteps = null;
		double miles = 0;
		double kilomiters = 0;
		long miters = 0;
		double cost = 0;
		double totalcost = 0;
		try{
			/** Geocoder Waypoints status */
			jGeocoder = obj.getJSONArray("geocoded_waypoints");
			String status = (String)((JSONObject)jGeocoder.get(0)).get("geocoder_status");
			String place_id = (String)((JSONObject)jGeocoder.get(0)).get("place_id");
			if(status.equals("OK")){
				
				gwp.setGeocoder_status(true);
				gwp.setPlace_id(place_id);
				
				jRoutes = obj.getJSONArray("routes");
				/** Traversing all routes */
				Roots roots = new Roots();
				for(int i=0;i<jRoutes.length();i++){
					
					Bounds bounds = new Bounds();
					JSONObject g = (JSONObject)((JSONObject)jRoutes.get(i)).get("bounds");
					
					double north_east_lat = (Double)((JSONObject)g.get("northeast")).get("lat");
					bounds.setNortheast_lat(north_east_lat);
					
					double north_east_lng = (Double)((JSONObject)g.get("northeast")).get("lng");
					bounds.setNortheast_lng(north_east_lng);
					
					double south_west_lat = (Double)((JSONObject)g.get("southwest")).get("lat");
					bounds.setSouthwest_lat(south_west_lat);
					
					double south_west_lng = (Double)((JSONObject)g.get("southwest")).get("lng");
					bounds.setSouthwest_lng(south_west_lng);
					
					roots.setBounds(bounds);
					
					Legs legs = new Legs();
					jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
					for(int j=0;j<jLegs.length();j++){
						Distance distance = new Distance();
						JSONObject distance_json = (JSONObject)((JSONObject)jLegs.get(j)).get("distance");
						distance.setDistance_text(distance_json.getString("text"));
						distance.setDistance_value(distance_json.getLong("value"));
						
						miters = distance.getDistance_value();
						kilomiters = miters/1000;
						miles = kilomiters/1.5;
						
						cost = (miles / mileage) * cost_per_litter;
						cost = Math.round(cost);
						if(cost == 0){
							cost = 1 * noofpsgrs;
						}else{
							cost = cost * noofpsgrs;
						}
						distance.setCost(cost);
						legs.setDistance(distance);
						
						Duration duration = new Duration();
						JSONObject duration_json = (JSONObject)((JSONObject)jLegs.get(j)).get("duration");
						duration.setDuration_text(duration_json.getString("text"));
						duration.setDuration_value(duration_json.getLong("value"));
						legs.setDuration(duration);
						
						String end_address = (String)((JSONObject)jLegs.get(j)).get("end_address");
						legs.setEnd_address(end_address);
						
						EndLocation endLocation = new EndLocation();
						JSONObject endlocation_json = (JSONObject)((JSONObject)jLegs.get(j)).get("end_location");
						endLocation.setLatitude(endlocation_json.getDouble("lat"));
						endLocation.setLongitude(endlocation_json.getDouble("lng"));
						legs.setEndlocation(endLocation);
						
						String start_address = (String)((JSONObject)jLegs.get(j)).get("start_address");
						legs.setStart_address(start_address);
						
						StartLocation startLocation = new StartLocation();
						JSONObject startLocation_json = (JSONObject)((JSONObject)jLegs.get(j)).get("end_location");
						startLocation.setLatitude(startLocation_json.getDouble("lat"));
						startLocation.setLongitude(startLocation_json.getDouble("lng"));
						legs.setStartlocation(startLocation);
						
						jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");
						
						Steps steps = new Steps();
						List<Step> listofsteps = new ArrayList<Step>();
						for(int k=0;k<jSteps.length();k++){
							Step step = new Step();
							
							Distance stepdistance = new Distance();
							JSONObject step_distance_json = (JSONObject)((JSONObject)jSteps.get(k)).get("distance");
							stepdistance.setDistance_text(step_distance_json.getString("text"));
							stepdistance.setDistance_value(step_distance_json.getLong("value"));
							
							miters = stepdistance.getDistance_value();
							kilomiters = miters/1000;
							miles = kilomiters/1.5;
							
							cost = (miles / mileage) * cost_per_litter;
							cost = Math.round(cost);
							if(cost == 0){
								cost = 1 * noofpsgrs;
							}else{
								cost = cost * noofpsgrs;
							}
							totalcost = totalcost + cost;
							stepdistance.setCost(cost);
							
							step.setDistance(stepdistance);
							
							Duration stepduration = new Duration();
							JSONObject step_duration_json = (JSONObject)((JSONObject)jSteps.get(k)).get("duration");
							stepduration.setDuration_text(step_duration_json.getString("text"));
							stepduration.setDuration_value(step_duration_json.getLong("value"));
							step.setDuration(stepduration);
							
							EndLocation stepEndLocation = new EndLocation();
							JSONObject step_endlocation_json = (JSONObject)((JSONObject)jSteps.get(k)).get("end_location");
							stepEndLocation.setLatitude(step_endlocation_json.getDouble("lat"));
							stepEndLocation.setLongitude(step_endlocation_json.getDouble("lng"));
							step.setEnd_location(stepEndLocation);
							
							StartLocation stepStartLocation = new StartLocation();
							JSONObject step_startLocation_json = (JSONObject)((JSONObject)jSteps.get(k)).get("start_location");
							stepStartLocation.setLatitude(step_startLocation_json.getDouble("lat"));
							stepStartLocation.setLongitude(step_startLocation_json.getDouble("lng"));
							step.setStart_location(stepStartLocation);
							
							String travel_mode = (String)((JSONObject)jSteps.get(k)).get("travel_mode");
							step.setTravel_mode(travel_mode);
							
							listofsteps.add(step);
						}
						steps.setListofsteps(listofsteps);
						legs.getDistance().setCost(totalcost);
						legs.setSteps(steps);
					}
					roots.setLegs(legs);
				}
				gwp.setRoots(roots);
			}
		} catch (JSONException e) {
			gwp.setGeocoder_status(false);
			//e.printStackTrace();
		}catch (Exception e){
			gwp.setGeocoder_status(false);
			//e.printStackTrace();
		}
		return gwp;
	}
	/**
	    * Method to decode polyline points
	    * Courtesy : jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
	    * 
	 * @throws IOException */
	/*private List<LatLng> decodePoly(String encoded) {
		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;
		while (index < len) {
			int b, shift = 0, result = 0;
			do {
					b = encoded.charAt(index++) - 63;
					result |= (b & 0x1f) << shift;
					shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;
			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;
			LatLng p = new LatLng((((double) lat / 1E5)),(((double) lng / 1E5)));
			poly.add(p);
		}
		return poly;
	}*/
	public String getCityNameByLatitudeAndLongitude(double latitude,double longitude) throws JSONException, IOException{
		String address = null;
		String addressurl = getkey.getProperty("getcityservice");//"http://localhost:8080/madhuri-pr/rest/getcarresults/city";
		
		Client client = Client.create();

		WebResource webResource = client.resource(addressurl);

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
				
		params.add("latitude",""+latitude);
		params.add("longitude",""+longitude);

		ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

		String data = clientResponse.getEntity(String.class);
		JSONObject obj = new JSONObject(data);
		address = (String)((JSONObject)((JSONArray)obj.get("results")).get(0)).get("formatted_address");
		
		return address;
	}
	public String getWeareAndTear() throws IOException{
		String wearandtear = getkey.getProperty("wearandtear");
		return wearandtear;
	}
	public String getTolls() throws IOException{
		String tolls = getkey.getProperty("tolls");
		return tolls;
	}
	public String getDirectionsBySourceAndDestination(String origin,String destination) throws IOException{
		String data = null;
	    
		String url = getkey.getProperty("getdirection");//"http://localhost:8080/madhuri-pr/rest/getcarresults/json";
	    
	    try{
			Client client = Client.create();

			WebResource webResource = client.resource(url);

			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("origin",origin);
			params.add("destination",destination);

			ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

			data = clientResponse.getEntity(String.class);
			
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return data;
	}
	public List<Step> setAddressToSteps(List<Step> listofsteps) throws JSONException{
		
		try{	
			for(Step step:listofsteps){
				StartLocation startlocation = step.getStart_location();
				String startaddress = getCityNameByLatitudeAndLongitude(startlocation.getLatitude(),startlocation.getLongitude());
				startlocation.setAddress(startaddress);
				
				EndLocation endlocation = step.getEnd_location();
				String endaddress = getCityNameByLatitudeAndLongitude(endlocation.getLatitude(), endlocation.getLongitude());
				endlocation.setAddress(endaddress);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listofsteps;
	}
	public GeocodedWaypoints setAddressToSteps(GeocodedWaypoints gwp) throws JSONException{
		
		try{	
			boolean status = gwp.getGeocoder_status();
			if(status){
				Roots roots = gwp.getRoots();
				Legs legs = roots.getLegs();
				List<Step> listofsteps = legs.getSteps().getListofsteps();
				for(Step step:listofsteps){
					StartLocation startlocation = step.getStart_location();
					String startaddress = getCityNameByLatitudeAndLongitude(startlocation.getLatitude(),startlocation.getLongitude());
					startlocation.setAddress(startaddress);
					
					EndLocation endlocation = step.getEnd_location();
					String endaddress = getCityNameByLatitudeAndLongitude(endlocation.getLatitude(), endlocation.getLongitude());
					endlocation.setAddress(endaddress);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gwp;
	}
	public List<Step> reducedStepsService(List<Step> listofsteps){
		
		DecimalFormat df = new DecimalFormat("#.00");
	    
		List<Step> reducedSteps = new ArrayList<Step>();
		if(listofsteps.size() <= 5){
			return listofsteps;
		}else{
			
			for(int i=0;i<listofsteps.size();i++){
				Duration stepDuration = listofsteps.get(i).getDuration();
				long duration = stepDuration.getDuration_value();
				if(duration < 122599){
					Step step = new Step();
					StartLocation startlocation = listofsteps.get(i).getStart_location();
					Distance stepDistance = listofsteps.get(i).getDistance();
					double cost = stepDistance.getCost();
					long distance = stepDistance.getDistance_value();
					
					for(int j=i+1;j<listofsteps.size();j++){
							i++;
							EndLocation endlocation = listofsteps.get(j).getEnd_location();
							step.setEnd_location(endlocation);
							Distance innerDistance = listofsteps.get(j).getDistance();
							Duration innerDuration = listofsteps.get(j).getDuration();
							
							distance = distance + innerDistance.getDistance_value();
							duration = duration + innerDuration.getDuration_value();
							cost     = cost     + innerDistance.getCost();
							if(duration < 3600){
								continue;
							}else{
								break;
							}
					}
					step.setStart_location(startlocation);
					stepDuration.setDuration_value(duration);
					stepDuration.setDuration_text(getDurationString(duration));
					stepDistance.setDistance_value(distance);
					stepDistance.setDistance_text(df.format((distance/1000)/1.5)+" mi");
					stepDistance.setCost(cost);
					step.setDistance(stepDistance);
					step.setDuration(stepDuration);
					reducedSteps.add(step);
				}else{
					reducedSteps.add(listofsteps.get(i));
				}
			}
		}
		return reducedSteps;
	}
	private String getDurationString(long seconds) {

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        
       String hourss = twoDigitString(hours);
        if(hourss.equals("")){
        	return twoDigitString(minutes) + " min ";
        }else{
        	return hourss + " hour " + twoDigitString(minutes) + " min ";
        }
    }

    private String twoDigitString(long number) {

        if (number == 0) {
            return "";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
	public static void main(String args[]) throws JSONException, IOException{
		GetDirectionJsonParserService gdjs = new GetDirectionJsonParserService();
		
		String data = gdjs.getDirectionsBySourceAndDestination("Chicago", "New York");
		GeocodedWaypoints gwp = gdjs.parse(data,99,99,0);
		gwp = gdjs.setAddressToSteps(gwp);
		
		try{	
			String place_id = gwp.getPlace_id();
			boolean status = gwp.getGeocoder_status();
			System.out.println(place_id);
			System.out.println(status);
			
			Roots roots = gwp.getRoots();
			Bounds bounds = roots.getBounds();
			System.out.println("---------------  Bounds -----------------------");
			System.out.println("North East Lat :"+bounds.getNortheast_lat());
			System.out.println("North East Lng :"+bounds.getNortheast_lng());
			System.out.println("South West Lat :"+bounds.getSouthwest_lat());
			System.out.println("South West Lng :"+bounds.getSouthwest_lng());
			Legs legs = roots.getLegs();
			System.out.println("------------------- -------------  Legs -------------");
			Distance distance = legs.getDistance();
			System.out.println("\t Distance : "+distance.getDistance_text()+"  , "+distance.getDistance_value());
			Duration duration = legs.getDuration();
			System.out.println("\t Duration : "+duration.getDuration_text()+"  , "+duration.getDuration_value());
			System.out.println("\t Start Address :"+legs.getStart_address());
			System.out.println("\t End Address : "+legs.getEnd_address());
			List<Step> listofsteps = legs.getSteps().getListofsteps();
			for(Step step:listofsteps){
				StartLocation startlocation = step.getStart_location();
				
				EndLocation endlocation = step.getEnd_location();
				
				System.out.println("\t \t \t Starting Address : "+startlocation.getAddress());
				System.out.println("\t \t \t End Address : "+endlocation.getAddress());
				Distance stepDistance = step.getDistance();
				System.out.println("\t \t \t Distance :"+stepDistance.getDistance_text()+"  , "+stepDistance.getDistance_value());
				Duration stepDuration = step.getDuration();
				System.out.println("\t \t \t Duration :"+stepDuration.getDuration_text()+"  , "+stepDuration.getDuration_value());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		 
	}
}