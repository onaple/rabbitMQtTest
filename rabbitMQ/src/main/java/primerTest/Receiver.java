package primerTest;

/**
 * Created by onaple on 2017/2/14.
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;


public class Receiver {

    private static final String QUEUE_NAME = "hello world";

    public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
            ConsumerCancelledException, InterruptedException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置mq的host或者 ip
        factory.setHost("localhost");
        // 创建一个连接
        Connection conn = factory.newConnection();
        // 创建一个channel
        Channel channel = conn.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定消费队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        // 循环
        while (true) {
            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("[x] Received '" + msg + "'");
        }

    }
}