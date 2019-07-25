package springcloud.learn.wsc.servicehi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
* @ClassName: LcOpHandlerInterceptor2.java
* @Description: 全局拦截器
* @date: 2019/5/28 2:10
* @version V1.0
*/
@Slf4j
public class LcOpHandlerInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long beginTime = System.currentTimeMillis();
        StringBuffer info = new  StringBuffer();
        InputStream in = null ;
        BufferedInputStream buf = null ;
        Map map = request.getParameterMap();
        try {
            in = request.getInputStream();
            buf=new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            int iRead;
            while ((iRead = buf.read(buffer)) != -1) {
                info.append(new String(buffer, 0, iRead, StandardCharsets.UTF_8));
            }

        } catch (Exception e){

        } finally {
            if(buf != null ){buf.close();buf.close();}
        }
        log.info(">>>开始计时: {}  URI: {} 请求参数：{}", new SimpleDateFormat("yy-mm-dd hh:mm:ss.SSS")
                .format(beginTime), request.getRequestURI(), info.toString());
        LcOpContext.put(LcOpContext.REQUEST_WAIT_TIME, beginTime);
        return true ;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //prehandler抛异常不会走 其他都会走
        //得到线程绑定的局部变量（开始时间）
        Long beginTime = LcOpContext.getObj(Long.class,LcOpContext.REQUEST_WAIT_TIME);
        if(beginTime == null){
            return ;
        }
        long endTime = System.currentTimeMillis(); 	//2、结束时间
        log.info(">>>计时结束：{}  URI: {} 耗时：{} ms ",
                new SimpleDateFormat("yy-mm-dd hh:mm:ss.SSS").format(endTime),
                request.getRequestURI(), endTime - beginTime);
        //删除线程变量中的数据，防止内存泄漏
        LcOpContext.remove();
    }


}
