package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.Undertale.shapeRenderer;
import static com.game.BlackScreen.VH_WIDTH;

public class BoxHeart extends Actor {

    public int mode = 0;

    public float MAX_WIDTH = VH_WIDTH * 90,  MAX_HEIGHT_SQUARE = VH_WIDTH * 20, MIN_WIDTH = VH_WIDTH * 70;
    private final Color borderColor = Color.WHITE;
        public BoxHeart(float width, float height) {
            setWidth(width);
            setHeight(height);
            setOrigin(getWidth() / 2, getHeight() / 2);
            setX(VH_WIDTH * 5);
            setY(VH_HEIGHT * 30);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(borderColor);
            Gdx.gl20.glLineWidth(10f);
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
            shapeRenderer.end();
        }

        public void changeDimensionsMinSquare() {
            if ( mode == 0  || mode == 1) {
                float prevWidth = getWidth();
                setWidth(Math.max(getWidth() - 20, MAX_HEIGHT_SQUARE));
                float deltaX = (prevWidth - getWidth()) / 2;
                setX(getX() + deltaX);
                if (getWidth() == MAX_HEIGHT_SQUARE) {
                    mode = 2;
                }
            }

        }

    public void changeDimensionsMax() {
        if (mode == 2 || mode == 1) {
            float prevWidth = getWidth();
            setWidth(Math.min(getWidth() + 20, MAX_WIDTH));
            float deltaX = (getWidth() - prevWidth) / 2;
            setX(getX() - deltaX);
            if (getWidth() == MAX_WIDTH) {
                mode = 0;
            }
        }
    }


    public void changeDimensionsMin() {
        if(mode == 0) {
            float prevWidth = getWidth();
            setWidth(Math.max(getWidth() - 20, MIN_WIDTH));
            float deltaX = (prevWidth - getWidth()) / 2;
            setX(getX() + deltaX);
            if (getWidth() == MIN_WIDTH) {
                    mode = 1;
                }
            }
            if (mode == 2) {
                float prevWidth = getWidth();
                setWidth(Math.min(getWidth() + 20, MIN_WIDTH));
                float deltaX = (getWidth() - prevWidth) / 2;
                setX(getX() - deltaX);
                if (getWidth() == MIN_WIDTH) {
                    mode = 1;
                }
            }

    }
}
