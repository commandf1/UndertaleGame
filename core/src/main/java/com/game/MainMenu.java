package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.BlackScreen.VH_WIDTH;
import static com.game.Sounds.stopAllSounds;
import static com.game.Undertale.*;

public class MainMenu implements Screen {
    private Texture backgroundImage;

    private final Undertale game;

    public MainMenu(Undertale game) {
        this.game = game;
        dispose();
    }

    public void createButtons() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont(30);
        textButtonStyle.fontColor = Color.WHITE;

        TextButton playButton = new TextButton("Play", textButtonStyle);
        TextButton rankingButton = new TextButton("Ranking TOP", textButtonStyle);
        TextButton creditsButton = new TextButton("Credits", textButtonStyle);
        TextButton howToPlayButton = new TextButton("How to play", textButtonStyle);

        playButton.setPosition((float) Gdx.graphics.getWidth() / 2, 200);
        rankingButton.setPosition(playButton.getX() - rankingButton.getWidth()/4, playButton.getY() - playButton.getHeight() - 20);
        creditsButton.setPosition((float) Gdx.graphics.getWidth() / 2 - 1.5f*VH_WIDTH, rankingButton.getY() - rankingButton.getHeight() - VH_HEIGHT * 2f );
        howToPlayButton.setPosition((float) Gdx.graphics.getWidth() / 2 - 3.0f*VH_WIDTH, creditsButton.getY() - creditsButton.getHeight() - VH_HEIGHT * 2f );

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showInputNameScreen();
                playButton.getLabel().setColor(Color.YELLOW);
            }
        });

        rankingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showRankingScreen();
                rankingButton.getLabel().setColor(Color.YELLOW);
            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showCreditsScreen();
                creditsButton.getLabel().setColor(Color.YELLOW);
            }
        });

        howToPlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showHowToPlayScreen();
                howToPlayButton.getLabel().setColor(Color.YELLOW);
            }
        });

        playButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.getLabel().setColor(Color.YELLOW);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!playButton.isOver()) {
                    playButton.getLabel().setColor(Color.WHITE);
                }
            }
        });

        rankingButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                rankingButton.getLabel().setColor(Color.YELLOW);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!rankingButton.isOver()) {
                    rankingButton.getLabel().setColor(Color.WHITE);
                }
            }
        });

        creditsButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                creditsButton.getLabel().setColor(Color.YELLOW);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!creditsButton.isOver()) {
                    creditsButton.getLabel().setColor(Color.WHITE);
                }
            }
        });

        howToPlayButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                howToPlayButton.getLabel().setColor(Color.YELLOW);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!howToPlayButton.isOver()) {
                    howToPlayButton.getLabel().setColor(Color.WHITE);
                }
            }
        });

        stage.addActor(playButton);
        stage.addActor(rankingButton);
        stage.addActor(creditsButton);
        stage.addActor(howToPlayButton);
    }

    @Override
    public void show() {
        Sounds.introSound();
        backgroundImage = new Texture(Gdx.files.internal("images/UndertaleBackground.jpg"));
        createButtons();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroundImage,0 , 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
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
        if (backgroundImage != null ) {
            backgroundImage.dispose();
        }
        stopAllSounds();
        stage.clear();
    }
}
