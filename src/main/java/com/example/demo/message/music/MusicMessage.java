package com.example.demo.message.music;

import com.example.demo.message.BaseMessage;

/**
 * Created by xin on 3/15/2018.
 */
public class MusicMessage extends BaseMessage {
    private Music Music;

    public com.example.demo.message.music.Music getMusic() {
        return Music;
    }

    public void setMusic(com.example.demo.message.music.Music music) {
        Music = music;
    }
}
