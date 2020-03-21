package com.geekbrains.rpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geekbrains.rpg.game.logic.GameController;
import com.geekbrains.rpg.game.logic.WorldRenderer;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class GameScreen extends AbstractScreen {

    public enum GameState {
        INMENU, STARTED, PAUSED
    }
    private GameState gameState;

    private GameController gc;
    private WorldRenderer worldRenderer;
    private Stage stage;
    Label pausedText;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        gc = new GameController();
        worldRenderer = new WorldRenderer(gc, batch);

        gameState = GameState.STARTED;
        createGui();
    }

    @Override
    public void render(float delta) {
        if(gameState == GameState.STARTED) {
            gc.update(delta);
        }
        worldRenderer.render();

        stage.draw();
    }

    public void createGui() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
        TextButton.TextButtonStyle menuBtnStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("shortButton"), null, null, font14);

        TextButton btnPause = new TextButton("Pause", menuBtnStyle);
        btnPause.setPosition(ScreenManager.WORLD_WIDTH - 100, ScreenManager.WORLD_HEIGHT - 100);
        TextButton btnMenu = new TextButton("Menu", menuBtnStyle);
        btnMenu.setPosition(ScreenManager.WORLD_WIDTH - 200, ScreenManager.WORLD_HEIGHT - 100);

        btnPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(gameState == GameState.PAUSED) {
                    gameState = GameState.STARTED;
                    pausedText.setVisible(false);
                }
                else {
                    gameState = GameState.PAUSED;
                    pausedText.setVisible(true);
                }
            }
        });

        btnMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });


        pausedText = new Label("GAME PAUSED", new Label.LabelStyle(font14, new Color(.7f,.7f,.7f,1)));
        pausedText.setPosition(ScreenManager.HALF_WORLD_WIDTH, ScreenManager.HALF_WORLD_HEIGHT);
        pausedText.setVisible(false);

        stage.addActor(btnPause);
        stage.addActor(btnMenu);
        stage.addActor(pausedText);
        skin.dispose();
    }

}