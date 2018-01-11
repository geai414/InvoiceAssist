package com.shareit.ocr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareit.ocr.domain.Simulator;
import com.shareit.ocr.domain.SimulatorRequestInfo;
import com.shareit.ocr.domain.Invoice;
import com.shareit.ocr.properties.SimulatorProperties;
import com.shareit.ocr.properties.UIProperties;

@Service
public class SimulatorService {
	
	private final static Logger log = LoggerFactory.getLogger(SimulatorService.class);
	@Autowired
	private SimulatorProperties simulatorProperties;
	
	
	public  Invoice simulate(SimulatorRequestInfo sri) {
		
		Invoice vi= null;;
		Simulator sm = new Simulator();
		try {
			sm.setTrycount(simulatorProperties.getTryCount());
			sm.setDamaUsername(simulatorProperties.getDamaUserName());
			sm.setDamaPassword(simulatorProperties.getDamaPassword());
			sm.setStartURL(simulatorProperties.getStartURL());
			vi = sm.Verify(sri);
		}
		finally {
			sm.close();
		}
		return vi;
	}
	

}
