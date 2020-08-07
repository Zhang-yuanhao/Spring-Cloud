package m2.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;


public class Producer {
    public static void main(String[] args) throws Exception {
        //连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.151");
        f.setUsername("anmin");
        f.setPassword("anmin");

        Channel c = f.newConnection().createChannel();
        //定义队列
        c.queueDeclare("helloworld",false,false,false,null);

        while (true){
            System.out.println("输入消息");
            String msg = new Scanner(System.in).nextLine();
            c.basicPublish("","helloworld",null,msg.getBytes());
        }
    }
}
