package springcloud.learn.wsc.servicehi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @ClassName: LcOpLoginInterceptor.java
* @Description: 登陆拦截器
* @date: 2019/5/22 18:50
* @version V1.0
*/
@Slf4j
public class LcOpLoginInterceptor  extends HandlerInterceptorAdapter {

    @Value("${iam.cas.server.tokenTimeOut}")
    private String tokenTimeOut ;

    @Resource
    private IOpAuthService iOpAuthService ;
    @Resource
    private IAdministratorsService iAdministratorsService ;
    @Resource
    private RedisTemplate redisTemplateOne;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag = false;
//        //测试从cookies中读取
//        Cookie[] cookies = request.getCookies();
//        if( cookies != null && cookies.length > 0 ) {
//            for( Cookie cookie : cookies ) {
//                if(LcOpEndpointConstant.LC_OP_LOGIN_KEY.equals(cookie.getName())) {
//                    String val = cookie.getValue();
//                    Object obj = redisTemplateOne.opsForValue().get(val) ;
//                    if(obj != null) {
//                        AdministratorsDTO dto = (AdministratorsDTO)obj ;
//                        LcOpContext.put(LcOpContext.LC_OP_USER_KEY, dto);
//                        LcOpContext.put(LcOpContext.LC_OP_LOGIN_KEY_VAL, val);
//                        flag = true ;
//                    }
//                    break ;
//                }
//            }
//        }
//
//        String b = request.getHeader("access-control-request-headers");
//        Enumeration<String> v =  request.getHeaderNames() ;
        //从消息头中获取

//        String loginKeyVal = request.getHeader(LcOpEndpointConstant.LC_OP_LOGIN_KEY);
//        if(RequestMethod.OPTIONS.name().equals(request.getMethod())) {
//            response.setStatus(HttpStatus.OK.value());
//            return true ;
//        }
//
//        if(StringUtils.isEmpty(loginKeyVal)){
//            throw new LcOpBusinessException(LcOpEndpointResultCodeEnum.NOT_LOGIN) ;
//        }
//        //每次请求 token验证
//        UserDTO authUserDTO = iOpAuthService.getUserByTokenFromAuth(loginKeyVal);
//
//        if (authUserDTO == null) {
//            throw new LcOpBusinessException(LcOpEndpointResultCodeEnum.NOT_LOGIN);
//        }
//        //判断是否是龙币管理员
//        String redisKey = RedisKey.join("token",loginKeyVal) ;
//        Object obj = redisTemplateOne.opsForValue().get(redisKey) ;
//        OpUserDto opUserDto = null ;
//        if(obj != null) {
//            opUserDto = (OpUserDto)obj ;
//        }else {
//            Administrators admin = iAdministratorsService.getUsername(authUserDTO.getUsername());
//            if (admin == null) {
//                throw new LcOpBusinessException(LcOpEndpointResultCodeEnum.USERNAME_NOT_EXISTS);
//            }
//            opUserDto = new OpUserDto() ;
//            BeanUtils.copyProperties(authUserDTO, opUserDto);
//            redisTemplateOne.opsForValue().set(redisKey, opUserDto,
//                    Integer.parseInt(tokenTimeOut), TimeUnit.MINUTES);
//        }
//        LcOpContext.put(LcOpContext.LC_OP_USER_KEY, opUserDto);
//        LcOpContext.put(LcOpContext.LC_OP_LOGIN_KEY_VAL, loginKeyVal);
        return true ;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        int i = 0 ;
        super.postHandle(httpServletRequest,httpServletResponse,o,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
