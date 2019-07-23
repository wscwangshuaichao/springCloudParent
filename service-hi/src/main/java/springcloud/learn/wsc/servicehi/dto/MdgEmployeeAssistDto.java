package springcloud.learn.wsc.servicehi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MdgEmployeeAssistDto {

    private Integer keyId;

    private String employee;

    private String deptid;

    private String postion;

    private String jobcode;

    private String deleted;

}