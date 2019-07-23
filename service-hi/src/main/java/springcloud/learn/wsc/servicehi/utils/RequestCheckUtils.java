package springcloud.learn.wsc.servicehi.utils;

import com.alibaba.fastjson.JSONArray;
import com.longfor.gf.lc.account.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author guoguangxiao
 * @date 2019/5/17 10:42:30
 */
public class RequestCheckUtils {

    public static final String DLPATTERN = "^[A-Za-z0-9\\-]+$";
    public static final String DLPATTERN_AMOUNT = "^[0-9]*.?[0-9]$";
    public static final String DLPATTERN_POSITIVE_INTEGER = "^[0-9]*[1-9][0-9]*$";

    /**
     * Method: 字段校验
     * Description:
     * Author guoguangxiao
     * Data 2019/5/17 10:43
     * @param
     * @return
     */
    public static void checkNotEmpty(Object value,String fieldName)throws ServiceException {
        if(value==null){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY.getCode(),"有必要参数为空:"+fieldName+"");
        }
        if(value instanceof String){
            if(((String) value).trim().length()==0){
                throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY.getCode(),"有必要参数为空:"+fieldName+"");
            }
        }else if(value instanceof BigDecimal){
            if(((BigDecimal) value).compareTo(BigDecimal.ZERO) <= 0){
                throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_LE_ZERO.getCode(),CodeEnum.BizCode.PARAM_NOT_LE_ZERO.getMsg() + ":" + fieldName + "");
            }
        }
    }

    public static <T> List<T> checkNotEmptyAndSize(String data, int saveNum, Class<T> clazz) throws ServiceException {
        if(StringUtils.isBlank(data)){
            throw new ServiceException(CodeEnum.BizCode.PARAM_NOT_EMPTY.getCode(),"有必要参数为空:data");
        }
        List<T> reqList = JSONArray.parseArray(data, clazz);
        if(CollectionUtils.isEmpty(reqList)){
            throw new ServiceException( CodeEnum.BizCode.PARAM_NOT_EMPTY.getCode(),CodeEnum.BizCode.PARAM_NOT_EMPTY.getMsg());
        }
        if(reqList.size() > saveNum){
            throw new ServiceException(CodeEnum.BizCode.BATCH_SAVE_NUM_ERROR.getCode(),CodeEnum.BizCode.BATCH_SAVE_NUM_ERROR.getMsg() + ",一次最多新增" + saveNum + "条数据");
        }
        return reqList;
    }

    /**
     * Method: 是否为有效金额
     * Description:
     * Author guoguangxiao
     * Data 2019/5/24 17:04
     * @param
     * @return
     */
    public static void checkValidAmount(String in) throws ServiceException {

        Pattern pattern = Pattern.compile(DLPATTERN_AMOUNT);
        if(!(pattern.matcher(in).matches())){
            throw new ServiceException(CodeEnum.BizCode.AMOUNT_ERROR.getCode(),CodeEnum.BizCode.AMOUNT_ERROR.getMsg()+in);
        }
        if(new BigDecimal(in).compareTo(BigDecimal.ZERO) <= 0){
            throw new ServiceException(CodeEnum.BizCode.AMOUNT_ERROR.getCode(),CodeEnum.BizCode.AMOUNT_ERROR.getMsg()+in);
        }
    }

    /**
     * Method: 校验正整数
     * Description:
     * Author guoguangxiao
     * Data 2019/6/18 18:11
     * @param
     * @return
     */
    public static void checkIfNumber(String in) throws ServiceException {
        Pattern pattern = Pattern.compile(DLPATTERN_POSITIVE_INTEGER);
        if(!(pattern.matcher(in).matches())){
            throw new ServiceException(CodeEnum.BizCode.PARAM_FORMAT_ERROR.getCode(),"无效数字:"+in);
        }
    }

    /**
     * Method: 字符串为数字或字母
     * Description:
     * Author guoguangxiao
     * Data 2019/5/28 16:52
     * @param
     * @return
     */
    public static void checkDigitalOrLetters(String value,String fieldName) throws ServiceException {
		Pattern pattern = Pattern.compile(DLPATTERN);
		if(!(pattern.matcher(value).matches())){
			throw new ServiceException(CodeEnum.BizCode.DL_PARAM_ERROR.getCode(), "只能数字或字母或-:"+fieldName);
		}
    }

    /**
     * Method: 校验字符长度
     * Description:
     * Author guoguangxiao
     * Data 2019/6/21 11:57
     * @param
     * @return
     */
    public static void checkMaxLength(String fieldValue, int maxLength, String fieldName) throws ServiceException {
        if (fieldValue != null && fieldValue.length() > maxLength) {
            throw new ServiceException(CodeEnum.BizCode.PARAM_FIELD_INVALID_ERROR.getCode(), fieldName + ":最大长度为" + maxLength);
        }
    }
}
