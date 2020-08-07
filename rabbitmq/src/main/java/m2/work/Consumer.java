package m2.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.151");
        //f.setPort(5672);
        f.setUsername("admin");
        f.setPassword("admin");
        //f.setVirtualHost("/wht");

        Channel c = f.newConnection().createChannel();

        //定义队列
        c.queueDeclare("helloworld",false,false,false,null);

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                System.out.println("收到"+msg);
                for (int i=0;i<msg.length();i++){
                    if (msg.charAt(i)=='.'){
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                        }
                    }
                }
                System.out.println("消息处理完成/n");
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
            }
        };
        //消费数据
        c.basicConsume("helloworld",true,deliverCallback,cancelCallback);
    }
}
