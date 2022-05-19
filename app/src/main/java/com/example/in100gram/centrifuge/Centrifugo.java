package com.example.in100gram.centrifuge;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.util.Log;

import com.example.in100gram.centrifuge.ObservePattern.Observed;
import com.example.in100gram.centrifuge.ObservePattern.Observer;
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

public class Centrifugo implements Observed {
    private String apiURL = "ws://192.168.24.22:8000/connection/websocket";
    private Client client;
    private String returnData;
    private List<Observer> observers = new ArrayList<>();


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
                returnData = new String(event.getData(), UTF_8);
                notifyObservers();
                Log.d("onPublish" + Calendar.getInstance().getTime(), returnData);
            }
        };
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for(Observer observer: observers){
            observer.handleEvent(returnData);
        }
    }
}
