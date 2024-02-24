package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class GameScreen implements Screen {

    Random rand = new Random();

    // screen
    private Camera camera;
    private Viewport viewport;

    // graphics
    private SpriteBatch batch;
    private Texture background;
    private  Texture pipeTexture;
    private Texture duckTexture;

    // timing
    private int backgroundOffset;
    private int pipeOffset;

    // world parameters
    private final int WORLD_WIDTH = 624;
    private final int WORLD_HEIGHT = 352;

    // game objects
    private Duck playerDuck;
    private Pipes pipeObject;

    private Sound sound;


    GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        background = new Texture("background.png");
        backgroundOffset = 0;

        // set up game objects
        
        // pipe texture
        String[] pipes = {"pipe_bottom.png", "pipe_middle.png", "pipe_top.png"};
        String pipe = pipes[rand.nextInt(pipes.length)];
        pipeTexture = new Texture(pipe);

        pipeObject = new Pipes(50, 60, 325, WORLD_WIDTH, WORLD_HEIGHT/2+21, pipeTexture);

        // duck
        duckTexture = new Texture("duck.png");

        playerDuck = new Duck(3000, 64, 64, WORLD_WIDTH/2, WORLD_HEIGHT/2, duckTexture);

        batch = new SpriteBatch();

    }


    @Override
    public void render(float deltaTime) {
        batch.begin();

        detectInput(deltaTime);

        // quack sound
        sound = Gdx.audio.newSound(Gdx.files.internal("assets/quack_1.wav"));

        // scrolling background
        backgroundOffset++;

        if (backgroundOffset % WORLD_WIDTH == 0)  backgroundOffset = 0;

        batch.draw(background, -backgroundOffset, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, -backgroundOffset+WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);


        // drawing player
        playerDuck.draw(batch);

        if (playerDuck.boundingBox.y > 23) {
            playerDuck.boundingBox.y = playerDuck.boundingBox.y - 2;
        }

        // drawing pipe
        pipeObject.draw(batch);
        pipeObject.boundingBox.x--;

        if (pipeObject.boundingBox.x == -80) pipeObject.boundingBox.x = WORLD_WIDTH+80;


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
            sound.play();
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
        sound.dispose();
    }
}