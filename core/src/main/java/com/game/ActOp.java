package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;

public class ActOp extends Options {

    private final Rectangle hitBox;
    private final Sprite sprite;
    private final SpriteBatch batch;

    private float scale = 0.2f, minus = 0;

    private final int halfWidth , height;

    public ActOp(Texture image, SpriteBatch batch, float x, float y) {
        setX(x); setY(y);
        setScale(VH_WIDTH / 80, VH_HEIGHT / 40);
        this.sprite = new Sprite(image);
        this.batch = batch;
        halfWidth = image.getWidth() / 2;
        this.height = image.getHeight();
        sprite.setRegion(0, 1, halfWidth - 60, image.getHeight() - 2);
        hitBox = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public float getHeight() {
        return this.sprite.getHeight() * getScaleX();
    }

    @Override
    public float getWidth() {
        return this.sprite.getWidth() * getScaleY();
    }

    public void draw() {
        batch.begin();
        batch.draw(sprite,  getX(), getY(), halfWidth * getScaleX() * 2f, (this.height * getScaleY()) - 1) ;
        batch.end();
    }

    public void updateIsCollided(boolean isCollided) {
        if (isCollided) {
            sprite.setRegion(this.halfWidth + 55, 0, this.halfWidth - 60, this.height);
        }
        else {
            sprite.setRegion(0, 1, this.halfWidth - 60, this.height - 2);
        }
    }

}
