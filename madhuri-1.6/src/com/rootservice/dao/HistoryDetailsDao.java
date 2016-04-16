package com.rootservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rootservice.bean.HistoryDetails;
import com.rootservice.queries.QueryBundle;

public class HistoryDetailsDao extends QueryBundle {

	private JdbcTemplate jdbcTemplate;
	
	public HistoryDetailsDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public boolean saveDetails(HistoryDetails history) {
		boolean status = false;
		
		String uuid = jdbcTemplate.queryForObject(get_uuid_query, String.class);
		
		Object params[] = new Object[] { uuid,history.getOrigin(),history.getDestin(), history.getNumberofpassengers(),history.getCaryear(),history.getCarmake(),history.getCarmodel(),history.getHours() };
		int count = jdbcTemplate.update(save_history_query,params);
		if(count>0){
			
			status = true;
		}
		return status;
	}
	public List<HistoryDetails> getHistoryDetailsDao(){
		List<HistoryDetails> historydetails = null;
		historydetails = jdbcTemplate.query(get_history_details_query, new RowMapper<HistoryDetails>() {
			public HistoryDetails mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				HistoryDetails history = new HistoryDetails();
				history.setSno(rowNumber+1);
				history.setHistoryid(resultSet.getString("historyid"));
				history.setOrigin(resultSet.getString("origin"));
				history.setDestin(resultSet.getString("destination"));
				history.setNumberofpassengers(resultSet.getInt("npssgrs"));
				history.setCaryear(resultSet.getString("caryear"));
				history.setCarmake(resultSet.getString("carmake"));
				history.setCarmodel(resultSet.getString("carmodel"));
				history.setHours(resultSet.getInt("hours"));
			    history.setDate(""+resultSet.getTimestamp("dateofentry"));
				return history;
			}
		});
		return historydetails;
	}
}
