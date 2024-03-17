package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;

public class Sans extends Actor {
    private Sprite body;
    private Sprite head;
    private Sprite legs;

    private TextureRegion headTexture, bodyTexture, legsTexture;

    private Animation<TextureRegion> animation;

    private float stateTime;

    private boolean canAnimate = true;
    private final Sprite image;

    public Sans() {
        image = new Sprite(new Texture(Gdx.files.internal("images/SansSprite.png")));
        head = new Sprite(new TextureRegion(image, 5, 468, 30, 28));
        body = new Sprite(new TextureRegion(image, 15, 23, 52, 25));
        legs = new Sprite(new TextureRegion(image, 6, 82, 42, 21));

        setOriginY(new TextureRegion(image, 12, 390, 52, 47).getRegionHeight());
        setScale(VH_WIDTH / 5, VH_HEIGHT / 3);

        head.setScale(getScaleX(), getScaleY());
        body.setScale(getScaleX(), getScaleY());
        legs.setScale(getScaleX(), getScaleY());

        head.setOrigin(head.getWidth() / 2, 0);
        body.setOriginCenter();
        legs.setOrigin(legs.getWidth() / 2, legs.getRegionHeight());

        setPosition((float) Gdx.graphics.getWidth() / 2, 3 * (float) Gdx.graphics.getHeight() / 4);
        body.setPosition(getX(), getY());
        head.setPosition(body.getX(), body.getY() + body.getRegionHeight() + VH_HEIGHT * 1.5f);
        legs.setPosition(body.getX(), body.getY() - (float) body.getRegionHeight() / 2 - legs.getRegionHeight() - VH_HEIGHT * 2f);
    }

    public boolean isCanAnimate() {
        return this.canAnimate;
    }

