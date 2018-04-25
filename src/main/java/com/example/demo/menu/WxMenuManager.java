package com.example.demo.menu;

import com.example.demo.service.WxApiRequest;
import net.sf.json.JSONObject;


/**
 * Created by admin on 2017/10/13.
 */
public class WxMenuManager {

    public static Menu initMenu() {
        Menu menu = new Menu();

        ViewButton receiveButton = new ViewButton();
        receiveButton.setName("百度翻译");
        receiveButton.setType("view");
        receiveButton.setUrl("http://fanyi.baidu.com/?aldtype=16047#auto/zh");

        ViewButton viewButton =new ViewButton();
        viewButton.setName("百度首页");
        viewButton.setType("view");
        viewButton.setUrl("https://www.baidu.com/");

        Button secondButton = new Button();
        secondButton.setName("百度");
        secondButton.setSub_button(new Button[]{receiveButton,viewButton});

        ClickButton clickButton = new ClickButton();
        clickButton.setName("扫一扫");
        clickButton.setType("scancode_push");
        clickButton.setKey("1");

        ClickButton clickButton1 =new ClickButton();
        clickButton1.setName("系统拍照发图");
        clickButton1.setType("pic_sysphoto");
        clickButton1.setKey("2");

        ClickButton clickButton2 =new ClickButton();
        clickButton2.setName("拍照或者相册发图");
        clickButton2.setType("pic_photo_or_album");
        clickButton2.setKey("3");

        ClickButton clickButton3=new ClickButton();
        clickButton3.setName("微信相册发图");
        clickButton3.setType("pic_weixin");
        clickButton3.setKey("4");

        Button threeButton = new Button();
        threeButton.setName("扫码和发图");
        threeButton.setSub_button(new Button[]{clickButton,clickButton1,clickButton2,clickButton3});

        ClickButton clickButton4 =new ClickButton();
        clickButton4.setName("发送位置");
        clickButton4.setType("location_select");
        clickButton4.setKey("5");

        Button fourButton =new Button();
        fourButton.setName("发送消息");
        fourButton.setSub_button(new Button[]{clickButton4});

        menu.setButton(new Button[]{secondButton, threeButton,fourButton});
        return menu;
    }

    public static void main(String[] args) {
        //先删除原来的菜单
        JSONObject delResult = WxApiRequest.del_menu();
        if (delResult.getString("errcode").equals("0")) {
            System.out.println("删除菜单成功");
        } else {
            System.out.println("删除菜单失败");
        }
        String menu = JSONObject.fromObject(initMenu()).toString();
        JSONObject createResult = WxApiRequest.create_menu(menu);
        if (createResult.getString("errcode").equals("0")) {
            System.out.println("创建成功");
        } else {
            System.out.println("错误码:" + createResult.getString("errcode") + "错误信息:" + createResult.getString("errmsg"));
        }
    }
}
