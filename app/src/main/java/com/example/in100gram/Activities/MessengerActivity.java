package com.example.in100gram.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Adapters.MessengerAdapter;
import com.example.in100gram.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MessengerActivity extends AppCompatActivity{
    String MessegeText;
    String timestamp;
    String datestamp;
    int user;
    JSONObject Messobj = new JSONObject();


    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MessengerActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else
            return false;
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MessengerActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MessengerActivity.this,"Storage permission is requires,please allow from settings",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MessengerActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},111);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_main);

        List<String> items = new LinkedList<>();
        List<String> datestamps = new LinkedList<>();
        List<String> timestamps = new LinkedList<>();
        List<String> UserNames = new LinkedList<>();
        List<Integer> users = new LinkedList<>();

        ImageButton SendBtn = findViewById(R.id.button_gchat_send);
        ImageButton AddFileBtn = findViewById(R.id.button_add_file);
        EditText editMessegeText = findViewById(R.id.edit_gchat_message);
        TextView noMessegesText = findViewById(R.id.noMesseges_textview);

        RecyclerView MessengerRecycler = findViewById(R.id.recycler_gchat);
        MessengerAdapter adapter = new MessengerAdapter(getApplicationContext(),Messobj, items, datestamps, timestamps, users, UserNames);

        AddFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()){
                    //permission allowed
                    Intent intent = new Intent(MessengerActivity.this, FileListActivity.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
                }else {
                    //permission not allowed
                    requestPermission();
                }
            }
        });

        SendBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (editMessegeText.getText().toString().trim().length() > 0){
                    MessengerRecycler.scrollToPosition(items.size());
                    adapter.notifyDataSetChanged();

                    MessegeText = editMessegeText.getText().toString().trim();
                    timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    datestamp = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
                    user = 2;
                    if(MessegeText != ""){
                        try {
                            items.add(MessegeText);
                            timestamps.add(timestamp);
                            users.add(user);
                            datestamps.add(datestamp);

//                            if(!datestamps.contains(datestamp)){
//                                datestamps.add(datestamp);
//                            }
//                            else {
//                                datestamps.add(null);
//                            }
                            Messobj.put("MessageText",MessegeText);
                            Messobj.put("Timestamp",timestamp);
                            Messobj.put("datestamp",datestamp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyItemInserted(user);
                        adapter.notifyItemInserted(items.size());
                        adapter.notifyItemInserted(timestamps.size());
                        adapter.notifyItemInserted(datestamps.size());
                    }
                    editMessegeText.setText("");
                    Log.d("Messobj", Messobj.toString());
                    Log.d("user", "" + user);
                    Log.d("datelist", datestamps.toString());
                    Log.d("itemsSize", "Size of list = " + items.size());
                }
                return false;
            }
        });

        SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editMessegeText.getText().toString().trim().length() > 0){
                    MessengerRecycler.scrollToPosition(items.size());
                    adapter.notifyDataSetChanged();

                    MessegeText = editMessegeText.getText().toString().trim();
                    timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    datestamp = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
                    user = 1;
                    if(MessegeText != ""){
                        try {
                            items.add(MessegeText);
                            timestamps.add(timestamp);
                            users.add(user);
                            datestamps.add(datestamp);

//                            if(!datestamps.contains(datestamp)){
//                                datestamps.add(datestamp);
//                            }
//                            else {
//                                datestamps.add(null);
//                            }
                            Messobj.put("MessageText",MessegeText);
                            Messobj.put("Timestamp",timestamp);
                            Messobj.put("datestamp",datestamp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyItemInserted(user);
                        adapter.notifyItemInserted(items.size());
                        adapter.notifyItemInserted(timestamps.size());
                        adapter.notifyItemInserted(datestamps.size());
                    }
                    Log.d("Messobj", Messobj.toString());
                    editMessegeText.setText("");
                    Log.d("datelist", datestamps.toString());
                    Log.d("user", "" + user);
                    Log.d("itemsSize", "Size of list = " + items.size());
                }
            }
        });

        MessengerRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessengerRecycler.setAdapter(adapter);

        if(items.size() == 0){
            noMessegesText.setVisibility(View.VISIBLE);
            return;
        }

        noMessegesText.setVisibility(View.INVISIBLE);
    }
}
