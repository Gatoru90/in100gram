package com.example.in100gram.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Adapters.ContactsAdapter;
import com.example.in100gram.Model.Contact;
import com.example.in100gram.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    ArrayList<Contact> ContactList;
    JSONObject Contactsobj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_main);

        ContactList = new ArrayList<>();

        RecyclerView ContacsRecycler = findViewById(R.id.recycler_contacts);
        ContactsAdapter adapter = new ContactsAdapter(this,Contactsobj,ContactList);

        Contact Contact = new Contact("Никита", "null");
        ContactList.add(Contact);
        Contact = new Contact("Ганя", "null");
        ContactList.add(Contact);
        Contact = new Contact("Захар", "null");
        ContactList.add(Contact);
        adapter.notifyDataSetChanged();
    }
}