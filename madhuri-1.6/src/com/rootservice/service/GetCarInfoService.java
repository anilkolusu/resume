package com.rootservice.service;

import java.util.List;

import com.rootservice.dao.GetCarInfoDao;

public class GetCarInfoService {

	private GetCarInfoDao getCarInfoDao;
	
	public void setGetCarInfoDao(GetCarInfoDao getCarInfoDao){
		this.getCarInfoDao = getCarInfoDao;
	}
	public List<String> getCarMakeInfoService(String year){
		return getCarInfoDao.getCarMakeInfoDao(year);
	}
	public List<String> getCarModelInfoService(String year,String make){
		return getCarInfoDao.getCarModelInfoDao(year,make);
	}
	public List<String> getCarModelInfoService(String make){
		return getCarInfoDao.getCarModelInfoDao(make);
	}
	public int getCarMileageService(String year,String make,String model){
		return getCarInfoDao.getCarMileageDao(year,make,model);
	}
}