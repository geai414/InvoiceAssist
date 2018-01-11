package com.shareit.ocr.controller;

import com.shareit.ocr.domain.APIResult;
import com.shareit.ocr.service.UIService;
import com.shareit.ocr.util.APIResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.shareit.ocr.util.PicUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/face")
public class UIController {

	@Autowired
	private UIService uiService;
	@GetMapping(value ="/{facename}")
	public APIResult getSource(@PathVariable(value = "facename", required = true) String facename) throws IOException {

		return APIResultUtil.sucess(uiService.getFacebyName(facename));
	}
}
