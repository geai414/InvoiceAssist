package com.shareit.ocr.domain;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.shareit.ocr.exception.SimulatorException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.shareit.ocr.domain.Dama2Web.DecodeResult;
import com.shareit.ocr.enums.APIResultEnum;
import com.shareit.ocr.util.PicUtil;

public class Simulator {
	
	private final static Logger logger = LoggerFactory.getLogger(Simulator.class);
		
	//尝试次数
	private  int trycount ;
	
	//打码帐号
	private  String damaUsername;
	
	//打码帐号密码
	private  String damaPassword;
	
	//国税局网站地址
	private  String startURL;
	
	//浏览器模拟器
	private  HtmlUnitDriver webdriver;
	
	//UUID
	private String id="";
	
	public  int getTrycount() {
		return trycount;
	}

	public void setTrycount(int trycount) {
		this.trycount = trycount;
	}

	public  String getDamaUsername() {
		return damaUsername;
	}

	public void setDamaUsername(String damaUsername) {
		this.damaUsername = damaUsername;
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

	public Simulator() {
		//创建浏览器模拟器
		webdriver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER,true);
		webdriver.setJavascriptEnabled(true);
		System.out.println("BrowserVersion:" + webdriver.getBrowserVersion().toString());
		//设置浏览器模拟器等待渲染时间
		webdriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		id = UUID.randomUUID().toString();
	}
	
	public String getid() {
		return this.id;
	}
	
	public void close() {
		webdriver.quit();
	}
	
	private boolean isCorrectCode(String codeinfo) {
		
		return codeinfo.contains("红色") || codeinfo.contains("蓝色") || codeinfo.contains("黄色");
//		return codeinfo.contains("黄色");
	}
	
	private void consoleLog(String title,Object obj) {
		String detaillog ="";
		detaillog = "<" + this.id + "> :" + title + ">>>{}";
		logger.info(detaillog,obj);	
	}
	
	private void consoleLog(String log) {
		String detaillog ="";
		detaillog = "<" + this.id + "> :" + log;
		logger.info(detaillog);	
	}
	
