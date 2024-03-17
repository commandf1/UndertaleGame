package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HeartFragment extends Actor {
    private final Sprite sprite;

    private final Texture image = new Texture(Gdx.files.internal("images/heart.png"));
    private final Vector2 velocity;
    private int currentRegionIndex = 0;

    private float timeElapsed = 0;

    Vector2[] points = {new Vector2(7, 141), new Vector2(19, 142), new Vector2(30, 139)};

    public HeartFragment(float x, float y, Vector2 velocity) {
        setPosition(x, y);
        this.sprite = new Sprite(image);
        this.sprite.setRegion((int) points[0].x, (int) points[1].y, 8, 7);
        this.sprite.setOriginCenter();
        this.velocity = velocity;
    }

    private void updateSpriteRegion(float delta) {
        timeElapsed += delta;
        if (timeElapsed > 0.1f) {
            timeElapsed = 0;
            currentRegionIndex++;
            if (currentRegionIndex >= points.length) {
                currentRegionIndex = 0;
            }
            Vector2 point = points[currentRegionIndex];
            switch (currentRegionIndex) {
                case 0 -> this.sprite.setRegion((int) point.x, (int) point.y, 8, 7);
                case 1 -> this.sprite.setRegion((int) point.x, (int) point.y, 8, 6);
                case 2 -> this.sprite.setRegion((int) point.x, (int) point.y, 9, 9);
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + velocity.x * delta, getY() + velocity.y * delta);
        updateSpriteRegion(delta);
        velocity.y -= 100 * delta;

        if (getX() < 0 || getX() > Gdx.graphics.getWidth()
            || getY() < 0  || getY() > Gdx.graphics.getHeight()) {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setScale(0.5f);
        batch.begin();
        batch.draw(sprite,  getX(), getY());
        batch.end();
    }

    public void dispose() {
        this.image.dispose();
    }
}
