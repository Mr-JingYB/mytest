package com.ruoyi.wxgzh.checkNews.domain.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName testDomain
 * @Author Jing Yaobin
 * @Date 2021/3/5 16:29
 * @Description 文本信息实体类（接收）
 **/
@Data
@JacksonXmlRootElement
public class TextNewsEntity implements Serializable {

    /** 开发者微信号 */
    private String ToUserName;

    /** 发送方帐号（一个OpenID） */
    private String FromUserName;

    /** 消息创建时间 （整型） */
    private String CreateTime;

    /** 消息类型，文本为text */
    private String MsgType;

    /** 文本消息内容 */
    private String Content;

    /** 图片链接（有系统生成） */
    private String PicUrl;

    /** 图片消息媒体 ID，可以调用获取临时素材接口拉取数据 */
    private String MediaId;

    /** 消息id，64位整型 */
    private String MsgId;

    @JacksonXmlProperty(localName = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    @JacksonXmlProperty(localName = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    @JacksonXmlProperty(localName = "CreateTime")
    public String getCreateTime() {
        return CreateTime;
    }

    @JacksonXmlProperty(localName = "MsgType")
    public String getMsgType() {
        return MsgType;
    }

    @JacksonXmlProperty(localName = "Content")
    public String getContent() {
        return Content;
    }

    @JacksonXmlProperty(localName = "PicUrl")
    public String getPicUrl() {
        return PicUrl;
    }

    @JacksonXmlProperty(localName = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    @JacksonXmlProperty(localName = "MsgId")
    public String getMsgId() {
        return MsgId;
    }

}
