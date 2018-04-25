package com.example.demo.controller;

import com.example.demo.juheshuju.Joke;
import com.example.demo.message.TextMessage;
import com.example.demo.message.template.TemplateService;
import com.example.demo.util.CheckUtil;
import com.example.demo.util.GetMediaId;
import com.example.demo.util.MessageUtil;
import org.apache.catalina.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by xin on 3/5/2018.
 */
@Controller
@RequestMapping("/wx")
public class WxController {
    @RequestMapping("/getToken")
    @ResponseBody
    public void connectWx(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
//        System.out.println("s");
//        String signature =request.getParameter("signature");
//        String timestamp =request.getParameter("timestamp");
//        String nonce =request.getParameter("nonce");
//        String echostr =request.getParameter("echostr");
//        PrintWriter out = response.getWriter();
//        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
//            out.print(echostr);
//        }


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String message = null;
        PrintWriter out = response.getWriter();
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            String eventKey = map.get("Event");
            if (eventKey.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {//关注后回复的消息
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.subscribeMsg());
                System.out.println("------"+map.get("EventKey"));
            }
        }else if (MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {//获取地理位置并回复所选取的位置
            String lable = map.get("Label");
            message = MessageUtil.initText(toUserName, fromUserName, lable);
        }else if(msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)){//文本消息的回复
            String content = map.get("Content");
            if(content.equals("1")){
                message=MessageUtil.initText(toUserName,fromUserName,MessageUtil.textMessage());//回复文字
            }else if(content.equals("2")){
                message=MessageUtil.initNewMessage(toUserName,fromUserName);//回复图文消息
            }else  if(content.equals("3")){
                message=MessageUtil.initPicMessage(toUserName,fromUserName,"-2exU2PFa36iG-cGe1ZmpBulJtQHuwEZTzdae44a3TOzBw5_-owK3hCQgac8qz0P");//回复图片消息
            }else  if(content.equals("4")){
                message=MessageUtil.initVoiceMessage(toUserName,fromUserName,"Xs9TxbWXcI0VuH3p_WxrNgICtHX5r_sQYhuoWTHY8Ws2RhF_aP0KULAB_5R5PNSS");//回复语音消息
            }else  if(content.equals("5")){
                message=MessageUtil.initVideoMessage(toUserName,fromUserName);//回复视频消息
            }else  if(content.equals("6")){
                message = MessageUtil.initMusicMessage(toUserName,fromUserName);//回复音乐消息
            }else if(content.equals("7")){//模版消息
                TemplateService.sendGetMessage(fromUserName);
            } else if(content.equals("笑话")){
               message=MessageUtil.initText(toUserName,fromUserName, Joke.getNowJoke());
            } else {
                message = MessageUtil.initText(toUserName,fromUserName,"喜欢你");
            }
        }else if(MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)){//用户发送图片 公总号回复图片
            String picUrl= map.get("PicUrl");
            String MediaId = map.get("MediaId");
            System.out.println(picUrl);
            if(picUrl!=null){
                message=MessageUtil.initPicMessage(toUserName,fromUserName,MediaId);
//                StringBuilder stringBuilder =new StringBuilder();
//                stringBuilder.append("您上传的图片地址是：\n");
//                stringBuilder.append(picUrl);
//                message = MessageUtil.initText(toUserName,fromUserName,stringBuilder.toString());
            }
        }else if(MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)){//用户发送语音，公总号回复语音
            String Recognition=map.get("Recognition");
            boolean stuta=Recognition.contains("笑话");
            System.out.println(Recognition);
            if(stuta){
                message=MessageUtil.initText(toUserName,fromUserName,Joke.getNowJoke());
            }else {
                message=MessageUtil.initText(toUserName,fromUserName,Recognition);
            }
        }
        System.out.println(message);
        if(message!=null){
            out.print(message);
            out.close();
        }

    }
}
