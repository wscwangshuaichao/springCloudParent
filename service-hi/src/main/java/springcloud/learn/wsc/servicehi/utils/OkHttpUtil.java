package springcloud.learn.wsc.servicehi.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;

/**
* @ClassName: OkHttpUtil.java
* @Description: http请求util
* @author: ChongLi
* @date: 2019/6/4 1:27
* @version V1.0
*/
@Slf4j
public class OkHttpUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public  static  String post(String url,Map<String,String> params) throws Exception{
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry: params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public  static  String postGaiaHeader(String url,Map<String,String> params, String header) throws Exception{
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry: params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-gaia-api-key", header)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

}
