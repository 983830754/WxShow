package com.example.demo.service;

import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by xin on 3/5/2018.
 */
public class WxApiRequest {

    public static void updateAccessToken(){
        String url = WxApiConfig.GET_TOKEN;
        url = url.replace("APPID",WxApiConfig.APP_ID).replace("APPSECRET",WxApiConfig.APP_SECRET);
        JSONObject result = HttpTools.httpGetRequest(url);
        WxApiConfig.ACCESS_TOKEN = result.getString("access_token");
    }
    //获取access_token
    public static String getAccessToken(){
        if(StringUtils.isEmpty(WxApiConfig.ACCESS_TOKEN)){
            updateAccessToken();
        }
        return WxApiConfig.ACCESS_TOKEN;
    }

    //创建微信菜单
    public static JSONObject create_menu(String menu){
        String url = WxApiConfig.MENU_CREATE;
        url = url.replace("ACCESS_TOKEN", getAccessToken());
        return HttpTools.httpPostRequest(url, menu);
    }

    //删除微信菜单
    public static JSONObject del_menu(){
        String url = WxApiConfig.MENU_DEL;
        url = url.replace("ACCESS_TOKEN", getAccessToken());
        return HttpTools.httpGetRequest(url);
    }

    public static JSONObject getTemplate(String data){
        String url = WxApiConfig.PUSH_TEMPLATE;
        url =url.replace("ACCESS_TOKEN",getAccessToken());
        JSONObject jsonObject=HttpTools.httpPostRequest(url,data);
        return jsonObject;
    }

}
