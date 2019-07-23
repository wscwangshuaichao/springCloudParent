package springcloud.learn.wsc.servicehi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.auth.api.ApiAuthService;
import com.longfor.gf.lc.auth.dto.UserDTO;
import com.longfor.gf.lc.report.config.ThreadContextHolder;
import com.longfor.gf.lc.report.constants.AuthConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author xupeng
 * time 2019/5/15
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private ApiAuthService apiAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader(AuthConstants.X_LONGCHAT_TOKEN);
        if (token != null && !token.isEmpty()) {
            BaseResponse<UserDTO> baseResponse = apiAuthService.getUserByToken(token);
            UserDTO userDTO = baseResponse.getData();
            if (userDTO != null) {
                ThreadContextHolder.USER.set(userDTO);
                return true;
            }
        }

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.toJSONString(new BaseResponse<>(AuthConstants.errorCode, AuthConstants.errorMsg, "")));
        pw.flush();
        pw.close();
        return false;
    }
}
