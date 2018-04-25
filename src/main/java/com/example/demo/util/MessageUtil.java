package com.example.demo.util;


import com.example.demo.message.*;
import com.example.demo.message.Pic.Image;
import com.example.demo.message.Pic.PhotoMessage;
import com.example.demo.message.music.Music;
import com.example.demo.message.music.MusicMessage;
import com.example.demo.message.picAndtext.Article;
import com.example.demo.message.picAndtext.NewsMessage;
import com.example.demo.message.video.Video;
import com.example.demo.message.video.VideoMesagge;
import com.example.demo.message.voice.Speech;
import com.example.demo.message.voice.Voice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.*;

/**
 * Created by wangwei on 17/10/13.
 */
public class MessageUtil {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)and 未关注群体扫描二维码
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：已关注群体扫描二维码
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：VIEW(点击自定义菜单跳转链接时的事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：transfer_customer_service(把消息推送给客服)
     */
    public static final String EVENT_TYPE_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";


    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    //屏蔽某些编译时的警告信息(在强制类型转换的时候编译器会给出警告)
//    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
//        // 将解析结果存储在HashMap中
//        Map<String, String> map = new HashMap<String, String>();
//
//        // 从request中取得输入流
//        InputStream inputStream = request.getInputStream();
//        // 读取输入流
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(inputStream);
//        // 得到xml根元素
//        Element root = document.getRootElement();
//        // 得到根元素的所有子节点
//        List<Element> elementList = root.elements();
//
//        // 遍历所有子节点
//        for (Element e : elementList)
//            map.put(e.getName(), e.getText());
//
//        // 释放资源
//        inputStream.close();
//        inputStream = null;
//        return map;
//    }
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        reader.setEncoding("utf-8");
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        doc.setXMLEncoding("utf-8");
        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for(Element e : list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    public static String initText(String toUserName,String fromUserName,String count){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(REQ_MESSAGE_TYPE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent(count);
        return textMessageToXml(textMessage);
    }

    /**
     * 关注消息
     * @return
     */
    public static String subscribeMsg(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("感谢您关注「我」\n\n");
        stringBuffer.append("微信Demo——\n");
        stringBuffer.append("回复1：文本消息的回复\n");
        stringBuffer.append("回复2：图文消息的回复\n");
        stringBuffer.append("回复3：图片消息的回复\n");
        stringBuffer.append("回复4：语音消息的回复\n");
        stringBuffer.append("回复5：视频消息的回复\n");
        stringBuffer.append("回复6：音乐消息的回复\n");
        stringBuffer.append("回复7：模版消息的测试\n");
        stringBuffer.append("发语音可以转文字\n");
        stringBuffer.append("发图片会回复图片\n");
        return stringBuffer.toString();
    }

    /**
     * 文本消息
     */
    public static String textMessage(){
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append("L\n\n");
        stringBuilder.append("O\n\n");
        stringBuilder.append("V\n\n");
        stringBuilder.append("E\n\n");
        return stringBuilder.toString();
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成xml
     *
     * @param photoMessage 文本消息对象
     * @return xml
     */
    public static String PicMessageToXml(PhotoMessage photoMessage) {
        xstream.alias("xml", photoMessage.getClass());
        return xstream.toXML(photoMessage);
    }
    /**
     * 视频消息对象转换成xml
     */
    public static  String ViodeMessageXml(VideoMesagge videoMesagge){
        xstream.alias("xml",videoMesagge.getClass());
        return  xstream.toXML(videoMesagge);
    }

    /**
     * 语音消息对象的转换XML
     */
    public static String VoiceMessageToXml(Speech speech){
        xstream.alias("xml",speech.getClass());
        return xstream.toXML(speech);
    }

    /**
     * 音乐消息对象转换XML
     */
    public static String MusicMessageToXml(MusicMessage musicMessage){
        xstream.alias("xml",musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }


    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewMessage(String toUserName,String fromUserName){
        String message=null;
        List<Article> newsList =new ArrayList<>();
        NewsMessage newsMessage =new NewsMessage();
        Article article =new Article();
        article.setTitle("测试1");
        article.setPicUrl("http://img4.imgtn.bdimg.com/it/u=1483205935,2932348033&fm=27&gp=0.jpg");
        article.setUrl("http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%CE%A8%C3%C0%D2%E2%BE%B3%CD%BC%C6%AC&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=000000");
        newsList.add(article);
        Article article1 =new Article();
        article1.setTitle("one");
        article1.setPicUrl("http://img1.imgtn.bdimg.com/it/u=3436416246,1777747283&fm=27&gp=0.jpg");
        article1.setUrl("http://blog.csdn.net/a1447275722/article/details/79388122");
        newsList.add(article1);
        Article article2=new Article();
        article2.setTitle("two");
        article2.setPicUrl("http://img2.imgtn.bdimg.com/it/u=3333613237,1532402705&fm=27&gp=0.jpg");
        article2.setUrl("http://blog.csdn.net/forezp/article/details/70148833");
        newsList.add(article2);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message= newsMessageToXml(newsMessage);
        return message;
    }

    /**
     * 组装图片消息
     */
      public static String initPicMessage(String toUserName,String fromUserName,String mediaId){
          String message=null;
          PhotoMessage photoMessage=new PhotoMessage();
          photoMessage.setToUserName(fromUserName);
          photoMessage.setFromUserName(toUserName);
          Image image=new Image();
          image.setMediaId(mediaId);
          photoMessage.setCreateTime(new Date().getTime());
          photoMessage.setMsgType("image");
          photoMessage.setImage(image);
          message =PicMessageToXml(photoMessage);
          return message;
      }

    /**
     * 组装语音消息
     */
    public static String initVoiceMessage(String toUserName,String formUserName,String MediaID){
        String message=null;
        Speech speech=new Speech();
        speech.setFromUserName(toUserName);
        speech.setToUserName(formUserName);
        speech.setCreateTime(new Date().getTime());
        speech.setMsgType("voice");
        Voice voice =new Voice();
        voice.setMediaId(MediaID);
        speech.setVoice(voice);
        message=VoiceMessageToXml(speech);
        return message;
    }

    /**
     * 组装视频消息
     */
    public static String initVideoMessage(String toUserName,String formUserName){
        String message=null;
        Video video =new Video();
        video.setMediaId("19D2Ii_wZRYv7FwspDapynBQPB3pRMQYIWSmPXF87qitMlPn8M29FWR4tujGjXsJ");
        video.setTitle("奋斗");
        video.setDescription("一个人的城市，没人帮");
        VideoMesagge videoMesagge=new VideoMesagge();
        videoMesagge.setToUserName(formUserName);
        videoMesagge.setFromUserName(toUserName);
        videoMesagge.setMsgType("video");
        videoMesagge.setCreateTime(new Date().getTime());
        videoMesagge.setVideo(video);
       message =ViodeMessageXml(videoMesagge);
        return message;
    }

    /**
     * 组装音乐消息
     */
    public static  String initMusicMessage(String toUserName,String formUserName){
        String message=null;
        MusicMessage musicMessage=new MusicMessage();
        musicMessage.setMsgType("music");
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setToUserName(formUserName);
        musicMessage.setFromUserName(toUserName);
        Music music = new Music();
        music.setTitle("夜空最亮的星");
        music.setDescription("你是心中最亮的星星");
        music.setHQMusicUrl("http://url.cn/5lnrA4B");
        music.setMusicUrl("http://url.cn/5lnrA4B");
        music.setThumbMediaId("hpmtXZCUVYYfaapLHB6DNMiinEpsA4qcmiW5IpkXZRLNlp7k5arMCEO6uLw-I_R9");
        musicMessage.setMusic(music);
        message=MusicMessageToXml(musicMessage);
        return message;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    }


