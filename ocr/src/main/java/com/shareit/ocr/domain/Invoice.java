package com.shareit.ocr.domain;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	
	private String text;
	private String fpdm;
	private String fphm;
	private String kprq;
	private String jym;
	private String jqbh;
	private String gfmc;
	private String gfsbh;
	private String gfyhzh;
	private String gfdzdh;
	
	private String xfmc;
	private String xfsbh;
	private String xfyhzh;
	private String xfdzdh;
	
	private String je;
	private String se;
	private String jshjdx;
	private String jshjxx;
	
	private List<InvoiceItem> items = new ArrayList<InvoiceItem>();
	
	private boolean ispp = false;
	
	public Invoice() {
		
	}
	
	public Invoice(SimulatorRequestInfo sri) {
		this.fpdm = sri.getFpdm();
		this.fphm = sri.getFphm();
		this.je = sri.getKjje();
		this.kprq = sri.getKprq();
		this.jym = sri.getJym();
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getKprq() {
		return kprq;
	}
	public void setKprq(String kprq) {
		this.kprq = kprq;
	}
	public String getJym() {
		return jym;
	}
	public void setJym(String jym) {
		this.jym = jym;
	}
	public String getJqbh() {
		return jqbh;
	}
	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}
	public String getGfmc() {
		return gfmc;
	}
	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}
	public String getGfsbh() {
		return gfsbh;
	}
	public void setGfsbh(String gfsbh) {
		this.gfsbh = gfsbh;
	}
	public String getGfyhzh() {
		return gfyhzh;
	}
	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}
	public String getGfdzdh() {
		return gfdzdh;
	}
	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}
	public String getXfmc() {
		return xfmc;
	}
	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}
	public String getXfsbh() {
		return xfsbh;
	}
	public void setXfsbh(String xfsbh) {
		this.xfsbh = xfsbh;
	}
	public String getXfyhzh() {
		return xfyhzh;
	}
	public void setXfyhzh(String xfyhzh) {
		this.xfyhzh = xfyhzh;
	}
	public String getXfdzdh() {
		return xfdzdh;
	}
	public void setXfdzdh(String xfdzdh) {
		this.xfdzdh = xfdzdh;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getJshjdx() {
		return jshjdx;
	}
	public void setJshjdx(String jshjdx) {
		this.jshjdx = jshjdx;
	}
	public String getJshjxx() {
		return jshjxx;
	}
	public void setJshjxx(String jshjxx) {
		this.jshjxx = jshjxx;
	}
	
	
	public boolean isIspp() {
		return ispp;
	}
	public void setIspp(boolean ispp) {
		this.ispp = ispp;
	}
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("发票文本:" + this.text + "\r\n");
		sb.append("发票代码:" + this.fpdm + "\r\n");
		sb.append("发票号码:" + this.fphm + "\r\n");
		sb.append("开票日期:" + this.kprq + "\r\n");
		sb.append("校验码:" + this.jym + "\r\n");
		sb.append("机器编号:" + this.jqbh + "\r\n");
		sb.append("购买方名称:" + this.gfmc + "\r\n");
		sb.append("购买方纳税人识别号:" + this.gfsbh + "\r\n");
		sb.append("购买方地址电话:" + this.gfdzdh + "\r\n");
		sb.append("购买方银行帐号:" + this.gfyhzh + "\r\n");
		sb.append("销售方名称:" + this.xfmc + "\r\n");
		sb.append("销售方纳税人识别号:" + this.text + "\r\n");
		sb.append("销售方地址电话:" + this.text + "\r\n");
		sb.append("销售方银行帐号:" + this.xfyhzh + "\r\n");
		sb.append("不含税金额:" + this.je + "\r\n");
		sb.append("税额:" + this.se + "\r\n");
		sb.append("价税合计(大写):" + this.jshjdx + "\r\n");
		sb.append("价税合计(小写):" + this.jshjxx + "\r\n");
		return sb.toString();
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void addItem(InvoiceItem item) {
		this.items.add(item);
	}

}
