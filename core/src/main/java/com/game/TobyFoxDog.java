package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;

public class TobyFoxDog extends Actor {
    private final Sprite IMAGE;

    private float stateTime;

    private Animation<TextureRegion> animation;
    public TobyFoxDog() {
        setScale(VH_WIDTH / 5, VH_HEIGHT / 3);
        IMAGE = new Sprite(new Texture(Gdx.files.internal("images/TobyFoxSprite.png")));
    }

    public void animationTobyFox() {
        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(IMAGE, 3, 55, 18, 24);
        frames[1] = new TextureRegion(IMAGE, 24, 55, 18, 24);
        frames[2] = new TextureRegion(IMAGE, 45, 55, 18, 24);
        frames[3] = new TextureRegion(IMAGE, 66, 55, 18, 24);
        animation = new Animation<>(0.25f, frames);
        stateTime = 0;
        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta/3;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.begin();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime*4, true);
        batch.draw(currentFrame, getX() + VH_WIDTH * 0.01f , getY() - VH_HEIGHT, getOriginX(), getOriginY(), currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), getScaleX(), getScaleY(), 0);
        batch.end();

    }
}
