package com.example.in100gram.centrifuge.Handlers.CreateHandler;

import android.util.Log;

public abstract class CentrifugeHandler{
    protected static boolean handlerNameAction(String handlerName, String action){
        Log.i("handlerNameAction", ""+ handlerName);
        return handlerName.equals(action);
    }
    public abstract void doAction();

}
