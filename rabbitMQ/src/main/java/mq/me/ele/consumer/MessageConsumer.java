package mq.me.ele.consumer;

/**
 * Created by onaple on 2017/2/14.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 功能概要：消费接收
 */
public class MessageConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    public void onMessage(Message message) {
        logger.info("receive message:{}",message);
    }

}