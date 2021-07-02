package com.ruoyi.project.system.wechatPublic.wechatMenu.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName WechatMenuController
 * @Author Jing Yaobin
 * @Date 2021/3/17 15:46
 * @Description 微信自定义菜单
 **/
@Controller
@RequestMapping("/system/wechatPublic")
public class WechatMenuController extends BaseController {

    /** 定义访问路径前缀 */
    private String prefix = "system/wechatPublic/wechatMenu";

    @GetMapping()
    @RequiresPermissions("system:wechatMenu:view")
    public String wechatMenu(){
        return prefix + "/wechatMenu";
    }


}
