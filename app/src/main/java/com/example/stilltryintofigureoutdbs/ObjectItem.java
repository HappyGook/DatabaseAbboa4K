package com.example.stilltryintofigureoutdbs;

public class ObjectItem {
    private String header,desc,time;

    public ObjectItem(String header,String desc,String time) {
        this.header=header;
        this.desc=desc;
        this.time=time;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String header) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String header) {
        this.time = time;
    }

}
