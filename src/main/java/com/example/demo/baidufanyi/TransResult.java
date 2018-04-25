package com.example.demo.baidufanyi;



/**
 * Created by xin on 3/5/2018.
 */
public class TransResult {
    private String form;
    private String to;
    private String errno;
    private Data data;

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public Data getData() {
        return data;
    }

    public void setDate(Data data) {
        this.data = data;
    }
}
