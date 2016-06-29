package com.rootservice.bean;

public class FlightStatus extends Flight{

	private String flightid;
	private String origin;
	private String destination;
	private int availableseets;
	private String departuretime;
	private String araivaltime;
	private String airportname;
	private String via;
	
	
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getFlightid() {
		return flightid;
	}
	public void setFlightid(String flightid) {
		this.flightid = flightid;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getAvailableseets() {
		return availableseets;
	}
	public void setAvailableseets(int availableseets) {
		this.availableseets = availableseets;
	}
	public String getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}
	public String getAraivaltime() {
		return araivaltime;
	}
	public void setAraivaltime(String araivaltime) {
		this.araivaltime = araivaltime;
	}
	public String getAirportname() {
		return airportname;
	}
	public void setAirportname(String airportname) {
		this.airportname = airportname;
	}
}
