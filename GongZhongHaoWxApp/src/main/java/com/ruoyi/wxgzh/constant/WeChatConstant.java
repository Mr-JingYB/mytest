package com.ruoyi.wxgzh.constant;

/**
 * @ClassName WeChatConstant
 * @Author Jing Yaobin
 * @Date 2021/3/11 16:38
 * @Description 常量
 **/
public class WeChatConstant {

    /**
     *  @Description：redis 中 token 存储
     **/
    /** token 缓存地址 */
    public static final String ACCESS_TOKEN = "weChatGongZhongHao:access_token";


    /**
     * @Description：获取微信 token
     **/
    /** 获取 Access_token 地址 */
    public static final String TOKEN = "/token?";


    /**
     * @Description：获取用户管理
     **/
    /** 创建标签地址 */
    public static final String ADD_USER_TITLE = "/tags/create";


    /**
     * @Description：获取自定义菜单
     **/
    /** 查询自定义菜单 */
    public static final String MENU_GET = "/get_current_selfmenu_info?";

    /** 创建自定义菜单 */
    public static final String MENU_CREATE = "/menu/create?";

}
