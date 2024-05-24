package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;

public class BoxAttack extends Actor {
    private final Sprite boxAttack;

    private final Rectangle hitBoxCenter, hitBoxGreenLeft, hitBoxGreenRight, hitBoxYellowLeft, hitBoxYellowRight, hitBoxRedLeft, hitBoxRedRight;

    public BoxAttack(float x, float y, float width, float height) {
        boxAttack = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
        boxAttack.setRegion(13, 30, 546, 115);
        setSize(width - 7 * VH_WIDTH, height- 5 * VH_HEIGHT);
        boxAttack.setOriginCenter();
        boxAttack.setPosition(getX(), getY());

        setOrigin((float) boxAttack.getRegionWidth() /2, (float) boxAttack.getRegionHeight() /2);
        setPosition(x - getWidth()/2,y - getHeight()/2);
        hitBoxCenter = new Rectangle(x - VH_WIDTH, y, 2 * VH_WIDTH, getHeight() );
        hitBoxGreenLeft = new Rectangle(hitBoxCenter.getX() - 2.5f * VH_WIDTH, y, 2.5f * VH_WIDTH, getHeight());
        hitBoxGreenRight = new Rectangle(hitBoxCenter.getX() + hitBoxCenter.getWidth(), y, 2.5f * VH_WIDTH, getHeight());

        hitBoxYellowLeft = new Rectangle(hitBoxGreenLeft.getX() - 12f * VH_WIDTH, y, 12f * VH_WIDTH, getHeight());
        hitBoxYellowRight = new Rectangle(hitBoxGreenRight.getX() + hitBoxGreenRight.getWidth(), y, 12f * VH_WIDTH, getHeight());

        hitBoxRedLeft = new Rectangle(hitBoxYellowLeft.getX() - 17f * VH_WIDTH, y, 17f * VH_WIDTH, getHeight());
        hitBoxRedRight = new Rectangle(hitBoxYellowRight.getX() + hitBoxYellowRight.getWidth(), y, 17f * VH_WIDTH, getHeight());

    }

    public Rectangle getHitBoxCenter() {
        return hitBoxCenter;
    }

    public Rectangle getHitBoxGreenLeft() {
        return hitBoxGreenLeft;
    }

    public Rectangle getHitBoxGreenRight() {
        return hitBoxGreenRight;
    }

    public Rectangle getHitBoxYellowLeft() {
        return hitBoxYellowLeft;
    }

    public Rectangle getHitBoxYellowRight() {
        return hitBoxYellowRight;
    }

    public Rectangle getHitBoxRedLeft() {
        return hitBoxRedLeft;
    }

    public Rectangle getHitBoxRedRight() {
        return hitBoxRedRight;
    }

    public void dispose() {
        boxAttack.getTexture().dispose();
        remove();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(boxAttack,  getX(), getY(), (float) boxAttack.getRegionWidth() /2, (float) boxAttack.getRegionHeight() /2, getWidth(), getHeight(),  1, 1, 0);
    }
}
