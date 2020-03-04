package com.mygdx.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GB_RPG;
import com.mygdx.game.core.GameObject;

public class Hero extends GameObject {

    private float speed = 300.0f;
    private GameObject target = null;
    private Vector2 targetPosition = null;
    private Vector2 tmp = new Vector2();
    private Vector2 tmpPos = new Vector2();

    private int hpValue = 2;
    private int hpMax = 10;

    InfoScale hpScale;

    public Hero(String region) {
        super(region);
    }

    public void fire (float directionX, float directionY)
    {
        GameObject go = GB_RPG.activateGO(Projectile.class);
        Projectile projectile;
        if(go != null)
            projectile = (Projectile) go;
        else
            projectile = new Projectile("arrow");



        GB_RPG.spawnGO(projectile, transform, Vector2.Zero, directionX, directionY);
        projectile.setup();
    }

    @Override
    public void  init ()
    {
        transform.zIndex = 2f;
        hpScale = new InfoScale(this, "hp");
        initCollider();
    }
    @Override
    public void update()
    {
        if(target != null)
            targetPosition = target.transform.getWorldPosition();
        else if(targetPosition == null) return;

        tmp.set(targetPosition).sub(transform.localPosition).nor().scl(speed); // вектор скорости
        if (transform.localPosition.dst(targetPosition) > speed * deltaTime) {
            transform.localPosition.mulAdd(tmp, deltaTime);
        } else {
            transform.localPosition.set(targetPosition);
        }
        hpScale.setScaleValue((float) hpValue / hpMax);
    }

    public void setTarget(GameObject t) {
        target = t;
    }


    public int getHpValue() {
        return hpValue;
    }

    public int getHpMax() {
        return hpMax;
    }


}
