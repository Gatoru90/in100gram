package com.example.in100gram.Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Adapters.ContactsAdapter;
import com.example.in100gram.Model.Contact;
import com.example.in100gram.R;
import com.example.in100gram.centrifuge.Centrifugo;
import com.example.in100gram.centrifuge.Service;
import com.example.in100gram.centrifuge.handlerData;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

        Service serv = new Service();

        Centrifugo centrifugo = new Centrifugo();
        handlerData handleData = new handlerData();
        centrifugo.addObserver(handleData);
        centrifugo.getClient().setConnectData("{ \"auth_id\": \"b18b04de-b7db-467c-9bb3-5a81aec0593c\" }".getBytes(StandardCharsets.UTF_8));
        centrifugo.onConnect();

        centrifugo.sendRPC("get_chats", new NullData());
//        Contact Contact = new Contact("Никита", "null");
//        ContactList.add(Contact);
//        Contact = new Contact("Ганя", "null");
//        ContactList.add(Contact);
//        Contact = new Contact("Захар", "null");
//        ContactList.add(Contact);
//        adapter.notifyDataSetChanged();

        ContactList.add("Никита");
        ContactList.add("Ганя");
        ContactList.add("Захар");

        ContacsRecycler.setLayoutManager(new LinearLayoutManager(this));
        ContacsRecycler.setAdapter(adapter);
    }
}
class NullData{}