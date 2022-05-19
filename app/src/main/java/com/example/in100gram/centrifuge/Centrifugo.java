package com.example.in100gram.centrifuge;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Calendar;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.RPCResult;
import io.github.centrifugal.centrifuge.ReplyCallback;
import io.github.centrifugal.centrifuge.ReplyError;
import io.github.centrifugal.centrifuge.ServerPublishEvent;

public class Centrifugo {
    private String apiURL = "ws://192.168.24.22:8000/connection/websocket";
    private Client client;

    public Centrifugo() {

        EventListener listener = subscribeListeners();
        client = new Client(
                apiURL,
                new Options(),
                listener
        );
    }

    public Client getClient(){
        return this.client;
    }

    public void onConnect(){
        this.client.connect();
    }
    public <T> void sendRPC(String rpcName, T data){
        String stringData = new Gson().toJson(data);
        Log.i("SendRPC", "method: " + rpcName + " data: " + stringData);

        this.client.rpc(rpcName, stringData.getBytes(), new CentrifugoReplyCallback<>(rpcName));
    }
    private EventListener subscribeListeners(){
        return new EventListener() {
            @Override
            public void onConnect(Client client, ConnectEvent event) {
                super.onConnect(client, event);
            }

            @Override
            public void onDisconnect(Client client, DisconnectEvent event) {
                super.onDisconnect(client, event);
            }

            @Override
            public void onPublish(Client client, ServerPublishEvent event) {
                super.onPublish(client, event);
                String data = new String(event.getData(), UTF_8);
                Log.d("onPublish" + Calendar.getInstance().getTime(), data);
            }
        };
    }
}
