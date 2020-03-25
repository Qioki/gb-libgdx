package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.MapElement;
import com.geekbrains.rpg.game.logic.utils.Poolable;

public class TextObject  implements Poolable, MapElement {

    private GameController gc;
    private String text;
    private Vector2 position;
    private Vector2 velocity;
    private float time;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public TextObject(GameController gc) {
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
    }

    public void setup(float x, float y, String text) {
        System.out.println("DSd" + text);
        this.text = text;
        position.set(x, y+30);
        velocity.set(MathUtils.random(-10.0f, 10.0f), 30f);
        time = 0.0f;
        active = true;
    }

    public void update(float dt) {
        time += dt;
        position.mulAdd(velocity, dt);
        if (time > 4.0f) {
            active = false;
        }
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public int getCellX() {
        return (int) (position.x / Map.CELL_WIDTH);
    }

    @Override
    public int getCellY() {
        return (int) (position.y / Map.CELL_HEIGHT);
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
