package com.ruoyi.wxgzh.wechatUser.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.wxgzh.config.WeChatConfig;
import com.ruoyi.wxgzh.constant.WeChatConstant;
import com.ruoyi.wxgzh.utils.TokenUtil;
import com.ruoyi.wxgzh.wechatUser.domain.entity.UserTitleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatUserController
 * @Author Jing Yaobin
 * @Date 2021-06-18 10:51
 * @Description 用户标签管理
 **/
@Slf4j
@RestController
@RequestMapping("/weChat/user")
public class WechatUserController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private WeChatConfig weChatConfig;


    @PostMapping("/addUserTitle")
    public AjaxResult addUserTitle(@Valid UserTitleEntity userTitleEntity, BindingResult result){

        //验证字段是否符合规则
        if (result.hasErrors()){
            throw new RuntimeException(result.getFieldError().getDefaultMessage());
        } else {

            // 获取 access_token
            Map<String, Object> map = tokenUtil.getToken();
            map.put("name",userTitleEntity.getName());

            String s = HttpUtil.get(weChatConfig.getUrl() + WeChatConstant.ADD_USER_TITLE, map);
            System.out.println("获取到的s是：" + s);
            String s1 = Convert.toStr(s);
            Integer integer = Convert.toInt(userTitleEntity.getName());
            System.out.println("获取到的str是：" + integer);
            // 判断操作成功与否
            if (integer > 0){
                return AjaxResult.success("新增标签名成功！");
            } else {
                return AjaxResult.error("新增标签名失败！");
            }
        }
    }

}
