package springcloud.learn.wsc.servicehi.utils;

import com.google.common.collect.Maps;
import com.longfor.gf.op.person.mdg.common.MdgConstants;

import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: BuildResultUtil
 * @Decription TOO
 * @Date 2019/7/15 14:27
 **/
public class BuildResultUtil {

    public static Map<String,String> buildResult(String saveOrUpdateMsg){
        Map<String,String> result = Maps.newConcurrentMap();
        result.put(MdgConstants.SAVEORUPDATESTATE,saveOrUpdateMsg);
        return result;
    }
}
