package springcloud.learn.wsc.core.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import springcloud.learn.wsc.core.utils.MaskToStringBuilder;
import uk.co.jemos.podam.common.PodamStringValue;

import java.io.Serializable;

/**
 * @author wangshuaichao
 * @ClassName: BaseResponse
 * @Decription http 通用返回结构
 * @Date 2019/7/25 16:33
 **/
@SuppressWarnings({"squid:S1068"})
@Getter
@Setter
public class BaseResponse<T> {

    public static final String DEFAULT_CODE = "200";
    public static final String DEFAULT_MESSAGE = "ok";

    @PodamStringValue(strValue = DEFAULT_CODE)
    private String code;
    @PodamStringValue(strValue = DEFAULT_MESSAGE)
    private String message;
    @PodamStringValue(length = 3)
    private String extraCode;
    private String extraMessage;
    @PodamStringValue(length = 0)
    private String tracestack;
    @PodamStringValue(length = 0)
    private String url;
    private T data;

    public BaseResponse(String code, String message, String extraCode, String extraMessage, T data) {
        this.code = StringUtils.defaultIfBlank(code, DEFAULT_CODE);
        this.message = StringUtils.defaultIfBlank(message, DEFAULT_MESSAGE);
        this.extraCode = StringUtils.trimToEmpty(extraCode);
        this.extraMessage = StringUtils.trimToEmpty(extraMessage);
        this.data = data;
    }

    public BaseResponse(String code, String message, T data) {
        this(code, message, null, null, data);
    }

    public BaseResponse(T data) {
        this(null, null, data);
    }

    public BaseResponse() {
        this(null);
    }

    @Override
    public String toString() {
        return new MaskToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
