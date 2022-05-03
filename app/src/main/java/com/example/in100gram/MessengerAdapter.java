package com.example.in100gram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.ViewHolder>{

    Context context;
    JSONObject messobj;
    int user;
    List<String> items;
    List<String> timestamps;
    List<String> datestamps;
    private static final int TYPE_ME = 1;
    private static final int TYPE_OTHER = 2;


    public MessengerAdapter(Context applicationContext, JSONObject messobj, List<String> items, List<String> datestamps, List<String> timestamps, int user) {
        this.context = applicationContext;
        this.messobj = messobj;
        this.items = items;
        this.datestamps = datestamps;
        this.timestamps = timestamps;
        this.user = user;

    }

    @NonNull
    @Override
    public MessengerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if(viewType == TYPE_ME){
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.messenger_recycler, parent, false));
//        } else {
//            return new ViewHolderOther(LayoutInflater.from(context).inflate(R.layout.messenger_other_recycler, parent, false));
//        }
    }

    private MessengerAdapter adapter;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //if(getItemViewType(position) == TYPE_ME){
            holder.textView.setText(items.get(position));
            holder.dateView.setText(datestamps.get(position));
            holder.timeView.setText(timestamps.get(position));

            if(datestamps.get(position) == null){
                holder.dateView.setVisibility(View.GONE);
            }
//        } else {
//
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (user == 1) {
            return TYPE_ME;
        } else {
            return TYPE_OTHER;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolderOther extends RecyclerView.ViewHolder {

        TextView textView;
        TextView dateView;
        TextView timeView;

        public ViewHolderOther(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_gchat_message_other);
            dateView = itemView.findViewById(R.id.text_gchat_date_other);
            timeView = itemView.findViewById(R.id.text_gchat_timestamp_other);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView dateView;
        TextView timeView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_gchat_message_me);
            dateView = itemView.findViewById(R.id.text_gchat_date_me);
            timeView = itemView.findViewById(R.id.text_gchat_timestamp_me);

            textView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    Log.d("LongClickTest","успех");
                    PopupMenu popupMenu = new PopupMenu(context, textView);
                    popupMenu.getMenu().add("Удалить");
                    popupMenu.getMenu().add("Изменить");

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().equals("Удалить")) {
                                adapter.items.remove(getAbsoluteAdapterPosition());
                                adapter.notifyItemRemoved(getAbsoluteAdapterPosition());
                            }
                            if (item.getTitle().equals("Изменить")) {

                            }
                            return true;
                        }
                    });
                    return false;
                }
            });
        }
    }
}
