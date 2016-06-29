package com.rootservice.bean;

public class Step {

	private Distance distance;
 	private Duration duration;
 	private EndLocation end_location;
 	private String html_instructions;
    private String polyline_points;
 	private StartLocation start_location;
 	private String travel_mode;
 	
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
	public EndLocation getEnd_location() {
		return end_location;
	}
	public void setEnd_location(EndLocation end_location) {
		this.end_location = end_location;
	}
	public String getHtml_instructions() {
		return html_instructions;
	}
	public void setHtml_instructions(String html_instructions) {
		this.html_instructions = html_instructions;
	}
	public String getPolyline_points() {
		return polyline_points;
	}
	public void setPolyline_points(String polyline_points) {
		this.polyline_points = polyline_points;
	}
	public StartLocation getStart_location() {
		return start_location;
	}
	public void setStart_location(StartLocation start_location) {
		this.start_location = start_location;
	}
	public String getTravel_mode() {
		return travel_mode;
	}
	public void setTravel_mode(String travel_mode) {
		this.travel_mode = travel_mode;
	}
}
