package com.ruoyi.wxgzh.checkNews.domain.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @ClassName TextNewsVo
 * @Author Jing Yaobin
 * @Date 2021/3/8 16:55
 * @Description 文本信息实体类(返回)
 **/
@Data
@JacksonXmlRootElement(localName = "xml") // 返回 xml 格式的最顶层
public class TextNewsVo {

    /** 接收方帐号（收到的OpenID） */
    private String ToUserName;

    /** 开发者微信号 */
    private String FromUserName;

    /** 消息创建时间 （整型） */
    private Long CreateTime;

    /** 消息类型，文本为text */
    private String MsgType;

    /** 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） */
    private String Content;

    @JacksonXmlProperty(localName = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    @JacksonXmlProperty(localName = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    @JacksonXmlProperty(localName = "CreateTime")
    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    @JacksonXmlProperty(localName = "MsgType")
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @JacksonXmlProperty(localName = "Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
