package com.ruoyi.wxgzh.checkNews.controller;

import com.ruoyi.wxgzh.config.WeChatConfig;
import com.ruoyi.wxgzh.checkNews.domain.entity.CheckNewsEntity;
import com.ruoyi.wxgzh.checkNews.domain.entity.TextNewsEntity;
import com.ruoyi.wxgzh.checkNews.domain.vo.TextNewsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName GetCheck
 * @Author Jing Yaobin
 * @Date 2021/3/8 14:42
 * @Description 验证消息的确来自微信服务器、接收基础消息
 **/
@Api("验证消息的确来自微信服务器、接收基础消息")
@Slf4j
@RestController
@RequestMapping("/weChat/check")
public class CheckNewsController {

    /**
     * @MethodName getCheck
     * @Author Jing Yaobin
     * @Param [getCheckEntity, response]
     * @Return void
     * @Date 2021/3/8 15:17
     * @Description
     *      //TODO 验证微信配置信息（微信公众号开发之配置开发服务器或验证消息的确来自微信服务器）
     **/
    @ApiOperation("验证消息的确来自微信服务器")
    @GetMapping(value = "/getCheck")
    public void getCheck(@Validated CheckNewsEntity checkNewsEntity, HttpServletResponse response){

        log.info("《《《《《《《《《《 调用的是消息验证接口 》》》》》》》》》》");

        if (checkNewsEntity != null){

            log.info("获取到的验证消息是：" + checkNewsEntity.getSignature());

            // 微信工具服务类
            WxMpService wxMpService = new WxMpServiceImpl();

            // 生产环境
            WxMpInMemoryConfigStorage wxMpIn = new WxMpInMemoryConfigStorage();

            // 注入 token 值
            wxMpIn.setToken(WeChatConfig.token);

            // 将注入的 token 值存入生产环境
            wxMpService.setWxMpConfigStorage(wxMpIn);

            // 验证 token 跟微信配置的是否一样
            boolean flag = wxMpService.checkSignature(checkNewsEntity.getTimestamp(),checkNewsEntity.getNonce(),checkNewsEntity.getSignature());

            // 声明返回数据
            PrintWriter out = null;

            try {
                out = response.getWriter();
                // 验证成功，返回接收到的随机字符串，和微信进行验证
                if (flag){
                    out.print(checkNewsEntity.getEchostr());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                out.close();
            }
        }
    }

    @ApiOperation("接收普通消息")
    @PostMapping(value = "/getCheck", consumes = "text/xml", produces = "text/xml")
    public TextNewsVo getTextNews(@RequestBody TextNewsEntity textNewsEntity){

        log.info("《《《《《《《《《《 调用的是消息 》》》》》》》》》》");

        TextNewsVo textNewsVo = new TextNewsVo();

        // 声明返回数据
        PrintWriter out = null;

        if (textNewsEntity != null){
            log.info("接收到的消息是：" + textNewsEntity);

            // 获取接收到的消息类型
            String msgType = textNewsEntity.getMsgType();

            // 根据类型进行处理
            switch (msgType){
                // 文本消息类型
                case "text":
                    break;

                // 图片消息类型
                case "image":
                    break;

                // 语音消息类型
                case "voice":
                    break;

                // 视频消息类型
                case "video":
                    break;

                // 小视频消息类型
                case "shortvideo":
                    break;

                // 地理位置消息类型
                case "location":
                    break;

                // 连接消息类型
                case "link":
                    break;
            }


            if (textNewsEntity.getContent().equals("消息列表")){

                textNewsVo.setToUserName(textNewsEntity.getFromUserName());//getFromUserName
                textNewsVo.setFromUserName(textNewsEntity.getToUserName());
                textNewsVo.setCreateTime(new Date().getTime());
                textNewsVo.setMsgType("text");
                textNewsVo.setContent("这个是测试返回消息：\r\n 1、回复文本消息 \r\n 2、回复图片消息 \n 3、回复语音消息 \n 4、回复视频消息 \n 5、回复音乐消息 \n 6、回复如闻消息");
                log.info("返回的消息：" + textNewsVo);
            }
        }else {
            log.info("接收到的消息是空！！！");
        }
        return textNewsVo;
    }
}
