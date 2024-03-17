package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import listener.CollisionListener;

import static com.game.BlackScreen.*;
import static com.game.Sounds.soulDamaged;

public class ObjetsItems extends Options{
    private Rectangle hitBox;

    private boolean startFadeOut = false;

    private float opacity = 1f;

    private static final int[] valuesX = { 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,6, 6, 6, 55, 55, 55, 55,104, 104 , 104, 104, 104, 104 , 149, 149, 149, 197, 197,  245, 245, 245, 245, 245, 197, 197, 149, 149};
    private int index = 0;
    private boolean isReverseSense = false;
    Sprite imageObject;

    public void setIsReverseSense(boolean sense) {
        isReverseSense = sense;
    }

    public boolean getSense() {
        return this.isReverseSense;
    }

    public ObjetsItems(int kind, float x, float y) {
        switch (kind) {
            case 0:
                // Word sans
                this.imageObject = new Sprite(new Texture("images/Sans.png"));
                setX(x); setY(y);
                hitBox = new Rectangle(getX() - 40, getY(), getWidth() * 0.2f, getHeight());
                break;
            case 1:
                // small bone
                this.imageObject = new Sprite(new Texture("images/SansSprite.png"));
                this.imageObject.setRegion( 398 , 693 , 12, 52);
                setOriginX(getWidth()/2); setOriginY(getHeight()/2);
                setX(x); setY(y);
                hitBox = new Rectangle( getX() - getWidth() / 2, getY() - getHeight()/2, getWidth()*2.5f-5, getHeight()-8);
                break;
            case 2:
                // long bone
                this.imageObject = new Sprite(new Texture("images/SansSprite.png"));
                this.imageObject.setRegion( 278 , 573 , 12, 202);
                setOriginX(getWidth()/2); setOriginY(getHeight()/2);
                setX(x); setY(y);
                hitBox = new Rectangle( getX() - getWidth() / 2,  getY() - getHeight() / 2, (getWidth()*2.5f)-4, getHeight()-4);
                break;
            case 3:
                // long bones vertical
                this.imageObject = new Sprite(new Texture("images/SansSprite.png"));
                this.imageObject.setRegion( 398 , 573 , 204, 102);
                setX(x); setY(y);
                hitBox = new Rectangle(getX() , getY() , (getWidth()*2.5f)-4, getHeight()*2f-4);
                break;
            case 4:
                // long bones horizontal
                this.imageObject = new Sprite(new Texture("images/SansSprite.png"));
                this.imageObject.setRegion( 293 , 573 , 102, 204);
                setX(x); setY(y);
                hitBox = new Rectangle( getX() , getY() , (getWidth()*2.5f)-4, getHeight()*2f-4);
                break;
            case 5:
                // Ga--------------------------------------ster Blaster opened
                this.imageObject = new Sprite(new Texture("images/SansSprite.png"));
                // Gaster Blaster opened left eye   : this.imageObject.setRegion( 245 , 861 , 44, 57);
                // Gaster Blaster opened right eye  : this.imageObject.setRegion( 197 , 861 , 44, 57);
                // Gaster Blaster opened            : this.imageObject.setRegion( 149 , 861 , 44, 57);
                // Gaster Blaster closed small      : this.imageObject.setRegion( 104 , 861 , 44, 57);
                // Gaster Blaster closed medium     : this.imageObject.setRegion( 55 , 861 , 44, 57);
                // Gaster Blaster almost opened     : this.imageObject.setRegion( 6 , 861 , 44, 57);
                this.imageObject.setRegion( 104 , 861 , 44, 57);
                setOriginX(getWidth()/2); setOriginY(getHeight()/2);
                setX(x); setY(y);

                hitBox = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, (getWidth()*2.5f)-4, getHeight()*2f-4);
                break;
        }

    }

    public void drawAttackerGasterBlaster(int valueX, float scale) {
        float heightIncrease = 0;
        if (valueX == 149) {
            heightIncrease = 16*scale;
        } else if (valueX == 197) {
            heightIncrease = 24*scale;
        } else if (valueX == 245) {
            heightIncrease = 26*scale;
        } else {
            startFadeOut = true;
        }

        if (startFadeOut && opacity > 0) {
            opacity -= Gdx.graphics.getDeltaTime() / 2;
            if (opacity < 0) {
                opacity = 0;
            }
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(1, 1, 1, opacity));
        float width = Gdx.graphics.getWidth();
        shapeRenderer.rect(0, boxHeart.getY() + 13 - heightIncrease, width - imageObject.getOriginX(), 2 * heightIncrease);
        if (CollisionListener.isCollided(heart, new Rectangle(0, boxHeart.getY() + 13 - heightIncrease, width - imageObject.getOriginX(), 2 * heightIncrease))) {
            soulDamaged();
            heart.setHp(heart.getHp() - 1.0f);
        }
        shapeRenderer.end();
    }

    public void gasterBlasterAttack(float scale) {
        if (valuesX[index] >= 149) {
            drawAttackerGasterBlaster(valuesX[index], scale);
        }
        this.imageObject.setRegion( valuesX[index], 861 , 44, 57);
        if (index < valuesX.length - 1) {
            index ++;
        }
        else {
            index = valuesX.length - 14;
        }

    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public float getWidth() {
        return this.imageObject.getRegionWidth();
    }

    @Override
    public float getHeight() {
        return this.imageObject.getRegionHeight();
    }

    public void draw() {
        batch.begin();
        batch.draw(imageObject,  getX(), getY(), imageObject.getWidth()  * 0.2f , imageObject.getHeight() * 0.2f);
        batch.end();
    }

    public void drawBone() {
        batch.begin();
        batch.draw(imageObject, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 2.5f, 1f, 0);
        batch.end();
    }

    public void drawBoneTab() {
        batch.begin();
        batch.draw(imageObject,  getX(), getY(), getWidth()*2.5f, getHeight()*2f) ;
        batch.end();
    }

    public void drawGhosterBlaster(float angle, float scale) {
        batch.begin();
        batch.draw(imageObject, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), scale + .5f, scale, angle);
        batch.end();
    }


    public void updateHitBox() {
        hitBox.setX(getX());
        hitBox.setY(getY());
    }

    public void moveLeft() {
        setX(getX() - 7);
        updateHitBox();
    }

    public void moveLeftFast(float min) {
        setX(Math.max(getX() - 15 , min));
        updateHitBox();
    }

    public void moveRightFast(float max) {
        setX( Math.min( getX() + 15 , max));
        updateHitBox();
    }

    public void moveDownFast(float min) {
        setY(Math.max(getY() - 15 , min) );
        updateHitBox();
    }

    public void moveUpFast(float max) {
        setY(Math.min(getY() + 15 , max) );
        updateHitBox();
    }

    public void moveRight() {
        setX(getX() + 7);
        updateHitBox();
    }

    public void delete() {
        this.imageObject.getTexture().dispose();
    }

}
