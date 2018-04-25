package com.example.demo.message.voice;

import com.example.demo.message.BaseMessage;

/**
 * Created by xin on 3/15/2018.
 */
public class Speech extends BaseMessage {
    private Voice Voice;

    public com.example.demo.message.voice.Voice getVoice() {
        return Voice;
    }

    public void setVoice(com.example.demo.message.voice.Voice voice) {
        Voice = voice;
    }
}
