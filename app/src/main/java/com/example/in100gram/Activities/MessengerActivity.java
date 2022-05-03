package com.example.in100gram.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.in100gram.Adapters.MessengerAdapter;
import com.example.in100gram.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class MessengerActivity extends AppCompatActivity{
    String MessegeText;
    String timestamp;
    String datestamp;
    int user;
    Calendar calendar = Calendar.getInstance();
    JSONObject Messobj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_main);

        List<String> items = new LinkedList<>();
        List<String> datestamps = new LinkedList<>();
        List<String> timestamps = new LinkedList<>();

        Button SendBtn = findViewById(R.id.button_gchat_send);
        EditText editMessegeText = findViewById(R.id.edit_gchat_message);
        TextView noMessegesText = findViewById(R.id.noMesseges_textview);

        RecyclerView MessengerRecycler = findViewById(R.id.recycler_gchat);
        MessengerAdapter adapter = new MessengerAdapter(getApplicationContext(),Messobj, items, datestamps, timestamps, user);

        SendBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MessengerRecycler.scrollToPosition(items.size());
                adapter.notifyDataSetChanged();



                MessegeText = editMessegeText.getText().toString();
                timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());;
                datestamp = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
                if(MessegeText != ""){
                    try {
                        items.add(MessegeText);
                        timestamps.add(timestamp);

                        if(!datestamps.contains(datestamp)){
                            datestamps.add(datestamp);
                        }
                        else {
                            datestamps.add(null);
                        }
                        Messobj.put("MessageText",MessegeText);
                        Messobj.put("Timestamp",timestamp);
                        Messobj.put("datestamp",datestamp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    user = 2;
                    adapter.notifyItemInserted(user = 2);
                    adapter.notifyItemInserted(items.size());
                    adapter.notifyItemInserted(timestamps.size());
                    adapter.notifyItemInserted(datestamps.size());
                }
                editMessegeText.setText("");
                Log.d("Messobj", Messobj.toString());
                Log.d("user", "" + user);
                Log.d("datelist", datestamps.toString());
                Log.d("itemsSize", "Size of list = " + items.size());

                return false;
            }
        });

        SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessengerRecycler.scrollToPosition(items.size());
                adapter.notifyDataSetChanged();

                MessegeText = editMessegeText.getText().toString();
                timestamp = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());;
                datestamp = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
                if(MessegeText != ""){
                    try {
                        items.add(MessegeText);
                        timestamps.add(timestamp);

                        if(!datestamps.contains(datestamp)){
                            datestamps.add(datestamp);
                        }
                        else {
                            datestamps.add(null);
                        }
                        Messobj.put("MessageText",MessegeText);
                        Messobj.put("Timestamp",timestamp);
                        Messobj.put("datestamp",datestamp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyItemInserted(user = 1);
                    adapter.notifyItemInserted(items.size());
                    adapter.notifyItemInserted(timestamps.size());
                    adapter.notifyItemInserted(datestamps.size());
                }
                Log.d("Messobj", Messobj.toString());
                editMessegeText.setText("");
                Log.d("datelist", datestamps.toString());
                Log.d("itemsSize", "Size of list = " + items.size());
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
