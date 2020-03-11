package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameController {
    private ProjectilesController projectilesController;
    private Map map;
    private Hero hero;
//    private Monster monster;
    private ArrayList<Monster> monsters;
    private ArrayList<Monster> needToDestroy = new ArrayList<>();
    private Vector2 tmp, tmp2;
    Spawner spawner;

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Map getMap() {
        return map;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public GameController() {
        this.projectilesController = new ProjectilesController();
        this.hero = new Hero(this);
//        this.monster = new Monster(this);
        this.monsters = new ArrayList<>();
        spawner = new Spawner(this, new Vector2(200, 200), 30);
        this.map = new Map();
        this.tmp = new Vector2(0, 0);
        this.tmp2 = new Vector2(0, 0);
    }

    public void update(float dt) {
        hero.update(dt);
//        monster.update(dt);

        needToDestroy.forEach(monster -> monsters.remove(monster));
        needToDestroy.clear();

        monsters.forEach(monster -> monster.update(dt));

        checkCollisions();
        monsters.forEach(monster -> collideUnits(hero, monster));
//        collideUnits(hero, monster);
        projectilesController.update(dt);
    }

    public void collideUnits(GameCharacter u1, GameCharacter u2) {

        if (u1.getArea().overlaps(u2.getArea())) {
            tmp.set(u1.getArea().x, u1.getArea().y);
            tmp.sub(u2.getArea().x, u2.getArea().y);
            float halfInterLen = ((u1.getArea().radius + u2.getArea().radius) - tmp.len()) / 2.0f;
            tmp.nor();

            tmp2.set(u1.getPosition()).mulAdd(tmp, halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u1.changePosition(tmp2);
            }

            tmp2.set(u2.getPosition()).mulAdd(tmp, -halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u2.changePosition(tmp2);
            }
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < projectilesController.getActiveList().size(); i++) {
            Projectile p = projectilesController.getActiveList().get(i);
            if (!map.isAirPassable(p.getCellX(), p.getCellY())) {
                p.deactivate();
                continue;
            }
            monsters.forEach(monster -> {
                if (p.getPosition().dst(monster.getPosition()) < 24) {
                    p.deactivate();
                    if (monster.takeDamage(3)) {
                        hero.addCoins(MathUtils.random(1, 10));
                    }
                }
            });
        }
    }

    public void destroyObject(Monster monster) {
        needToDestroy.add(monster);
    }
}
