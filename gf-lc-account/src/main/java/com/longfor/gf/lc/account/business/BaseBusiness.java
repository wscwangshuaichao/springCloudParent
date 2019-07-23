package com.longfor.gf.lc.account.business;

import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.factory.FrequencyEntity;
import com.longfor.gf.lc.account.service.RedisService;
import com.longfor.gf.lc.account.util.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guoguangxiao
 * @date 2019/5/21 15:26:07
 */
@Slf4j
@Service
public class BaseBusiness {

    @Resource
    private RedisService redisService;

    /**
     * Method: 频次限制
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 15:48
     * @param
     * @return
     */
    public void checkFrequency(FrequencyEntity frequencyEntity) throws ServiceException {
        if (null != frequencyEntity) {
            boolean flag = redisService.rateLimit(frequencyEntity.generateKey(), frequencyEntity.getLimitTime(), frequencyEntity.getLimitCount());
            if (!flag) {
                throw new ServiceException(CodeEnum.BizCode.HIGH_FREQUENCY_ERROR);
            }
        }
    }

    public String getAccType(String accNo) {
        return StringUtils.isEmpty(accNo) ? "null" : accNo.substring(0,2);
    }

    /**
     * Method: 校验业务类型
     * Description:
     * Author guoguangxiao
     * Data 2019/6/14 10:28
     * @param
     * @return
     */
    public void checkBusinessType(String outBusinessType, String inBusinessType) throws ServiceException {
        if(StringUtils.isNotEmpty(outBusinessType) && StringUtils.isNotEmpty(inBusinessType) && !outBusinessType.equals(inBusinessType)){
            throw new ServiceException(CodeEnum.BizCode.PARAM_FORMAT_ERROR.getCode(),"businessType不一致");
        }
    }
}
