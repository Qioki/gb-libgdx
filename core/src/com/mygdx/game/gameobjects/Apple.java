package com.mygdx.game.gameobjects;

import com.mygdx.game.core.GameObject;

import java.util.Random;

public class Apple extends GameObject {

    public String name = "Apple";

    public Apple() {

        super("knight");

        Random random = new Random();
        initCollider();
        collider.addListener(() -> {
            transform.localPosition.set(100 + random.nextInt(900), 100 + random.nextInt(500));
        });
    }
}
