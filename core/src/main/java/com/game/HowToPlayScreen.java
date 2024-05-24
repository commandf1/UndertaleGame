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
import static com.game.BlackScreen.VH_WIDTH;
import static com.game.Sounds.creditsSound;
import static com.game.Sounds.stopAllSounds;
import static com.game.Undertale.*;

public class HowToPlayScreen implements Screen {
    private final Undertale game;

    private final OrthographicCamera camera;

    private final TobyFoxDog tobyFoxDog;

    private Label optionMercy;

    public HowToPlayScreen(Undertale game) {
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
        Label.LabelStyle labelStyleTitle = new Label.LabelStyle();

        labelStyleTitle.font = getFont(50);
        labelStyleTitle.fontColor = Color.YELLOW;

        Label title = new Label("How to play", labelStyleTitle);


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont(30);
        labelStyle.fontColor = Color.WHITE;

        Label controls = new Label("Controls:", labelStyle);

        Label left = new Label("Left arrow - Move to the left", labelStyle);
        Label right = new Label("Right arrow - Move to the right", labelStyle);
        Label up = new Label("Up arrow - Move to up", labelStyle);
        Label down = new Label("Down arrow- Move to down", labelStyle);
        Label enter = new Label("Enter - To accept / Scroll down the Credits and How to Play page.", labelStyle);
        Label escape = new Label("Escape - Back", labelStyle);
        Label aim = new Label("Aim:", labelStyle);
        Label aimMessage = new Label("The objective of undertale is to defeat the enemy Sans, the player has 4 options, \nFight, Act, Item, Mercy, choosing an action consumes one turn of the player, \npassing the turn to Sans.", labelStyle);
        Label optionFight = new Label("Fight: This action allows you to attack Sans.", labelStyle);
        Label optionAct = new Label("Act: This action allows you to interact with Sans, in this case you are\nlimited to obtaining a description of him.", labelStyle);
        Label optionItem = new Label("Item: This action allows you to consume an item to recover health.", labelStyle);
        optionMercy = new Label("Mercy: This action allows you to ask Sans for mercy.", labelStyle);


        title.setPosition((float) Gdx.graphics.getWidth() /2 - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()/2 - VH_HEIGHT*20f);

        controls.setPosition(3 * VH_WIDTH, title.getY() - VH_HEIGHT * 20f );

        tobyFoxDog.setPosition((float) Gdx.graphics.getWidth() /2, controls.getY() - VH_HEIGHT * 10f);
        tobyFoxDog.animationTobyFox();


        left.setPosition(3 * VH_WIDTH ,  title.getY() - VH_HEIGHT * 30f );
        right.setPosition(3 * VH_WIDTH , left.getY() - VH_HEIGHT * 20f );
        up.setPosition(3 * VH_WIDTH , right.getY() - VH_HEIGHT * 20f);
        down.setPosition(3 * VH_WIDTH , up.getY() - VH_HEIGHT * 20f);
        enter.setPosition(3 * VH_WIDTH , down.getY() - VH_HEIGHT * 20f);
        escape.setPosition(3 * VH_WIDTH, enter.getY() - VH_HEIGHT * 20f);
        aim.setPosition(3 * VH_WIDTH , escape.getY() - VH_HEIGHT * 40f);
        aimMessage.setPosition(3 * VH_WIDTH , aim.getY() - VH_HEIGHT * 20f);
        optionFight.setPosition(3 * VH_WIDTH , aimMessage.getY() - VH_HEIGHT * 20f);
        optionAct.setPosition(3 * VH_WIDTH , optionFight.getY() - VH_HEIGHT * 20f);
        optionItem.setPosition(3 * VH_WIDTH , optionAct.getY() - VH_HEIGHT * 20f);
        optionMercy.setPosition(3 * VH_WIDTH , optionItem.getY() - VH_HEIGHT * 20f);

        stage.addActor(title);
        stage.addActor(controls);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(up);
        stage.addActor(down);
        stage.addActor(enter);
        stage.addActor(escape);
        stage.addActor(aim);
        stage.addActor(aimMessage);
        stage.addActor(optionFight);
        stage.addActor(optionAct);
        stage.addActor(optionItem);
        stage.addActor(optionMercy);
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


        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && camera.position.y > optionMercy.getY()) {
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
