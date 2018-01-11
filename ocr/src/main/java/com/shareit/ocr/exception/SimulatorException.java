package com.shareit.ocr.exception;

import com.shareit.ocr.enums.APIResultEnum;

public class SimulatorException extends RuntimeException {


	private static final long serialVersionUID = 671592930419472386L;
	private int returnCode;
	
	public SimulatorException(APIResultEnum e) {
		super(e.getMessage());
		this.setReturnCode(e.getCode());
	}

	public APIResultEnum getResultEnum(){
		APIResultEnum apiResultEnum = null;
		for(APIResultEnum e:APIResultEnum.values()){
			if(e.getCode() == this.getReturnCode()){
				apiResultEnum = e;
				break;
			}
		}
		return apiResultEnum;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

}
