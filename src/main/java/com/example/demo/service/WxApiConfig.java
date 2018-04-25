package com.example.demo.service;

/**
 * Created by xin on 3/5/2018.
 */
public class WxApiConfig {
    public static final String APP_ID = "wx5b5d5a3c62e1fd41";

    public static final String APP_SECRET = "6b0cfc54f4b2194e49ef3f2f9be0c547";



    //存在内存的中的token
    public static String ACCESS_TOKEN;

    //模版消息的ID
    public static String TEST_TEMPLATE="I1QHXccWe68-XkQrUEOOiTatiBCNwxjFAcHvRAE3rrY";


    //获取access_token
    public static final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //菜单创建
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //删除菜单
    public static final String MENU_DEL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    //模版消息
    public static final String PUSH_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";


}
