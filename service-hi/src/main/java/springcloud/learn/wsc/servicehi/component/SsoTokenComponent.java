package springcloud.learn.wsc.servicehi.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author wangshuaichao
 * @Description //TODO
 * @Date 17:20 2019/7/24
 * @Param 
 * @return 
 * @throws        
 **/
@Slf4j
@Component
public class SsoTokenComponent {

//
//    private String casUrl ="http://api.longfor.sit/cas-server-sit";
//
//    private String getUserByTokenUrl ="/cas/v1/user/getByToken";
//
//    private String casGaiaApiKey = "f5936a3a-8562-49cb-b3ff-cb0829685553";
//
//    private String createCasToken = "/cas/v1/token/createByUsernamePassword";
//    private String sysCode = "oa" ;
//    private String deleteToken = "/cas/v1/token/invalidate" ;
//    private String getByTicket = "/cas/v1/token/getByTicket" ;
//
//
//
//    public String getTokenByTicket(String ticket) {
//        try {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("grant_type", "authorization_code");
//            map.put("client_id", sysCode);
//            map.put("client_secret", "secret");
//            map.put("redirect_uri", "http://localhost:8811/toLogin");
//            map.put("code", ticket);
//            String result = OkHttpUtil.postGaiaHeader(casUrl + getByTicket, map,casGaiaApiKey);
////            返回码:
////            1-成功
////            1010-clientId不能为空
////            1011-redirectUri不能为空
////            1012-code不能为空
////            1013-clientId与redirectUri不匹配
////            1014-ticket已失效
////            9999-Internal Server Error
//            JSONObject obj = JSONObject.parseObject(result);
//            String code = obj.getJSONObject("respCode").getString("code");
//            if("1".equals(code)){
//                return obj.getJSONObject("data").getString("accessToken") ;
//            }
//        }catch (Exception e ){
//
//        }
//        return null ;
//
//    }
//    public OpUserDto getSsoUserByToken(String token) {
//        try {
//
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("access_token", token);
//            String result = OkHttpUtil.postGaiaHeader(casUrl + getUserByTokenUrl, map,casGaiaApiKey);
//            JSONObject obj = JSONObject.parseObject(result);
//            String code = obj.getJSONObject("respCode").getString("code");
//            if("1".equals(code)){
//                return  JSONObject.parseObject(obj.getJSONObject("data").toJSONString(), OpUserDto.class);
//            }
//        }catch (Exception e) {
//            log.error("sso请求 根据token获取用户信息异常 token:{}", token, e);
//        }
//        return null ;
//    }
//
//    public String  ssoDeleteToken( String token) {
//        try {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("access_token", token);
//            String result = OkHttpUtil.postGaiaHeader(casUrl + deleteToken, map,casGaiaApiKey);
//            JSONObject obj = JSONObject.parseObject(result);
//            String code = obj.getString("code");
//            if(!"1".equals(code)){
//               log.error("sso请求 将token置为失效失败 code:{} token:{}", code, token);
//            }
//            return code ;
//        }catch (Exception e) {
//            log.error("sso请求  将token置为失效异常 token:{}", token, e);
//        }
//        return "" ;
//    }
//
//    public String  ssoCreateByUsernamePassword(String username, String password) {
//        String result = "" ;
//        try {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("username", username);
//            map.put("password", password);
//            map.put("sysCode", sysCode);
//
//            result = OkHttpUtil.postGaiaHeader(casUrl + createCasToken,  map,casGaiaApiKey);
////            JSONObject obj = JSONObject.parseObject(result);
////            String code = obj.getString("code");
////            //1	Auth Success	验证通过
////            //1003	Username Or Password Is Empty	用户名或密码为空
////            //1004	Auth Failed	认证失败
////            //9999	Internal Server Error	服务器内部错误
////            if(!"1".equals(code)){
////                log.error("将token置为失效失败 code:{} token:{}", code, token);
////            }
//            return result ;
//        }catch (Exception e) {
//            log.error("sso请求 用户登陆异常 username:{} sysCode:{}", username, sysCode, e);
//        }
//        return "" ;
//    }
}
