package com.example.demo.util;

import com.example.demo.service.HttpTools;
import com.example.demo.service.WxApiConfig;
import net.sf.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by xin on 2017/10/24.
 */
public class AuthorizeUtil {

    public static String urlEncodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static String GetaccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String REDIRECT_URI = "http://mujiechen.tunnel.qydev.com/demo/index.html";
    public static String SCOPE = "snsapi_userinfo";

    public static String getCodeRequest() {
        String url = GetCodeRequest;
        url = url.replace("APPID", urlEncodeUTF8("wx5b5d5a3c62e1fd41"));
        url = url.replace("REDIRECT_URI", urlEncodeUTF8(REDIRECT_URI));
        url = url.replace("SCOPE", SCOPE);
        System.out.println(url);
        JSONObject jsonObject = HttpTools.httpGetRequest(url);
        System.out.println(jsonObject.toString());
        return jsonObject.getString("code");
    }


    /**
     * 获取授权token
     * @return
     */
    public static String getTokenUrl(String code){
        StringBuilder builder = new StringBuilder();
        builder.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        builder.append("appid="+WxApiConfig.APP_ID);
        builder.append("&secret="+ WxApiConfig.APP_SECRET);
        builder.append("&code="+code);
        builder.append("&grant_type=authorization_code");
        JSONObject jsonObject=HttpTools.httpGetRequest(builder.toString());
        System.out.println(jsonObject);
        return builder.toString();
    }

    public static void main(String[] args) {

    }

}
