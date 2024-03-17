package com.game;

import com.badlogic.gdx.Gdx;
import listener.CollisionListener;

import java.util.ArrayList;
import java.util.Iterator;

import static com.game.BlackScreen.*;
import static com.game.Sounds.alertSound;
import static com.game.Sounds.soulDamaged;

public class BattleController {
    static int act = 1, nextAttack = 0;
    static boolean canAdd = true;

    static ArrayList<ObjetsItems> bonesLeft = new ArrayList<>();
    static ArrayList<ObjetsItems> bonesRight = new ArrayList<>();

    static ArrayList<ObjetsItems> bonesUp = new ArrayList<>();

    static ArrayList<ObjetsItems> bonesDown = new ArrayList<>();


    static float secondsTime = 0, secondTimeBetweenTransitions = 0;


    static void generateBonesTab(int direction)
    {
        switch (direction) {
            case 0:
                bonesUp.add(new ObjetsItems(3, boxHeart.getX(), boxHeart.getY() + boxHeart.getHeight()));
                break;
            case 1:
                bonesRight.add(new ObjetsItems(4, boxHeart.getX() + boxHeart.getWidth(), boxHeart.getY()));
                break;
            case 2:
                bonesDown.add(new ObjetsItems(3, boxHeart.getX() , boxHeart.getY() - 204));
                break;
            case 3:
                bonesLeft.add(new ObjetsItems(4, boxHeart.getX() - 250, boxHeart.getY()));
                break;
        }
    }

    static void bonesTab(boolean isGeneratedRight, boolean isGeneratedLeft, boolean isGeneratedDown, boolean isGeneratedUp, float extension) {
        if (boxHeart.getX() == boxHeart.X_POS_MAX_SQUARE && boxHeart.getWidth() == boxHeart.MAX_HEIGHT_SQUARE) {
            secondTimeBetweenTransitions += Gdx.graphics.getDeltaTime();
            if (secondTimeBetweenTransitions < 1 ) {
                canAdd = true;
                if (isGeneratedRight) {
                    new AlertBox(extension, boxHeart.getHeight() - 20, boxHeart.getX() + boxHeart.getWidth() - 10 - extension, boxHeart.getY() + 10).draw(batch, 1);
                }
                if (isGeneratedLeft) {
                    new AlertBox( extension, boxHeart.getHeight() - 20, boxHeart.getX() + 10, boxHeart.getY() + 10).draw(batch, 1);
                }
                if (isGeneratedUp) {
                    new AlertBox(boxHeart.getWidth() - 20, extension - 10, boxHeart.getX() + 10, boxHeart.getY() + boxHeart.getHeight() - extension).draw(batch, 1);
                }
                if (isGeneratedDown) {
                    new AlertBox(boxHeart.getWidth() - 20, extension - 10, boxHeart.getX() + 10, boxHeart.getY() + 10).draw(batch, 1);
                }
            } else if (secondTimeBetweenTransitions > 1) {
                if (isGeneratedRight) {
                    if (bonesRight.isEmpty() && canAdd) {
                        alertSound();
                        generateBonesTab(1);
                    }
                    moveBonesLeftFast(extension);
                }
                if (isGeneratedLeft) {
                    if (bonesLeft.isEmpty() && canAdd) {
                        alertSound();
                        generateBonesTab(3);
                    }
                    moveBonesRightFast();
                }
                if (isGeneratedUp) {
                    if (bonesUp.isEmpty() && canAdd) {
                        alertSound();
                        generateBonesTab(0);
                    }
                    moveBonesDownFast(extension);
                }
                if (isGeneratedDown) {
                    if (bonesDown.isEmpty() && canAdd) {
                        alertSound();
                        generateBonesTab(2);
                    }
                    moveBonesUpFast();
                }
                canAdd = false;
            }
        }
    }

    static void setConfigTurnPlayer() {
        heart.isTurn = true;
        heart.setPositionFightOp();
        heart.setColorRed();
        secondTimeBetweenTransitions = secondsTime = 0;
        act ++;
        deleteBones();
    }

    static void generateBones() {
        secondsTime+= Gdx.graphics.getDeltaTime();
        secondTimeBetweenTransitions += Gdx.graphics.getDeltaTime();
        if ( secondTimeBetweenTransitions < 0.7) {
            canAdd = true;
        } else if (canAdd && secondTimeBetweenTransitions > 0.7) {
            bonesLeft.add(new ObjetsItems(1,boxHeart.getX() + boxHeart.getWidth(), boxHeart.getY() - 20));
            bonesLeft.add(new ObjetsItems(2,boxHeart.getX() + boxHeart.getWidth(), boxHeart.getY() + boxHeart.getHeight()/2 - 30));
            bonesRight.add(new ObjetsItems(2, boxHeart.getX() - 20 , boxHeart.getY() + boxHeart.getHeight()/2 - 30));
            bonesRight.add(new ObjetsItems(1, boxHeart.getX() - 20 , boxHeart.getY() - 20));
            canAdd = false;
            secondTimeBetweenTransitions = 0;
        }
        moveBonesLeft();
        moveBonesRight();
        if (secondsTime > 8) {
            setConfigTurnPlayer();
        }

    }

