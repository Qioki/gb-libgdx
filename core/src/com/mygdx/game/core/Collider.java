package com.mygdx.game.core;

import com.mygdx.game.GB_RPG;

import java.util.ArrayList;

public class Collider {
    public float radius = 1f;
    public final Transform transform;
    public final GameObject gameObject;

    private ArrayList<OnTriggerListener> listeners = new ArrayList<OnTriggerListener>();

    public Collider(GameObject own) {
        gameObject = own;
        transform = gameObject.transform;
        GB_RPG.registerOnTrigger(this);
    }

    public void onTriggerEnter(Collider c) {

        for (OnTriggerListener l : listeners)
            l.onTrigger();
    }


    public void addListener(OnTriggerListener toAdd) {
        listeners.add(toAdd);
    }
}