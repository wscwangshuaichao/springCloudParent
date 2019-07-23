package springcloud.learn.wsc.servicehi.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * @author guoguangxiao
 * @date 2019/6/25 14:14:19
 */
public class EncryptUtils {

    /** 盐值 */
    private static final String KEY = "root";
    /** 加密算法 */
    private static final String ALGORITHM = "PBEWithMD5AndDES";

    /**
     * 加密
     * @param text 明文
     * @return     密文
     */
    public static String encrypt(String text) {
        // 创建加密器
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // 配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        // 加密算法
        config.setAlgorithm(ALGORITHM);
        // 密码
        config.setPassword(KEY);
        encryptor.setConfig(config);
        // 加密
        return encryptor.encrypt(text);
    }

    /**
     * 解密
     * @param ciphertext 密文
     * @return           明文
     */
    public static String decrypt(String ciphertext) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm(ALGORITHM);
        config.setPassword(KEY);
        encryptor.setConfig(config);

        //解密
        return encryptor.decrypt(ciphertext);

    }

    public static void main(String[] args){
        System.out.println(EncryptUtils.encrypt("longfor"));
        System.out.println(EncryptUtils.decrypt("RRnz9NFThjLuy8q0r9mPvg=="));
        System.out.println(EncryptUtils.decrypt("Pv0w6N18XzWYbYYFouK0gg=="));
        System.out.println(EncryptUtils.decrypt("rGaLGU002Lvob4xhBS4RCw=="));


    }


}
