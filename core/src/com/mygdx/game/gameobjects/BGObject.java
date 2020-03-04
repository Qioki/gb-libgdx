package com.mygdx.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GB_RPG;
import com.mygdx.game.core.GameObject;

public class BGObject extends GameObject {

    private SpriteBatch batch;
    private TextureRegion texture;


    public BGObject(String region) {
        super();

        batch = GB_RPG.getBatch();
        texture = GB_RPG.getAtlas().findRegion(region);

    }


    @Override
    public void  init () {
        transform.zIndex = .1f;
    }

    @Override
    public void render() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(texture, i * 80, j * 80);
            }
        }
    }

}
