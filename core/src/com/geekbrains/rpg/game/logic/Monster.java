package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Monster extends GameCharacter {
    private float attackTime;
    Sensor sensor;

    private Vector2 patrolPosition = new Vector2();


    public Monster(GameController gc) {
        super(gc, 20, 100.0f);
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.changePosition(800.0f, 300.0f);
        System.out.println("new");

        sensor = new Sensor(gc.getHero().getPosition(), position);
        sensor.radius = 300f;

    }

    @Override
    public void onDeath() {
//        this.position.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
//        this.hp = this.hpMax;
        gc.destroyObject(this);
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void update(float dt) {
        super.update(dt);

        if(sensor.checkTarget()) {
            dst.set(gc.getHero().getPosition());
        } else {
            walking();
        }

        if (this.position.dst(gc.getHero().getPosition()) < 40) {
            attackTime += dt;
            if(attackTime > 0.3f) {
                attackTime = 0.0f;
                gc.getHero().takeDamage(1);
            }
        }

    }

    private void walking() {
        if(position.dst2(patrolPosition) < 1) {
            patrolPosition.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        }
        dst.set(patrolPosition);
    }
}
