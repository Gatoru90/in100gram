package com.example.in100gram.centrifuge;

import android.util.Log;

import com.example.in100gram.Model.Chat;
import com.example.in100gram.centrifuge.ObservePattern.Observer;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HandlerData implements Observer {
    private String data;
    public String getData(){
        return data;
    }

    @Override
    public void handleEvent(String data) {
        this.data = data;
        Log.d("RPCReturn", this.data);
    }
}
