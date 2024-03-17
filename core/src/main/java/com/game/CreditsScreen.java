package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.game.BlackScreen.VH_HEIGHT;
import static com.game.Sounds.creditsSound;
import static com.game.Sounds.stopAllSounds;
import static com.game.Undertale.*;

public class CreditsScreen implements Screen {
    private final Undertale game;

    private final OrthographicCamera camera;

    private final TobyFoxDog tobyFoxDog;

    private Label MESSAGE_EXTRA5;

    public CreditsScreen(Undertale game) {
        this.game = game;
        tobyFoxDog = new TobyFoxDog();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2f, 0);
        camera.update();

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);
    }

    public void createCredits()
    {
        String TOBY_FOX = "Toby Fox";
        Label.LabelStyle labelStyleTitle = new Label.LabelStyle();

        labelStyleTitle.font = getFont(50);
        labelStyleTitle.fontColor = Color.YELLOW;

        Label CREDITS_LABEL = new Label("CREDITS", labelStyleTitle);


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(30);
        labelStyle.fontColor = Color.WHITE;
        Label SOUNDTRACK = new Label(String.format("All songs written and composed by %s.", TOBY_FOX), labelStyle);
        Label INSPIRED = new Label("This game is a fan made fight of Sans Fight from the original game Undertale.", labelStyle);
        Label DESIGN_CHARACTERS = new Label(String.format("The design characters was made originally by %s", TOBY_FOX), labelStyle);
        Label LOGIC_GAME = new Label(String.format("The original game was developed and made by %s", TOBY_FOX), labelStyle);
        Label PROGRAMMED = new Label("This fan game was developed and animated by David Andrés Carrero Tinoco", labelStyle);
        Label MESSAGE_EXTRA = new Label("EhhHhH ... The credits already finished ", labelStyle);
        Label MESSAGE_EXTRA2 = new Label("Why are you still here? ", labelStyle);
        Label MESSAGE_EXTRA3 = new Label("You could do something more productive", labelStyle);
        Label MESSAGE_EXTRA4 = new Label("Did I mention Toby fox already?", labelStyle);
        MESSAGE_EXTRA5 = new Label("I'm tired boss", labelStyle);


        CREDITS_LABEL.setPosition((float) Gdx.graphics.getWidth() /2 - CREDITS_LABEL.getWidth()/2, Gdx.graphics.getHeight() - CREDITS_LABEL.getHeight()/2 - VH_HEIGHT*20f);
        SOUNDTRACK.setPosition((float) Gdx.graphics.getWidth() /2 - SOUNDTRACK.getWidth()/2 , CREDITS_LABEL.getY() - VH_HEIGHT * 20f );
        tobyFoxDog.setPosition(SOUNDTRACK.getX() + SOUNDTRACK.getWidth()/2, SOUNDTRACK.getY() - VH_HEIGHT * 10f);
        tobyFoxDog.animationTobyFox();
        INSPIRED.setPosition((float) Gdx.graphics.getWidth() /2 - INSPIRED.getWidth()/2, SOUNDTRACK.getY() - VH_HEIGHT * 20f );
        DESIGN_CHARACTERS.setPosition((float) Gdx.graphics.getWidth() /2 - DESIGN_CHARACTERS.getWidth()/2, INSPIRED.getY() - VH_HEIGHT * 20f );
        LOGIC_GAME.setPosition((float) Gdx.graphics.getWidth() /2 - LOGIC_GAME.getWidth()/2, DESIGN_CHARACTERS.getY() - VH_HEIGHT * 20f);
        PROGRAMMED.setPosition((float) Gdx.graphics.getWidth() /2 - PROGRAMMED.getWidth()/2, LOGIC_GAME.getY() - VH_HEIGHT * 20f);
        MESSAGE_EXTRA.setPosition((float) Gdx.graphics.getWidth() /2 - MESSAGE_EXTRA.getWidth()/2, PROGRAMMED.getY() - VH_HEIGHT * 200f);
        MESSAGE_EXTRA2.setPosition((float) Gdx.graphics.getWidth() /2 - MESSAGE_EXTRA2.getWidth()/2, MESSAGE_EXTRA.getY() - VH_HEIGHT * 200f);
        MESSAGE_EXTRA3.setPosition((float) Gdx.graphics.getWidth() /2 - MESSAGE_EXTRA3.getWidth()/2, MESSAGE_EXTRA2.getY() - VH_HEIGHT * 200f);
        MESSAGE_EXTRA4.setPosition((float) Gdx.graphics.getWidth() /2 - MESSAGE_EXTRA4.getWidth()/2, MESSAGE_EXTRA3.getY() - VH_HEIGHT * 200f);
        MESSAGE_EXTRA5.setPosition((float) Gdx.graphics.getWidth() /2 - MESSAGE_EXTRA5.getWidth()/2, MESSAGE_EXTRA4.getY() - VH_HEIGHT * 200f);
        stage.addActor(CREDITS_LABEL);
        stage.addActor(SOUNDTRACK);
        stage.addActor(INSPIRED);
        stage.addActor(DESIGN_CHARACTERS);
        stage.addActor(LOGIC_GAME);
        stage.addActor(PROGRAMMED);
        stage.addActor(MESSAGE_EXTRA);
        stage.addActor(MESSAGE_EXTRA2);
        stage.addActor(MESSAGE_EXTRA3);
        stage.addActor(MESSAGE_EXTRA4);
        stage.addActor(MESSAGE_EXTRA5);
    }


    @Override
    public void show() {
        creditsSound();
        createCredits();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && camera.position.y > MESSAGE_EXTRA5.getY()) {
            camera.position.y -= 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && camera.position.y <  Gdx.graphics.getHeight()) {
            camera.position.y += 5;
        }
        camera.update();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.showMainMenuScreen();
        }
        tobyFoxDog.act(v);
        tobyFoxDog.draw(batch, 1);
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
        dispose();
        stage = new Stage();
    }

    @Override
    public void dispose() {
        stopAllSounds();
        stage.clear();
    }
}