	private void getYzm(Invoice vi) {
	
		consoleLog("开始获取验证码");
		
		WebElement web_fpdm;
		WebElement web_fphm;
		WebElement web_kprq;
		WebElement web_kjje;
		WebElement web_context;
		WebElement web_yzmimg = null;
		WebElement web_yzmimg_info = null;
		
		String img_src_attr="";
		String imgInfo ="";

		
		webdriver.get(startURL);
		imgInfo ="";
		img_src_attr ="";

		web_fpdm = webdriver.findElement(By.id("fpdm"));
		web_fpdm.sendKeys(vi.getFpdm());
		
		web_fphm = webdriver.findElement(By.id("fphm"));
		web_fphm.sendKeys(vi.getFphm());
		
		web_kprq = webdriver.findElement(By.id("kprq"));
		web_kprq.sendKeys(vi.getKprq());
		
		web_kjje = webdriver.findElement(By.id("kjje"));
		web_kjje.sendKeys(vi.getJe());
		
		if(webdriver.findElement(By.id("fpdmjy")).getText().contains("有误")) {
			throw new SimulatorException(APIResultEnum.INVOICE_FPDM_ERROR);
		}
		
		if(webdriver.findElement(By.id("fphmjy")).getText().contains("有误")) {
			throw new SimulatorException(APIResultEnum.INVOICE_FPHM_ERROR);
		}
		if(webdriver.findElement(By.id("kprqjy")).getText().contains("有误")) {
			throw new SimulatorException(APIResultEnum.INVOICE_FPRQ_ERROR);
		}
		
		web_context = webdriver.findElement(By.id("context"));
		if(web_context.getText().contains("校验码")) {
			web_kjje.clear();
			web_kjje.sendKeys(vi.getJym());
			vi.setIspp(true);
		}
		
		do {
			web_yzmimg = webdriver.findElement(By.id("yzm_img"));
			web_yzmimg.click();
			while (!img_src_attr.startsWith("data:")) {
				img_src_attr = web_yzmimg.getAttribute("src");
			}
		
			web_yzmimg_info=webdriver.findElement(By.id("yzminfo"));
			imgInfo = web_yzmimg_info.getText();
		
			if(imgInfo.contains("黄色")) {
				imgInfo = "黄色验证码";
			}else if(imgInfo.contains("蓝色")) {
				imgInfo = "蓝色验证码";
			}else if (imgInfo.contains("红色")) {
				imgInfo = "红色验证码";
			}
		}
		while(!isCorrectCode(imgInfo));
		
		DecodeResult decoderesult = null;
		Dama2Web dw = new Dama2Web(damaUsername,damaPassword);
		consoleLog("处理验证码图片");
		byte[] data = PicUtil.getCodeData(img_src_attr, imgInfo, 30);
		consoleLog("处理验证码图片完成，开始获取验证码结果");
		decoderesult = dw.d2File(200, 60, data);
		consoleLog("获取到验证码结果 " + decoderesult.result);
		
		WebElement web_yzm = null;
		WebElement web_checkfp=null;
		WebElement web_popup_message = null;
		String message = "";
		
		web_yzm = webdriver.findElement(By.id("yzm"));
		web_yzm.sendKeys(decoderesult.result);
		
		
		web_checkfp = webdriver.findElement(By.id("checkfp"));
		consoleLog("点击验证");

		web_checkfp.click();
		consoleLog("点击验证完成");
		
		webdriver.switchTo().defaultContent();
	    try{
	    	web_popup_message =webdriver.findElement(By.id("popup_message")); 
	    	message = web_popup_message.getText();
	    	consoleLog("弹出错误对话框:" + message);
	    	throw new SimulatorException(APIResultEnum.VALID_ERROR);		
	    }catch(NoSuchElementException e){
	    	consoleLog("未弹出错误对话框");
	    }

	    consoleLog("切换到 dialog-body");
	    try {
		 webdriver.switchTo().frame("dialog-body");
	  
	    }
	    catch(NoSuchFrameException e) {
    	  message = "未找到frame dialog-body";
    	  consoleLog(message);
	    }	   
	}
	
	@SuppressWarnings("resource")
	public Invoice Verify(SimulatorRequestInfo sri) {
		
		consoleLog("接收到请求",sri);
		Invoice vi = new Invoice(sri);

		int try_count = 0;
		
		while(try_count <= trycount) {
			
			try_count =try_count + 1;
			consoleLog("尝试第[" + try_count + "]次获取验证码");
			try {
				getYzm(vi);
				break;
			}catch(SimulatorException se) {
				if(try_count == trycount) {
					throw se;
				}else
					continue;
			}catch(Exception e) {
				if(try_count == trycount) {
					throw new SimulatorException(APIResultEnum.UNKOWN_ERROR);
				}else
					continue;
			}
		}
		
		if(try_count > trycount) {
			throw new SimulatorException(APIResultEnum.UNKOWN_ERROR);
		}else {
			consoleLog("验证码通过验证,获取网页发票详细数据");
			DetailInfo(vi);
//			consoleLog("发票数据",vi);
		}
		return vi;
	}
	
