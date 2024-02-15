package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Pipes {

    // duck characteristics
    float stumpSpeed;

    // position & dimension
    Rectangle boundingBox;
    // graphics
    Texture stumpTexture;

    public Pipes(float stumpSpeed, float width, float height, float xCenter, float yCenter, Texture stumpTexture) {
        this.stumpSpeed = stumpSpeed;
        this.stumpTexture = stumpTexture;
        this.boundingBox = new Rectangle(xCenter - width/2, yCenter - height/2, width, height);
    }

    public void draw(Batch batch) {
        batch.draw(stumpTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
    public Rectangle boundingBox() {
        return  new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
