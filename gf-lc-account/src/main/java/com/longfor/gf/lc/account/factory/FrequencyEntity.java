package com.longfor.gf.lc.account.factory;

import com.longfor.gf.lc.account.util.Constants;
import org.apache.commons.lang.StringUtils;

/**
 * @author guoguangxiao
 * @date 2019/5/21 15:33:02
 */
public class FrequencyEntity {

    /** 拦截规则 */
    private String rule;
    /** 拦截有效期 单位s */
    private int limitTime;
    /** 有效期内拦截次数 */
    private int limitCount;
    /** 业务码 */
    private String businessType;
    /** 接口类型 */
    private String type;

    /**
     * Method: 生成缓存键值
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 15:41
     * @param
     * @return
     */
    public String generateKey() {
        rule = StringUtils.isNotEmpty(rule) ? rule : "";
        businessType = StringUtils.isNotEmpty(businessType) ? businessType : "";
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.LIMIT_PREFIX).append(type).append("_").append(businessType).append("_").append(rule);
        return sb.toString();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