    static void gasterBlasterAttack(float scale) {
        if ( !bonesLeft.isEmpty())
        {
            for (ObjetsItems gasterBlaster: bonesLeft) {
                gasterBlaster.gasterBlasterAttack(scale);
            }
        }
        if ( !bonesRight.isEmpty())
        {
            for (ObjetsItems gasterBlaster: bonesRight) {
                gasterBlaster.gasterBlasterAttack(scale);
            }
        }
    }

    static void generateGastersBlaster() {
        secondsTime+= Gdx.graphics.getDeltaTime();
        secondTimeBetweenTransitions += Gdx.graphics.getDeltaTime();
        if ( secondTimeBetweenTransitions < 0.7) {
            canAdd = true;
        } else if (canAdd && secondTimeBetweenTransitions > 0.7) {
            bonesLeft.add(new ObjetsItems(5,boxHeart.getX() + boxHeart.getWidth() + 40, boxHeart.getY()-20));
            canAdd = false;
        }
        if (!bonesLeft.isEmpty() ) {
            gasterBlasterAttack((float) 4.0);
        }
        if (secondsTime > 20) {
            setConfigTurnPlayer();
            deleteBones();
            secondTimeBetweenTransitions = 0;
        }

    }

    public static void deleteBones() {
        Iterator<ObjetsItems> iteratorLeft = bonesLeft.iterator();
        Iterator<ObjetsItems> iteratorRight = bonesRight.iterator();
        Iterator<ObjetsItems> iteratorUp = bonesUp.iterator();
        Iterator<ObjetsItems> iteratorDown = bonesUp.iterator();

        while (iteratorLeft.hasNext() && iteratorRight.hasNext()) {
            ObjetsItems bone = iteratorLeft.next();
            bone.delete();
            iteratorLeft.remove();
        }
        while (iteratorRight.hasNext()) {
            ObjetsItems bone = iteratorRight.next();
            bone.delete();
            iteratorRight.remove();
        }
        while (iteratorUp.hasNext()) {
            ObjetsItems bone = iteratorUp.next();
            bone.delete();
            iteratorUp.remove();
        }
        while (iteratorDown.hasNext()) {
            ObjetsItems bone = iteratorDown.next();
            bone.delete();
            iteratorDown.remove();
        }
    }


    public static void drawBones() {
        for(ObjetsItems bone : bonesLeft) {
            bone.drawBone();
        }
        for(ObjetsItems bone : bonesRight ) {
            bone.drawBone();
        }
    }

    public static void drawBonesTab() {
        for(ObjetsItems bone : bonesLeft) {
            bone.drawBoneTab();
        }
        for(ObjetsItems bone : bonesRight ) {
            bone.drawBoneTab();
        }
        for(ObjetsItems bone : bonesUp ) {
            bone.drawBoneTab();
        }
        for(ObjetsItems bone : bonesDown ) {
            bone.drawBoneTab();
        }
    }

    public static void drawGasterBlaster(float scale) {
        for(ObjetsItems bone : bonesLeft) {
            bone.drawGhosterBlaster(-90, scale);
        }
        for(ObjetsItems bone : bonesRight ) {
            bone.drawGhosterBlaster(90, scale);
        }
    }

