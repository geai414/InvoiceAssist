package com.shareit.ocr.domain;

import com.shareit.ocr.enums.APIResultEnum;

public class SimulatorException extends RuntimeException {


	private static final long serialVersionUID = 671592930419472386L;
	private int returnCode;
	
	public SimulatorException(APIResultEnum e) {
		super(e.getMessage());
		this.setReturnCode(e.getCode());
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

}
