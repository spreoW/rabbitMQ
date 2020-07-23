package com.rabbitmq.api.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

/**
 * @author wq
 * 发送消息
 */
public class Send {

    /**
     *   Set up the class and name the queue,设置队列名称
     *
     */
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 链接到主机服务器上面
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.80.5");
        factory.setUsername("wq");
        factory.setPassword("wq");
        factory.setVirtualHost("/wq");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
