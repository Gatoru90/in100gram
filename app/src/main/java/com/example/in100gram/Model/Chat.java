package com.example.in100gram.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Chat {
    public class ChatData{
        @SerializedName("title")
        public String title;
        @SerializedName("chat_type")
        public int chatType;
        @SerializedName("last_msg_type")
        public int lastMsgType;
        @SerializedName("send_time")
        public String sendTime;
        @SerializedName("sender_id")
        public int senderId;
    }
    @SerializedName("ChatId")
    public int chatId;
    @SerializedName("ChatData")
    public ChatData chatData;
    @SerializedName("NewMessage")
    public int newMessage;
}

