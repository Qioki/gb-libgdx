package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Spawner {
    private Vector2 spawnPosition;
    private int spawnEverySecond = 30;
    private boolean needSpawn = false;
    private ArrayList<Monster> monsters;
    protected GameController gc;

    public Spawner(GameController gc, Vector2 spawnPosition, int spawnEverySecond) {
        this.spawnPosition = spawnPosition;
        this.spawnEverySecond = spawnEverySecond;
        this.gc = gc;
        monsters = gc.getMonsters();

        startSpawn();
    }
    private void spawn() {
        Monster monster = new Monster(gc);
        monster.getPosition().set(spawnPosition);
        monsters.add(monster);
    }
    public void stopSpawn (){
        needSpawn = false;
    }
    public void startSpawn (){
        if(needSpawn) return;

        needSpawn = true;
        Thread t = new Thread(() -> {
            while(needSpawn) {
                spawn ();
                try {
                    sleep(spawnEverySecond*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
