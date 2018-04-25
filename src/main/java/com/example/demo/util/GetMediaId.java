package com.example.demo.util;

import com.example.demo.service.HttpTools;
import com.example.demo.service.WxApiRequest;
import net.sf.json.JSONObject;

import java.io.File;

/**
 * Created by xin on 3/6/2018.
 */
public class GetMediaId {
    private static String getUrl="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    public  static  String getMediaId(String url){
    String urls=getUrl.replace("ACCESS_TOKEN", WxApiRequest.getAccessToken()).replace("TYPE","image");
        System.out.println(url);
        File file=new File(url);
        JSONObject jsonObject= JSONObject.fromObject(HttpTools.httpPicRequest(urls,file));
        System.out.println(jsonObject);
        System.out.println( jsonObject.get("media_id"));
        return (String) jsonObject.get("media_id");
    }
    public static void main(String[] args) {
//       String url=getUrl.replace("ACCESS_TOKEN", WxApiRequest.getAccessToken()).replace("TYPE","image");
//        System.out.println(url);
//        File file=new File("C:\\Users\\xin\\Desktop\\WxDemo\\src\\main\\resources\\static\\image\\1.jpg");
//        JSONObject jsonObject= JSONObject.fromObject(HttpTools.httpPicRequest(url,file));
//        System.out.println(jsonObject);

//        String url=getUrl.replace("ACCESS_TOKEN", WxApiRequest.getAccessToken()).replace("TYPE","voice");
//        System.out.println(url);
//        File file=new File("C:\\Users\\xin\\Desktop\\WxDemo\\src\\main\\resources\\static\\voice\\2.mp3");
//        JSONObject jsonObject= JSONObject.fromObject(HttpTools.httpPicRequest(url,file));
//        System.out.println(jsonObject);

//        String url=getUrl.replace("ACCESS_TOKEN", WxApiRequest.getAccessToken()).replace("TYPE","video");
//        System.out.println(url);
//        File file=new File("C:\\Users\\xin\\Desktop\\WxDemo\\src\\main\\resources\\static\\video\\3.mp4");
//        JSONObject jsonObject= JSONObject.fromObject(HttpTools.httpPicRequest(url,file));
//        System.out.println(jsonObject);


//        String url=getUrl.replace("ACCESS_TOKEN", WxApiRequest.getAccessToken()).replace("TYPE","thumb");
//        System.out.println(url);
//        File file=new File("C:\\Users\\xin\\Desktop\\WxDemo\\src\\main\\resources\\static\\image\\4.jpg");
//        JSONObject jsonObject= JSONObject.fromObject(HttpTools.httpPicRequest(url,file));
//        System.out.println(jsonObject);

    }
}
