package springcloud.learn.wsc.servicehi.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroSecretUtils {
    //secret
    /**  加密算法 */
    public final static String hashAlgorithmName = "md5";
    /**  循环次数 */
    public final static int hashIterations = 2;

    public static String generatorMd5(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toHex();//realm 设置为true 16进制
    }

    public static String generatorRandom(){
        return new SecureRandomNumberGenerator().nextBytes().toHex() ;
    }

    public static String buildLoginSale(String userName, String id ,String randomStr){
        return userName + id + randomStr ;
    }
}
