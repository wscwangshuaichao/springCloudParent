package springcloud.learn.wsc.servicehi.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiAccountService;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.h5.endpoint.constant.EventConstant;
import com.longfor.gf.lc.h5.endpoint.pojo.Message;
import com.longfor.gf.lc.h5.endpoint.pojo.MessageDto;
import com.longfor.gf.lc.h5.endpoint.service.IRedisService;
import com.longfor.gf.lc.h5.endpoint.utils.MessageTemplateUtil;
import com.longfor.gf.op.gop.api.MdgEmployeeServiceApi;
import com.longfor.gf.op.gop.mdg.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhangjianbing
 * time 2019/5/24
 */
@Component
@Slf4j(topic = "H5PopMessageServiceListener")
public class H5PopMessageServiceListener {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private Environment environment;

    @Autowired
    private ApiAccountService apiAccountService;

    @Resource
    private MdgEmployeeServiceApi mdgEmployeeServiceApi;

    /**
     * 用来监听转账、打赏、节日福利发放成功的topic
     *
     * @param record 传输数据
     */
    @KafkaListener(topics = EventConstant.MQ.KAFKA_TOPICS.H5_ENDPOINT.LONGCOIN_SCHEDULSERVICE_TO_H5_ENDPOINT_EVENT)
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("topic = {}, offset = {}, value = {}", record.topic(), record.offset(), record.value());
        String value = (String) record.value();
        MessageDto messageDto = JSONObject.parseObject(value, MessageDto.class);
        if (null != messageDto) {
            Message message = new Message();
            Date date = DateUtil.date();
            // 转账人
            BaseResponse<AccountDto> detailByAccNo = apiAccountService.getDetailByAccNo(messageDto.getTransferPerson());
            if (detailByAccNo.getCode().equals(CodeEnum.BizCode.SUCCESS.getCode())) {
                BaseResponse<EmployeeDto> response = mdgEmployeeServiceApi.getEmployeeByUsercode(detailByAccNo.getData().getPersonAd());
                // 转账人真实姓名
                message.setTransferPersonRealName(response.getData().getName1());
                // 转账人的usercode
                message.setTransferPerson(detailByAccNo.getData().getPersonAd());
            } else {
                log.error("【H5ServiceEndpointListener#listen】没有此转账人账户：{}", messageDto.getTransferPerson());
                return;
            }
            // 被转账人
            BaseResponse<AccountDto> dtoBaseResponse = apiAccountService.getDetailByAccNo(messageDto.getUserCode());
            if (dtoBaseResponse.getCode().equals(CodeEnum.BizCode.SUCCESS.getCode())) {
                BaseResponse<EmployeeDto> response = mdgEmployeeServiceApi.getEmployeeByUsercode(dtoBaseResponse.getData().getPersonAd());
                // 被转账人的真实姓名
                message.setTransferedPersonRealName(response.getData().getName1());
                // 被转账人的usercode
                message.setUserCode(dtoBaseResponse.getData().getPersonAd());
            } else {
                log.error("【H5ServiceEndpointListener#listen】没有此被转账人账户账户：{}", messageDto.getUserCode());
                return;
            }
            message.setMoney(messageDto.getMoney());
            message.setMsgType(messageDto.getMsgType());
            // 批次号
            message.setBatchNum(messageDto.getBatchNum());
            // 流水号（可不传）
            message.setTransNum(messageDto.getTransNum());
            // 月
            message.setMonth(String.valueOf(DateUtil.month(date) + 1));
            // 日
            message.setDay(String.valueOf(DateUtil.dayOfMonth(date)));
            // 留言
            message.setMark(messageDto.getMark());
            // 获取模板
            String template = environment.getProperty(messageDto.getTemplateName());
            String temp = UnicodeUtil.toString(template);
            // 模板填充
            String s = MessageTemplateUtil.fillTemplate(temp, message);
            String[] split = s.split("&");
            message.setMessageTitle(split[0]);
            message.setMessageBody(split[1]);
            // jobType
            message.setJobType(messageDto.getTemplateName());
            try {
                // 向redis中插入消息
                redisService.insertKey(message.getUserCode(), message);
                log.info("将消息存入redis成功，usercode--->{}，message--->{}", message.getUserCode(), JSONUtil.toJsonStr(message));
            } catch (Exception e) {
                log.error("监听转账、打赏、节日福利发放成功的topic出现异常【listen#】，异常信息：{}", e.getMessage());
            }

        }
    }

}
