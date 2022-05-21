package com.example.in100gram.centrifuge.Handlers.CreateHandler;

public abstract class CentrifugeHandler{
    protected static boolean handlerNameAction(String handlerName, String action){
        return handlerName.equals(action);
    }
    public abstract void doAction();
}
