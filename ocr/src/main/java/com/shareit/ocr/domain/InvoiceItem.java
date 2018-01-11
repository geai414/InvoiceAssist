package com.shareit.ocr.domain;

public class InvoiceItem {

	//项目名称
	private String xmmc;
	
	//规格型号
	private String xh;
	
	//单位
	private String dw;
	
	//数量
	private String sl;
	
	//单价
	private String dj;
	
	//金额
	private String je;
	
	//税率
	private String rate;
	
	//税额
	private String se;

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	@Override
	public String toString() {
		return "InvoiceItem [xmmc=" + xmmc + ", xh=" + xh + ", dw=" + dw + ", sl=" + sl + ", dj=" + dj + ", je=" + je
				+ ", rate=" + rate + ", se=" + se + "]";
	}
	
	
	
}
