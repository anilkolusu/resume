package com.rootservice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rootservice.queries.QueryBundle;

public class GetCarInfoDao extends QueryBundle{

private JdbcTemplate jdbcTemplate;
	
	public GetCarInfoDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<String> getCarMakeInfoDao(String year){
		List<String> listofmakes = new ArrayList<String>();
		Object params[] =  new Object[] { year };
		try{
			listofmakes = (List<String>) jdbcTemplate.queryForList(get_car_make_info_query, params, String.class);
		}catch(Exception e){
			e.printStackTrace();
			return listofmakes;
		}
		return listofmakes;
	}
	public List<String> getCarModelInfoDao(String year,String make){
		List<String> listofmakes = new ArrayList<String>();
		Object params[] =  new Object[] { year,make };
		try{
			listofmakes = (List<String>) jdbcTemplate.queryForList(get_car_model_info_by_year_make_query, params, String.class);
		}catch(Exception e){
			e.printStackTrace();
			return listofmakes;
		}
		return listofmakes;
	}
	public List<String> getCarModelInfoDao(String make){
		List<String> listofmakes = new ArrayList<String>();
		Object params[] =  new Object[] { make };
		try{
			listofmakes = (List<String>) jdbcTemplate.queryForList(get_car_model_info_by_make_query, params, String.class);
		}catch(Exception e){
			e.printStackTrace();
			return listofmakes;
		}
		return listofmakes;
	}
	public int getCarMileageDao(String year,String make,String model){
		int mileage = 0;
		Object params[] =  new Object[] { year,make,model };
		try{
			mileage = jdbcTemplate.queryForInt(get_car_mileage_query, params);
		}catch(Exception e){
			e.printStackTrace();
			return mileage;
		}
		return mileage;
	}
	
}
