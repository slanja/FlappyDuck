package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class Duck {

    // duck characteristics
    float duckSpeed;

    // position & dimension
    Rectangle boundingBox;
    // graphics
    Texture duckTexture;

    public Duck(float duckSpeed, float width, float height, float xCenter, float yCenter, Texture duckTexture) {
        this.duckSpeed = duckSpeed;
        this.duckTexture = duckTexture;
        this.boundingBox = new Rectangle(xCenter - width/2, yCenter - height/2, width, height);
    }

    public boolean collides(com.badlogic.gdx.math.Rectangle otherRectangle) {
        Rectangle thisRectangle = new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        return thisRectangle.overlaps(otherRectangle);
    }


    public void translate(float xChange, float yChange) {
        boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
    }

    public void draw(Batch batch) {
        batch.draw(duckTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    public Rectangle boundingBox() {
        return  new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}