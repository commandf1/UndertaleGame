package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.game.Events.*;
import static com.game.Undertale.stage;
import static com.game.BattleController.getDirection;
import static com.game.BlackScreen.*;
import static com.game.Sounds.selectSound;
import static com.game.Sounds.soulShatterSound;

public class Heart extends Actor {

    private final Circle hitBox;

    private float seconds = 0;

    public int option = 0;

    public boolean isTurn = true;

    private float hp = 90, angle = 0, static_y = 0;

    private float friction = 0;
    private boolean onFloor = false;

    public static boolean canMoveRight = true, canMoveLeft = true;
    private final Sprite sprite;

    private int iterator = 0;

    public static int iteratorItems = 0;

    public void setStatic_y(float static_y) {
        this.static_y = static_y;
    }

    public float getYStatic() {
        return this.static_y;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public void setOption(int op) {
        this.option = op;
    }

    public int getIteratorItems() {
        return iteratorItems;
    }

    public Heart(Texture image, float xPos, float yPos) {
        setX(xPos); setY(yPos);
        this.sprite = new Sprite(image);
        this.sprite.setRegion(7,6, 16, 16);

        hitBox = new Circle(getX(), getY(),  ((this.sprite.getRegionWidth() -1) * 2f)/2);
    }

    public void setBrokenHeart() {
        this.sprite.setRegion(7,114, 22, 17);
        soulShatterSound();
    }

    public Sprite getSprite() {
        return sprite;
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
        this.option = 0;
        this.setX(fightOp.getX() + VH_WIDTH * 2);
        this.setY(fightOp.getY() + fightOp.getHeight()/2 - VH_HEIGHT * getScaleX());
        updateHitBox();
    }

    public void setPositionActOp() {
        this.option = 0;
        this.setX(actOp.getX() + VH_WIDTH * 2);
        this.setY(actOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
        updateHitBox();
    }

    public void setPositionItemOp() {
        if (this.getStage() == null) {
            stage.addActor(this);
        }
        this.option = 0;
        this.setX(itemOp.getX() + VH_WIDTH * 2);
        this.setY(itemOp.getY() + itemOp.getHeight()/2 - VH_HEIGHT * getScaleX());
        updateHitBox();
    }

    public void setPositionMercyOp() {
        this.option = 0;
        this.setX(mercyOp.getX() + VH_WIDTH * 2);
        this.setY(mercyOp.getY() + mercyOp.getHeight()/2 - VH_HEIGHT * getScaleX());
        updateHitBox();

    }

    public void setPositionFight() {
        stage.addActor(this);
        this.setX(boxHeart.getX() + boxHeart.getWidth()/2);
        this.setY(boxHeart.getY() + boxHeart.getHeight()/2);
        updateHitBox();
    }

    public void setPositionSelectTarget() {
        this.setX(labelSans.getX() - VH_WIDTH * 3);
        this.setY(labelSans.getY() + this.sprite.getRegionHeight() + VH_HEIGHT);
        this.option = -1;
        updateHitBox();
    }

    public void setPositionSelectItem() {
        if (!getItems().isEmpty()) {
            iteratorItems = 0;
            LabelMessage labelMessage = getItems().getFirst();
            this.setX(labelMessage.getX() - VH_WIDTH * 3);
            this.setY(labelMessage.getY() + this.sprite.getRegionHeight() + VH_HEIGHT);
            this.option = -1;
        }
        updateHitBox();
    }

    public void moveOptionsItems() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && canMoveRight) {
            selectSound();
            LabelMessage item;
            if (iteratorItems++ >= items.size() - 1) {
                iteratorItems = 0;
            }
            item = getItems().get(iteratorItems);
            this.setX(item.getX() - VH_WIDTH * 3);
            this.setY(item.getY() + this.sprite.getRegionHeight() + VH_HEIGHT);
            canMoveRight = false;

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && canMoveLeft) {
            selectSound();
            LabelMessage item;
            if (iteratorItems-- <= 0) {
                iteratorItems = items.size() - 1;
            }
            item = getItems().get(iteratorItems);
            this.setX(item.getX() - VH_WIDTH * 3);
            this.setY(item.getY() + this.sprite.getRegionHeight() + VH_HEIGHT);
            canMoveLeft = false;
        }
        updateHitBox();
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite,  getX(), getY(), (float) sprite.getRegionWidth() /2, (float) sprite.getRegionWidth() /2, sprite.getRegionWidth(), sprite.getRegionHeight(), 2f, 2f, angle);
        switch (option) {
            case 0 -> this.moveOptions(fightOp, actOp, itemOp, mercyOp);
            case 1 -> this.move();
            case 2 -> this.moveGravityApplied();
            case 3 -> this.moveGravityAppliedThrew();
            case 4 -> this.moveOptionsItems();
        }
        updateHitBox();
    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
           setX(Math.min(getX() + 5, boxHeart.getX() + boxHeart.getWidth() - sprite.getRegionWidth() - 15));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(Math.max(getX() - 5, boxHeart.getX() + sprite.getRegionWidth()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            setY(Math.min(getY() + 5, boxHeart.getY() + boxHeart.getHeight() - sprite.getRegionHeight() - 15));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            setY(Math.max(getY() - 5, boxHeart.getY() + sprite.getRegionHeight()));
        }
    }

