package com.example.in100gram;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.centrifuge.Centrifuge;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.Options;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.ViewHolder>{

    Context context;
    Calendar calendar = Calendar.getInstance();
    String Message = "123";

//    public MessengerAdapter(String date, String text, String timestamp) {
//        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        text = Message;
//        timestamp = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//    }

    @Override
    public MessengerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.messenger_recycler, parent, false);
        return new MessengerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_gchat_date_me);
            textView = itemView.findViewById(R.id.text_gchat_timestamp_me);
            textView = itemView.findViewById(R.id.text_gchat_message_me);
        }
    }
}
