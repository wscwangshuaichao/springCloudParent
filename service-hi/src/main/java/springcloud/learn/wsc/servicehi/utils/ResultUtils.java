package springcloud.learn.wsc.servicehi.utils;

import com.longfor.gaia.gfs.core.response.BaseResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author guoguangxiao
 * @date 2019/4/11 15:35:06
 */
public class ResultUtils {

    /**
     * Method: 封装异常结果
     * Description:
     * Author guoguangxiao
     * Data 2019/4/11 16:15
     * @param
     * @return
     */
    public static BaseResponse getException() {
        return getResponse(null,CodeEnum.BizCode.SYS_ERROR);
    }

    public static BaseResponse getException(Throwable e) {
        BaseResponse response =  getException();
        response.setTracestack(getStackTrace(e));
        response.setExtraMessage(e.getMessage());
        return response;
    }


    public static BaseResponse getSuccess() {
        return new BaseResponse();
    }
    public static BaseResponse getFail() {
        return getResponse(null,CodeEnum.BizCode.FAIL);
    }

    public static BaseResponse getExist() {
        return getResponse(null,CodeEnum.BizCode.EXIST);
    }

    public static BaseResponse getResponse(BaseResponse response, CodeEnum.BizCode bizCode) {
        return getResponse(response,bizCode.getCode(),bizCode.getMsg());
    }
    public static BaseResponse getResponse(BaseResponse response, String code,String msg) {
        if(response == null){
            response = new BaseResponse();
        }
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try{
            throwable.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            pw.close();
        }
        return "";
    }

}
