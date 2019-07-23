package springcloud.learn.wsc.servicehi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
@Slf4j
public class CommonUtils {

	private static int index = 0;

	/**
	 * GSON工具类，用于把对象转为JSON。
	 */
	static final Gson GSON;

	static {
		GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
	}

	/**
	 * 将实体或集合转换成json。
	 * 
	 * @param src
	 * @return
	 */
	public static String toJson(Object src) {
		return GSON.toJson(src);
	}
	
	/**
     * 获取访问ip。
     *
     * @param request
     * @return
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");
        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = forwarded;
            }
        }
        return ip;
    }

	/**
	 *
	 * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
	 * 实现步骤: <br>
	 *
	 * @param paraMap   要排序的Map对象
	 * @param urlEncode   是否需要URLENCODE
	 * @param keyToLower    是否需要将Key转换为全小写
	 *            true:key转化成小写，false:不转化
	 * @return
	 */
	public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower)
	{
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		try
		{
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
			{

				@Override
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
				{
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds)
			{
				if (StringUtils.isNotBlank(item.getKey()))
				{
					String key = item.getKey();
					String val = item.getValue();
					if (urlEncode)
					{
						val = URLEncoder.encode(val, "utf-8");
					}
					if (keyToLower)
					{
						buf.append(key.toLowerCase() + "=" + val);
					} else
					{
						buf.append(key + "=" + val);
					}
					buf.append("&");
				}

			}
			buff = buf.toString();
			if (buff.isEmpty() == false)
			{
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e)
		{
			return null;
		}
		return buff;
	}

	/**
	 * 用于后台向前端返回json
	 *
	 * @param response
	 * @param src
	 */
	public static void writerJson(HttpServletResponse response, Object src) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		try {
			PrintWriter out = response.getWriter();
			out.write(GSON.toJson(src));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用于后台向前端返回字符串。
	 * 
	 * @param response
	 */
	public static void writerStr(HttpServletResponse response, String str) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		try {
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * json转list
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String, String>> parseJSON2List(String jsonStr)
			throws Exception {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(fromJson(json2.toString()));
		}
		return list;
	}

	/**
	 * json字符串转为map。
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> fromJsonMapobj(String json) {
		Map<String, Object> jsonMap = null;
		try {
			json = json.replaceAll("\r", "").replaceAll("\n", "");
			jsonMap=new Gson().fromJson(json,new TypeToken<Map<String,Object>>(){}.getType());
		} catch (Exception e) {
			log.error("JSON解析错误：" + CommonUtils.getPrintStackTrace(e) + "json:" + json);
		}
		return jsonMap;
	}

	/**
	 * json数组转为map。
	 *
	 * @param json
	 * @return
	 */
	public static Map<String, String> fromJson(String json) {
		Map<String, String> jsonMap = null;
		try {
			json = json.replaceAll("\r", "").replaceAll("\n", "");
			JSONArray jsonArray = JSONArray.fromObject("[" + json + "]");
			jsonMap = new HashMap<String, String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
					String key = (String) iter.next();
					String value = jsonObject.get(key).toString();
					jsonMap.put(key, value);
				}
			}
		} catch (Exception e) {
			log.error("JSON解析错误：" + CommonUtils.getPrintStackTrace(e)
					+ "json:" + json);
		}
		return jsonMap;
	}

	/**
	 * 将json转化为实体POJO
	 * 
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static <T> Object jsonToObject(String jsonStr, Class<T> obj) {
		T t = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"));
			t = objectMapper.readValue(jsonStr, obj);
		} catch (Exception e) {
			log.equals("json转Object异常,json:" + jsonStr + ",message"
					+ CommonUtils.getPrintStackTrace(e));
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将实体POJO转化为JSON
	 * 
	 * @param obj
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws org.json.JSONException
	 */
	public static <T> org.json.JSONObject objectToJson(T obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string
		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw e;
		}
		return new org.json.JSONObject(jsonStr);
	}

	/**
	 * 将json格式封装的列表数据转换成java的List数据
	 * 
	 * @return
	 */
	public static <T> List<T> jsonToList(String json, Class<T> obj) {
		try {
			return JSONArray.toList(JSONArray.fromObject(json),obj.newInstance(), new JsonConfig());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回错误信息。
	 * 
	 * @param e
	 * @return
	 */
	public static String getPrintStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		String str = sw.toString();
		return str;
	}

	/**
	 * 判断字符串是否为空。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (null != str && !"".equals(str.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 读取配置文件。
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Map<String, String> readProperties(InputStream inputStream) {
		// 生成输入流
		InputStreamReader is = null;
		// 生成properties对象
		Properties properties = new Properties();
		try {
			is = new InputStreamReader(inputStream, "UTF-8");
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		Enumeration en = properties.keys();
		while (en.hasMoreElements()) {
			String key = en.nextElement().toString().trim();
			resultMap.put(key, properties.getProperty(key).trim());
		}
		return resultMap;
	}

	/**
	 * 将一个base64转换成图片保存在服务器上。
	 *
	 * @param path
	 *            是一个文件夹路径
	 * @param imgName
	 *            图片名字
	 * @throws Exception
	 */
	public static void decodeBase64ToImage(String base64, String path,
			String imgName) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			FileOutputStream write = new FileOutputStream(new File(path + imgName));
			byte[] decoderBytes = decoder.decodeBuffer(base64.replace("data:image/jpeg;base64,", ""));
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个url的图片地址转换成图片保存在服务器上。
	 * @param path
	 *            是一个文件夹路径
	 * @param imgName
	 *            图片名字
	 * @throws Exception
	 */
	public static void decodeUrlToImage(String urlString, String path, String imgName) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		OutputStream os = new FileOutputStream(path + imgName);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 龙信发送消息id生成。
	 * 
	 * @return
	 */
	public static String getMsgId() {
		String msgid = "";
		if (index >= 9999) {
			index = 0;
		}
		if (index > 9) {
			msgid = "000" + index;
		} else if (index > 99) {
			msgid = "00" + index;
		} else if (index > 999) {
			msgid = "0" + index;
		} else if (index >= 9000 && index <= 9999) {
			msgid = "" + index;
		}
		index++;
		return msgid;
	}

	/**
	 * 生成随机名称。
	 * 
	 * @return
	 */
	public static String getRandomName() {
		// 生成随机名称
		String nowDate = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		int random = (int) ((Math.random() * 9 + 1) * 100000);
		return nowDate + random;
	}

	/**
	 * 算出两个日期里的天数。
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long betWeenDays(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		long time2 = cal2.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return between_days;
	}

	/**
	 * @param date
	 * @param day
	 *            想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
	 * @return
	 */
	public static String getSomeDay(Date date, int day) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(calendar.getTime());
		return dateStr;
	}

	/**
	 * @Author wangshuaichao
	 * @获取请求的ip
	 * @Date 10:44 2019/5/17
	 * @Param [request]
	 * @return java.lang.String
	 * @throws
	 **/
	public static String getIpAddr(HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			StringBuffer buf = new StringBuffer("all head info:\n");
			Enumeration enumeration = request.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				Object head = enumeration.nextElement();
				if (null != head) {
					String value = request.getHeader(String.valueOf(head));
					buf.append(head + "=" + value + "\n");
				}
			}
			log.debug(buf.toString());
		}

		String ip = request.getHeader("x-forwarded-for");
		log.debug("request.getHeader(\"x-forwarded-for\")=" + ip);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
			log.debug("request.getHeader(\"X-Forwarded-For\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.debug("request.getHeader(\"Proxy-Client-IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.debug("request.getHeader(\"WL-Proxy-Client-IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			log.debug("request.getHeader(\"HTTP_CLIENT_IP\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			log.debug("request.getHeader(\"HTTP_X_FORWARDED_FOR\")=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.debug("request.getRemoteAddr()=" + ip);
		}

		if (null != ip && ip.indexOf(',') != -1) {
			log.debug("ip=" + ip);
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (null != ips[i] && !"unknown".equalsIgnoreCase(ips[i])) {
					ip = ips[i];
					break;
				}
			}
		}

		log.debug("====================================================");
		return ip;
	}

	/**
	 * map转bean
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	 public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
	 	if (map == null) return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
     }

	  public static Map<?, ?> objectToMap(Object obj) {
		if(obj == null)
				 return null;

		 return new org.apache.commons.beanutils.BeanMap(obj);
	 }

	/**
	 * 获取用户信息。不存在去eds拉取再返回
	 *
	 * @param
	 * @param
	 * @return
	 */
//	public static String getUserInfo(String username, RedisTemplate redis11141) {
//		String result = redis11141.get("FINAL_USER_" + username);
//		if(!StringUtils.isEmpty(result)) {
//			return result;
//		}else {
//				Map map = new HashMap<>();
//				map.put("loginName", username);
//				String httpsPost = HttpUtil.get("http://192.168.11.141:9055/api/tongbuByUsername", map);
//				log.info("eds同步人员到141："+ username);
//				result = redis11141.get("FINAL_USER_" + username);
//				if(StringUtils.isEmpty(result)) {
//					return "";
//				}
//		}
//		return result;
//
//	}

	 /**
		 * 通过141redis usercode 获取 truename  不存在手动刷新一次 还不存在返回""
		 * @param usercode
		 * @return
		 */
//		public static String tbUserTo141(String usercode,RedisTemplate redis11141) {
//			String truename = null;
//			String userInfo = redis11141.get("FINAL_USER_" + usercode);
//
//			if(!StringUtils.isEmpty(userInfo)) {
//				// 用户信息
//				Map<String, String> userMap = CommonUtils.fromJson(userInfo);
//				truename = userMap.get("true_name");
//			}else {
//					Map map = new HashMap<>();
//					map.put("loginName", usercode);
//					String httpsPost = HttpUtil.get("http://192.168.11.141:9055/api/tongbuByUsername", map);
//					log.info("eds同步人员到141："+ usercode);
//					 userInfo = CommonUtils.getUserInfo(usercode, redis11141);
//					 if(!StringUtils.isEmpty(userInfo)) {
//							// 用户信息
//							Map<String, String> userMap = CommonUtils.fromJson(userInfo);
//							truename = userMap.get("true_name");
//					 }
//
//			}
//			return truename;
//		}
	
}