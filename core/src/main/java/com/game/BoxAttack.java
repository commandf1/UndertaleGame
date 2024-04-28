package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoxAttack extends Actor {
    private final Sprite boxAttack;

    public BoxAttack(float x, float y) {
        setPosition(x, y);
        boxAttack = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
        boxAttack.setRegion(13, 30, 546, 115);
        setX(getX() - (float) boxAttack.getRegionWidth() /2);
        setSize(boxAttack.getRegionWidth(), boxAttack.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(boxAttack,  getX(), getY(), (float) boxAttack.getRegionWidth() /2, (float) boxAttack.getRegionHeight() /2, boxAttack.getRegionWidth(), boxAttack.getRegionHeight(), 1f, 1f, 0);
    }
}
