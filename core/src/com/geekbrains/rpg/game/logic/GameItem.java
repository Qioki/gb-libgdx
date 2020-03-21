package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.geekbrains.rpg.game.logic.utils.Consumable;
import com.geekbrains.rpg.game.logic.utils.MapElement;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class GameItem implements MapElement, Poolable, Consumable {

    private TextureRegion texture;
    private Vector2 position = new Vector2(0, 0);
    OnConsumeEvent onConsumeEvent;
    private boolean active;

    GameItem() {

    }

    public void setup(String regionName, Vector2 position, OnConsumeEvent onConsumeEvent) {
        texture = Assets.getInstance().getAtlas().findRegion(regionName);
        this.position = position;
        this.onConsumeEvent = onConsumeEvent;
        this.active = true;

    }

    @Override
    public void consume(GameCharacter gameCharacter) {
        if(onConsumeEvent != null)
            onConsumeEvent.OnConsume(gameCharacter);

        active = false;

    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(texture, position.x - 32, position.y - 32);
    }

    @Override
    public int getCellX() {
        return (int) (position.y / 80);
    }

    @Override
    public int getCellY() {
        return (int) (position.x / 80);
    }

    @Override
    public boolean isActive() {
        return active;
    }


    public Vector2 getPosition() {
        return position;
    }
}
