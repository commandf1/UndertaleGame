package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.bson.Document;
import java.util.List;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.DataBase.*;
import static com.game.FabricElements.*;
import static com.game.Sounds.menuSound;
import static com.game.Undertale.stage;

public class RankingScreen implements Screen {
    private final Undertale game;

    public RankingScreen(Undertale game) {
        this.game = game;
    }
    public void createComponents() {
        List<Document> topTenScores = getRanking();

        TextButton backButton = createButtonBack(game, VH_HEIGHT * 2f);
        if (topTenScores.isEmpty()) {
            Label blankTop = new Label("There are not scores to show", getLabelStyle(30));
            blankTop.setPosition((float) Gdx.graphics.getWidth() /2 - blankTop.getWidth()/2, (float) Gdx.graphics.getHeight() /2);
            stage.addActor(blankTop);
        } else {
            Table table = createTable( topTenScores, backButton.getY());
            stage.addActor(table);
        }
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        createConnection();

        menuSound();
        createComponents();
        }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.clear();
        closeConnection();
    }
}
