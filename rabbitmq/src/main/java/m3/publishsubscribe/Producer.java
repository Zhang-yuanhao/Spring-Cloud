package m3.publishsubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.151");
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();

        // 定义交换机
        //c.exchangeDeclare("logs", "fanout");
        c.exchangeDeclare("logs",BuiltinExchangeType.FANOUT);

        // 向交换机发送数据
        while (true){
            System.out.println("输入消息:");
            String msg = new Scanner(System.in).nextLine();

            // 第二个参数，对于fanout交换机无效
            c.basicPublish("logs","",null,msg.getBytes());
        }
    }
}
