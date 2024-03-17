package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ItemOp extends Options {
    private final Rectangle hitBox;

    private final Sprite sprite;
    private final SpriteBatch batch;
    private final int halfWidth, height;

    public ItemOp(Texture image, SpriteBatch batch, float x, float y) {
        setX(x); setY(y);
        this.sprite = new Sprite(image);
        this.batch = batch;
        halfWidth = image.getWidth() / 2;
        height = image.getHeight();
        sprite.setRegion(0, 1, halfWidth - 3, image.getHeight() - 2);
        hitBox =  new Rectangle(getX(), getY(), getWidth()* 0.2f, getHeight()* 0.2f);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public float getWidth() {
        return this.sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return this.sprite.getHeight();
    }

    public void draw() {
        batch.begin();
        batch.draw(sprite,  getX(), getY(), sprite.getWidth()  * 0.2f , sprite.getHeight() * 0.2f) ;
        batch.end();
    }

    public void updateIsCollided(boolean isCollided) {
        if (isCollided) {
            sprite.setRegion(this.halfWidth + 50, -1, this.halfWidth - 60, this.height);
        }
        else {
            sprite.setRegion(0, 1, this.halfWidth - 60 , this.height - 2);
        }
    }

}
