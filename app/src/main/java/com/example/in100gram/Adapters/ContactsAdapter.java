package com.example.in100gram.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    JSONObject Contactsobj;

    public ContactsAdapter(Context applicationContext, JSONObject contactsobj) {
        context = applicationContext;
        Contactsobj = contactsobj;
    }

    public static void setLayoutManager(LinearLayoutManager linearLayoutManager) {

    }

    public static void setAdapter(ContactsAdapter adapter) {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
