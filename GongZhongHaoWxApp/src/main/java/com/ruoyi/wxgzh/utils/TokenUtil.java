package com.ruoyi.wxgzh.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.wxgzh.config.WeChatConfig;
import com.ruoyi.wxgzh.constant.WeChatConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenUtil
 * @Author Jing Yaobin
 * @Date 2021/3/5 11:13
 * @Description 获取access token工具类
 **/
@Slf4j
@Component // 将类注入到 bean 中
public class TokenUtil {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @MethodName getToken
     * @Author Jing Yaobin
     * @Param []
     * @Return java.lang.String
     * @Date 2021/3/8 15:55
     * @Description
     *      //TODO 获取 token 方法
     **/
    public Map<String,Object> getToken(){

        // 声明返回的 Map
        Map<String,Object> restMap = new HashMap<>();

        // 查询缓存中是否存在 Access_token
        boolean hasKey = redisUtil.exists(WeChatConstant.ACCESS_TOKEN);

        // 声明返回字符串
        String tokenStr = "";

        // 缓存中存在 Access_token
        if (hasKey){
            // 获取 Redis 缓存
            Object object = redisUtil.get(WeChatConstant.ACCESS_TOKEN);
            tokenStr = object.toString();
        }else {
            // 从第三方接口获取
            Map<String,Object> map = new HashMap<>();
            map.put("grant_type",weChatConfig.getGrantType());
            map.put("appid",weChatConfig.getAppId());
            map.put("secret",weChatConfig.getAppSecret());

            try {
                tokenStr = HttpUtil.get(weChatConfig.getUrl() + WeChatConstant.TOKEN,map);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("调用第三方接口异常！");
            }
            JSONObject jsonTokenStr = JSON.parseObject(tokenStr);
            tokenStr = jsonTokenStr.getString("access_token");
            // 添加 Reids 缓存（set中参数含义：key值，user对象，缓存存在时间(Long类型)，时间单位(秒)）
            redisUtil.set(WeChatConstant.ACCESS_TOKEN,tokenStr,6900L, TimeUnit.SECONDS);
        }
        log.info("获取到的 Token 是：" + tokenStr);

        restMap.put("access_token",tokenStr);
        return restMap;
    }

}
