package com.mygdx.game.core;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GB_RPG;
import com.mygdx.game.gameobjects.Hero;

public class GUIObject extends GameObject {

    private StringBuilder strBuilder = new StringBuilder();
    private SpriteBatch batch;
    private BitmapFont font32;

    private Hero player;


    public GUIObject(Hero player) {
        super();
        this.player = player;
    }

    @Override
    public void init() {
        batch = GB_RPG.getBatch();
        font32 = GB_RPG.getFont32();
    }

    @Override
    public void render() {
        renderGUI();
    }

    public void renderGUI() {
        strBuilder.setLength(0);
        strBuilder.append("Class: ").append("Knight").append("\n");
        strBuilder.append("HP: ").append(player.getHpValue()).append(" / ").append(player.getHpMax()).append("\n");
        font32.draw(batch, strBuilder, 10, 710);
    }
}
