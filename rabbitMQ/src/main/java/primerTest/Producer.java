package primerTest;

/**
 * Created by onaple on 2017/2/14.
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private static final String QUEUE_NAME = "hello world";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建到rabbitmq的连接
        ConnectionFactory factory = new ConnectionFactory();
        //设置rabbitmq所在主机ip或地址
        factory.setHost("localhost");
        //创建一个连接
        Connection conn = factory.newConnection();
        //创建一个频道
        Channel channel = conn.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String msg = "this is my first rabbitmq msg" +
                "dssdssssd";
        //往队列里发一条消息
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println(" [x] Sent '" + msg + "'");
        //关闭频道和连接
        channel.close();
        conn.close();
    }
}