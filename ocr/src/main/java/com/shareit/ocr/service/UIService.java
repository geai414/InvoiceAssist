package com.shareit.ocr.service;


import com.shareit.ocr.domain.APIResult;
import com.shareit.ocr.properties.UIProperties;
import com.shareit.ocr.util.PicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UIService {

    private final static Logger logger = LoggerFactory.getLogger(UIService.class);

    @Autowired
    private UIProperties uiProperties;

    public Map<String,String> getFacebyName(String facename) throws IOException {
        Map<String,String> map ;
        if("index".equalsIgnoreCase(facename)){
            map = this.getIndexFace();
        }else {
            return null;
        }
        return map;
    }

    private Map<String,String> getIndexFace() throws IOException {
        Map<String,String> map = new HashMap<String, String>();

        map.put("headerImgData", PicUtil.encodeImage(uiProperties.getHeaderImgSource()));
        map.put("scanImgData", PicUtil.encodeImage(uiProperties.getScanImgSource()));
        map.put("inputImgData", PicUtil.encodeImage(uiProperties.getInputImgSource()));

        return map;
    }


}
