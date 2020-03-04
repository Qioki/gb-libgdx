package com.mygdx.game.gameobjects;

import com.mygdx.game.core.GameObject;

public class InfoScale extends GameObject {


    public GameObject owner;

    private float scaleValue = 1f;


    public InfoScale(GameObject owner, String region) {
        super(owner, region);
    }

    @Override
    public void  init () {
        transform.zIndex = 3f;
        transform.localPosition.y = 40;
    }

    @Override
    public void update() {
        renderer.scaleWidth = scaleValue;
    }


    public void setScaleValue(float scaleValue) {
        this.scaleValue = scaleValue;
    }
}
