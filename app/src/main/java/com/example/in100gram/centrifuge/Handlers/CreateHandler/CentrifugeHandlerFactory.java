package com.example.in100gram.centrifuge.Handlers.CreateHandler;

import com.example.in100gram.Model.ActionData;
import com.example.in100gram.centrifuge.Handlers.GetChatsHandler;
import com.example.in100gram.centrifuge.Handlers.NullHandler;
import com.google.gson.Gson;

public class CentrifugeHandlerFactory {
    public static CentrifugeHandler getCentrifugeHandler(String data){
            ActionData json = new Gson().fromJson(data, ActionData.class);

            if (GetChatsHandler.handlerNameAction("get_chats", json.action)) { return new GetChatsHandler(); }

        return new NullHandler();
    }
}
