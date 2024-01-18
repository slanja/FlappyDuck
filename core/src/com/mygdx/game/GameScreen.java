package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.ListIterator;

public class GameScreen implements Screen {

    // screen
    private Camera camera;
    private Viewport viewport;

    // graphics
    private SpriteBatch batch;
    private Texture background;
    private  Texture stumpTexture;
    private Texture duckTexture;

    // timing
    private int backgroundOffset;
    private int stumpOffset;

    // world parameters
    private final int WORLD_WIDTH = 624;
    private final int WORLD_HEIGHT = 352;

    // game objects
    private Duck playerDuck;
    private Stump stumpObject;


    GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        background = new Texture("background.png");
        backgroundOffset = 0;

        // set up game objects

        // stump object
        stumpTexture = new Texture("stump.png");

        stumpObject = new Stump(50, 90, 130, WORLD_WIDTH, 27, stumpTexture);

        // duck
        duckTexture = new Texture("duck.png");

        playerDuck = new Duck(3000, 64, 64, WORLD_WIDTH/2, WORLD_HEIGHT/2, duckTexture);

        batch = new SpriteBatch();

    }

    @Override
    public void render(float deltaTime) {
        batch.begin();

        detectInput(deltaTime);

        // scrolling background
        backgroundOffset++;

        if (backgroundOffset % WORLD_WIDTH == 0)  backgroundOffset = 0;

        batch.draw(background, -backgroundOffset, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, -backgroundOffset+WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);

        // drawing stump


        // drawing player
        playerDuck.draw(batch);

        if (playerDuck.boundingBox.y > 23) {
            playerDuck.boundingBox.y = playerDuck.boundingBox.y - 2;
        }

        batch.end();
    }

    private void spawnStump(float deltaTime) {
        //stumpList.add(new Stump(50, 90, 130, WORLD_WIDTH, 27, stumpTexture));
    }

    private void detectInput(float deltaTime) {
        // keyboard input
        float upLimit;
        upLimit = WORLD_HEIGHT - playerDuck.boundingBox.y - playerDuck.boundingBox.height;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && upLimit > 0) {
            float yChange = playerDuck.duckSpeed*deltaTime;
            yChange = Math.min(yChange, upLimit);
            playerDuck.translate(0f, yChange);
        }
    }

    private void detectCollisions() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
