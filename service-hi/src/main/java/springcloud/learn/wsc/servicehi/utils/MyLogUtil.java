package springcloud.learn.wsc.servicehi.utils;

import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.bean.PageInfo;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/6/26 16:22:35
 */
public class MyLogUtil {

    /**
     * Method: 输出日志处理
     * Description:
     * Author guoguangxiao
     * Data 2019/6/26 16:16
     * @param
     * @return
     */
    public static String getResponseLog(BaseResponse baseResponse){
        if(baseResponse==null || baseResponse.getData()==null){
            return null;
        }
        String resLog ;
        Object data = baseResponse.getData();
        if(data instanceof PageInfo && ((PageInfo) data).getList()!=null){
            resLog = baseResponse.getCode()+"|"+baseResponse.getMessage()+"|"+((PageInfo) data).getList().size();
        }else if(data instanceof List && CollectionUtils.isNotEmpty((List)data)){
            resLog = baseResponse.getCode()+"|"+baseResponse.getMessage()+"|"+((List) data).size();
        }else{
            resLog = JSONObject.toJSONString(baseResponse);
        }
        return resLog;
    }
}
