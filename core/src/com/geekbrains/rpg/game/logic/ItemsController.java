package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class ItemsController extends ObjectPool<GameItem> {

    private GameController gc;

    @Override
    protected GameItem newObject() {
        return new GameItem();
    }

    public ItemsController(final GameController gc) {
        this.gc = gc;

        GameItem item = getActiveElement();
        item.setup("arrow", new Vector2(200, 200), new OnConsumeEvent() {
            @Override
            public void OnConsume(GameCharacter gameCharacter) {
                if(gameCharacter == gc.getHero())
                    gameCharacter.hp += 10;
            }
        });

        item = getActiveElement();
        item.setup("arrow", new Vector2(300, 300), new OnConsumeEvent() {
            @Override
            public void OnConsume(GameCharacter gameCharacter) {
                if(gameCharacter == gc.getHero())
                    ((Hero)gameCharacter).addCoins(10);
            }
        });



    }

    public void update(float dt) {
        checkPool();
    }
}
