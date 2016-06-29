package com.rootservice.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;

public class CarController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		PrintWriter out = response.getWriter();
		String origin = request.getParameter("origin");
		String destin = request.getParameter("destin");
		String date = request.getParameter("noofpsgrs");
		try{
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/madhuri-pr/rest/getcarresults/get");

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("origins",origin);
		params.add("destinations",destin);
		params.add("date",date);

		ClientResponse clientResponse = webResource.queryParams(params).get(ClientResponse.class);

		String data = clientResponse.getEntity(String.class);
		
		//String data = "{'destination_addresses' : [ 'A,B'],'origin_addresses':['N,M'],'rows':[{'elements' : [{'distance':{'text':'10','value':'22'},'duration':{'text':'tx','value':'val'},'status':'ok'}]}],'status':'ok'}";
		JSONObject obj = new JSONObject(data);

		JSONArray destination_addresses = obj.getJSONArray("destination_addresses");
		JSONArray origin_addresses = obj.getJSONArray("origin_addresses");
		origin = (String)origin_addresses.get(0);
		destin = (String)destination_addresses.get(0);
		String origindata[] = origin.split(",");
		String destindata[] = destin.split(",");
		JSONArray rows = obj.getJSONArray("rows");
		
		JSONObject rows_obj = rows.getJSONObject(0);
		JSONArray elements_obj = rows_obj.getJSONArray("elements");
		
		JSONObject element_values = (JSONObject)elements_obj.get(0);
		
		JSONObject distance_obj = element_values.getJSONObject("distance");
		JSONObject duration_obj = element_values.getJSONObject("duration");
		
		//String distance_text = (String)distance_obj.getString("text");
		int distance = distance_obj.getInt("value");
		double mails = distance/1500;
		int cost = (distance/(1000*30))*100;
		String duration_text = (String)duration_obj.getString("text");
		int duration = duration_obj.getInt("value");
		
		StringBuilder main = new StringBuilder();
		main.append("{'s':'0','otl':'"+origindata[0]+"','dtl':'"+destindata[0]+"','otltxt':'"+origindata[1]+", "+origindata[2]+"','dtltxt':'"+destindata[1]+", "+destindata[2]+"'},");
		StringBuilder sb = new StringBuilder();
		sb.append("{'s':'0','dis':'"+mails+" Mails','distxt':'"+distance+"','dur':'"+duration_text+"','durtxt':'"+duration+"','cost':'"+cost+"'},");
		out.print("{'main':["+main.toString()+"],'items':["+sb.toString()+"]}");
		/*StringBuilder jsondata = new StringBuilder();
		jsondata.append("{'otl':'"+origindata[0]+"','dtl':'"+destindata[0]+"','otltxt':'"+origindata[1]+", "+origindata[2]+"','dtltxt':'"+destindata[1]+", "+destindata[2]+"','dis':'"+mails+" Mails','distxt':'"+distance+"','dur':'"+duration_text+"','durtxt':'"+duration+"','cost':'"+cost+"'}");
		out.print(jsondata);*/
		
		return mav;
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
}
