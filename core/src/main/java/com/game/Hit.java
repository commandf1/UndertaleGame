package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Hit extends Actor {
    private Animation<TextureRegion> animation;

    private final Sprite hit;

    private float stateTime;

    public Hit(float x, float y) {
        setPosition(x,y);
        hit = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta/3;
    }

    public boolean isAnimationFinished() {
        if (animation==null) {
            return true;
        }
        return this.animation.isAnimationFinished(stateTime*4);
    }

    public void animationHit() {
        TextureRegion[] frames = new TextureRegion[6];
        frames[0] = new TextureRegion(hit, 910, 235, 26, 110);
        frames[1] = new TextureRegion(hit, 941, 235, 26, 110);
        frames[2] = new TextureRegion(hit, 972, 235, 26, 110);
        frames[3] = new TextureRegion(hit, 1003, 235, 26, 110);
        frames[4] = new TextureRegion(hit, 1034, 235, 26, 110);
        frames[5] = new TextureRegion(hit, 1065, 235, 26, 110);
        animation = new Animation<>(0.1f, frames);
        stateTime = 0;

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (isAnimationFinished()) {
            remove();
        } else {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime*7, false);
            batch.draw(currentFrame, getX() , getY(), (float) currentFrame.getRegionWidth() /2, (float) currentFrame.getRegionHeight() /2, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1.5f, 1.5f, 0);
        }


    }
}
