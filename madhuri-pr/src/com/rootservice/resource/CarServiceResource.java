package com.rootservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/getcarresults")
public class CarServiceResource {
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDistanceDetails(@QueryParam("origins") String origin,@QueryParam("destinations") String destin) throws JSONException {
		System.out.println("Entering into provider...");
		
		//http://maps.googleapis.com/maps/api/distancematrix/json?origins=Chicago&destinations=New%20York&mode=car&language=en&sensor=false
		Client client = Client.create();

		WebResource webResource = client.resource("http://maps.googleapis.com/maps/api/distancematrix/json");

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("origins",origin);
		params.add("destinations",destin);
		params.add("mode", "car");
		params.add("language", "en");

		ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

		String en = clientResponse.getEntity(String.class);
		System.out.println(en);
		return en;
	}
	@GET
	@Path("/json")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDirectionsBySourceAndDestination(@QueryParam("origin") String origin,@QueryParam("destination") String destination){
		String data = null;
	    String waypoints = "";
	    /*for(int i=2;i<markerPoints.size();i++){
        	LatLng point  = (LatLng) markerPoints.get(i);
        	if(i==2)
            	waypoints = "waypoints=";
        	waypoints += point.latitude + "," + point.longitude + "|";
    	}*/
	    String url = "https://maps.googleapis.com/maps/api/directions/json";
	    
	    try{
			Client client = Client.create();

			WebResource webResource = client.resource(url);

			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("origin",origin);
			params.add("destination",destination);
			params.add("mode","car");
			params.add("sensor","false");
			params.add("waypoints",waypoints);

			ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

			data = clientResponse.getEntity(String.class);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return data;
	}
	@GET
	@Path("/city")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCityNameByLatitudeAndLongitude(@QueryParam("latitude") String latitude,@QueryParam("longitude") String longitude) throws JSONException{
		String data = null;
		String addressurl = "http://maps.googleapis.com/maps/api/geocode/json";
		
		Client client = Client.create();

		WebResource webResource = client.resource(addressurl);

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
				
		params.add("latlng",latitude+","+longitude);
		params.add("sensor","true");

		ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

		data = clientResponse.getEntity(String.class);
		return data;
	}
	public static void main(String args[]) throws JSONException{
		
		String data = "{'destination_addresses' : [ 'A,B'],'origin_addresses':['N,M'],'rows':[{'elements' : [{'distance':{'text':'10','value':'22'},'duration':{'text':'tx','value':'val'},'status':'ok'}]}],'status':'ok'}";
		JSONObject obj = new JSONObject(data);

		JSONArray destination_addresses = obj.getJSONArray("destination_addresses");
		JSONArray origin_addresses = obj.getJSONArray("origin_addresses");
		
		for(int i = 0 ; i < destination_addresses.length() ; i++){
			System.out.println(destination_addresses.get(i));
		}
		for(int i = 0 ; i < origin_addresses.length() ; i++){
			System.out.println(origin_addresses.get(i));
		}
		JSONArray rows = obj.getJSONArray("rows");
		
		JSONObject rows_obj = rows.getJSONObject(0);
		JSONArray elements_obj = rows_obj.getJSONArray("elements");
		
		JSONObject element_values = (JSONObject)elements_obj.get(0);
		System.out.println();
		JSONObject distance_obj = element_values.getJSONObject("distance");
		JSONObject duration_obj = element_values.getJSONObject("duration");
		
		System.out.println(distance_obj.getString("text"));
		System.out.println(distance_obj.getString("value"));
		
		System.out.println(duration_obj.getString("text"));
		System.out.println(duration_obj.getString("value"));
	}
	
}
