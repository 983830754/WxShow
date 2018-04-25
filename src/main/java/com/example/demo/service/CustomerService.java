package com.example.demo.service;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xin on 3/16/2018.
 */
public class CustomerService {
    private static String addCustomerUrl="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";

    private static String updateCustomerUrl="https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";

    private static String customerListUrl="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
    /**
     * 添加客服
     */
    public static  String add(String name,String nickName,String pwd){
         String url =addCustomerUrl.replace("ACCESS_TOKEN",WxApiRequest.getAccessToken());
        Map<String,String> map = new HashMap<>();
        map.put("kf_account",name);
        map.put("nickname",nickName);
        map.put("password",pwd);
        JSONObject jsonObject=JSONObject.fromObject(map);
        JSONObject jsonObject1=HttpTools.httpPostRequest(url, String.valueOf(jsonObject));
        System.out.println(jsonObject1);
        return (String) jsonObject1.get("errmsg");
    }

    /**
     * 修改客服账号
     *
     * @param args
     */

    public static String update(String name,String nickName,String pwd){
        String url = updateCustomerUrl.replace("ACCESS_TOKEN",WxApiRequest.getAccessToken());
        Map<String,String> map=new HashMap<>();
        map.put("kf_account",name);
        map.put("nickname",nickName);
        map.put("password", pwd);
        JSONObject jsonObject=JSONObject.fromObject(map);
        JSONObject jsonObject1=HttpTools.httpPostRequest(url, String.valueOf(jsonObject));
        System.out.println(jsonObject1);
        return (String) jsonObject1.get("errmsg");
    }

    public static String list(){
        String url= customerListUrl.replace("ACCESS_TOKEN",WxApiRequest.getAccessToken());
        JSONObject jsonObject=HttpTools.httpGetRequest(url);
        return jsonObject.toString();
    }
    public static void main(String[] args) {
      //  String list=list();
     //   System.out.println(list);
        add("text@gh_ac0d33a53265","客服2","11111");
    }


}
