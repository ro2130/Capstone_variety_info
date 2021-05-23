package com.example.shingshinginfo.h_network;

public class ResponseSensorJson {


    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }


    private String soil; // 토양습도
    private String light; // 조도
    private String temp; // 공기 온도
    private String humi; // 공기 습도

    private String comment; // 센서 총 코멘트



}
