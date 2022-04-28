package com.example.in100gram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

public class MessengerActivity extends AppCompatActivity{
    String MessegeText;
    String timestamp;
    Calendar calendar = Calendar.getInstance();
    JSONObject Messobj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_main);

        Button SendBtn = findViewById(R.id.button_gchat_send);
        EditText editMessegeText = findViewById(R.id.edit_gchat_message);

        RecyclerView MessengerRecycler = findViewById(R.id.recycler_gchat);

        MessengerRecycler.setLayoutManager(new LinearLayoutManager(this));
        MessengerRecycler.setAdapter(new MessengerAdapter());

        SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessegeText = editMessegeText.getText().toString();
                timestamp = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                try {
                    Messobj.put("MessageText",MessegeText);
                    Messobj.put("Timestamp",timestamp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Messobj", Messobj.toString());
                editMessegeText.setText("");
            }
        });
    }
}
