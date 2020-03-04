package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.core.Collider;
import com.mygdx.game.core.GUIObject;
import com.mygdx.game.core.GameObject;
import com.mygdx.game.core.Transform;
import com.mygdx.game.gameobjects.Apple;
import com.mygdx.game.gameobjects.BGObject;
import com.mygdx.game.gameobjects.Hero;
import com.mygdx.game.gameobjects.Pointer;

import java.util.ArrayList;
import java.util.TreeMap;

public class GB_RPG extends ApplicationAdapter {
	private static SpriteBatch batch;
	private static TextureAtlas atlas;
	private static BitmapFont font32;

	private TextureRegion textureGrass;
	private static Hero player;
	private GUIObject guiObject;

	public static float deltaTime = 0f;
	private Pointer pointer;
	private static TreeMap<Float, ArrayList<GameObject>> gameObjects = new TreeMap<>();
	private static ArrayList<GameObject> needToDeactivateGO = new ArrayList<>();
	private static ArrayList<GameObject> usedGameObjects = new ArrayList<>();

	private static ArrayList<Collider> triggerObjects = new ArrayList<>();


	// Домашнее задание:
	// 0. Разобраться с кодом
	// 1. Добавить на экран яблоко, и попробовать отследить попадание
	// стрелы в яблоко, при попадании яблоко должно появиться в новом месте
	// 2. ** Попробуйте заставить героя выпускать по несколько стрел

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		atlas = new TextureAtlas("game.pack");
		font32 = new BitmapFont(Gdx.files.internal("font32.fnt"));

		pointer = new Pointer("pointer");
		player = new Hero("knight");
		player.setTarget(pointer);
		new BGObject("grass");
		guiObject = new GUIObject(player);

		Apple testApple = new Apple();
		testApple.transform.localPosition.set(400, 400);

	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		deltaTime = Gdx.graphics.getDeltaTime();
		GameObject.deltaTime = deltaTime;

		batch.begin();


		update();

		gameObjects.forEach((k, gos) -> {
			gos.forEach(GameObject::render);
		});


		batch.end();
	}

	public void update() {

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			pointer.transform.localPosition.set(Gdx.input.getX(), 720.0f - Gdx.input.getY());
		}
		if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			player.fire(Gdx.input.getX(), 720.0f - Gdx.input.getY());
		}

		gameObjects.forEach((k, gos) -> {
			gos.forEach(GameObject::update);
		});
		deactivateGOs();

		analiseTriggers();
	}


	public static void addGO(GameObject go)
	{
		gameObjects.computeIfAbsent(go.transform.zIndex, k -> new ArrayList<>()).add(go);
	}

	public static void spawnGO(GameObject tagret, Transform parentT, Vector2 offsetV2, float directionX, float directionY)
	{
		Transform targetT = tagret.transform;
		Vector2 parentPos = parentT.getWorldPosition();

		targetT.localPosition.x = parentPos.x + offsetV2.x;
		targetT.localPosition.y = parentPos.y + offsetV2.y;
		targetT.setWorldDirection(directionX - targetT.localPosition.x, directionY - targetT.localPosition.y);


	}

	public static GameObject activateGO(Class c)
	{
		for (GameObject usedGO:usedGameObjects) {
			if(c == usedGO.getClass()) {
				usedGameObjects.remove(usedGO);
				addGO(usedGO);
				return usedGO;
			}
		}
		return null;
	}

	private static void deactivateGOs()
	{
		for (GameObject go : needToDeactivateGO) {
			if(!gameObjects.containsKey(go.transform.zIndex))
				continue;
			gameObjects.get(go.transform.zIndex).remove(go);
			usedGameObjects.add(go);
		}
		needToDeactivateGO.clear();
	}
	public static void deactivateGO(GameObject go)
	{
		needToDeactivateGO.add(go);
	}


	public void analiseTriggers() {

		Collider c1;
		Collider c2;
		Vector2 pos1;
		Vector2 pos2;
		float dst;
		for (int i = 0; i < triggerObjects.size(); i++) {
			for (int j = i+1; j < triggerObjects.size(); j++) {
				c1 = triggerObjects.get(i);
				c2 = triggerObjects.get(j);
				pos1 = c1.transform.localPosition;
				pos2 = c2.transform.localPosition;
				dst = Vector2.dst(pos1.x, pos1.y, pos2.x, pos2.y);
				if(dst < c1.radius + c2.radius) {
					c1.onTriggerEnter(c2);
					c2.onTriggerEnter(c1);
				}
			}
		}
		triggerObjects.forEach(c -> {

		});
	}
	public static void registerOnTrigger(Collider c) {
		triggerObjects.add(c);
	}
	public static void unRegisterOnTrigger(Collider c) {
		triggerObjects.remove(c);
	}



	@Override
	public void dispose() {
		batch.dispose();
	}



	public static SpriteBatch getBatch() {
		return batch;
	}

	public static TextureAtlas getAtlas() {
		return atlas;
	}

	public static BitmapFont getFont32() {
		return font32;
	}

}
