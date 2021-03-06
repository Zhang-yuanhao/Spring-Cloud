package cn.tedu.sp04.order.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    //获取订单
    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        log.info("获取订单: orderId = "+orderId);

        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }

    //添加订单
    @GetMapping("/")
    public JsonResult addOrder() {
        //模拟post提交的数据
        Order order = new Order();
        order.setId("123abc");
        order.setUser(new User(8,null,null));
        order.setItems(Arrays.asList(new Item[] {
                new Item(1,"商品1",2),
                new Item(2,"商品2",4),
                new Item(3,"商品3",3),
                new Item(4,"商品4",1),
                new Item(5,"商品5",5),
        }));
        orderService.addOrder(order);//保存订单
        return JsonResult.ok().msg("添加订单成功");
    }
}