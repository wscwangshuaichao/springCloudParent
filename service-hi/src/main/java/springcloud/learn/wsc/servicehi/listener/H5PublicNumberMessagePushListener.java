package springcloud.learn.wsc.servicehi.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gf.lc.account.api.ApiAccountService;
import com.longfor.gf.lc.account.dto.AccountDto;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.h5.endpoint.constant.EventConstant;
import com.longfor.gf.lc.h5.endpoint.pojo.PublicNumber;
import com.longfor.gf.lc.h5.endpoint.pojo.PublicNumberPushDto;
import com.longfor.gf.lc.h5.endpoint.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 龙信公众号推送Kafka监听器
 *
 * @author zhangjianbing
 * time 2019/7/3
 */
@Component
@Slf4j(topic = "H5PublicNumberMessagePushListener")
public class H5PublicNumberMessagePushListener {

    /**
     * 网关（Gravitee）X-Gaia-Api-Key
     */
    @Value("${dragonletter.dynamic.gaia-api-key}")
    private String dragonletterDynamicApiKey;

    /**
     * 网关的url
     */
    @Value("${dragonletter.dynamic.url}")
    private String dragonletterDynamicApiKeyUrl;

    /**
     * 网关ContextPath + interface path eg: /dragonletter-dynamic-sit/pushOfficialAccountsText
     */
    @Value("${dragonletter.dynamic.publicNumberPushPath}")
    private String dragonletterDynamicPublicNumberPush;

    @Autowired
    private Environment environment;

    @Autowired
    private ApiAccountService apiAccountService;

    /**
     * 龙信公众号推送，默认自动提交offset
     *
     * @param record
     */
    @KafkaListener(topics = EventConstant.MQ.KAFKA_TOPICS.H5_ENDPOINT.LONGCOIN_H5_PUBLICNUMBER_MESSAGE_PUSH_EVENT)
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("主题 = {}, 偏移量 = {}, key = {}, value = {}", record.topic(), record.offset(), record.key(), record.value());
        String jsonStr = (String) record.value();
        PublicNumberPushDto publicNumberPushDto = JSONObject.parseObject(jsonStr, PublicNumberPushDto.class);
        List<String> accountNumList = publicNumberPushDto.getUsers();
        List<String> personAdList = new ArrayList<>();
        for (String accountNum : accountNumList) {
            BaseResponse<AccountDto> response = apiAccountService.getDetailByAccNo(accountNum);
            if (response.getCode().equals(CodeEnum.BizCode.SUCCESS.getCode())) {
                // 加入集合
                personAdList.add(response.getData().getPersonAd());
            }
        }

        PublicNumber publicNumber = new PublicNumber();
        String publicId = environment.getProperty("dragonletter.dynamic.publicId");
        // 0：文字   1：图片
        publicNumber.setType(publicNumberPushDto.getType());
        // 某公众号唯一标识
        publicNumber.setPublicId(publicId);
        // 需要推送公众号的人
        publicNumber.setUsers(personAdList);
        // 消息摘要
        publicNumber.setDigest("龙币公众号");
        // 文字信息推送
        if (publicNumber.getType() == 0) {
            // 文字标识
            publicNumber.setObjectName("RC:TxtMsg");
            // 消息内容
            publicNumber.setContent("【龙信小秘】人和人相遇,靠的是一点缘分,人和人相处,靠的是一份诚意,人和人相爱,靠的是一颗真心,正因为人世间每段缘份都来之不易,我希望我们永远是最好的朋友!");
        } else {
            // 图片标题
            publicNumber.setTitle("图片标题：外面的世界很精彩");
            // 图片的链接
            publicNumber.setPicurl("http://pic3.nipic.com/20090527/1242397_102231006_2.jpg");
            // 图片跳转的链接
            publicNumber.setUrl("https://www.baidu.com");
            // 图片标识
            publicNumber.setObjectName("RC:PSImgTxtMsg");
            // 是否对跳转链接鉴权
            publicNumber.setPermission(false);
        }


        // 公众号推送url
        String url = dragonletterDynamicApiKeyUrl + dragonletterDynamicPublicNumberPush;
        log.info("公众号推送消息url = {}", url);
        Map<String, Object> header = new HashMap<>();
        header.put("X-Gaia-Api-Key", dragonletterDynamicApiKey);
        header.put("Content-Type", "application/json");
        String result = HttpUtil.postBody(url, JSONUtil.toJsonStr(publicNumber), header);
        log.info("请求接口后返回的结果：{}", result);
    }

}