    public void moveOptions(FightOp fightOp, ActOp actOp, ItemOp itemOp, MercyOp mercyOp) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && canMoveRight) {
            selectSound();
            if (iterator++ < 3) {
                switch (iterator) {
                    case 0:
                        setX(fightOp.getX() + VH_WIDTH * 2); setY(fightOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                    case 1:
                        setX(actOp.getX() + VH_WIDTH * 2); setY(actOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                    case 2:
                        setX(itemOp.getX() + VH_WIDTH * 2); setY(itemOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                    case 3:
                        setX(mercyOp.getX() + VH_WIDTH * 2); setY(mercyOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                }
            }else {
                iterator = 0;
                setX(fightOp.getX() + VH_WIDTH * 2); setY(fightOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
            }
            canMoveRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canMoveLeft) {
            selectSound();
            if (iterator-- > 0) {
                switch (iterator) {
                    case 0:
                        setX(fightOp.getX() + VH_WIDTH * 2); setY(fightOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                    case 1:
                        setX(actOp.getX() + VH_WIDTH * 2); setY(actOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                    case 2:
                        setX(itemOp.getX() + VH_WIDTH * 2); setY(itemOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
                        break;
                }
            }
            else {
                iterator = 3;
                setX(mercyOp.getX() + VH_WIDTH * 2); setY(mercyOp.getY() + actOp.getHeight()/2 - VH_HEIGHT * getScaleX());
            }
            canMoveLeft = false;
        }
    }

    public void moveGravityApplied() {
        heart.setColorBlue();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setX(Math.min(getX() + 5,  boxHeart.getX() + boxHeart.getWidth() - sprite.getRegionWidth() - 15));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(Math.max(getX() - 5, boxHeart.getX() + sprite.getRegionWidth()));
        }
        if ( getY() == boxHeart.getY() + sprite.getRegionHeight() || static_y != 0)
        {
            onFloor = true;
            friction = 0;
            this.seconds = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && getY() < boxHeart.getY() + boxHeart.getHeight() - 35 && (onFloor || static_y != 0)) {
            this.seconds += Gdx.graphics.getDeltaTime();
            friction += (-0.08f * this.seconds);
            setY(Math.max(Math.max(Math.min(getY() + 5 + friction, boxHeart.getY() + boxHeart.getHeight() - sprite.getRegionHeight() - 15), static_y), boxHeart.getY() + sprite.getRegionHeight() ));
        } else if (getY() > boxHeart.getY() + 14 && static_y == 0) {
            onFloor = false;
        }
        if (!onFloor && getY() > boxHeart.getY() + sprite.getRegionHeight()) {
            setY( Math.max(Math.max(getY() - 5 + friction, boxHeart.getY() + sprite.getRegionHeight()), static_y));
        }

    }

    public void moveGravityAppliedThrew() {
        heart.setColorBlue();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setX(Math.min(getX() + 4,  boxHeart.getX() + boxHeart.getWidth() - sprite.getRegionWidth() - 15));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(Math.max(getX() - 4, boxHeart.getX() + sprite.getRegionWidth()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            setY(Math.max(getY() - 4 + friction, boxHeart.getY() + sprite.getRegionHeight()));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && getY() < boxHeart.getY() + boxHeart.getHeight() - 35 && onFloor) {

            this.seconds += Gdx.graphics.getDeltaTime();
            friction += (-0.08f * this.seconds);

            setY(Math.min(getY() + 4, boxHeart.getY() + boxHeart.getHeight() - sprite.getRegionHeight() - 15));
            switch (getDirection()) {
                case 1 -> {

                }
                case 2 -> setY(Math.max(getY() + friction, boxHeart.getY() + sprite.getRegionHeight()));
            }
        } else {
            switch (getDirection()) {
                case 1 -> {

                }
                case 2 -> {
                    if (getY() > boxHeart.getY() + sprite.getRegionHeight()) {
                        onFloor = false;
                    }
                }
            }
        }

        switch (getDirection()) {
            case 0 -> {

            }
            case 2-> {
                if (!onFloor && getY() > boxHeart.getY() + sprite.getRegionHeight()) {
                    setY(Math.max(getY() - 4 + friction, boxHeart.getY() + sprite.getRegionHeight()));
                }
                if ( getY() == boxHeart.getY() + sprite.getRegionHeight())
                {
                    onFloor = true;
                    friction = 0;
                    this.seconds = 0;
                }
            }


        }


    }
}
