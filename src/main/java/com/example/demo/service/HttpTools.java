package com.example.demo.service;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * Created by wangwei on 17/10/12.
 */
public class HttpTools {

    //GET请求
    public static JSONObject httpGetRequest(String requestUrl) {
        return httpRequest(requestUrl, RequestMethod.GET, null);
    }

    //POST请求
    public static JSONObject httpPostRequest(String requestUrl, String outputStr) {
        return httpRequest(requestUrl, RequestMethod.POST, outputStr);
    }

    public static JSONObject httpRequest(String requestUrl, RequestMethod method, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());

            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setConnectTimeout(5000);
            httpUrlConn.setReadTimeout(5000);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            if (method == RequestMethod.GET) {
                httpUrlConn.setRequestMethod("GET");
            } else {
                httpUrlConn.setRequestMethod("POST");
            }

            if (outputStr != null) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();

            inputStream.close();
            httpUrlConn.disconnect();
            return JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * @desc ：微信上传素材的请求方法
     *
     * @param requestUrl  微信上传临时素材的接口url
     * @param file    要上传的文件
     * @return String  上传成功后，微信服务器返回的消息
     */
    public static String httpPicRequest(String requestUrl, File file) {
        StringBuffer buffer = new StringBuffer();

        try{
            //1.建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  //打开链接

            //1.1输入输出设置
            httpUrlConn.setDoInput(true);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setUseCaches(false); // post方式不能使用缓存
            //1.2设置请求头信息
            httpUrlConn.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConn.setRequestProperty("Charset", "UTF-8");
            //1.3设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            httpUrlConn.setRequestProperty("Content-Type","multipart/form-data; boundary="+ BOUNDARY);

            // 请求正文信息
            // 第一部分：
            //2.将文件头输出到微信服务器
            StringBuilder sb = new StringBuilder();
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length()
                    + "\";filename=\""+ file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream outputStream = new DataOutputStream(httpUrlConn.getOutputStream());
            // 将表头写入输出流中：输出表头
            outputStream.write(head);

            //3.将文件正文部分输出到微信服务器
            // 把文件以流文件的方式 写入到微信服务器中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                outputStream.write(bufferOut, 0, bytes);
            }
            in.close();
            //4.将结尾部分输出到微信服务器
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            outputStream.write(foot);
            outputStream.flush();
            outputStream.close();


            //5.将微信服务器返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();


        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static void forward(String url) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
        builder.append("appid=wx01529ae4dfafed33&");
        builder.append("redirect_uri=" + URLEncoder.encode("http://test.cool-grid.com/wx/login"));
        builder.append("&response_type=code&");
        builder.append("scope=snsapi_userinfo&");
        builder.append("state=STATE&");
        builder.append("#wechat_redirect");
        System.out.println(builder.toString());
    }

}
