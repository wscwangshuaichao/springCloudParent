package springcloud.learn.wsc.servicehi.business;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @Author wangshuaichao
 * 调用外部http链接的service
 * @Date 10:02 2019/5/17
 * @Param 
 * @return 
 * @throws        
 **/
@SuppressWarnings("all")
@Slf4j
@Service
public class HandleExternalRequest {

	/**
	 * @Author wangshuaichao
	 * @不带参数调用外部http链接
	 * @Date 10:03 2019/5/17
	 * @Param [url]
	 * @return java.lang.String
	 * @throws
	 **/
	public  String getUrlSource(String url) {
		URL urlmy = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		String s = "";
		try {
			urlmy = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			con.setInstanceFollowRedirects(false);
			con.connect();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			log.error("调用"+url+"--->接口异常！");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @Author wangshuaichao
	 * @通过post请求调用外部http链接，参数类型为Map<String, Object>
	 * @Date 10:04 2019/5/17
	 * @Param [url, map]
	 * @return java.lang.String
	 * @throws        
	 **/
	public  String getUrlPostCntins(String url, Map<String, Object> map) {
		String str = null;
		PostMethod postMethod = new PostMethod(url);
		for (String key : map.keySet()) {
			postMethod.addParameter(key, map.get(key).toString());
		}
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(30000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(120000);
		int status = 0;
		try {
			status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				str = postMethod.getResponseBodyAsString();
			}
			postMethod.releaseConnection();

		} catch (Exception e) {
			log.error("调用"+url+"--->接口异常！" + map.toString());
			e.printStackTrace();
		}
		return str;
	}

	public  String getUrlPostRequest(String url, Map<String, String> map) {
		String str = null;
		PostMethod postMethod = new PostMethod(url);
		for (String key : map.keySet()) {
			postMethod.addParameter(key, map.get(key).toString());
		}
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(30000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(120000);
		int status = 0;
		try {
			status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				str = postMethod.getResponseBodyAsString();
			}
			postMethod.releaseConnection();

		} catch (Exception e) {
			log.error("调用"+url+"--->接口异常！" + map.toString());
			e.printStackTrace();
		}
		return str;
	}

	private static RequestConfig requestConfig = null;

	static
	{
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
	}

	/**
	 * post请求传输json参数
	 * @param url  url地址
	 * @param json 参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam)
	{
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try
		{
			if (null != jsonParam)
			{
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				String str = "";
				try
				{
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				}
				catch (Exception e)
				{
					log.error("post请求提交失败:" + url, e);
				}
			}
		}
		catch (IOException e)
		{
			log.error("post请求提交失败:" + url, e);
		}
		finally
		{
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type:application/x-www-form-urlencoded
	 * @param url            url地址
	 * @param strParam       参数
	 * @return
	 */
	public static JSONObject httpPost(String url, String strParam)
	{
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try
		{
			if (null != strParam)
			{
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				String str = "";
				try
				{
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				}
				catch (Exception e)
				{
					log.error("post请求提交失败:" + url, e);
				}
			}
		}
		catch (IOException e)
		{
			log.error("post请求提交失败:" + url, e);
		}
		finally
		{
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * @param url 路径
	 * @return
	 */
	public static JSONObject httpGet(String url)
	{
		// get请求返回结果
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try
		{
			CloseableHttpResponse response = client.execute(request);

			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(strResult);
			}
			else
			{
				log.error("get请求提交失败:" + url);
			}
		}
		catch (IOException e)
		{
			log.error("get请求提交失败:" + url, e);
		}
		finally
		{
			request.releaseConnection();
		}
		return jsonResult;
	}

}
