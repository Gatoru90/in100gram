package com.example.in100gram.centrifuge;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.util.Log;

import com.example.in100gram.centrifuge.Handlers.CreateHandler.CentrifugeHandler;
import com.example.in100gram.centrifuge.Handlers.CreateHandler.CentrifugeHandlerFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.ServerPublishEvent;

public class Centrifugo{
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
        Log.i("SendRPC", "method " + rpcName + " data: " + stringData);

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
                String returnData = new String(event.getData(), UTF_8);
                CentrifugeHandler handler = CentrifugeHandlerFactory.getCentrifugeHandler(returnData);
                handler.doAction();
                Log.d("onPublish " + Calendar.getInstance().getTime(), returnData);
            }
        };
    }
}
