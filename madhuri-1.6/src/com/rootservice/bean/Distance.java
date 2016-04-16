package com.rootservice.bean;

public class Distance {

	private String distance_text;
	private long distance_value;
	private double cost;
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDistance_text() {
		return distance_text;
	}
	public void setDistance_text(String distance_text) {
		this.distance_text = distance_text;
	}
	public long getDistance_value() {
		return distance_value;
	}
	public void setDistance_value(long distance_value) {
		this.distance_value = distance_value;
	}
}
