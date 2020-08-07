package m1.simple;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //连接
        ConnectionFactory f =new ConnectionFactory();
        f.setHost("192.168.64.151");
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c =f.newConnection().createChannel();

        //定义队列
        c.queueDeclare("helloworld",false,false,false,null);

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consemerTag, Delivery message) throws IOException {
                byte[] body = message.getBody();
                String msg = new String(body);
                System.out.println("收到:"+msg);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
            }
        };
        //从 helloworld队列接收消息,消费消息
        c.basicConsume("helloworld",true,deliverCallback,cancelCallback);
    }
}
