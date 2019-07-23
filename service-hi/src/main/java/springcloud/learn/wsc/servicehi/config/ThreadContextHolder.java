package springcloud.learn.wsc.servicehi.config;

import com.longfor.gf.lc.auth.dto.UserDTO;

public class ThreadContextHolder {
    private ThreadContextHolder(){}

    /**
     * 用户上下文信息
     */
    public static class USER {
        private USER() {
        }
        private static final ThreadLocal<UserDTO> USER_INFO_CONTEXT = new ThreadLocal<>();
        /**
         * 线程上下文信息
         * @return 获取线程变量
         */
        public static UserDTO get(){
            return USER_INFO_CONTEXT.get();
        }

        /**
         * 设置线程上下文信息
         * @param user 线程上下文信息
         */
        public static void set(UserDTO user){
            USER_INFO_CONTEXT.set(user);
        }

        /**
         * 删除线程上下文信息
         */
        private static void remove() {
            USER_INFO_CONTEXT.remove();
        }
    }
}
