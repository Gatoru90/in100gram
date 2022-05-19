package com.example.in100gram.centrifuge;

import android.util.Log;

import com.example.in100gram.centrifuge.ObservePattern.Observer;

public class handlerData implements Observer {
    private String data;
    public String getData(){
        return data;
    }
    @Override
    public void handleEvent(String data) {
        this.data = data;
        Log.d("RPC return", data);
    }
}
