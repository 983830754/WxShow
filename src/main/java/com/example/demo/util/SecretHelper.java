package com.example.demo.util;



public class SecretHelper {

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            return Base64.getEncoder().encodeToString(content.getBytes("utf-8"));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        try {
            return new String(Base64.getDecoder().decode(content.getBytes("utf-8")));
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args){

    }


}
