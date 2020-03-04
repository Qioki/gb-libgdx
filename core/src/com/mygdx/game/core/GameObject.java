package com.mygdx.game.core;

import com.mygdx.game.GB_RPG;

public class GameObject {

    public String name = "GameObject";
    public boolean isVisible = true;

    public final Transform transform = new Transform();
    public Collider collider;
    public Renderer renderer;

    public GameObject parent;

    public static float deltaTime = 0f;


    public GameObject() {
        init();

        GB_RPG.addGO(this);
    }

    public GameObject(String region) {
        renderer = new Renderer(this, region);
        init();

        GB_RPG.addGO(this);
    }

    public GameObject(GameObject parent, String region) {
        this.parent = parent;
        transform.parent = parent.transform;
        renderer = new Renderer(this, region);

        init();

        GB_RPG.addGO(this);
    }

    public void  init () {

    }

    public void render() {
        renderer.render();
    }

    public void update() {

    }


    public void initCollider() {
        if(collider == null)
            collider = new Collider(this);
        if(renderer == null)
            return;

        collider.radius = renderer.getTextureWidth()/2f;
    }
    public void initCollider(float radius) {

        if(collider == null)
            collider = new Collider(this);

        collider.radius = radius;

    }
}
