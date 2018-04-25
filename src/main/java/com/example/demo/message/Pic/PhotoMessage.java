package com.example.demo.message.Pic;

import com.example.demo.message.BaseMessage;
import com.example.demo.message.Pic.Image;

/**
 * Created by xin on 3/6/2018.
 */
public class PhotoMessage extends BaseMessage {
    private com.example.demo.message.Pic.Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
    }
}
