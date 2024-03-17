package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoxHeart extends Actor {

    public int mode = 0;

    public float MAX_WIDTH = 1000,  MAX_HEIGHT_SQUARE = 300, X_POS_MAX_SQUARE = 550, X_POS_MIN = 200, X_POS_INTERMEDIATE = 350, MIN_WIDTH = 700;
    private final Color borderColor = Color.WHITE;
        private final ShapeRenderer shapeRenderer;

        public BoxHeart(float width, float height) {
            setWidth(width);
            setHeight(height);
            setX(getX() + X_POS_MIN);
            float y_POS_MIN = 200;
            setY(getY() + y_POS_MIN);
            shapeRenderer = new ShapeRenderer();
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
                setWidth(Math.max(getWidth() - 10, MAX_HEIGHT_SQUARE));
                setX(Math.min(getX() + 5 , X_POS_MAX_SQUARE));
                if (getX() == X_POS_MAX_SQUARE && getWidth() == MAX_HEIGHT_SQUARE) {
                    mode = 2;
                }
            }

        }

    public void changeDimensionsMax() {
        if( mode == 2 || mode == 1) {
            setWidth(Math.min(getWidth() + 10, MAX_WIDTH));
            setX(Math.max(getX() - 5 , X_POS_MIN));
            if (getX() == X_POS_MIN && getWidth() == MAX_WIDTH) {
                mode = 0;
            }
        }
    }

    public void changeDimensionsMin() {
        if(mode == 0) {
                setWidth(Math.max( getWidth() - 15, MIN_WIDTH));
                setX(Math.min( getX() + 9, X_POS_INTERMEDIATE));
                if (getX() == X_POS_INTERMEDIATE && getWidth() == MIN_WIDTH) {
                    mode = 1;
                }
            }
            if (mode == 2) {
                setWidth(Math.min( getWidth() + 15, MIN_WIDTH));
                setX(Math.max( getX() - 9, X_POS_INTERMEDIATE));
                if (getX() == X_POS_INTERMEDIATE && getWidth() == MIN_WIDTH) {
                    mode = 1;
                }
            }

    }
}
