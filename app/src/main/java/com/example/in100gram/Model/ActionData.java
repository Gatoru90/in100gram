package com.example.in100gram.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ActionData {
    @SerializedName("action")
    public String action;
    @SerializedName("data")
    public JsonObject data;
}
