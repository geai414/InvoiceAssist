package com.shareit.ocr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareit.ocr.domain.APIResult;
import com.shareit.ocr.domain.SimulatorRequestInfo;
import com.shareit.ocr.domain.Invoice;
import com.shareit.ocr.service.SimulatorService;
import com.shareit.ocr.util.APIResultUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/invoice")
@Validated
public class SimulatorController {

	@Autowired
	private SimulatorService simulatorService;
	
	@GetMapping(value = "/check")
	public APIResult<Invoice> verify(@Valid SimulatorRequestInfo sri){
		return APIResultUtil.sucess(simulatorService.simulate(sri));
	}
	
}
