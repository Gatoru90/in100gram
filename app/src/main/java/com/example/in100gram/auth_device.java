package com.example.in100gram;

public class auth_device {

    private String uuid;
    private String device_name;

    public auth_device(String uuid, String device_name){
        this.uuid = uuid;
        this.device_name = uuid;
    }

    public String getUuid(){
        return uuid;
    }

    public String getDevice_name(){
        return device_name;
    }
}
