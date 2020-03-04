package com.mygdx.game.gameobjects;

import com.mygdx.game.core.GameObject;

public class Pointer extends GameObject {

    private float rotationSpeed = 100f;

    public Pointer(String region) {
        super(region);
    }

    @Override
    public void  init () {
        transform.zIndex = 1.1f;
    }
    @Override
    public void update() {
        transform.worldRotation = (transform.worldRotation + deltaTime * rotationSpeed) % 360;


    }
}
