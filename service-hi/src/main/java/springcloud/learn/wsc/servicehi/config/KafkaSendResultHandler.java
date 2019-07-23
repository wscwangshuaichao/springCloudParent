package springcloud.learn.wsc.servicehi.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * 自定义Kafka监听器
 *
 * @author zhangjianbing
 * time 2019/7/5
 */
@Slf4j
@Component
public class KafkaSendResultHandler implements ProducerListener {

    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        log.info("Kafka消息发送成功。主题：{}，分区：{}，偏移量：{}，key：{}，value：{}", topic, partition, recordMetadata.offset(), key, value);
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        log.error("Kafka消息发送失败。主题：{}，分区：{}，key：{}，value：{}，异常信息：{}", topic, partition, key, value, exception.getMessage());
    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }
}
