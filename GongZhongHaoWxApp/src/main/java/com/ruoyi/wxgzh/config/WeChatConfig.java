package com.ruoyi.wxgzh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName constant
 * @Author Jing Yaobin
 * @Date 2021/3/4 11:17
 * @Description 获取微信公众号常量
 **/

@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class WeChatConfig {

    /** 获取 access token 的请求地址 */
    public String url;

    /** 公众号的 AppId */
    public String appId;

    /** appSecret */
    public String appSecret;

    /** 获取 token 填写： client_credential */
    public String grantType;

    /** 获取服务器配置中的 令牌（token） */
    public static String token;

}
