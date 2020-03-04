package com.mygdx.game.core;

import com.badlogic.gdx.math.Vector2;


public class Transform {

    public float zIndex = 1f;

    public final Vector2 localPosition = new Vector2(0,0);
    private final Vector2 worldPosition = new Vector2(0,0);

//    private final Vector2 localDirection = new Vector2(1f,0);
    private final Vector2 worldDirection = new Vector2(1f,0);

//    public float localRotation = 0f;
    public float worldRotation = 0f;

    public final Vector2 scale =  new Vector2(1f, 1f);

    public Transform parent;


    public Vector2 getWorldPosition()
    {
        if(parent == null)
            return localPosition;

        worldPosition.set(localPosition);
        worldPosition.add(parent.getWorldPosition());
        return worldPosition;
    }
    public void setWorldDirection(float directionX, float directionY)
    {
        worldDirection.set(directionX, directionY).nor();
        worldRotation = worldDirection.angle();
    }
    public Vector2 getWorldDirection()
    {
        return worldDirection;
    }


}
