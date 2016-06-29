package com.rootservice.bean;

public class Legs {

	private Distance distance;
	private Duration duration;
	private String end_address;
	private EndLocation endlocation;
	private String start_address;
	private StartLocation startlocation;
	private Steps steps;
	
	public Distance getDistance() {
		return distance;
	}
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public String getEnd_address() {
		return end_address;
	}
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	public EndLocation getEndlocation() {
		return endlocation;
	}
	public void setEndlocation(EndLocation endlocation) {
		this.endlocation = endlocation;
	}
	public String getStart_address() {
		return start_address;
	}
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	public StartLocation getStartlocation() {
		return startlocation;
	}
	public void setStartlocation(StartLocation startlocation) {
		this.startlocation = startlocation;
	}
	public Steps getSteps() {
		return steps;
	}
	public void setSteps(Steps steps) {
		this.steps = steps;
	}	
}
