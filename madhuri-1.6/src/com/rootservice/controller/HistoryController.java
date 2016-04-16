package com.rootservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.rootservice.bean.HistoryDetails;
import com.rootservice.service.GetHistoryService;

public class HistoryController extends AbstractController {

	private GetHistoryService getHistoryService;
	
	public void setGetHistoryService(GetHistoryService getHistoryService){
		this.getHistoryService = getHistoryService;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		List<HistoryDetails> historydetails = getHistoryService.getHistoryDetails();
		mav = new ModelAndView();
		mav.setViewName("historydetailstiles");
		mav.addObject("historydetails",historydetails);
		return mav;
	}
}
