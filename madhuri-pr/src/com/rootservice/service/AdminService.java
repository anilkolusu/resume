package com.rootservice.service;

import com.rootservice.bean.Flight;
import com.rootservice.dao.AdminDao;

public class AdminService {

	public int authentication(String username,String password){
		AdminDao admindao = new AdminDao();
		int valid = admindao.authentication(username, password);
		return valid;
	}
	public int addFlightDetails(Flight flight){
		AdminDao admindao = new AdminDao();
		int count = admindao.addFlightDetails(flight);
		return count;
	}
}
