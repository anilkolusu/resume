package com.rootservice.service;

import java.util.List;

import com.rootservice.bean.HistoryDetails;
import com.rootservice.dao.HistoryDetailsDao;

public class GetHistoryService {

	private HistoryDetailsDao historyDao;
	
	public void setHistoryDao(HistoryDetailsDao historyDao){
		this.historyDao = historyDao;
	}
	public List<HistoryDetails> getHistoryDetails(){
		
		return historyDao.getHistoryDetailsDao();
	}
}
