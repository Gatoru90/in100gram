package com.example.in100gram.centrifuge.ObservePattern;

public interface Observed {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
