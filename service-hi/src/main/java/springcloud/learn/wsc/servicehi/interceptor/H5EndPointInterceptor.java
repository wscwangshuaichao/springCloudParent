package springcloud.learn.wsc.servicehi.interceptor;

import com.longfor.gf.lc.auth.api.ApiAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangjianbing
 * time 2019/5/22
 */
@Slf4j
public class H5EndPointInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ApiAuthService apiAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 前端H5调用后台接口，必须在header中加入token（此token是龙信用户的单点token）
//        String token = request.getHeader(TokenConstant.LONGCHAT_LOGIN_TOKEN);
//        BaseResponse<UserDTO> baseResponse = apiAuthService.getUserByToken(token);
//        if ("200".equals(baseResponse.getCode()) && baseResponse.getData() != null) {
//            return true;
//        }
//        return false;

        return true;
    }

}
