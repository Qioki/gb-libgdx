package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class TextObjectController extends ObjectPool<TextObject> {
    private GameController gc;

    @Override
    protected TextObject newObject() {
        return new TextObject(gc);
    }

    public TextObjectController(GameController gc) {
        this.gc = gc;
    }

    public void setup(float x, float y, String text) {
        getActiveElement().setup(x, y, text);
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).render(batch, font);
        }
    }
    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }
}