    static void moveBonesLeft() {
        for (Iterator<ObjetsItems> iterator = bonesLeft.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            bone.moveLeft();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 0.1f);
            }
            if (bone.getX() < boxHeart.getX() - 30) {
                bone.delete();
                iterator.remove();
            }
        }
    }

    static void moveBonesLeftFast(float extension) {
        for (Iterator<ObjetsItems> iterator = bonesRight.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 5f);
            }
            if(bone.getX() > boxHeart.getX() + boxHeart.getWidth() - 10 - extension && !bone.getSense()) {
                bone.moveLeftFast(boxHeart.getX() + boxHeart.getWidth() - 10 - extension);
            } else {
                bone.setIsReverseSense(true);
            }
            if (bone.getX() < boxHeart.getX() + boxHeart.getWidth() && bone.getSense()) {
                bone.moveRightFast(boxHeart.getX() + boxHeart.getWidth());
                if (bone.getX() >= boxHeart.getX() + boxHeart.getWidth() ) {
                    bone.delete();
                    iterator.remove();
                    if (bonesDown.isEmpty() && bonesUp.isEmpty() && bonesLeft.isEmpty() && bonesRight.isEmpty()) {
                        secondTimeBetweenTransitions = 0;
                        nextAttack ++;
                    }
                }
            }
        }
    }

    static void moveBonesRight() {
        for (Iterator<ObjetsItems> iterator = bonesRight.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            bone.moveRight();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 0.1f);
            }
            if (bone.getX() > boxHeart.getX() + boxHeart.getWidth() + 30) {
                bone.delete();
                iterator.remove();
            }
        }
    }

    static void moveBonesRightFast() {
        for (Iterator<ObjetsItems> iterator = bonesLeft.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 5f);
            }
            if(bone.getX() < boxHeart.getX() - 40 - bone.getWidth() && !bone.getSense()) {
                bone.moveRightFast(boxHeart.getX() - 40 - bone.getWidth());
            } else {
                bone.setIsReverseSense(true);
            }
            if (bone.getX() > 100 && bone.getSense()) {
                bone.moveLeftFast(100);
                if (bone.getX() <= 100) {
                    bone.delete();
                    iterator.remove();
                    if (bonesDown.isEmpty() && bonesUp.isEmpty() && bonesLeft.isEmpty() && bonesRight.isEmpty()) {
                        secondTimeBetweenTransitions = 0;
                        nextAttack ++;
                    }
                }
            }
        }
    }

    static void moveBonesDownFast(float extension) {
        for (Iterator<ObjetsItems> iterator = bonesUp.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 5f);
            }
            if(bone.getY() > boxHeart.getY() + boxHeart.getHeight() - 10 - bone.getHeight() && !bone.getSense()) {
                bone.moveDownFast(boxHeart.getY() + boxHeart.getHeight() - 10 - bone.getHeight());
            } else {
                bone.setIsReverseSense(true);
            }
            if (bone.getY() < boxHeart.getY() + boxHeart.getHeight() + extension && bone.getSense()) {
                bone.moveUpFast(boxHeart.getY() + boxHeart.getHeight() + extension);
                if (bone.getY() >= boxHeart.getY() + boxHeart.getHeight() + extension) {
                    bone.delete();
                    iterator.remove();
                    if (bonesDown.isEmpty() && bonesUp.isEmpty() && bonesLeft.isEmpty() && bonesRight.isEmpty()) {
                        secondTimeBetweenTransitions = 0;
                        nextAttack ++;
                    }
                }
            }
        }
    }

    static void moveBonesUpFast() {
        for (Iterator<ObjetsItems> iterator = bonesDown.iterator(); iterator.hasNext();) {
            ObjetsItems bone = iterator.next();
            if (CollisionListener.isCollided(heart, bone)) {
                soulDamaged();
                heart.setHp(heart.getHp() - 5f);
            }
            if(bone.getY() < boxHeart.getY() - bone.getHeight() && !bone.getSense()) {
                bone.moveUpFast(boxHeart.getY() - bone.getHeight());
            } else {
                bone.setIsReverseSense(true);
            }
            if (bone.getY() > 0 && bone.getSense()) {
                bone.moveDownFast(0);
                if (bone.getY() <= 0) {
                    bone.delete();
                    iterator.remove();
                    if (bonesDown.isEmpty() && bonesUp.isEmpty() && bonesLeft.isEmpty() && bonesRight.isEmpty()) {
                        secondTimeBetweenTransitions = 0;
                        nextAttack ++;
                    }
                }
            }
        }
    }

    static void generateAct() {
        switch (act) {
            case 0:
            break;
            case 1:
                heart.setOption(2);
                boxHeart.changeDimensionsMin();
                generateBones();
                drawBones();
                drawBlackGround();
                break;
            case 2:
                heart.setOption(1);
                boxHeart.changeDimensionsMinSquare();
                drawBonesTab();
                drawBlackGround();
                switch (nextAttack) {
                    case 0:
                        bonesTab(true, false, false, false, boxHeart.getHeight()/2 - 20);
                        break;
                    case 1:
                        bonesTab(false, true, false, false, boxHeart.getHeight()/2 - 20);
                        break;
                    case 2:
                        bonesTab(false, false, true, false, boxHeart.getHeight()/2 - 20);
                        break;
                    case 3:
                        bonesTab(false, false
                            , false, true, boxHeart.getHeight()/2 - 20);
                        break;
                    case 4:
                        bonesTab(true, false, true, false, boxHeart.getHeight()/2 - 20);
                        break;
                    case 5:
                        bonesTab(false, true, false, true, boxHeart.getHeight()/2 - 20);
                        break;
                    case 6:
                        bonesTab(false, true, true, false, boxHeart.getHeight()/2 - 20);
                        break;
                    case 7:
                        bonesTab(false, true, true, true, boxHeart.getHeight()/2 - 20);
                        break;
                    case 8:
                        bonesTab(true, true, true, true, boxHeart.getHeight()/2 - 20);
                        break;
                    case 9:
                        nextAttack = 0;
                        setConfigTurnPlayer();
                        break;
                }
                break;
            case 3:
                boxHeart.changeDimensionsMin();
                heart.setOption(2);
                generateGastersBlaster();
                drawGasterBlaster(4f);

        }
    }


}
