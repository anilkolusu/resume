package com.rootservice.command;

public class FlightCommand {

	private String origin;
	private String destin;
	private int noofpassengers;
	private int bagfee;
	private double timetocheckin;
	private double timetolanding;
	private double parkingcostperday;
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestin() {
		return destin;
	}
	public void setDestin(String destin) {
		this.destin = destin;
	}
	public int getNoofpassengers() {
		return noofpassengers;
	}
	public void setNoofpassengers(int noofpassengers) {
		this.noofpassengers = noofpassengers;
	}
	public int getBagfee() {
		return bagfee;
	}
	public void setBagfee(int bagfee) {
		this.bagfee = bagfee;
	}
	public double getTimetocheckin() {
		return timetocheckin;
	}
	public void setTimetocheckin(double timetocheckin) {
		this.timetocheckin = timetocheckin;
	}
	public double getTimetolanding() {
		return timetolanding;
	}
	public void setTimetolanding(double timetolanding) {
		this.timetolanding = timetolanding;
	}
	public double getParkingcostperday() {
		return parkingcostperday;
	}
	public void setParkingcostperday(double parkingcostperday) {
		this.parkingcostperday = parkingcostperday;
	}
}
