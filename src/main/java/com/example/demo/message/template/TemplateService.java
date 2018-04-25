package com.example.demo.message.template;

import com.example.demo.service.WxApiConfig;
import com.example.demo.service.WxApiRequest;
import com.example.demo.util.DateUtils;
import net.sf.json.JSONObject;

import java.util.TreeMap;

/**
 * Created by admin on 2017/10/16.
 * 消息模板
 *   1.存取物消息
 *   2.领取物品消息
 *   3.支付消息
 */
public class TemplateService {

    private static JSONObject sendTemplate(String templateId,String openId,TreeMap<String,TreeMap<String,String>> params, String url){
        SendTemplate sendTemplate = new SendTemplate();
        sendTemplate.setData(params);
        sendTemplate.setTemplate_id(templateId);
        sendTemplate.setTouser(openId);
        sendTemplate.setUrl(url);
        String data = String.valueOf(JSONObject.fromObject(sendTemplate));
        JSONObject  templateMessage = WxApiRequest.getTemplate(data);
        return templateMessage;
    }


    public static boolean sendGetMessage(String openId){
        TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        //根据具体模板参数组装
        params.put("first",SendTemplate.item("测试1", "#000000"));
        params.put("keyword1",SendTemplate.item("慕孑晨CSDN", "#00008B"));
        params.put("keyword2",SendTemplate.item(DateUtils.getNowTime(), "#000000"));
        params.put("remark",SendTemplate.item("点击进入\n", "#ea535c"));
        String url = "http://blog.csdn.net/a1447275722/article/details/79388122";
        JSONObject jsonObject = sendTemplate(WxApiConfig.TEST_TEMPLATE, openId, params, url);
        return jsonObject.getInt( "errcode" ) == 0;
    }



}
