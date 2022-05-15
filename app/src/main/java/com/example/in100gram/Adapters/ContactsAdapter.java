package com.example.in100gram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.in100gram.Activities.AuthActivity;
import com.example.in100gram.Activities.ContactsActivity;
import com.example.in100gram.Activities.MessengerActivity;
import com.example.in100gram.Activities.RegActivity;
import com.example.in100gram.Model.Contact;
import com.example.in100gram.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    JSONObject Contactsobj;
    List<String> contactList;

    public ContactsAdapter(Context applicationContext, JSONObject contactsobj, List<String> contactList) {
        this.context = applicationContext;
        this.Contactsobj = contactsobj;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contacts_recycler, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ContactHolder) holder).ContactNameView.setText(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {

        private TextView ContactNameView;
        private View ContactBtn;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            ContactNameView = itemView.findViewById(R.id.name_contact);
            ContactBtn = itemView.findViewById(R.id.Contact_recycler_layout);

            ContactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MessengerActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
