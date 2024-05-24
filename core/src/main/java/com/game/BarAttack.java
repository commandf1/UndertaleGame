package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.boxHeart;
import static com.game.Direction.BACKWARD;
import static com.game.Direction.TOWARD;

public class BarAttack extends Actor {
    private final Sprite barAttackImage;

    private Animation<TextureRegion> animation;

    private float stateTime = 0;

    private boolean canMove = true;

    private final Direction direction;

    private boolean canAnimate = false;

    private final Rectangle hitBox;

    private int speedBar;

    public BarAttack(float y) {
        barAttackImage = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
        barAttackImage.setRegion(1139, 23,14,128);
        barAttackImage.setOriginCenter();
        setSize(barAttackImage.getRegionWidth(), boxHeart.getHeight() - 2 * VH_HEIGHT);

        if (random.nextInt(2) == 1) {
            direction = TOWARD;
            barAttackImage.setPosition(boxHeart.getX(), y);
        } else {
            direction = BACKWARD;
            barAttackImage.setPosition(boxHeart.getX() + boxHeart.getWidth(), y);
        }
        hitBox = new Rectangle(barAttackImage.getX(), barAttackImage.getY(), getWidth(), getHeight());

    }

    public void setSpeedBar() {
        speedBar = random.nextInt(1,11);
    }

    private void updateHitBox() {
        hitBox.setPosition(barAttackImage.getX(), barAttackImage.getY());
    }

    @Override
    public float getX() {
        return barAttackImage.getX();
    }

    public void verifyRegion() {
        if (barAttackImage.getTexture() == null) {
            barAttackImage.setTexture(new Texture(Gdx.files.internal("images/HitSprite.png")));
            barAttackImage.setRegion(13, 30, 546, 115);
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

    private void useFullTextureRegion() {
        Texture texture = barAttackImage.getTexture();
        barAttackImage.setRegion(0, 0, texture.getWidth(), texture.getHeight());
    }

    public void animationBar() {
        useFullTextureRegion();
        canAnimate = true;
        TextureRegion[] frames = new TextureRegion[2];
        frames[0] = new TextureRegion(barAttackImage, 1139, 23, 14, 128);
        frames[1] = new TextureRegion(barAttackImage, 1158, 23, 14, 128);
        animation = new Animation<>(0.1f, frames);
        stateTime = 0;
        for (TextureRegion region : frames) {
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public void move() {
        if (canMove) {
            if (direction == TOWARD) {
                barAttackImage.setX(Math.min(barAttackImage.getX() + speedBar, boxHeart.getX() + boxHeart.getWidth()));
                if (barAttackImage.getX() ==
                    boxHeart.getX() + boxHeart.getWidth()) {
                    remove();
                }
            } else {
                barAttackImage.setX(Math.max(barAttackImage.getX() - speedBar, boxHeart.getX()));
                if (barAttackImage.getX() == boxHeart.getX()) {
                    remove();
                }
            }
            updateHitBox();
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void stop() {
        canMove = false;
        animationBar();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (!canAnimate) {

            batch.draw(barAttackImage,  barAttackImage.getX(), barAttackImage.getY(), barAttackImage.getOriginX(), barAttackImage.getOriginY(), barAttackImage.getRegionWidth(), getHeight(), 1f, 1f, 0);
            return;
        }
        TextureRegion currentFrame = animation.getKeyFrame(stateTime*5, true);
        batch.draw(currentFrame, barAttackImage.getX() , barAttackImage.getY(), (float) currentFrame.getRegionWidth() /2, getHeight() /2, currentFrame.getRegionWidth(), getHeight(), 1f, 1f, 0);

    }

    public void dispose() {
        barAttackImage.getTexture().dispose();
        remove();
    }
}
