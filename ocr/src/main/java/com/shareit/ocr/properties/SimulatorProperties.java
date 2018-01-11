package com.shareit.ocr.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "simulator")
public class SimulatorProperties {
	
	//尝试次数
	private int tryCount;
	
	//打码帐号
	private String damaUserName;
	
	//打码帐号密码
	private String damaPassword;
	
	//国税局网站URL
	private String startURL;

	public int getTryCount() {
		return tryCount;
	}

	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}

	public String getDamaUserName() {
		return damaUserName;
	}

	public void setDamaUserName(String damaUserName) {
		this.damaUserName = damaUserName;
	}

	public String getDamaPassword() {
		return damaPassword;
	}

	public void setDamaPassword(String damaPassword) {
		this.damaPassword = damaPassword;
	}

	public String getStartURL() {
		return startURL;
	}

	public void setStartURL(String startURL) {
		this.startURL = startURL;
	}
	
	

}
