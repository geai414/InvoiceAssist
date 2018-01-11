package com.shareit.ocr.enums;

public enum APIResultEnum {
	SUCCESS(0,"成功"),
	UNKOWN_ERROR(-1,"服务器内未知错误"),
	PAGE_ERROR(-2,"解析发票完整数据发生错误"),
	INVOICE_FPDM_ERROR(-1001,"发票代码错误"),
	INVOICE_FPHM_ERROR(-1002,"发票号码有误"),
	INVOICE_FPRQ_ERROR(-1003,"开票日期有误"),
	VALID_ERROR(-2001,"发票验证错误"),

	;
	private int code;
	
	private String message;
	
	
	APIResultEnum(Integer code,String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
}
