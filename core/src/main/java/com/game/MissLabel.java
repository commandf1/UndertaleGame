package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.BlackScreen.VH_WIDTH;
import static com.game.Direction.DOWNWARD;
import static com.game.Direction.UPWARD;

public class MissLabel extends Actor {
    private final Sprite miss;

    private boolean cycleDone = false;

    private final float originY;

    private float timeElapsed;

    private Direction direction;
    public MissLabel(float x, float y) {
        miss = new Sprite(new Texture(Gdx.files.internal("images/HitSprite.png")));
        miss.setRegion(825, 174,  124,38);
        setPosition(x - (float) miss.getRegionWidth() /2 + 2 * VH_WIDTH,y);
        setSize(miss.getRegionWidth(), miss.getHeight());
        direction = UPWARD;
        originY = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (getStage() != null) {
            if (direction == UPWARD && !cycleDone) {
                setY(Math.min(getY() + 2, originY + 20));
                if (getY() == originY + 20) {
                    direction = DOWNWARD;
                }
            } else if (direction == DOWNWARD) {
                setY(Math.max(getY() - 2, originY - 10));
                if (getY() == originY - 10) {
                    direction = UPWARD;
                    cycleDone = true;
                }
            } else if (cycleDone) {
                setY(Math.min(getY() + 2, originY));
                if (getY() == originY) {
                    timeElapsed += Gdx.graphics.getDeltaTime();
                    if (timeElapsed > 0.25) {
                        timeElapsed = 0;
                        cycleDone = false;
                        remove();
                    }
                }
            }
        }
        batch.draw(miss, getX(), getY(), (float) miss.getRegionWidth() /2, (float) miss.getRegionHeight() /2, miss.getRegionWidth(), miss.getRegionHeight(), 1,1, 0);

    }

    public void dispose() {
        miss.getTexture().dispose();
        remove();
    }
}
