package com.ruoyi.wxgzh.wechatUser.domain.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName UserTitleEntity
 * @Author Jing Yaobin
 * @Date 2021-06-18 11:12
 * @Description 用户标签管理实体类
 **/
@Data
public class UserTitleEntity implements Serializable {

    @NotBlank(message = "标签名不能为空")
    @Length(max = 15, message = "标签名 长度不得超过 15 个字符")
    private String name;

}
