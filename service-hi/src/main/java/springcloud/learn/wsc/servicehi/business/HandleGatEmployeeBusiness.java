package springcloud.learn.wsc.servicehi.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gf.lc.gat.exchange.config.GuanAiTongConfig;
import com.longfor.gf.lc.gat.exchange.utils.CommonUtils;
import com.longfor.gf.lc.gat.exchange.utils.GatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangshuaichao
 * @ClassName: HandleGatEmployeeBusiness
 * @Decription TOO
 * @Date 2019/6/13 19:22
 **/
@SuppressWarnings("all")
@Slf4j
@Service
public class HandleGatEmployeeBusiness {
    @Autowired
    private GatUtil gatUtil;
    @Autowired
    private GuanAiTongConfig guanAiTongConfig;
    @Autowired
    private HandleExternalRequest handleExternalRequest;

    /**
     * @Description 查询关爱通人员是否存在
     **/
    public JSONObject gatEmployeeGet(Map<String,String> map){
        long curTime = System.currentTimeMillis();
        log.info("[RequestParameter] - [gatEmployeeGet] - request:[{}]", JSONObject.toJSONString(map));
        Map<String, String> queryMap = new ConcurrentHashMap<String, String>();
        queryMap.put("access_token", map.get("access_token").toString());
        queryMap.put("timestamp", map.get("timestamp").toString());
        queryMap.put("corp_code", map.get("corp_code").toString() + "");
        queryMap.put("appsecret",guanAiTongConfig.getAppSecret());
        queryMap.put("sign", gatUtil.getSha1(CommonUtils.formatUrlMap(queryMap,false,true)));
        queryMap.remove("appsecret");
        String queryRes = handleExternalRequest.getUrlPostRequest(guanAiTongConfig.getEmployeeGetUrl(),queryMap);
        JSONObject jsonQueryRes = JSON.parseObject(queryRes);
        log.info("[ResponseParameter] - [gatEmployeeGet] - response:[{}] - duration:[{}]",JSONObject.toJSONString(jsonQueryRes),
                System.currentTimeMillis()-curTime);
        return jsonQueryRes;
    }

    /**
     * @Description 新增关爱通人员
     **/
    public JSONObject gatEmployeeAdd(Map<String,String> map){
        long curTime = System.currentTimeMillis();
        log.info("[RequestParameter] - [gatEmployeeAdd] - request:[{}]", JSONObject.toJSONString(map));
        String addRes = handleExternalRequest.getUrlPostRequest(guanAiTongConfig.getEmployeeAddUrl(),map);
        JSONObject jsonAddRes = JSON.parseObject(addRes);
        log.info("[ResponseParameter] - [gatEmployeeAdd] - response:[{}] - duration:[{}]",JSONObject.toJSONString(jsonAddRes),
                System.currentTimeMillis()-curTime);
        return jsonAddRes;
    }

    /**
     * @Description 更新关爱通人员
     **/
    public JSONObject gatEmployeeUpdate(Map<String,String> map){
        long curTime = System.currentTimeMillis();
        log.info("[RequestParameter] - [gatEmployeeUpdate] - request:[{}]", JSONObject.toJSONString(map));
        String updateStr = handleExternalRequest.getUrlPostRequest(guanAiTongConfig.getEmployeeUpdateUrl(),map);
        JSONObject jsonIpdateStr = JSON.parseObject(updateStr);
        log.info("[ResponseParameter] - [gatEmployeeUpdate] - response:[{}] - duration:[{}]",JSONObject.toJSONString(jsonIpdateStr),
                System.currentTimeMillis()-curTime);
        return jsonIpdateStr;
    }

    /**
     * @Description 处理关爱通人员离职
     **/
    public JSONObject gatEmployeeResign(Map<String,String> map){
        long curTime = System.currentTimeMillis();
        log.info("[RequestParameter] - [gatEmployeeResign] - request:[{}]", JSONObject.toJSONString(map));
        Map<String,String> resignMap= new ConcurrentHashMap<String,String>();
        resignMap.put("access_token", map.get("access_token").toString());
        resignMap.put("timestamp", map.get("timestamp").toString());
        resignMap.put("corp_code", map.get("corp_code").toString() + "");
        resignMap.put("appsecret",guanAiTongConfig.getAppSecret());
        resignMap.put("sign",gatUtil.getSha1(CommonUtils.formatUrlMap(resignMap,false,true)));
        resignMap.remove("appsecret");
        String resignStr = handleExternalRequest.getUrlPostRequest(guanAiTongConfig.getEmployeeResignUrl(),resignMap);
        JSONObject jsonResignStr = JSON.parseObject(resignStr);
        log.info("[ResponseParameter] - [gatEmployeeResign] - response:[{}] - duration:[{}]",JSONObject.toJSONString(jsonResignStr),
                System.currentTimeMillis()-curTime);
        return jsonResignStr;
    }

    /**
     * @Description 处理关爱通人员复职
     **/
    public JSONObject gatEmployeeRestore(Map<String,String> map){
        long curTime = System.currentTimeMillis();
        log.info("[RequestParameter] - [gatEmployeeRestore] - request:[{}]", JSONObject.toJSONString(map));
        Map<String,String> restoreMap = new ConcurrentHashMap<String,String>();
        restoreMap.put("access_token", map.get("access_token").toString());
        restoreMap.put("corp_code",map.get("corp_code").toString() + "");
        restoreMap.put("timestamp", map.get("timestamp").toString());
        restoreMap.put("appsecret",guanAiTongConfig.getAppSecret());
        restoreMap.put("sign",gatUtil.getSha1(CommonUtils.formatUrlMap(restoreMap,false,true)));
        restoreMap.remove("appsecret");
        String restoreStr = handleExternalRequest.getUrlPostRequest(guanAiTongConfig.getEmployeeRestoreUrl(),restoreMap);
        JSONObject jsonrestoreStr = JSON.parseObject(restoreStr);
        log.info("[ResponseParameter] - [gatEmployeeRestore] - response:[{}] - duration:[{}]",JSONObject.toJSONString(jsonrestoreStr),
                System.currentTimeMillis()-curTime);
        return jsonrestoreStr;
    }


}