    public void animationRightHand() {
        setOriginX(body.getOriginX() + (VH_WIDTH  * getScaleX()));
        TextureRegion[] frames = new TextureRegion[6];
        frames[0] = new TextureRegion(image, -12, 390, 99, 47);
        frames[1] = new TextureRegion(image, 90, 390, 99, 47);
        frames[2] = new TextureRegion(image, 192, 390, 99, 47);
        frames[3] = new TextureRegion(image, 294, 390, 99, 47);
        frames[4] = new TextureRegion(image, 396, 390, 99, 47);
        frames[5] = new TextureRegion(image, 498, 390, 99, 47);
        animation = new Animation<>(0.25f, frames);
        stateTime = 0;

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void animationLeftHand() {
        setOriginX(body.getOriginX() + (VH_WIDTH  * getScaleX()));
        TextureRegion[] frames = new TextureRegion[6];
        frames[0] = new TextureRegion(image, 498, 390, 99, 47);
        frames[1] = new TextureRegion(image, 396, 390, 99, 47);
        frames[2] = new TextureRegion(image, 294, 390, 99, 47);
        frames[3] = new TextureRegion(image, 192, 390, 99, 47);
        frames[4] = new TextureRegion(image, 90, 390, 99, 47);
        frames[5] = new TextureRegion(image, -12, 390, 99, 47);
        animation = new Animation<>(0.25f, frames);
        stateTime = 0;

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void animationUpwardHand() {
        setOriginX(body.getOriginX());
        TextureRegion[] frames = new TextureRegion[6];
        frames[0] = new TextureRegion(image, 9, 297, 55, 66);
        frames[1] = new TextureRegion(image, 78, 297, 55, 66);
        frames[2] = new TextureRegion(image, 147, 297, 55, 66);
        frames[3] = new TextureRegion(image, 216, 297, 55, 66);
        frames[4] = new TextureRegion(image, 285, 297, 55, 66);
        frames[5] = new TextureRegion(image, 354, 297, 55, 66);

        animation = new Animation<>(0.25f, frames);
        stateTime = 0;

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void animationDownwardHand() {
        setOriginX(body.getOriginX());
        TextureRegion[] frames = new TextureRegion[6];
        frames[0] = new TextureRegion(image, 354, 297, 55, 66);
        frames[1] = new TextureRegion(image, 285, 297, 55, 66);
        frames[2] = new TextureRegion(image, 216, 297, 55, 66);
        frames[3] = new TextureRegion(image, 147, 297, 55, 66);
        frames[4] = new TextureRegion(image, 78, 297, 55, 66);
        frames[5] = new TextureRegion(image, 9, 297, 55, 66);

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

    public boolean isAnimationFinished() {
        if (animation==null) {
            return true;
        }
        return this.animation.isAnimationFinished(stateTime*4);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float movementY = Math.abs((float) Math.sin(stateTime * 20f));

        float pendulumSwingXBody = (float) (0.1f * VH_WIDTH * Math.sin(10f * stateTime / 2)) + 1.3f;
        if ((int) (stateTime / 10) % 2 == 1) {
            pendulumSwingXBody = -pendulumSwingXBody;
        }
        if (isAnimationFinished()) {
             batch.draw(head, body.getX() + VH_WIDTH * 0.3f * getScaleX() - pendulumSwingXBody + 1 * getScaleY(),  -0.5f * VH_HEIGHT * getScaleY() + body.getY()+ (float) body.getRegionHeight() /2 + head.getRegionHeight() - movementY , head.getOriginX(), 0, head.getRegionWidth(), head.getRegionHeight(), getScaleY(), getScaleY(), 0);
        float pendulumSwingX = (float) (2f * Math.sin(10f * stateTime / 2)) - 1.5f;
        float movementYBody = (float) (1.5f * Math.sin(20f * stateTime)) - 1.5f;
        batch.draw(body, body.getX() - pendulumSwingX, body.getY() - (VH_HEIGHT * getScaleY() * 0.3f) - movementYBody , body.getOriginX(), body.getOriginY(), body.getRegionWidth(), body.getRegionHeight(), getScaleX(), getScaleY(), 0);
        batch.draw(legs, legs.getX() + (VH_WIDTH*1.9f/ getScaleX()), legs.getY() + VH_HEIGHT * 0.2f, legs.getOriginX(), legs.getOriginY(), legs.getRegionWidth(), legs.getRegionHeight(), getScaleX(), getScaleY(), 0);
        } else {
            float deltaY, deltaX;
        // 20
        TextureRegion currentFrame = animation.getKeyFrame(stateTime*20, false);
        if (currentFrame.getRegionX() == 9 && currentFrame.getRegionY() == 297) {
            deltaY = -0.7f * VH_HEIGHT * getScaleY();
        }
        else if (currentFrame.getRegionX() == 78 && currentFrame.getRegionY() == 297) {
            deltaY = -0.8f * VH_HEIGHT * getScaleY();
        } else if (currentFrame.getRegionX() == 147 && currentFrame.getRegionY() == 297) {
            deltaY = -0.5f * VH_HEIGHT * getScaleY();
        } else if (currentFrame.getRegionX() == 285 || currentFrame.getRegionX() == 354 && currentFrame.getRegionY() == 297) {
            deltaY = -0.6f * VH_HEIGHT * getScaleY();
        }else if (currentFrame.getRegionX() == 216 && currentFrame.getRegionY() == 297) {
            deltaY = 0 * VH_HEIGHT * getScaleY();
        } else {
            deltaY = -0.5f * VH_HEIGHT * getScaleY();
        }

        if (currentFrame.getRegionX() == 90 && currentFrame.getRegionY() == 390) {
            deltaX = -0.2f *VH_WIDTH * getScaleX();
        } else if (currentFrame.getRegionX() == 192 && currentFrame.getRegionY() == 390) {
            deltaX = -0.2f * VH_WIDTH * getScaleX();
        } else if (currentFrame.getRegionX() == 294 && currentFrame.getRegionY() == 390) {
            deltaX = 0.2f * VH_WIDTH * getScaleX();
        }
        else {
            deltaX = 0;
        }
        batch.draw(head, body.getX() + VH_WIDTH * 0.3f * getScaleX()- pendulumSwingXBody + deltaX + 1 * getScaleY(), body.getY()+ (float) body.getRegionHeight() /2 + head.getRegionHeight() - movementY + deltaY, head.getOriginX(), 0, head.getRegionWidth(), head.getRegionHeight(), getScaleY(), getScaleY(), 0);

        batch.draw(currentFrame, body.getX() + VH_WIDTH * 0.01f , body.getY() - VH_HEIGHT, getOriginX(), getOriginY(), currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), getScaleX(), getScaleY(), 0);
        }
    }
}
