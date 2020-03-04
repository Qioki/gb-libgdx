package com.mygdx.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GB_RPG;
import com.mygdx.game.core.GameObject;

public class Projectile extends GameObject {

    private Vector2 position;
    private Vector2 velocity = new Vector2();
    private float speed = 800f;
    public String name = "Projectile";

    static int counter = 0;

    public Projectile(String region) {
        super(region);
        counter++;
        System.out.println(counter);
    }

    @Override
    public void init() {
        position = transform.localPosition;
        initCollider();
    }

    public void setup()
    {
        velocity.set(transform.getWorldDirection());
        velocity.scl(speed);
    }

    public void deactivate() {
        GB_RPG.deactivateGO(this);
    }

    @Override
    public void update() {
        position.mulAdd(velocity, deltaTime);
        if (position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720) {
            deactivate();
        }
    }
}
