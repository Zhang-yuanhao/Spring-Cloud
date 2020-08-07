package m1.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        //连接 Rabbitmq 服务器
       ConnectionFactory f = new ConnectionFactory();
       f.setHost("192.168.64.129");
       f.setPort(5672); //5672是通信端口
       f.setUsername("admin");
       f.setPassword("admin");

       //如果使用老师的服务器,需要使用自己的虚拟主机,避免互相影响
       Connection con = f.newConnection();
       Channel c = con.createChannel();

        //定义队列,会通知服务器想使用一个"helloworld"队列
        //服务器会找到这个队列 如果不存在,服务器会新建队列
        c.queueDeclare("helloworld",false,false,false,null);

        //发送消息
        c.basicPublish("","helloworld",null,"Hello world".getBytes());
        System.out.println("消息已发送");
        c.close();
        con.close();

    }
}
