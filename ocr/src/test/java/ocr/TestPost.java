package ocr;


import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;
public class TestPost {

	public static void main(String[] args) {
		
		String url="";
		Map<String,String> map = new HashMap<String,String>();
//		map.put("fpdm", "3100171320");
//		map.put("nowtime", "1514429834564");
//		map.put("r", "0.10778122784248456");
//		map.put("publickey", "E5BC117C9591B0C43711A577DC8F3791");
//		
//		url ="https://fpcyweb.tax.sh.gov.cn:1001/WebQuery/yzmQuery";
//		url = url + '?'+ HttpClientFactory.convertStringParamter(map);
//		System.out.println("response:  " + HttpClientFactory.sendHttpGet(url));
//		return ;
		
		url = "https://fpcyweb.tax.sh.gov.cn:1001/WebQuery/query";
//		url = url + '?'+ HttpClientFactory.convertStringParamter(map);
		
		map.put("fpdm", "3100171320");
		map.put("fphm", "22271367");
		map.put("fpje", "302399");
		map.put("fplx", "04");
		map.put("yzm", "S");
		map.put("yzmSj", "2017-12-29 09:55:07");
		map.put("index", "476bcf28d111354ee99945ccd6053475");
		map.put("iv","f2ba81f88ec903c1c89faa4bde23d9ba");
		map.put("kprq", "20171017");
		map.put("salt","c0d451bbed678c26f76c23b1b475b5aa");
		map.put("publickey", "9BD60F9600FAF6F2249A77AA39DAC308");
//		url = url + '?'+ HttpClientFactory.convertStringParamter(map);
//		System.out.println("response:  " + HttpClientFactory.sendHttpGet(url));
		try {
			System.out.println(SSLClient.doGet(map,url, "", "GBK", "GBK"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}

}
