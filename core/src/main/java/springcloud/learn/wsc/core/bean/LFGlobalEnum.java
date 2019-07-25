package springcloud.learn.wsc.core.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import lombok.Generated;
import springcloud.learn.wsc.core.constants.LFConstants;

import java.util.Map;

/**
 * @author wangshuaichao
 * @ClassName: LFGlobalEnum
 * @Decription 全局异常定义
 * @Date 2019/7/25 16:19
 **/
@SuppressWarnings({"squid:S1068"})
@Generated
public enum LFGlobalEnum {

    /* E90开头 */
    E9000("E9000", "[E9000]{?}"),
    E9001("E9001", "[E9001]数据不存在"),
    E9002("E9002", "[E9002]变更日志插入失败"),
    E9003("E9003", "[E9003]tenantTriggerId不能为空"),
    E9004("E9004", "[E9004]tenantId不能为空"),
    E9005("E9005", "[E9005]租户数据不存在:{?}"),
    E9006("E9006", "[E9006]Header中缺少参数 > {?}, 参考: http://wiki.longhu.net:8090/pages/viewpage.action?pageId=851301"),
    E9007("E9007", "[E9007]x-mapping-name > 方法不存在: {?}, 参考: http://wiki.longhu.net:8090/pages/viewpage.action?pageId=851472"),
    E9008("E9008", "[E9008]x-mapping-name > 存在多个方法"),
    E9009("E9009", "[E9009]未找到对应方法"),


    /* E91开头 */
    E9101("E9101", "[E9101]Token无效"),
    E9102("E9102", "[E9102]Token已过期"),


    /* E92开头 */
    E9201("E9201", "[E9201]请求超时:{?}"),

    /* E93开头 */
    E9301("E9301", "[E9301]服务异常终止 > {?}"),
    E9302("E9302", "[E9302]不支持的连接池类型 > {?}"),
    E9303("E9303", "[E9303]缺少配置项 > {?}"),
    E9304("E9304", "[E9304]数据源不存在 > {?}");


    /**
     * 枚举码
     */
    public final String code;
    /**
     * 枚举描述
     */
    public final String text;

    LFGlobalEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText(String... values) {
        String msg = text;
        for (String val : values) {
            msg = msg.replaceFirst(LFConstants.PLACE_HOLDER, val);
        }
        return msg;
    }

    /**
     * 根据枚举码获取枚举
     *
     * @param code 枚举码
     * @return 枚举
     */
    @JsonCreator
    public static LFGlobalEnum of(String code) {
        for (LFGlobalEnum commonEnum : values()) {
            if (code.equals(commonEnum.code)) {
                return commonEnum;
            }
        }
        return null;
    }

    @JsonValue
    public Map<String, Object> value() {
        return ImmutableMap.<String, Object>builder()
                .put("code", code)
                .put("text", text)
                .build();
    }

}

