package springcloud.learn.wsc.servicehi.common;


/**
 * @author wangshuaichao
 * @ClassName: GatConstants
 * @Decription TOO
 * @Date 2019/5/17 15:34
 **/
@SuppressWarnings("unchecked")
public class GatConstants {

    /**关爱通获取token的key*/
    public static final String GAT_ACCESS_TOKEN = "GAT_ACCESS_TOKEN";

    /**同步关爱通人员是否全量更新的key*/
    public static final String IS_ALL_KEY = "IS_ALL_KEY";

    /**同步关爱通人员失败统一key*/
    public static final String GAT_SYNC_FAIL = "GAT_SYNC_FAIL";

    public static final int  EXPIRETIME = 3300;//关爱通token过期时间

    public static final String  REASON = "龙币兑换龙币商城积分";

    public static final String  RETURNREASON = "关爱商城退回积分";

    public static final String SUCCESS = "1";//验证龙信token成功

    public static final String NOPERSONCODE = "501";

    public static final String NOPERSONMSG = "用户不存在！";

    public static final String GETSSOFIELDCODE = "502";

    public static final String GETSSOFIELDMSG = "调用关爱通获取授权码失败！";

    public static final String GETSSOSUCCESSMSG = "调用关爱通获取授权码成功！";

    public static final String GETPERSONALINTEGRALFIELDCODE = "503";

    public static final String GETPERSONALINTEGRALFIELDMSG = "调用关爱通查询个人积分失败！";

    public static final String GETPERSONALINTEGRALSUCCESSMSG = "调用关爱通查询个人积分成功！";

    public static final String PARAMERRORCODE = "504";

    public static final String PARAMERRORMSG = "传入参数错误！";

    public static final String EXCHANGEINTEGRALFIELDCODE = "505";

    public static final String EXCHANGEINTEGRALFIELDMSG = "兑换积分失败！";

    public static final String NOTENOUGHCODE = "506";

    public static final String NOTENOUGHMSG = "剩余积分不足！";

    public static final String RETURNINTEGRALCODE = "507";

    public static final String NORECORDCODE = "508";

    public static final String NORECORDMSG = "该用户没有兑换过积分！";

    public static final String RETURNINTEGRALMSG = "积分退回失败！";

    public static final String EXCHANGEINTEGRALSUCCESSMSG = "兑换积分成功！";

    public static final String RETURNINTEGRALFAILEDMSG = "积分退回成功！";

    public static final String RESULT_SUCCESSCODE = "200";//请求成功

    public static final String RESULT_SUCCESSMSG = "SUCCESS";//请求成功

    public static final String RESULT_ERRORCODE = "500";//请求失败

    public static final String RESULT_ERRORMSG = "ERROR";//请求失败

    public static final String MISSING_ACCESSTOKEN = "1001";//token参数为空

    public static final String EXPIRED_ACCESSTOKEN = "1002";//token已失效

}
