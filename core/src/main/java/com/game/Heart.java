package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.game.BlackScreen.*;
import static com.game.Sounds.selectSound;

public class Heart extends Actor {

    private final Circle hitBox;

    private float seconds = 0;

    public int option = 0;

    public boolean isTurn = true;

    private float hp = 90, angle = 0;

    private float friction = 0;
    private boolean onFloor = false;
    public static boolean canMoveRight = true, canMoveLeft = true;
    private final Sprite sprite;

    private int iterator = 0;

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public void setOption(int op) {
        this.option = op;
    }

    public Heart(Texture image, float xPos, float yPos) {
        setX(xPos); setY(yPos);
        this.sprite = new Sprite(image);
        this.sprite.setRegion(7,6, 16, 16);
        hitBox = new Circle(getX(), getY(),  ((this.sprite.getRegionWidth() -1) * 2f)/2);
    }

    public void setColorRed() {
        this.sprite.setRegion(7,6, 16, 16);
    }

    public void setColorBlue() {
        this.sprite.setRegion(62,6, 16, 16);
    }

    public Circle getHitBox() {
        return this.hitBox;
    }

    public void updateHitBox() {
        hitBox.setX(getX());
        hitBox.setY(getY());
    }

    public void setPositionFightOp() {
        Events.optionSelected = 0;
        this.option = 0;
        this.setX(fightOp.getX() + 25);
        this.setY(fightOp.getY() + 17);
        updateHitBox();
    }

    public void setPositionFight() {
        this.setX(boxHeart.getX() + boxHeart.getWidth()/2);
        this.setY(boxHeart.getY() + boxHeart.getHeight()/2);
        updateHitBox();
    }

    public void setPositionSelectItem() {
        this.setX(220); this.setY(410); this.option = 3;
        updateHitBox();
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        BlackScreen.batch.begin();
        BlackScreen.batch.draw(sprite,  getX(), getY(), (float) sprite.getRegionWidth() /2, (float) sprite.getRegionWidth() /2, sprite.getRegionWidth(), sprite.getRegionHeight(), 2f, 2f, angle);
        switch (option) {
            case 0:
                this.moveOptions(fightOp, actOp, itemOp, mercyOp);
                break;
            case 1:
                this.move();
                break;
            case 2:
                this.moveGravityApplied();
        }
        updateHitBox();
        BlackScreen.batch.end();
    }


    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && getX() < boxHeart.getX() + boxHeart.getWidth() - sprite.getRegionWidth() - 15) {
           setX(getX() + 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && getX() > boxHeart.getX() + sprite.getRegionWidth()) {
            setX(getX() - 5); // Math.max() , boxHeart.getX() + 7 - image.getWidth() * 0.5f));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)  && getY() < boxHeart.getY() + boxHeart.getHeight() - sprite.getRegionHeight() - 15) {
            setY(getY() + 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && getY()  > boxHeart.getY() + sprite.getRegionHeight() ) {
            setY(getY() - 5);
        }
    }

    public void moveOptions(FightOp fightOp, ActOp actOp, ItemOp itemOp, MercyOp mercyOp) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && canMoveRight) {
            selectSound();
            if (iterator++ < 3) {
                switch (iterator) {
                    case 0:
                        setX(fightOp.getX() + 25); setY(fightOp.getY() + 17);
                        break;
                    case 1:
                        setX(actOp.getX() + 30); setY(actOp.getY() + 17);
                        break;
                    case 2:
                        setX(itemOp.getX() + 25); setY(itemOp.getY() + 17);
                        break;
                    case 3:
                        setX(mercyOp.getX() + 25); setY(mercyOp.getY() + 17);
                        break;
                }
            }else {
                iterator = 0;
                setX(fightOp.getX() + 25); setY(fightOp.getY() + 17);
            }
            canMoveRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canMoveLeft) {
            selectSound();
            if (iterator-- > 0) {
                switch (iterator) {
                    case 0:
                        setX(fightOp.getX() + 25); setY(fightOp.getY() + 17);
                        break;
                    case 1:
                        setX(actOp.getX() + 30); setY(actOp.getY() + 17);
                        break;
                    case 2:
                        setX(itemOp.getX() + 25); setY(itemOp.getY() + 17);
                        break;
                }
            }
            else {
                iterator = 3;
                setX(mercyOp.getX() + 25); setY(mercyOp.getY() + 17);
            }
            canMoveLeft = false;
        }

    }


    public void moveGravityApplied() {
        heart.setColorBlue();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setX(Math.min(getX() + 4, boxHeart.getX() - 30 + boxHeart.getWidth()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(Math.max(getX() - 4, boxHeart.getX() + 14));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && getY() < boxHeart.getY() + boxHeart.getHeight() - 35 && onFloor) {
            this.seconds += Gdx.graphics.getDeltaTime();
            friction += (-0.08f * this.seconds);
            setY(Math.max(getY() + 4 + friction, boxHeart.getY() + 14));
        } else if (getY() > boxHeart.getY() + 14) {
            onFloor = false;
        }
        if (!onFloor && getY() > boxHeart.getY() + 14) {
            setY(Math.max(getY() - 4 + friction, boxHeart.getY() + 14));
        }
        if ( getY() == boxHeart.getY() + 14)
        {
            onFloor = true;
            friction = 0;
            this.seconds = 0;
        }
    }
}
