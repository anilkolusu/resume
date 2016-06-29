package com.rootservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rootservice.bean.Profile;
import com.rootservice.command.LoginCommand;
import com.rootservice.queries.QueryBundle;

public class LoginDao extends QueryBundle{

	private JdbcTemplate jdbcTemplate;
	
	public LoginDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Profile login(LoginCommand lc){
		Profile profile = null;
		Object params[] =  new Object[] { lc.getUsername(),lc.getPassword() };
		try{
			profile = jdbcTemplate.query(login_auth_query, params,new ResultSetExtractor<Profile>() {
		        @Override
		        public Profile extractData(ResultSet resultSet) throws SQLException,DataAccessException {
		            Profile profile = null;
		        	if(resultSet.next()){
		            	profile = new Profile(resultSet.getString("fullname"),resultSet.getString("email"), resultSet.getString("username"));
		            }
		        	return profile;
		        }
		    });
		}catch(Exception e){
			e.printStackTrace();
			return profile;
		}
		return profile;
	}
}
