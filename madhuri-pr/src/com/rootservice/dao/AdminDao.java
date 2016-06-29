package com.rootservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rootservice.bean.Flight;
import com.rootservice.bean.User;
import com.rootservice.database.DataBaseConnection;

public class AdminDao {

	private Connection connection;
	public int authentication(String username,String password){
		int valid = 0;
		PreparedStatement ps = null;
		try{
			connection = DataBaseConnection.getConnection();
			String loginsql = "select count(*) as valid from admin where username = ? and password = ?";
			ps = connection.prepareStatement(loginsql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				valid = rs.getInt("valid");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return valid;
	}
	public int addFlightDetails(Flight flight){
		int status = 0;
		PreparedStatement ps = null;
		try{
			connection = DataBaseConnection.getConnection();
			
			String regsql = "insert into flight(idflightid,flightname,airportname,origin,destin,atime,dtime) values(?,?,?,?,?,?,?)";
			
			ps = connection.prepareStatement(regsql);
			ps.setString(1, flight.getFlightid());
			ps.setString(2, flight.getFlightname());
			ps.setString(3,flight.getAirportname());
			ps.setString(4,flight.getOrigin());
			ps.setString(5,flight.getDestin());
			ps.setString(6,flight.getAtime());
			ps.setString(7,flight.getDtime());
			
			
			status = ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return status;
	}
}
