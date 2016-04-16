package com.rootservice.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rootservice.command.RegisterCommand;
import com.rootservice.queries.QueryBundle;

public class RegisterDao extends QueryBundle{

	private JdbcTemplate jdbcTemplate;
	
	public RegisterDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String register(RegisterCommand rc) {
		int count = 0;
		String uuid = null;
		
		try{
			uuid = jdbcTemplate.queryForObject(get_uuid_query, String.class);
			System.out.println(uuid);
			if(uuid!=null){
				Object params[] = new Object[] { uuid,rc.getFullname(), rc.getEmailid(),rc.getPassword() };
				count = jdbcTemplate.update(register_query,params);
				if(count>0){
					return uuid;
				}else{
					return null;
				}
			}
			
		}catch(Exception e){
			return null;
		}
		return uuid;
	}

}
