package com.example.in100gram;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.in100gram.centrifuge.Centrifuge;

import io.github.centrifugal.centrifuge.RPCResult;
import io.github.centrifugal.centrifuge.ReplyCallback;
import io.github.centrifugal.centrifuge.ReplyError;

public class Service extends android.app.Service {

    Centrifuge centrifuge;

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public Service getService() {
            return Service.this;
        }
    }

    @Override
    public void onCreate() {

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //createNotificationChannel();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i("Service", "onStartCommand flags: " + flags + " startId: " + startId);
        centrifuge = new Centrifuge(this);
        return Service.START_STICKY;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {

        String CHANNEL_ID = "my_channel_01";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "1",
                NotificationManager.IMPORTANCE_DEFAULT);

        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build();
        startForeground(1, notification);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void SendRPC(String method, String data) {
        Log.i("SendRPC", "method: " + method + " data: " + data);
        if (centrifuge == null) {
            Log.i("SendRPC", "centrifuge.client is null");
            return;
        }
        centrifuge.client.rpc(method, data.getBytes(), new ReplyCallback<RPCResult>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Throwable e) {
                Log.e("SEND_RPC_FAIL", e.toString());
            }
            @SuppressLint("LongLogTag")
            @Override
            public void onDone(ReplyError error, RPCResult result) {

                Log.i("SEND_RPC_SUCCESS", String.valueOf(error));
            }
        });
    }
}