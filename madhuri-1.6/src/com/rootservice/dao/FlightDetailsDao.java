package com.rootservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.rootservice.bean.CarDetails;
import com.rootservice.bean.FlightStatus;
import com.rootservice.bean.HistoryDetails;
import com.rootservice.bean.Profile;
import com.rootservice.queries.QueryBundle;

public class FlightDetailsDao extends QueryBundle {

	private JdbcTemplate jdbcTemplate;
	
	public FlightDetailsDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<FlightStatus> getFlightDetailsDao(HistoryDetails historyDetails){
		List<FlightStatus> flightdetails = null;
		String origin[] = historyDetails.getOrigin().split(",");
		String destin[] = historyDetails.getDestin().split(",");
		Object params[] =  new Object[] { origin[0]+"%",origin[1]+"%",origin[2]+"%",destin[0]+"%",destin[1]+"%",destin[2]+"%" };
		System.out.println(historyDetails.getOrigin()+":"+historyDetails.getDestin());
		try{
			flightdetails = jdbcTemplate.query(get_flight_details_by_origin_destin_query, params, new RowMapper<FlightStatus>() {
				public FlightStatus mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					FlightStatus flight = new FlightStatus();
					flight.setAirportname(resultSet.getString("airport_name"));
					flight.setAraivaltime(""+resultSet.getTime("araival_time"));
					//flight.setAvailableseets((resultSet.getInt("seetsavailable")));
					flight.setDeparturetime(""+resultSet.getTime("departure_time"));
					flight.setDescription("");
					flight.setDestination(resultSet.getString("destination"));
					flight.setFlightid(""+resultSet.getString("flight_id"));
					flight.setFlightname(resultSet.getString("flight_name"));
					flight.setOrigin(resultSet.getString("origin"));
					//flight.setVia(resultSet.getString("via"));
					
					return flight;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flightdetails;
	}
	public boolean saveDetails(HistoryDetails history,CarDetails cardetails) {
		boolean status = false;
		
		String uuid = jdbcTemplate.queryForObject(get_uuid_query, String.class);
		
		Object params[] = new Object[] { uuid,
						history.getOrigin(),
						history.getDestin(), 
						history.getNumberofpassengers(),
						history.getCaryear(),
						history.getCarmake(),
						history.getCarmodel(),
						history.getHours() };
		int count = jdbcTemplate.update(save_history_query,params);
		if(count>0){
			Object params1[] = new Object[] { uuid,
						history.getDrivingtimetomdwtext(),
						history.getCheckintext(),
						history.getFlyingtimetext(),
						history.getLandingtext(),
						history.getDrivingtimefromsjctext(),
						history.getTotaltimetext(),
						history.getTotalticketcosttext(),
						history.getBaggagefeetext(),
						history.getCarrentalcosttext(),
						history.getMdwtransportationcosttext(),
						history.getMdwparkingcosttext(),
						history.getLgatransportationcost(),
						history.getTotalonewaytripcost()};
			
			count = jdbcTemplate.update(save_flight_compare_history_query,params1);
			
			Object params2[] = new Object[] { uuid,
					cardetails.getDistance(),
					cardetails.getTime(),
					cardetails.getDuration(),
					cardetails.getTravel(),
					cardetails.getTotalfuelcost(),
					cardetails.getWeartear(),
					cardetails.getHotelcost(),
					cardetails.getEstimatedtolls(),
					cardetails.getTotalroundtripcost()};
			count = jdbcTemplate.update(save_car_compare_history_query,params2);
			
			status = true;
		}
		return status;
	}
	public HistoryDetails getFlightCompareDetailsDao(String historyid) {
		HistoryDetails history = null;
		Object params[] =  new Object[] { historyid };
		try{
			history = jdbcTemplate.query(get_flight_compare_details, params,new ResultSetExtractor<HistoryDetails>() {
		        @Override
		        public HistoryDetails extractData(ResultSet resultSet) throws SQLException,DataAccessException {
		        	HistoryDetails history = null;
		        	if(resultSet.next()){
		        		history = new HistoryDetails();
		        		history.setHistoryid(resultSet.getString("compare_id"));
		        		history.setDrivingtimetomdwtext(resultSet.getString("driving_time_to_mdw"));
		        		history.setCheckintext(resultSet.getString("time_at_mdw"));
		        		history.setFlyingtimetext(resultSet.getString("estimated_flying_time"));
		        		history.setLandingtext(resultSet.getString("time_at_sjc"));
		        		history.setDrivingtimefromsjctext(resultSet.getString("driving_time_from_sjc"));
		        		history.setTotaltimetext(resultSet.getString("one_way_door_to_door"));
		        		history.setTotalticketcosttext(resultSet.getString("total_ticket_cost"));
		        		history.setBaggagefeetext(resultSet.getString("total_baggage_fees"));
		        		history.setCarrentalcosttext(resultSet.getString("total_car_rental_cost"));
		        		history.setMdwtransportationcosttext(resultSet.getString("mdw_transportation_cost"));
		        		history.setMdwparkingcosttext(resultSet.getString("mdw_parking_cost"));
		        		history.setLgatransportationcost(resultSet.getString("lga_transportation_cost"));
		        		history.setTotalonewaytripcost(resultSet.getString("total_one_way_trip_cost"));
		            }
		        	return history;
		        }
		    });
		}catch(Exception e){
			e.printStackTrace();
			return history;
		}
		return history;
	}
	public CarDetails getCarCompareDetailsDao(String historyid) {
		
		CarDetails cardetails = null;
		Object params[] =  new Object[] { historyid };
		try{
			cardetails = jdbcTemplate.query(get_car_compare_details, params,new ResultSetExtractor<CarDetails>() {
		        @Override
		        public CarDetails extractData(ResultSet resultSet) throws SQLException,DataAccessException {
		        	CarDetails cardetails = null;
		        	if(resultSet.next()){
		        		cardetails = new CarDetails();
		        		//cardetails.setDistance(resultSet.getString("compare_id"));
		        		cardetails.setDistance(resultSet.getString("car_distance"));
		        		cardetails.setTime(resultSet.getString("car_time"));
		        		cardetails.setDuration(resultSet.getString("car_duration"));
		        		cardetails.setTravel(resultSet.getString("car_travel"));
		        		cardetails.setTotalfuelcost(resultSet.getString("total_fuel_cost"));
		        		cardetails.setWeartear(resultSet.getString("wear_tear"));
		        		cardetails.setHotelcost(resultSet.getString("estimated_hotel_cost"));
		        		cardetails.setEstimatedtolls(resultSet.getString("estimated_tolls"));
		        		cardetails.setTotalroundtripcost(resultSet.getString("total_round_trip_cost"));
		            }
		        	return cardetails;
		        }
		    });
		}catch(Exception e){
			e.printStackTrace();
			return cardetails;
		}
		return cardetails;
	}
}
