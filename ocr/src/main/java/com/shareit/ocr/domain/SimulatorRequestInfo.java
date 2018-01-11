package com.shareit.ocr.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class SimulatorRequestInfo {

	@NotNull(message = "发票代码不能为空")
	@NotEmpty(message = "发票代码不能为空")
	@Length(min = 10, max = 12, message = "发票代码需为10位或12位")
	private String fpdm;

	@NotNull(message = "发票号码不能为空")
	@NotEmpty(message = "发票号码不能为空")
	private String fphm;

	@NotNull(message = "发票开票日期不能为空")
	@NotEmpty(message = "发票开票日期不能为空")
	@Length(min = 8, max = 8, message = "发票开票日期需8位(YYYYMMDD)")
	private String kprq;

	private String kjje;

	private String jym;

	public SimulatorRequestInfo() {

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

	public String getKjje() {
		return kjje;
	}

	public void setKjje(String kjje) {
		this.kjje = kjje;
	}

	public String getJym() { return jym; }

	public void setJym(String jym) {
		this.jym = jym;
	}

	@Override
	public String toString() {
		return "SimulatorRequestInfo{" +
				"fpdm='" + fpdm + '\'' +
				", fphm='" + fphm + '\'' +
				", kprq='" + kprq + '\'' +
				", kjje='" + kjje + '\'' +
				", jym='" + jym + '\'' +
				'}';
	}

}



