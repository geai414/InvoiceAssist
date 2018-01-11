package com.shareit.ocr.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "UI")
public class UIProperties {

	private String headerImgSource;
	
	private String scanImgSource;
	
	private String inputImgSource;

	public String getHeaderImgSource() {
		return headerImgSource;
	}

	public void setHeaderImgSource(String headerImgSource) {
		this.headerImgSource = headerImgSource;
	}

	public String getScanImgSource() {
		return scanImgSource;
	}

	public void setScanImgSource(String scanImgSource) {
		this.scanImgSource = scanImgSource;
	}

	public String getInputImgSource() {
		return inputImgSource;
	}

	public void setInputImgSource(String inputImgSource) {
		this.inputImgSource = inputImgSource;
	}
}
