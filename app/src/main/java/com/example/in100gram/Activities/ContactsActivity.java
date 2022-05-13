package com.example.in100gram.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Adapters.ContactsAdapter;
import com.example.in100gram.R;

import org.json.JSONObject;

public class ContactsActivity extends AppCompatActivity {

    JSONObject Contactsobj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_main);

        RecyclerView ContactsRecycler = findViewById(R.id.recycler_contacts);
        ContactsAdapter adapter = new ContactsAdapter(getApplicationContext(),Contactsobj);

        ContactsAdapter.setLayoutManager(new LinearLayoutManager(this));
        ContactsAdapter.setAdapter(adapter);
    }
}
