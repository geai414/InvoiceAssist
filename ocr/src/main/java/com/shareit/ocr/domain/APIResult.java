package com.shareit.ocr.domain;

import java.util.HashMap;
import java.util.Map;

public class APIResult<T> {

	private int returnCode = 0;
	private String errorMessage ="";
	private T data;
	
	public APIResult() {
		
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getErrorMessage() {

		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {

		this.errorMessage = errorMessage;
	}
	public void setData(T data) {

		this.data =data;
	}
	
	public T getData() {

		return this.data;
	}
	
	@Override
	public String toString() {
		return "APIResult [returnCode=" + returnCode + ", errorMessage=" + errorMessage + ", data=" + data + "]";
	}
	
	
}
