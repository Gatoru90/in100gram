package com.example.in100gram.Adapters;

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
import com.example.in100gram.R;
import org.json.JSONObject;

public class MessengerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    JSONObject messobj;
    List<Integer> users;
    List<String> items;
    List<String> timestamps;
    List<String> datestamps;
    List<String> UserNames;
    private static final int TYPE_ME = 1;
    private static final int TYPE_OTHER = 2;
    private MessengerAdapter adapter;


    public MessengerAdapter(Context applicationContext, JSONObject messobj, List<String> items, List<String> datestamps, List<String> timestamps, List<Integer> users, List<String> userNames) {
        this.context = applicationContext;
        this.messobj = messobj;
        this.items = items;
        this.datestamps = datestamps;
        this.timestamps = timestamps;
        this.users = users;
        this.UserNames = userNames;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TYPE_ME) {
            view = LayoutInflater.from(context).inflate(R.layout.messenger_recycler, viewGroup, false);
            return new ViewHolderME(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.messenger_other_recycler, viewGroup, false);
            return new ViewHolderOther(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_ME){
            ((ViewHolderME) holder).textViewMe.setText(items.get(position));
            ((ViewHolderME) holder).dateViewMe.setText(datestamps.get(position));
            ((ViewHolderME) holder).timeViewMe.setText(timestamps.get(position));

            if (datestamps.get(position) == null) {
                ((ViewHolderME) holder).dateViewMe.setVisibility(View.GONE);
            }
        }
        else {
            ((ViewHolderOther) holder).textViewOther.setText(items.get(position));
            ((ViewHolderOther) holder).dateViewOther.setText(datestamps.get(position));
            ((ViewHolderOther) holder).timeViewOther.setText(timestamps.get(position));
            //((ViewHolderOther) holder).UserViewOther.setText(UserNames.get(position));

            Log.d("position-1", "" + (position-1));
            if (datestamps.get(position) == null){
                //datestamps.size() > 1 && datestamps.get(position).equals(datestamps.get(position-1))) {
                ((ViewHolderOther) holder).dateViewOther.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (users.get(position) == 1) {
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

        TextView textViewOther, dateViewOther, timeViewOther, UserViewOther;

        public ViewHolderOther(View itemView) {
            super(itemView);
            textViewOther = itemView.findViewById(R.id.text_gchat_message_other);
            dateViewOther = itemView.findViewById(R.id.text_gchat_date_other);
            timeViewOther = itemView.findViewById(R.id.text_gchat_timestamp_other);
            UserViewOther = itemView.findViewById(R.id.text_gchat_user_other);
        }
    }

    public class ViewHolderME extends RecyclerView.ViewHolder {

        TextView textViewMe, dateViewMe, timeViewMe;

        public ViewHolderME(View itemView) {
            super(itemView);
            textViewMe = itemView.findViewById(R.id.text_gchat_message_me);
            dateViewMe = itemView.findViewById(R.id.text_gchat_date_me);
            timeViewMe = itemView.findViewById(R.id.text_gchat_timestamp_me);

            textViewMe.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.getMenu().add("Переслать");
                    popupMenu.getMenu().add("Ответить");
                    popupMenu.getMenu().add("Изменить");
                    popupMenu.getMenu().add("Удалить");
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().equals("Удалить")) {
                                adapter.items.remove(getAbsoluteAdapterPosition());
                                adapter.notifyItemRemoved(getAbsoluteAdapterPosition());
                            }
                            if (item.getTitle().equals("Изменить")) {
                                //гениальный код
                            }
                            if (item.getTitle().equals("Переслать")) {
                                //гениальный код
                            }
                            if (item.getTitle().equals("Ответить")) {
                                //гениальный код
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
