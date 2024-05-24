package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.BattleController.act;
import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;
import static com.game.Direction.BACKWARD;
import static com.game.Direction.TOWARD;
import static com.game.Sounds.inflictingDamageSound;
import static com.game.Undertale.batch;

public class Sans extends Actor {
    private final Sprite body;
    private final Sprite head;
    private final Sprite legs;

    private Animation<TextureRegion> animation, animationBody, animationHead, animationLegs;

    private float stateTime;

    public static float timeHead = 0;

    private float offset = 0;

    private final Sprite image;

    private Direction direction = BACKWARD;

    private boolean isInThirdFrame = false;


    private boolean isAnimationVoidFinished, isAnimationHeadDone = false, isAnimationWonDone = true, canAnimateEvadeAttack = false, isOptionAvailable = true;

    private final float HALF_SCREEN_WIDTH = (float) Gdx.graphics.getWidth() / 2;

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

        setPosition(HALF_SCREEN_WIDTH, 3 * (float) Gdx.graphics.getHeight() / 4);
        body.setPosition(getX(), getY());
        head.setPosition(body.getX(), body.getY() + body.getRegionHeight() + VH_HEIGHT * 1.5f);
        legs.setPosition(body.getX(), body.getY() - (float) body.getRegionHeight() / 2 - legs.getRegionHeight() - VH_HEIGHT * 2f);
    }

    public Sprite getHead() {
        return head;
    }

    public void setAnimationMercy() {
        head.setRegion(228, 520, head.getRegionWidth(),  head.getRegionHeight());
    }

    public void setAnimationHeadWhite() {
        head.setRegion(191, 520, head.getRegionWidth(),  head.getRegionHeight());
    }

    public Animation<TextureRegion> getAnimationHead() {
        return animationHead;
    }

    public void animationWon() {
        isAnimationWonDone = false;
        TextureRegion[] framesBody = new TextureRegion[8];
        // extra de 18 width
        framesBody[0] = new TextureRegion(image, 6, 23, 70, 34);
        framesBody[1] = new TextureRegion(image, 83, 23, 70, 34);
        framesBody[2] = new TextureRegion(image, 160, 23, 70, 34);
        framesBody[3] = new TextureRegion(image, 237, 23, 70, 34);
        framesBody[4] = new TextureRegion(image, 314, 23, 70, 34);
        framesBody[5] = new TextureRegion(image, 391, 23, 70, 34);
        framesBody[6] = new TextureRegion(image, 469, 23, 70, 34);
        framesBody[7] = new TextureRegion(image, 545, 23, 70, 34);
        animationBody = new Animation<>(1.2f, framesBody);
        for (TextureRegion region : framesBody) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureRegion[] frames = new TextureRegion[8];
        frames[0] = new TextureRegion(image, 80, 520, 30, 28);
        frames[1] = new TextureRegion(image, 42, 520, 30, 28);
        frames[2] = new TextureRegion(image, 376, 520, 30, 28);
        frames[3] = new TextureRegion(image, 412, 520, 30, 28);
        frames[4] = new TextureRegion(image, 450, 520, 30, 28);
        frames[5] = new TextureRegion(image, 487, 520, 30, 28);
        frames[6] = new TextureRegion(image, 450, 520, 30, 28);
        frames[7] = new TextureRegion(image, 525, 520, 30, 28);

        animationHead = new Animation<>(1.2f, frames);
        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureRegion[] framesLegs = new TextureRegion[8];
        // 49
        framesLegs[0] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[1] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[2] = new TextureRegion(image, 55, 81, 50, 22);
        framesLegs[3] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[4] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[5] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[6] = new TextureRegion(image, 0, 81, 50, 22);
        framesLegs[7] = new TextureRegion(image, 0, 81, 50, 22);

        animationLegs = new Animation<>(1.2f, framesLegs);
        for (TextureRegion region : framesLegs) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void animationHeadMercy() {
        isAnimationHeadDone = true;
        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(image, 80, 520, 30, 28);
        frames[1] = new TextureRegion(image, 42, 520, 30, 28);
        frames[2] = new TextureRegion(image, 154, 520, 30, 28);
        frames[3] = new TextureRegion(image, 118, 520, 30, 28);
        animationHead = new Animation<>(1f, frames);

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void animationHeadMercy2() {
        isAnimationHeadDone = true;
        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(image, 42, 520, 30, 28);
        frames[1] = new TextureRegion(image, 154, 520, 30, 28);
        frames[2] = new TextureRegion(image, 118, 520, 30, 28);
        frames[3] = new TextureRegion(image, 191, 520, 30, 28);
        animationHead = new Animation<>(1f, frames);

        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
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

    public boolean isAnimationEvadeFinished() {
        return isAnimationVoidFinished;
    }

    public void setIsAnimationVoidFinishedFalse() {
        isAnimationVoidFinished = false;
    }

    public void activateAnimationEvade() {
        canAnimateEvadeAttack = true;
    }

    public boolean isCanAnimateEvadeAttack() {
        return canAnimateEvadeAttack;
    }

    public void animateVoidAttack() {
        if (canAnimateEvadeAttack) {
            switch (direction) {
                case BACKWARD -> {
                    float limitX = HALF_SCREEN_WIDTH - VH_WIDTH * 13;
                    head.setX(Math.max(head.getX() - 8, limitX)  );
                    body.setX(Math.max(body.getX() - 8, limitX)  );
                    legs.setX(Math.max(legs.getX() - 8, limitX)  );
                    if (head.getX() == limitX) {
                        direction = TOWARD;
                    }
                }
                case TOWARD -> {
                    head.setX(Math.min(head.getX() + 8, HALF_SCREEN_WIDTH)  );
                    body.setX(Math.min(body.getX() + 8, HALF_SCREEN_WIDTH)  );
                    legs.setX(Math.min(legs.getX() + 8, HALF_SCREEN_WIDTH)  );
                    if (head.getX() == HALF_SCREEN_WIDTH) {
                        direction = BACKWARD;
                        isAnimationVoidFinished = true;
                        canAnimateEvadeAttack = false;
                    }
                }
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta / 3;
        if (isInThirdFrame) {
            offset = (float) Math.sin(stateTime * 100) * 5; // Movimiento hacia los lados
        } else {
            offset = 0;
        }
    }

    public boolean isAnimationFinished() {
        if (animation==null) {
            return true;
        }
        return this.animation.isAnimationFinished(stateTime*4);
    }

    public boolean getIsAnimationHeadDone() {
        return isAnimationHeadDone;
    }

    public void setAnimationHeadFalse() {
        isAnimationHeadDone = false;
    }

    public boolean isAnimationWonDone() {
        if (animation == null && animationHead == null) {
            return true;
        }
        return isAnimationWonDone;
    }

    public boolean isAnimationHeadFinished() {
        if (animationHead == null) {
            return true;
        }
        return this.animationHead.isAnimationFinished(timeHead);
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
            float pendulumSwingX = (float) (2f * Math.sin(10f * stateTime / 2)) - 1.5f;
            float movementYBody = (float) (1.5f * Math.sin(20f * stateTime)) - 1.5f;
            if (act!=8) {
                if (isAnimationHeadFinished()) {
                    batch.draw(head, body.getX() + VH_WIDTH * 0.3f * getScaleX() - pendulumSwingXBody + 1 * getScaleY(),  -0.5f * VH_HEIGHT * getScaleY() + body.getY()+ (float) body.getRegionHeight() /2 + head.getRegionHeight() - movementY , head.getOriginX(), 0, head.getRegionWidth(), head.getRegionHeight(), getScaleY(), getScaleY(), 0);
                }
                else {
                    TextureRegion currentFrame = animationHead.getKeyFrame(timeHead, false);
                    batch.draw(currentFrame, body.getX() + VH_WIDTH * 0.3f * getScaleX() - pendulumSwingXBody + 1 * getScaleY(),  -0.5f * VH_HEIGHT * getScaleY() + body.getY()+ (float) body.getRegionHeight() /2 + head.getRegionHeight() - movementY , head.getOriginX(), 0, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), getScaleY(), getScaleY(), 0);
                }
                batch.draw(body, body.getX() - pendulumSwingX, body.getY() - (VH_HEIGHT * getScaleY() * 0.3f) - movementYBody , body.getOriginX(), body.getOriginY(), body.getRegionWidth(), body.getRegionHeight(), getScaleX(), getScaleY(), 0);
                batch.draw(legs, legs.getX() + (VH_WIDTH*1.9f/ getScaleX()), legs.getY() + VH_HEIGHT * 0.2f, legs.getOriginX(), legs.getOriginY(), legs.getRegionWidth(), legs.getRegionHeight(), getScaleX(), getScaleY(), 0);
            } else {
                if (animationBody.getKeyFrameIndex(timeHead) == 6 ) {
                    isAnimationWonDone = true;
                }
                TextureRegion currentBody = animationBody.getKeyFrame(timeHead, false);
                TextureRegion currentHead = animationHead.getKeyFrame(timeHead, false);
                TextureRegion currentLegs = animationLegs.getKeyFrame(timeHead, false);
                batch.draw(currentHead, body.getX() + VH_WIDTH * 0.4f * getScaleX() - pendulumSwingXBody + 1 * getScaleY()+ offset,  -0.5f * VH_HEIGHT * getScaleY() + body.getY()+ (float) body.getRegionHeight() /2 + head.getRegionHeight() - movementY , head.getOriginX(), 0, head.getRegionWidth(), head.getRegionHeight(), getScaleX(), getScaleY(), 0);
                batch.draw(currentBody, body.getX() - VH_WIDTH*0.2f + offset, body.getY() - (float) currentBody.getRegionHeight() /2 - VH_HEIGHT * 0.3f, (float) currentBody.getRegionWidth() /2, (float) currentBody.getRegionHeight()/2, currentBody.getRegionWidth(), currentBody.getRegionHeight(), getScaleX(), getScaleY(), 0);
                batch.draw(currentLegs, legs.getX() + offset , legs.getY() + VH_HEIGHT * 0.2f, legs.getOriginX(), legs.getOriginY(), currentLegs.getRegionWidth(), currentLegs.getRegionHeight(), getScaleX(), getScaleY(), 0);

            }
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



    public int advanceAnimation() {
        isInThirdFrame = false;
        if ((5 < animationBody.getKeyFrameIndex(timeHead) && animationBody.getKeyFrameIndex(timeHead) < 7) || ((0 <= animationBody.getKeyFrameIndex(timeHead) && animationBody.getKeyFrameIndex(timeHead) < 2))) {

            if (!Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                isOptionAvailable = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && isOptionAvailable) {
                isOptionAvailable = false;
                timeHead ++;
            }
        } else if (2 <= animationBody.getKeyFrameIndex(timeHead) && animationBody.getKeyFrameIndex(timeHead) <= 5) {
            if (animationBody.getKeyFrameIndex(timeHead) == 2){
                isInThirdFrame = true;
                inflictingDamageSound();
            }
            timeHead = (animationBody.getKeyFrameIndex(timeHead) == 2) ? timeHead + Gdx.graphics.getDeltaTime()/5 : timeHead + Gdx.graphics.getDeltaTime()/3;
        }
        return animationBody.getKeyFrameIndex(timeHead);
    }
}
