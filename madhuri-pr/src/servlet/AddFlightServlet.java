package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rootservice.bean.Flight;
import com.rootservice.service.AdminService;

public class AddFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
   		String resultpage = "/homepage";
   		
   		String flightid = request.getParameter("flightid");
   		String flightname = request.getParameter("flightname");
   		String origin = request.getParameter("origin");
   		String destin = request.getParameter("destin");
   		String atime = request.getParameter("atime");
   		String dtime = request.getParameter("dtime");
   		String airportname = request.getParameter("airportname");
   	
   		Flight flight = new Flight();
   		flight.setFlightid(flightid);
   		flight.setFlightname(flightname);
   		flight.setOrigin(origin);
   		flight.setDestin(destin);
   		flight.setAtime(atime);
   		flight.setDtime(dtime);
   		flight.setAirportname(airportname);
   		try{
   			AdminService adminService = new AdminService();
   			int c = adminService.addFlightDetails(flight);
   			if(c>0){
   				resultpage = "/temppage?p=success.jsp";
   			}else{
   				resultpage = "/temppage?p=failure.jsp";
   			}
   		}catch(Exception e){
   			resultpage = "/failurepage?p=failure.jsp";
   		}
   		RequestDispatcher rd = request.getRequestDispatcher(resultpage);
		rd.forward(request, response);
   	}
}
