package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BarAttack extends Actor {
    private final Sprite barAttackImage;

    public BarAttack(float x, float y) {
        setPosition(x, y);
        barAttackImage = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
        barAttackImage.setRegion(1139, 23,14,128);
        setSize(barAttackImage.getRegionWidth(), barAttackImage.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(barAttackImage,  getX(), getY(), (float) barAttackImage.getRegionWidth() /2, (float) barAttackImage.getRegionHeight() /2, barAttackImage.getRegionWidth(), barAttackImage.getRegionHeight(), 1f, 1f, 0);
    }
}
