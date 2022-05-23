package com.example.in100gram.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Adapters.ContactsAdapter;
import com.example.in100gram.R;
import com.example.in100gram.centrifuge.Centrifugo;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    JSONObject Contactsobj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_main);
        List<String> ContactList = new LinkedList<>();

        RecyclerView ContacsRecycler = findViewById(R.id.recycler_contacts);
        ContactsAdapter adapter = new ContactsAdapter(this,Contactsobj, ContactList);
        Centrifugo centrifugoContack = new Centrifugo(this);

        centrifugoContack.getClient().setConnectData("{ \"auth_id\": \"b18b04de-b7db-467c-9bb3-5a81aec0593c\" }".getBytes(StandardCharsets.UTF_8));
        centrifugoContack.onConnect();

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(GetUserChatsReceiver, new IntentFilter("get_chats"));

        centrifugoContack.sendRPC("get_chats", null);
//        Contact Contact = new Contact("Никита", "true");
//        ContactList.add(Contact);
//        Contact = new Contact("Ганя", "null");
//        ContactList.add(Contact);
//        Contact = new Contact("Захар", "null");
//        ContactList.add(Contact);
//        adapter.notifyDataSetChanged();

        ContactList.add("Никитаasdfasdf");
        ContactList.add("Ганя");
        ContactList.add("Захар");
        ContacsRecycler.setLayoutManager(new LinearLayoutManager(this));
        ContacsRecycler.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        try {
//            centrifugo.getClient().close(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private final BroadcastReceiver GetUserChatsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String nowStatus = intent.getStringExtra("data");
            Log.i("ReceiveGetChats", String.valueOf(nowStatus));

        }
    };

}