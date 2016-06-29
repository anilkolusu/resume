package com.rootservice.queries;

public class QueryBundle {

	public String get_uuid_query = "select uuid()";
	public String register_query = "insert into login(userid,fullname,email,password) values(?,?,?,?)";
	public String login_auth_query = "select l.userid as username,l.fullname as fullname,l.email as email from login l where l.email = ? and l.password = ?";
	public String get_car_make_info_query = "select distinct c.car_make from car_details c where c.car_year = ?";
	public String get_car_model_info_by_year_make_query = "select c.car_model from car_details c where c.car_year = ? and c.car_make = ?";
	public String get_car_model_info_by_make_query = "select c.car_model from car_details c where c.car_make = ?";
	public String get_car_mileage_query = "select c.car_mileage_per_litter from car_details c where c.car_year = ? and c.car_make = ? and c.car_model = ?";
	public String save_history_query = "insert into history_details(history_id,origin,destination,npssgrs,caryear,carmake,carmodel,hours,dateofentry) values(?,?,?,?,?,?,?,?,now())";
	public String get_history_details_query = "select h.history_id as historyid,h.origin,h.destination,h.npssgrs,h.caryear,h.carmake,h.carmodel,h.hours,h.dateofentry from history_details h order by h.dateofentry desc";
	public String get_flight_details_query = "select f.flight_id as flight_id,u.origin as origin,u.destination as destination,u.via as via,u.seetsavailable as seetsavailable,u.airport_name as airport_name,u.departure_time as departure_time,u.araival_time as araival_time,f.flight_name as flight_name  from flight_details f, flight_detail_status u where f.flight_id = u.flight_id and u.origin = ? and u.destination = ?";
	public String get_flight_details_by_origin_destin_query = "select f.idflightid as flight_id,f.flightname as flight_name,f.airportname as airport_name,f.origin as origin,f.destin as destination,f.atime as araival_time,f.dtime as departure_time from flight f where (f.origin like ? or f.origin like ? or f.origin like ?) and (f.destin like ? or f.destin like ? or f.destin  like ?)";
	public String save_flight_compare_history_query = "insert into flight_compare_details(compare_id,driving_time_to_mdw,time_at_mdw,estimated_flying_time,time_at_sjc,driving_time_from_sjc,one_way_door_to_door,total_ticket_cost,total_baggage_fees,total_car_rental_cost,mdw_transportation_cost,mdw_parking_cost,lga_transportation_cost,total_one_way_trip_cost) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public String save_car_compare_history_query = "insert into car_compare_details(compare_id,car_distance,car_time,car_duration,car_travel,total_fuel_cost,wear_tear,estimated_hotel_cost,estimated_tolls,total_round_trip_cost) values(?,?,?,?,?,?,?,?,?,?)";
	public String get_flight_compare_details = "select compare_id as compare_id,driving_time_to_mdw as driving_time_to_mdw,time_at_mdw as time_at_mdw,estimated_flying_time as estimated_flying_time,time_at_sjc as time_at_sjc,driving_time_from_sjc as driving_time_from_sjc,one_way_door_to_door as one_way_door_to_door,total_ticket_cost,total_baggage_fees as total_ticket_cost,total_baggage_fees,total_car_rental_cost as total_car_rental_cost,mdw_transportation_cost as mdw_transportation_cost,mdw_parking_cost as mdw_parking_cost,lga_transportation_cost as lga_transportation_cost,total_one_way_trip_cost as total_one_way_trip_cost from flight_compare_details where compare_id = ?";
	public String get_car_compare_details = "select compare_id as compare_id,car_distance as car_distance,car_time as car_time,car_duration as car_duration,car_travel as car_travel,total_fuel_cost as total_fuel_cost,wear_tear as wear_tear,estimated_hotel_cost as estimated_hotel_cost,estimated_tolls as estimated_tolls,total_round_trip_cost as total_round_trip_cost from car_compare_details where compare_id = ?";
}
