package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.MapElement;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Weapon implements MapElement {

    private GameCharacter owner;
    private WeaponController.WeaponClass weaponClass;
    private int rangeAttack;
    private int damage;
    private float rate;
    private GameController gc;
    private String regionName;
    protected TextureRegion texture;
    protected Vector2 position;

    public Weapon(GameController gc, String regionName,
                  WeaponController.WeaponClass weaponClass,
                  int rangeAttack, int damage, float rate) {
        this.gc = gc;
        this.texture = Assets.getInstance().getAtlas().findRegion(regionName);
        this.weaponClass = weaponClass;
        this.rangeAttack = rangeAttack;
        this.damage = damage;
        this.rate = rate;
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {



    }


    public void performAttack(GameCharacter target) {

        if (weaponClass == WeaponController.WeaponClass.Melee) {
            target.takeDamage(owner, damage);
        }
//        if(gc.getProjectilesController() ==  null)
//            System.out.println("@#@#@#@#@#@3");
        if (weaponClass == WeaponController.WeaponClass.Ranged) {
            gc.getProjectilesController().setup(owner, owner.position.x, owner.position.y,
                    target.getPosition().x, target.getPosition().y);
        }
    }

    public int getCellX() {
        return (int) position.x / 80;
    }

    public int getCellY() {
        return (int) (position.y - 20) / 80;
    }

    public void setOwner(GameCharacter owner) {
        this.owner = owner;
    }
    public GameCharacter getOwner() {
        return owner;
    }

    public String getRegionName() {
        return regionName;
    }

    public WeaponController.WeaponClass getWeaponClass() {
        return weaponClass;
    }

    public int getRangeAttack() {
        return rangeAttack;
    }

    public int getDamage() {
        return damage;
    }

    public float getRate() {
        return rate;
    }
}
