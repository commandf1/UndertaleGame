package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;

import static com.game.DataBase.closeConnection;
import static com.game.FabricElements.createLabel;
import static com.game.Sounds.gameOverTheme;
import static com.game.Sounds.isGameOverPlaying;
import static com.game.Undertale.*;


public class GameOverScreen implements Screen {

    private float seconds = 0;

    private boolean fragmentsCreated = false;


    private final Undertale game;

    private Texture image;

    private final Heart heart;

    private boolean isCalledBrokeHeart = false;

    private Label gameOverLabel, pressEnterToContinue;

    private final ArrayList<HeartFragment> fragments = new ArrayList<>(5);

    public GameOverScreen(Undertale game, Heart heart) {
        loadImage();
        this.heart = new Heart(image, heart.getX(), heart.getY());
        this.game = game;
    }

    public void loadImage() {
        image =  new Texture(Gdx.files.internal("images/heart.png"));
    }

    public void loadLabels() {
        gameOverLabel = createLabel("GAMER\n  Over",100);
        gameOverLabel.setPosition((float) Gdx.graphics.getWidth() /2 - gameOverLabel.getWidth()/2, (float) Gdx.graphics.getHeight() /2);
        gameOverLabel.setColor(gameOverLabel.getColor().r, gameOverLabel.getColor().g, gameOverLabel.getColor().b, 0f);

        pressEnterToContinue = createLabel("Press enter to start again", 30);
        pressEnterToContinue.setPosition((float) Gdx.graphics.getWidth() /2 - pressEnterToContinue.getWidth()/2, 200);
        pressEnterToContinue.setColor(pressEnterToContinue.getColor().r, pressEnterToContinue.getColor().g, pressEnterToContinue.getColor().b, 0f);

    }

    @Override
    public void show() {
        heart.setOption(4);
        heart.updateHitBox();
        loadLabels();
        stage.addActor(gameOverLabel);
        stage.addActor(pressEnterToContinue);
        stage.addActor(heart);
    }

    public void deleteFragments() {
        for (int i = fragments.size() - 1; i >= 0; i--) {
            HeartFragment fragment = fragments.get(i);
            if (fragment.getX() < 0 || fragment.getX() > Gdx.graphics.getWidth()
                || fragment.getY() < 0 || fragment.getY() > Gdx.graphics.getHeight()) {
                fragment.remove();
                fragments.remove(i);
            }
        }
    }

    @Override
    public void render(float v) {
        seconds += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!isCalledBrokeHeart && 2 < seconds && seconds < 2.8) {
            heart.setBrokenHeart();
            isCalledBrokeHeart = true;
        } else if (seconds > 2.8 && fragments.isEmpty() && !fragmentsCreated) {
            fragmentsCreated = true;
            for (int i = 0; i < 5; i++) {
                Vector2 velocity = new Vector2((float) Math.random() * 200 - 100, (float) Math.random() * 200);
                HeartFragment fragment = new HeartFragment(heart.getX(), heart.getY(), velocity);
                fragments.add(fragment);
                stage.addActor(fragment);
            }

        }
        if (fragments.isEmpty())  {
            batch.begin();
            heart.draw(batch, 1);
            batch.end();
        } else {
            image.dispose();
        }

        drawFragments(v);
        deleteFragments();
        if (fragments.size() == 4) {
            if (!isGameOverPlaying()) {
                gameOverTheme();
            }
        }

        if (fragments.isEmpty() && seconds > 2.8) {
            float newOpacity = Math.min(gameOverLabel.getColor().a + v * 0.4f, 1f);
            gameOverLabel.setColor(gameOverLabel.getColor().r, gameOverLabel.getColor().g, gameOverLabel.getColor().b, newOpacity);
            pressEnterToContinue.setColor(pressEnterToContinue.getColor().r, pressEnterToContinue.getColor().g, pressEnterToContinue.getColor().b, newOpacity);

            stage.act(v);
            stage.draw();
        }

        if (pressEnterToContinue.getColor().a >= 0.1 && Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.showMainMenuScreen();
        }


    }

    private void drawFragments(float v) {
        for (HeartFragment fragment : fragments) {
            fragment.act(v);
            fragment.draw(batch, 1);
        }
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
        dispose();

    }

    @Override
    public void dispose() {
        if (image!=null) {
            image.dispose();
        }
        stage.clear();
        for (HeartFragment fragment : fragments) {
            fragment.dispose();
        }
    }
}
