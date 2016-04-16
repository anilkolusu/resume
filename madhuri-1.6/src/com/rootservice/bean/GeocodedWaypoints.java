package com.rootservice.bean;

public class GeocodedWaypoints {

	private boolean geocoder_status = false;
	private String place_id;
	
	private Roots roots;

	public boolean getGeocoder_status() {
		return geocoder_status;
	}

	public void setGeocoder_status(boolean geocoder_status) {
		this.geocoder_status = geocoder_status;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public Roots getRoots() {
		return roots;
	}

	public void setRoots(Roots roots) {
		this.roots = roots;
	}
}
