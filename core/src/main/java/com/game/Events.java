package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import listener.CollisionListener;
import static com.game.BlackScreen.*;
import static com.game.Sounds.selectSound;


public class Events {

    static int optionSelected = 0;
    public static boolean canSelect = true;

    public static void createTarget() {
        objectItems.add(new ObjetsItems(0, 260, 400));
    }

    public static void deleteObjects() {
        for (ObjetsItems objectItem: objectItems) {
            objectItem.delete();
        }
        objectItems.clear();
    }

    public static void selectTarget() {
        if (objectItems.isEmpty()) {
            createTarget();
            heart.setPositionSelectItem();
        }
        if ( CollisionListener.isCollided(heart, objectItems.getFirst()) && Gdx.input.isKeyPressed(Keys.ENTER) && canSelect) {
            deleteObjects();
            selectSound();
            heart.isTurn = false;
            heart.setPositionFight();
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            selectSound();
            deleteObjects();
            heart.setPositionFightOp();
            heart.setOption(0);
            optionSelected = 0;
            canSelect = false;
        }
    }

    public static void isFightSelected(boolean isOverFightOp) {
        fightOp.updateIsCollided(CollisionListener.isCollided(heart, fightOp));
        if (Gdx.input.isKeyPressed(Keys.ENTER) && isOverFightOp && canSelect) {
            selectSound();
            canSelect = false;
            optionSelected = 1;
        }
    }

    public static void isItemSelected(boolean isOverItem) {
        itemOp.updateIsCollided(CollisionListener.isCollided(heart, itemOp));
        if (Gdx.input.isKeyPressed(Keys.ENTER) && isOverItem && canSelect) {
            canSelect = false;
        }
    }

    public static void isActSelected(boolean isOverAct) {
        actOp.updateIsCollided(CollisionListener.isCollided(heart, actOp));
        if (Gdx.input.isKeyPressed(Keys.ENTER) && isOverAct && canSelect) {
            canSelect = false;
        }
    }

    public static void isMercySelected(boolean isOverMercy) {
        mercyOp.updateIsCollided(CollisionListener.isCollided(heart, mercyOp));
        if (Gdx.input.isKeyPressed(Keys.ENTER) && isOverMercy && canSelect) {
            canSelect = false;
        }
    }

    public static void detectEvent() {
        if (!Gdx.input.isKeyPressed(Keys.ENTER)  && !canSelect) { canSelect = true;  }
        switch (optionSelected) {
            case 1:
                selectTarget();
                break;
            case 2:
                break;
        }

        isFightSelected(CollisionListener.isCollided(heart, fightOp));
        isItemSelected(CollisionListener.isCollided(heart, itemOp));
        isActSelected(CollisionListener.isCollided(heart, actOp));
        isMercySelected(CollisionListener.isCollided(heart,mercyOp));
    }
}
