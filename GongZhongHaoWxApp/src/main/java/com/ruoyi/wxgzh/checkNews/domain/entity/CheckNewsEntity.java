package com.ruoyi.wxgzh.checkNews.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName GetCheckEntity
 * @Author Jing Yaobin
 * @Date 2021/3/8 14:49
 * @Description 微信配置服务器验证实体类(接收)
 **/
@Data
public class CheckNewsEntity implements Serializable {

    /** 微信加密签名 */
    private String signature;

    /** 时间戳 */
    private String timestamp;

    /** 随机数 */
    private String nonce;

    /** 随机字符串 */
    private String echostr;

}
