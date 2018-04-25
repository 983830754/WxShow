package com.example.demo.juheshuju;

import com.example.demo.juheshuju.util.HttpTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xin on 3/20/2018.
 */
public class Joke {
    //获取最新的笑话
    public static String getNowJoke(){
        String result =null;
        Map<String,String> map=new HashMap<>();
        map.put("page","1");
        map.put("pagesize","5");
        map.put("key",PolymerizationConfig.LUCK_KEY);
        StringBuilder stringBuilder=new StringBuilder();
        try {
            result = HttpTool.net(PolymerizationConfig.JOKE_URL,map,"GET");
            System.out.println(result);
            JSONObject jsonObject=JSONObject.fromObject(result);
            if(jsonObject.getInt("error_code")==0){
                JSONObject jsonObject1= (JSONObject) jsonObject.get("result");
               JSONArray list= (JSONArray) jsonObject1.get("data");
                for (int i=0;i<list.size();i++){
                    JSONObject jsonObject2= (JSONObject) list.get(i);
                    String s= (String) jsonObject2.get("content");
                    System.out.println(s);
                   stringBuilder.append(s+"\n");
                }
            }else{
                System.out.println(jsonObject.get("error_code")+":"+jsonObject.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       if(stringBuilder==null){
           return null;
       }else {
           return String.valueOf(stringBuilder);
       }

    }


    public static void main(String[] args) {
        String s=getNowJoke();
        System.out.println(s);
    }
}
