package com.geekbrains.rpg.game.logic;

import java.util.HashMap;

public class WeaponController {

    enum WeaponClass {
        Melee,
        Ranged
    }
    private final HashMap<String, Weapon> weapons = new HashMap<>();
    protected GameController gc;

    public WeaponController(GameController gc) {
        this.gc = gc;

        createWeaponPrototype("Katana",
                new Weapon(gc, "arrow", WeaponClass.Melee,
                        50, 3, .2f));

        createWeaponPrototype("LongBow",
                new Weapon(gc, "arrow", WeaponClass.Ranged,
                        200, 2, 1f));


    }


    public void update(float dt)  {
    }

    public void createWeaponPrototype(String name, Weapon weapon) {
        weapons.put(name, weapon);
    }

    public Weapon getWeaponByName(String name) {
        return copyWeapon(weapons.get(name));
    }
    public Weapon copyWeapon(Weapon weapon) {
        if(weapon == null) return null;
        return new Weapon(gc, weapon.getRegionName(), weapon.getWeaponClass(),
                weapon.getRangeAttack(), weapon.getDamage(), weapon.getRate());

    }

}
