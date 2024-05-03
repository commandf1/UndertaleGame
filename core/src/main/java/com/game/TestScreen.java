package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.boxHeart;
import static com.game.Undertale.stage;

public class TestScreen implements Screen {
    private BoxAttack boxAttack;

    private BarAttack barAttack;

    private MissLabel missLabel;

    private boolean isOptionAvailable = true;

    private Hit hit;
    @Override
    public void show() {
        //boxAttack = new BoxAttack((float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2);
        barAttack = new BarAttack(boxHeart.getY() + 3);

        hit = new Hit(boxAttack.getX() + boxAttack.getWidth()/2, boxAttack.getY() + boxAttack.getHeight() + 4 * VH_HEIGHT);
        missLabel = new MissLabel(boxAttack.getX() + boxAttack.getWidth()/2, hit.getY() + hit.getHeight() + 4 * VH_HEIGHT);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            isOptionAvailable = true;
        }

        float positionBarNormalized = barAttack.getX() - boxAttack.getX();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && boxAttack.getStage() == null) {
            stage.addActor(boxAttack);
            isOptionAvailable = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && boxAttack.getStage() != null && isOptionAvailable){
            hit.animationHit();
            stage.addActor(hit);
            stage.addActor(missLabel);
            if (barAttack.getStage() == null) {
                stage.addActor(barAttack);
            }

            isOptionAvailable = false;

            System.out.println("POSITION PRESSED Enter : " + (barAttack.getX() - boxAttack.getX()));
            if ( 262 <=  positionBarNormalized &&  positionBarNormalized <= 282 ) {
                System.out.println("CENTERED");
                if (positionBarNormalized == 273 ) {
                    System.out.println("PERFECT");
                }
            }
            barAttack.setX(boxAttack.getX());

        }  else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && boxAttack.getStage() != null){
            boxAttack.remove();
            barAttack.remove();
            hit.remove();
            missLabel.remove();
            barAttack.setX(boxAttack.getX());
        }

        if (barAttack.getStage() != null) {
            barAttack.setX(Math.min(barAttack.getX() + 6, boxAttack.getX() + boxAttack.getWidth()));
        }
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
