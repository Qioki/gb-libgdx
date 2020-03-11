package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.Vector2;

public class Sensor {

    public float radius = 10f;
    Vector2 myPosition;
//    GameCharacter target;
    Vector2 targetPosition;
    private boolean finding = false;

    public Sensor(Vector2 targetPosition, Vector2 myPosition){
        this.targetPosition = targetPosition;
        this.myPosition = myPosition;
    }
    public boolean checkTarget()
    {
        if(finding) return true;
        if(myPosition.dst(targetPosition) < radius) {

            finding = true;

            return true;
        }
        return false;
    }
}
