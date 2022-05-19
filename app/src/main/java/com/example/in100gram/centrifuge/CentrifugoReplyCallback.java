package com.example.in100gram.centrifuge;

import android.annotation.SuppressLint;
import android.util.Log;

import io.github.centrifugal.centrifuge.ReplyCallback;
import io.github.centrifugal.centrifuge.ReplyError;

public class CentrifugoReplyCallback<T> implements ReplyCallback<T> {
    private String rpcName;

    public CentrifugoReplyCallback(String rpcName){
        this.rpcName = rpcName;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onFailure(Throwable e) {
        Log.e("SEND_RPC_FAIL" + rpcName, e.toString());
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onDone(ReplyError error, T result) {
        Log.i("SEND_RPC_SUCCESS" + rpcName, String.valueOf(error));
    }
}
