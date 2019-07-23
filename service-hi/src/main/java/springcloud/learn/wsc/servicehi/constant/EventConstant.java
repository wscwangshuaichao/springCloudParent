package springcloud.learn.wsc.servicehi.constant;

/**
 * @author zhangjianbing
 * time 2019/5/24
 */
public interface EventConstant {

    /**
     * kafka topic
     */
    interface MQ {
        interface KAFKA_TOPICS {
            interface H5_ENDPOINT {
                /**
                 * 龙币首页弹出框topic
                 */
                String LONGCOIN_SCHEDULSERVICE_TO_H5_ENDPOINT_EVENT = "longcoin-schedulservice-to-h5-endpoint-event";

                /**
                 * 龙币首页弹出框key
                 */
                String LONGCOIN_SCHEDULSERVICE_TO_H5_ENDPOINT_KEY = "homepage-message-key";

                /**
                 * 龙信公众号推送topic
                 */
                String LONGCOIN_H5_PUBLICNUMBER_MESSAGE_PUSH_EVENT = "longcoin-h5-publicNumber-message-push-event";

                /**
                 * 龙信公众号推送key
                 */
                String LONGCOIN_H5_PUBLICNUMBER_MESSAGE_PUSH_KEY = "public-number-key";
            }
        }
    }

}
