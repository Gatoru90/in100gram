package com.example.in100gram.centrifuge.Handlers;

import android.util.Log;

import com.example.in100gram.centrifuge.Handlers.CreateHandler.CentrifugeHandler;

public class NullHandler extends CentrifugeHandler {
    @Override
    public void doAction() {
        Log.d("RETURN", "Return NullObject");
    }
}
