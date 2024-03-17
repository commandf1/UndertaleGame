package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.game.Undertale.batch;

public class TestScreen implements Screen {
    private final Undertale game;
    private final OrthographicCamera camera;

    private Sans sans;

    public TestScreen(Undertale game ){
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }
    @Override
    public void show() {
        sans = new Sans(); sans.animationRightHand();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if ( Gdx.input.isKeyPressed(Input.Keys.ENTER )) {
            camera.position.y -= 5;
            camera.update();
        } else if ( Gdx.input.isKeyPressed(Input.Keys.UP )) {
            camera.position.y += 5;
            camera.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }

        if (sans.isAnimationFinished() && sans.isCanAnimate()) {
            int animation = random.nextInt(4);
            switch (animation) {
                case 0 -> {
                    sans.animationUpwardHand();
                }
                case 1 -> {
                    sans.animationLeftHand();
                }
                case 2 -> {
                    sans.animationDownwardHand();
                }
                case 3 -> {
                    sans.animationRightHand();
                }
            }

        }
        sans.act(v);
        batch.setProjectionMatrix(camera.combined);
        sans.draw(batch, 1);
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
