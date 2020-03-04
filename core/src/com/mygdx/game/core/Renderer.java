package com.mygdx.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GB_RPG;

public class Renderer {

    private SpriteBatch batch;
    private TextureRegion texture;
    private int textureWidth;
    private int textureHeight;
    private int halfTextureWidth;
    private int halfTextureHeight;

    public float scaleWidth = 1f;
    public float scaleHeight = 1f;


    private GameObject gameObject;
    private Transform transform;


    public Renderer(GameObject go, String region)
    {
        gameObject = go;
        transform = go.transform;

        batch = GB_RPG.getBatch();
        texture = GB_RPG.getAtlas().findRegion(region);
        textureWidth = texture.getRegionWidth();
        halfTextureWidth = textureWidth / 2;
        textureHeight = texture.getRegionHeight();
        halfTextureHeight = textureHeight / 2;
    }

    public void render()
    {
        Vector2 worldPosition = transform.getWorldPosition();
        batch.draw(texture,
                worldPosition.x - halfTextureWidth, worldPosition.y - halfTextureHeight,
                halfTextureWidth, halfTextureHeight,
                textureWidth * scaleWidth, textureHeight * scaleHeight,
                transform.scale.x, transform.scale.y,
                transform.worldRotation);

    }



    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

}
