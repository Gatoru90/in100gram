package com.example.in100gram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MessengerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_main);

        RecyclerView messenger_recycler = findViewById(R.id.recycler_gchat);

        messenger_recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
