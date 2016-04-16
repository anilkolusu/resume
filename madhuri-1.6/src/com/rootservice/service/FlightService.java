package com.rootservice.service;

import java.util.List;

import com.rootservice.bean.CarDetails;
import com.rootservice.bean.FlightStatus;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.dao.FlightDetailsDao;

public class FlightService {

	private FlightDetailsDao flightDetailsDao;
	
	public boolean saveDetails(HistoryDetails history,CarDetails cardetails){
		return flightDetailsDao.saveDetails(history,cardetails);
	}
	public void setFlightDetailsDao(FlightDetailsDao flightDetailsDao){
		this.flightDetailsDao = flightDetailsDao;
	}
	public List<FlightStatus> getFlightDetailsService(HistoryDetails historyDetails){
		return flightDetailsDao.getFlightDetailsDao(historyDetails);
	}
	public String getDurationString(long seconds) {

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        
       String hourss = twoDigitString(hours);
        if(hourss.equals("")){
        	return twoDigitString(minutes) + " min's ";
        }else{
        	return hourss + " hour's " + twoDigitString(minutes) + " min's ";
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
	public HistoryDetails getFlightComapreDetails(String historyid) {
		return flightDetailsDao.getFlightCompareDetailsDao(historyid);
	}
	public CarDetails getCarComapreDetails(String historyid) {
		return flightDetailsDao.getCarCompareDetailsDao(historyid);
	}
}