	public void DetailInfo(Invoice vi) {
	
      if(webdriver.findElements(By.id("cyjg")).size() != 0){
    	  throw new SimulatorException(APIResultEnum.VALID_ERROR);
      }
	  try{ 
	      if(!vi.isIspp()) {
			  vi.setText(webdriver.findElement(By.id("fpcc_zp")).getText());
			  vi.setFpdm(webdriver.findElement(By.id("fpdm_zp")).getText());
			  vi.setFphm(webdriver.findElement(By.id("fphm_zp")).getText());
			  vi.setKprq(webdriver.findElement(By.id("kprq_zp")).getText());
			  vi.setJym(webdriver.findElement(By.id("jym_zp")).getText());
			  vi.setJqbh(webdriver.findElement(By.id("jqbh_zp")).getText());
			  vi.setGfmc(webdriver.findElement(By.id("gfmc_zp")).getText());
			  vi.setGfsbh(webdriver.findElement(By.id("gfsbh_zp")).getText());
			  vi.setGfdzdh(webdriver.findElement(By.id("gfdzdh_zp")).getText());
			  vi.setGfyhzh(webdriver.findElement(By.id("gfyhzh_zp")).getText());
			  vi.setXfmc(webdriver.findElement(By.id("xfmc_zp")).getText());
			  vi.setXfsbh(webdriver.findElement(By.id("xfsbh_zp")).getText());
			  vi.setXfdzdh(webdriver.findElement(By.id("xfdzdh_zp")).getText());
			  vi.setXfyhzh(webdriver.findElement(By.id("xfyhzh_zp")).getText());
			  vi.setJe(webdriver.findElement(By.id("je_zp")).getText());
			  vi.setSe(webdriver.findElement(By.id("se_zp")).getText());
			  vi.setJshjdx(webdriver.findElement(By.id("jshjdx_zp")).getText());
			  vi.setJshjxx(webdriver.findElement(By.id("jshjxx_zp")).getText());
	      }else {
	    	  vi.setText(webdriver.findElement(By.id("fpcc_pp")).getText());
	    	  vi.setFpdm(webdriver.findElement(By.id("fpdm_pp")).getText());
	    	  vi.setFphm(webdriver.findElement(By.id("fphm_pp")).getText());
	    	  vi.setKprq(webdriver.findElement(By.id("kprq_pp")).getText());
	    	  vi.setJym(webdriver.findElement(By.id("jym_pp")).getText());
	    	  vi.setJqbh(webdriver.findElement(By.id("jqbh_pp")).getText());
	    	  vi.setGfmc(webdriver.findElement(By.id("gfmc_pp")).getText());
	    	  vi.setGfsbh(webdriver.findElement(By.id("gfsbh_pp")).getText());
	    	  vi.setGfdzdh(webdriver.findElement(By.id("gfdzdh_pp")).getText());
	    	  vi.setGfyhzh(webdriver.findElement(By.id("gfyhzh_pp")).getText());
	    	  vi.setXfmc(webdriver.findElement(By.id("xfmc_pp")).getText());
	    	  vi.setXfsbh(webdriver.findElement(By.id("xfsbh_pp")).getText());
	    	  vi.setXfdzdh(webdriver.findElement(By.id("xfdzdh_pp")).getText());
	    	  vi.setXfyhzh(webdriver.findElement(By.id("xfyhzh_pp")).getText());
	    	  vi.setJe(webdriver.findElement(By.id("je_pp")).getText());
	    	  vi.setSe(webdriver.findElement(By.id("se_pp")).getText());
	    	  vi.setJshjdx(webdriver.findElement(By.id("jshjdx_pp")).getText());
	    	  vi.setJshjxx(webdriver.findElement(By.id("jshjxx_pp")).getText()); 
	      }	
	      
	      //获取明细信息
	      String parent_xpath = "//table[@class='fppy_table_box']/tbody/tr";
	      List<WebElement> elements = webdriver.findElements(By.xpath(parent_xpath));
	      
	      //当前tr中包含了 明细抬头和 合计行数据，需要去掉
	      if(elements.size() > 3) {
		      for (int i = 1; i < elements.size() -2; i++) {
		    	WebElement element = elements.get(i);
				InvoiceItem item = new InvoiceItem();
				item.setXmmc(element.findElements(By.xpath(".//td[1]/span")).get(0).getText());
				item.setXh(element.findElements(By.xpath(".//td[2]/span")).get(0).getText());
				item.setDw(element.findElement(By.xpath(".//td[3]/span")).getText());
				item.setSl(element.findElement(By.xpath(".//td[4]/span")).getText());
				item.setDj(element.findElement(By.xpath(".//td[5]/span")).getText());
				item.setJe(element.findElement(By.xpath(".//td[6]/span")).getText());
				item.setRate(element.findElement(By.xpath(".//td[7]/span")).getText());
				item.setSe(element.findElement(By.xpath(".//td[8]/span")).getText());
				vi.addItem(item);
			}
	      } 
	      
	  }catch(Exception e) {
		    e.printStackTrace();
	    	throw new SimulatorException(APIResultEnum.PAGE_ERROR);
	    }
	}
}
