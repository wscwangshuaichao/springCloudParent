package springcloud.learn.wsc.servicehi.enums;

import lombok.Getter;

/**
* @ClassName: LcOpAccountStatusEnum.java
* @Description: 账户状态枚举
* @author: ChongLi
* @date: 2019/6/5 17:39
* @version V1.0
*/
@Getter
public enum LcOpAccountStatusEnum {
    NORMAL(1,"正常") ,
    NOTUSE(0,"禁用"),
    ;
    private int code;
    private String msg;
    LcOpAccountStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
