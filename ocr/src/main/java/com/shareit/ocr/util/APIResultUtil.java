package com.shareit.ocr.util;

import com.shareit.ocr.domain.APIResult;
import com.shareit.ocr.enums.APIResultEnum;

public class APIResultUtil {

	public static APIResult sucess(Object data) {
		APIResult result = new APIResult();
		result.setReturnCode(APIResultEnum.SUCCESS.getCode());
		result.setErrorMessage(APIResultEnum.SUCCESS.getMessage());
		result.setData(data);
		
		return result;
	}
	
	public static APIResult error(APIResultEnum em) {
		APIResult result = new APIResult();
		result.setReturnCode(em.getCode());
		result.setErrorMessage(em.getMessage());
	
		return result;
	}

	public static APIResult error(int code,String message) {
		APIResult result = new APIResult();
		result.setReturnCode(code);
		result.setErrorMessage(message);

		return result;
	}
}
