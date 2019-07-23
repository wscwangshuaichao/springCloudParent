package com.longfor.gf.lc.account.factory;

/**
 * @author guoguangxiao
 * @date 2019/5/21 15:50:35
 */
public class FrequencyFactory {

    /** 频次拦截枚举类型定义 */
    public enum FrequencyType {
        SAVE,BATCH_SAVE,UPDATE,TRANSFER,BATCHTRANSFER
    }

    public static FrequencyEntity getFrequencyEntity(FrequencyType frequencyType,String businessType, String rule, int limitTime, int limitCount) {
        if(frequencyType != null){
            FrequencyEntity frequencyEntity = new FrequencyEntity();
            frequencyEntity.setRule(rule);
            frequencyEntity.setLimitTime(limitTime);
            frequencyEntity.setLimitCount(limitCount);
            frequencyEntity.setBusinessType(businessType);
            frequencyEntity.setType(frequencyType.toString());
            return frequencyEntity;
        }
        return null;
    }

}
