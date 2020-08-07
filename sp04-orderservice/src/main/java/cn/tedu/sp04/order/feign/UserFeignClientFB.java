package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;

public class UserFeignClientFB implements UserFeignClient{
    @Override
    public JsonResult<User> getUser(Integer userId) {
        //模拟缓存数据
        if (Math.random()<0.5) {
            User user = new User(userId,"缓存用户"+userId,"缓存密码"+userId);
            return JsonResult.ok().data(user);
        }
        return JsonResult.err().msg("无法获取用户信息");
    }

    @Override
    public JsonResult<?> addScore(Integer userId, Integer score) {
        return JsonResult.err().msg("无法增加用户积分");
    }
}
