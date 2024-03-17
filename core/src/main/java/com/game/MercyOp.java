package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


import java.awt.*;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;

public class MercyOp extends Options {

    private final Rectangle hitBox;
    private final Sprite sprite;
    private final SpriteBatch batch;

    private final int halfWidth, height;

    public MercyOp(Texture image, SpriteBatch batch, float x, float y) {
        setX(x); setY(y);
        setScale(VH_WIDTH / 80, VH_HEIGHT / 40);
        this.sprite = new Sprite(image);
        this.batch = batch;
        this.height = image.getHeight();
        halfWidth = image.getWidth() / 2;
        sprite.setRegion(0, 1, halfWidth - 60, image.getHeight() - 2);
        hitBox = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public float getWidth() {
        return this.sprite.getWidth() * getScaleX();
    }

    @Override
    public float getHeight() {
        return this.sprite.getHeight() * getScaleY();
    }

    public void draw() {
        batch.begin();
        batch.draw(sprite,  getX(), getY(), getWidth() , getHeight()) ;
        batch.end();
    }
    public void updateIsCollided(boolean isCollided) {
        if (isCollided) {
            sprite.setRegion(halfWidth + 55, 0, halfWidth - 60, this.height);
        }
        else {
            sprite.setRegion(0, 1, halfWidth - 60, this.height - 2);
        }
    }

}
