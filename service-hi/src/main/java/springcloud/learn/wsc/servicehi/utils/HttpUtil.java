package springcloud.learn.wsc.servicehi.utils;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @description http请求工具类
 * @author bazhandao
 * @date 2018/11/10 15:58
 * @since JDK1.8
 */
@SuppressWarnings("all")
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static HttpClient client = HttpClients.createDefault();

    /**
     * 发送post请求，form表单格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, Map<String, Object> param) {
        return post(client,url,param, null);
    }

    /**
     * 发送post请求，form表单格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String post(String url, Map<String, Object> param, Map<String, Object> headers) {
        return post(client,url,param, headers);
    }

    /**
     * 发送post请求，form表单格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @param headers 请求头 可空
     * @return
     */
    public static String post(HttpClient client, String url, Map<String, Object> param, Map<String, Object> headers) {
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        post.setConfig(requestConfig);
        if(headers != null) {
            for(Map.Entry<String,Object> h : headers.entrySet()) {
                post.addHeader(h.getKey(), String.valueOf(h.getValue()));
            }
        }
        //设置参数
        List<NameValuePair> list = new ArrayList<>();
        for(Map.Entry<String, Object> e : param.entrySet()){
            list.add(new BasicNameValuePair(e.getKey(),String.valueOf(e.getValue())));
        }
        try {
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
                post.setEntity(entity);
            }
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            logger.error("http post request error! url=" + url + ",param=" + param, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param headers 请求头 可空
     * @return
     */
    public static String get(String url, Map<String, Object> headers) {
        HttpClient client = HttpClients.createDefault();
        return get(client,url,null, headers);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param   可空
     * @param headers 请求头 可空
     * @return
     */
    public static String get(String url, Map<String, Object> param, Map<String, Object> headers) {
        HttpClient client = HttpClients.createDefault();
        return get(client,url,param, headers);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @return
     */
    public static String get(HttpClient client, String url) {
        return get(client,url,null,null);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String get(HttpClient client, String url, Map<String, Object> param, Map<String, Object> headers) {
        StringBuilder sb = new StringBuilder();
        if(param != null){
            for(Map.Entry<String, Object> e : param.entrySet()){
                String k = e.getKey();
                String v = String.valueOf(e.getValue());
                sb.append("&").append(k).append("=").append(v);
            }
        }
        String query = sb.toString();
        if(query.length() >= 1){
            if(url.indexOf("?") == -1){
                query = query.replaceFirst("&", "");
                url = url + "?" + query;
            }else{
                url = url + query;
            }
        }
        HttpGet get = new HttpGet(url);
        if(headers != null) {
            for(Map.Entry<String,Object> h : headers.entrySet()) {
                get.addHeader(h.getKey(), String.valueOf(h.getValue()));
            }
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        get.setConfig(requestConfig);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            logger.error("http get request error! url=" + url + ",param=" + param, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 发送post请求,raw body格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param body
     * @Param headers 请求头 可空
     * @return
     */
    public static String postBody(String url, String body, Map<String, Object> headers) {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        if(headers != null) {
            for(Map.Entry<String,Object> h : headers.entrySet()) {
                post.addHeader(h.getKey(), String.valueOf(h.getValue()));
            }
        }
        post.setEntity(new StringEntity(body, "UTF-8"));
        try {
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            logger.error("[postBody] http post body error! url=" + url + ",body=" + body, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String postBodyByAuth(String url, String body, Map<String, Object> headers,Map<String,String> auth) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(auth.get("Username"), auth.get("Password"));
        provider.setCredentials(AuthScope.ANY, credentials);
        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpPost post = new HttpPost(url);
        if(headers != null) {
            for(Map.Entry<String,Object> h : headers.entrySet()) {
                post.addHeader(h.getKey(), String.valueOf(h.getValue()));
            }
        }
        post.setEntity(new StringEntity(body, "UTF-8"));
        try {
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            logger.error("[postBody] http post body error! url=" + url + ",body=" + body, e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
