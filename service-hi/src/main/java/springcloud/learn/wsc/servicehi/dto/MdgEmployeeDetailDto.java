package springcloud.learn.wsc.servicehi.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoguangxiao
 * @date 2019/4/26 17:49:46
 */
@Data
@NoArgsConstructor
public class MdgEmployeeDetailDto {

    private String id;
    private String username;
    @JSONField(name = "true_name")
    private String truename;
    private String telphone;
    private String email;
    private String region;
    private String dept;
    private String deptIds;
    private String regionAll;
    private String jobs;
    private String jobGradeName;
    private String jobable;
    private String userno;
    private String emStatus;

}
