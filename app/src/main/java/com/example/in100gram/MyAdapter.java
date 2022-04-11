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
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    File[] filesAndFolders;
    boolean mBound = false;
    public Service mService;
    int Count = 0;

    List<String> toJSON1 = new ArrayList<String>();

    public String JSON;

    public MyAdapter(Context context, File[] filesAndFolders) {
        this.context = context;
        this.filesAndFolders = filesAndFolders;

    }

    private ArrayList<File> files = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        File selectedFile = filesAndFolders[position];
        holder.textView.setText(selectedFile.getName());

        if (selectedFile.isDirectory()) {
            holder.imageView.setImageResource(R.drawable.ic_baseline_folder_24);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_baseline_insert_drive_file_24);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFile.isDirectory()) {
                    Intent intent = new Intent(context, FileListActivity.class);
                    String path = selectedFile.getAbsolutePath();
                    intent.putExtra("path", path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    //open the file
                    try {
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        String type = "image/*";
                        intent.setDataAndType(Uri.parse(selectedFile.getAbsolutePath()), type);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), "Cannot open the file", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenu().add("ДОБАВИТЬ");
                popupMenu.getMenu().add("ОТПРАВИТЬ");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("ДОБАВИТЬ")) {
                            files.add(selectedFile);
                            toJSON1.add(selectedFile.getName());
                            Count++;
                            popupMenu.getMenu().add("ВЫБРАННО " + Count);
                        }
                        if (item.getTitle().equals("ОТПРАВИТЬ")) {

                            JSON = new Gson().toJson(toJSON1);

                            Log.d("JSON", JSON);

                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {

                                    mService.SendRPC("rpc", JSON);

                                    JSONArray responseJSONArray = null;
                                    OkHttpClient client = new OkHttpClient().newBuilder()
                                            .build();
                                    try {
                                        MediaType mediaType = MediaType.parse("application/json");
                                        RequestBody body = RequestBody.create(mediaType, JSON);
                                        Request request = new Request.Builder()
                                                .url("http://192.168.24.81:9000")
                                                .method("post", body)
                                                .addHeader("Content-Type", "application/json")
                                                .build();

                                        Response response = client.newCall(request).execute();
                                        String responseS = new String(Objects.requireNonNull(response.body()).bytes(), StandardCharsets.UTF_8);
                                        Log.i("123", ""+ responseS);
                                        responseJSONArray = new JSONArray(responseS);
                                        Log.d("responseJSONArray", responseJSONArray.toString());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    Request request2 = null;
                                    Log.i("13",""+responseJSONArray);
                                    for (int i = 0; i < responseJSONArray.length(); i++) {
                                        MediaType mediaType2 = MediaType.parse("image/jpeg");
                                        RequestBody body2 = RequestBody.create(mediaType2, files.get(i));
                                        try {
                                            request2 = new Request.Builder()
                                                    .url(responseJSONArray.getJSONObject(i).getString("Scheme") + "://"
                                                            + responseJSONArray.getJSONObject(i).getString("Host")
                                                            + responseJSONArray.getJSONObject(i).getString("Path")
                                                            + responseJSONArray.getJSONObject(i).getString("RawQuery"))
                                                    .method("PUT", body2)
                                                    .build();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            Response response2 = client.newCall(request2).execute();
                                            Log.d("response2", response2.body().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    files.clear();
                                    toJSON1.clear();
                                    Count=0;
                                    Toast.makeText(context.getApplicationContext(), "ОТПРАВЛЕНО", Toast.LENGTH_SHORT).show();
                                }
                            };
                            new Thread(runnable).start();

                        }
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.file_name_text_view);
            imageView = itemView.findViewById(R.id.icon_view);
        }
    }
}

