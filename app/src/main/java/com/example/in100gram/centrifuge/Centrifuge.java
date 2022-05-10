package com.example.in100gram.centrifuge;

import static java.lang.Thread.sleep;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.PresenceStatsResult;
import io.github.centrifugal.centrifuge.RPCResult;
import io.github.centrifugal.centrifuge.ReplyCallback;
import io.github.centrifugal.centrifuge.ReplyError;
import io.github.centrifugal.centrifuge.ServerPublishEvent;
import io.github.centrifugal.centrifuge.ServerSubscribeEvent;

public class Centrifuge {
    public Client client;
    Context context;

    public Centrifuge(Context context) {

        EventListener listener = new EventListener() {
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

        client = new Client(
                "ws://192.168.24.22:8000/connection/websocket",
                new Options(),
                listener
        );
        client.connect();


    }


    private static class Json {
        @SerializedName("action")
        public String action;
    }


}

