package ocr;

import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;  
import org.apache.http.conn.ssl.SSLContextBuilder;  
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
  
import javax.net.ssl.SSLContext;  
import java.io.IOException;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
  
/** 
 * 名称：用于进行post请求的HttpClient  <br> 
 * 作者: 崔石磊<br> 
 * 日期: 2016/12/20<br> 
 * <p> 
 * 修改记录：10:11 新建 A 
 */  
public class SSLClient {  
  
    //请求配置，设置链接超时和读取超时  
    private static final RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();  
  
    //https策略，绕过安全检查  
    private static CloseableHttpClient getSingleSSLConnection()  
            throws Exception {  
        //CloseableHttpClient httpClient = null;  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
                public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {  
                    return true;  
                }  
            }).build();  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
  
            return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();  
        } catch (Exception e) {  
            throw e;  
        }  
  
    }  
  
    /** 
     * HTTP Post 获取内容 
     * 
     * @param params     请求的参数，key-value形式 
     * @param reqMsg     请求的参数，字符串 
     * @param url        请求的url地址 ?之前的地址 
     * @param reqCharset 编码格式 
     * @param resCharset 编码格式 
     * @return 页面内容 
     */  
    public static String doPost(Map<String, String> params, String url, String reqMsg, String reqCharset, String resCharset) throws Exception {  
        //获取绕过安全检查的httpClient，以便发送https请求  
        CloseableHttpClient httpClient = getSingleSSLConnection();  
        CloseableHttpResponse response = null;  
         
        try {  
            //创建httppost方法  
            HttpPost httpPost = new HttpPost(url);  
            //添加head，需要什么填什么  
//            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");  
//            httpPost.addHeader("Content-type", "text/html,application/xhtml+xml,application/xml");  
//  		httpPost.setHeader("Accept", "Accept	application/javascript, */*;q=0.8");
            httpPost.setHeader("Referer", "https://inv-veri.chinatax.gov.cn/");
            httpPost.setHeader("Accept-Language", "zh-CN");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("Cache-Control", "no-cache");
            //组装请求参数，key-value形式的  
            List<NameValuePair> pairs = null;  
            if (params != null && !params.isEmpty()) {  
                pairs = new ArrayList<NameValuePair>(params.size());  
                for (Map.Entry<String, String> entry : params.entrySet()) {  
                    String value = entry.getValue();  
                    if (value != null) {  
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));  
                    }  
                }  
            }  
            if (pairs != null && pairs.size() > 0) {  
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, reqCharset));  
            }  
            //这个是直接post一串字符串的方式，如json串，并不用带key  
            /*StringEntity reqEntity = new StringEntity(reqMsg, "GBK");//解决中文乱码问题 
            reqEntity.setContentEncoding("GBK"); 
            reqEntity.setContentType("application/json"); 
            httpPost.setEntity(reqEntity);*/  
  
            HttpEntity entity = null;  
            String result = null;  
            //执行post方法  
            response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != 200) {//出现链接异常，抛出  
                httpPost.abort();
                System.out.println("url:" + response.getFirstHeader("location"));
                throw new Exception("HttpClient,error status code :" + statusCode);  
            }  
            //获得返回结果  
            entity = response.getEntity();  
            if (entity != null) {  
                //返回结果转为字符串  
                result = EntityUtils.toString(entity, resCharset);  
            }  
            EntityUtils.consume(entity);  
            response.close();  
            return result;  
        } catch (Exception e) {  
            throw e;  
        } finally {  
            if (response != null)  
                try {  
                    response.close();  
                } catch (IOException e) {  
                }  
        }  
    }  
    
    /** 
     * HTTP Post 获取内容 
     * 
     * @param params     请求的参数，key-value形式 
     * @param reqMsg     请求的参数，字符串 
     * @param url        请求的url地址 ?之前的地址 
     * @param reqCharset 编码格式 
     * @param resCharset 编码格式 
     * @return 页面内容 
     */  
    public static String doGet(Map<String, String> params, String url, String reqMsg, String reqCharset, String resCharset) throws Exception {  
        //获取绕过安全检查的httpClient，以便发送https请求  
        CloseableHttpClient httpClient = getSingleSSLConnection();  
        CloseableHttpResponse response = null;  
        HttpGet get =null; 
        String parms ="";
        try {  
            //创建httppost方法  
            
            url = url + "?";
            //组装请求参数，key-value形式的  
            List<NameValuePair> pairs = null;  
            if (params != null && !params.isEmpty()) {  
                pairs = new ArrayList<NameValuePair>(params.size());  
                for (Map.Entry<String, String> entry : params.entrySet()) {  
                    String value = entry.getValue();  
                    parms = parms + entry.getKey() + "=" + java.net.URLEncoder.encode(entry.getValue(),reqCharset)+ "&";  
                }  
            }  
            url = url + parms.substring(0, parms.length() -1);
            System.out.println(url);
            get = new HttpGet(url);
            get.setHeader("Accept", "Accept	application/json, */*;q=0.8");
            get.setHeader("Referer", "https://inv-veri.chinatax.gov.cn/");
            get.setHeader("Accept-Language", "zh-CN");
            get.setHeader("User-Agent", "User-Agent	Mozilla/5.0 (MSIE 9.0; Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
            get.setHeader("Accept-Encoding", "gzip, deflate");
            get.setHeader("Connection", "keep-alive");
            get.setHeader("Cache-Control", "no-cache");
            
            String cookie = "JSESSIONID=VqGgBbAqlXQRqIjVeTRYYhlrk-hd0wQ1GBkh5fVvePmsGA1RX8S_!-1131135163";
            get.addHeader(new BasicHeader("Cookie",cookie));
            
            //这个是直接post一串字符串的方式，如json串，并不用带key  
            /*StringEntity reqEntity = new StringEntity(reqMsg, "GBK");//解决中文乱码问题 
            reqEntity.setContentEncoding("GBK"); 
            reqEntity.setContentType("application/json"); 
            httpPost.setEntity(reqEntity);*/  
            
            HttpEntity entity = null;  
            String result = null;  
            //执行post方法  
            response = httpClient.execute(get);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != 200) {//出现链接异常，抛出  
            	result = EntityUtils.toString(response.getEntity(), resCharset); 
            	 System.out.println("result:" + result);
                throw new Exception("HttpClient,error status code :" + statusCode);  
            }  
            //获得返回结果  
            entity = response.getEntity();  
            if (entity != null) {  
                //返回结果转为字符串  
                result = EntityUtils.toString(entity, resCharset);  
            }  
            EntityUtils.consume(entity);  
            response.close();  
            return "response :" + result;  
        } catch (Exception e) {  
            throw e;  
        } finally {  
            if (response != null)  
                try {  
                    response.close();  
                } catch (IOException e) {  
                }  
        }  
    }  
}  