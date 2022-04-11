package com.example.in100gram.centrifuge;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.RPCResult;
import io.github.centrifugal.centrifuge.ReplyCallback;
import io.github.centrifugal.centrifuge.ReplyError;
import io.github.centrifugal.centrifuge.ServerPublishEvent;
import io.github.centrifugal.centrifuge.ServerSubscribeEvent;

public class Centrifuge {
    public Client client;
    Context context;


    String remsg = "";

    public Centrifuge(Context AplicationContext) {
        context = AplicationContext;


        byte[] my_mess2 =  ("{\"auth_id\":\"00000000-0000-0000-0000-000000000000\"}").getBytes();
        EventListener listener = new EventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onConnect(Client client, ConnectEvent event) {

                client.setConnectData(my_mess2);
                SendErr("");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onDisconnect(Client client, DisconnectEvent event) {

                Log.e("onDisconnect", event.getReason());
                Log.e("onDisconnect", String.valueOf(event.getReconnect()));
                SendErr("Ошибка соединения");
            }

            @Override
            public void onSubscribe(Client client, ServerSubscribeEvent event) {
                super.onSubscribe(client, event);
                Log.i("TAG", client + " " + event);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPublish(Client client, ServerPublishEvent event) {
                super.onPublish(client, event);
                String data = new String(event.getData(), UTF_8);
                Log.d("onPublish" + Calendar.getInstance().getTime(), data);

                Gson gson = new Gson();
                Json json = gson.fromJson(data, Json.class);

                Log.i("Centrifugo", "onPublish: "+json.action);
            }
        };

        client = new Client(
                "ws://192.168.25.78:8000/connection/websocket",
//                "http://79.170.161.138:4242/connection/websocket",
                new Options(),

                listener
        );
        client.setConnectData(my_mess2);
        client.connect();
    }


    private static class Json {
        @SerializedName("action")
        public String action;
    }

    private void SendErr(String msg) {
        if (msg == remsg) return;
        remsg = msg;
        Intent intent = new Intent();
        intent.setAction("connection_error");
        intent.putExtra("data", msg);
        Log.i("Reciever", "Отправляю бродкаст connection_error");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}

