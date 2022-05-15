package com.example.in100gram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Model.Contact;
import com.example.in100gram.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    JSONObject Contactsobj;
    ArrayList<Contact> contactList;

    public ContactsAdapter(Context applicationContext, JSONObject contactsobj, ArrayList<Contact> contactList) {
        this.context = applicationContext;
        this.Contactsobj = contactsobj;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.contacts_recycler, viewType, false);
//        return new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
