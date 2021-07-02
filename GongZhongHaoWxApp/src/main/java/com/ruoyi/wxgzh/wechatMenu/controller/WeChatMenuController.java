package com.ruoyi.wxgzh.wechatMenu.controller;

import cn.hutool.http.HttpUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.wxgzh.config.WeChatConfig;
import com.ruoyi.wxgzh.constant.WeChatConstant;
import com.ruoyi.wxgzh.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MenuController
 * @Author Jing Yaobin
 * @Date 2021/3/11 16:49
 * @Description 自定义菜单接口
 **/
@Slf4j
@RestController
@RequestMapping("/weChat/menu")
public class WeChatMenuController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * @MethodName getMenu
     * @Author Jing Yaobin
     * @Param []
     * @Return com.ruoyi.framework.web.domain.AjaxResult
     * @Date 2021/3/17 10:39
     * @Description
     *      //TODO 获取自定义菜单
     **/
    @GetMapping("/getMenu")
    public AjaxResult getMenu(){

        // 获取 token
        Map<String, Object> map = tokenUtil.getToken();
        // 声明返回的值
        String menuStr = "";
        // 获取自定义菜单
        try {
            menuStr = HttpUtil.get(weChatConfig.getUrl() + WeChatConstant.MENU_GET, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取到的菜单：" + menuStr);
        return new AjaxResult().put("menuStr",menuStr);

    }

    @PostMapping("/createMenu")
    public AjaxResult createMenu(){

        // 获取 token
        Map<String,Object> map = tokenUtil.getToken();
        // 需要传递的值
        String menu = "{\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      }";
        map.put("body",menu);
        // 声明返回的值
        String menuStr = "";
        // 获取自定义菜单
        try {
            menuStr = HttpUtil.get(weChatConfig.getUrl() + WeChatConstant.MENU_CREATE, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取到的状态码是：" + menuStr);
        return new AjaxResult().put("menuStr",menuStr);
    }

}
