package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;

import static com.game.BattleController.*;
import static com.game.Events.detectEvent;

public class BlackScreen implements Screen {

    public static ShapeRenderer shapeRenderer;
    public static float deltaTime = 0;

    public static final ArrayList<ObjetsItems> objectItems = new ArrayList<>();

    public static Heart heart;

    public static FightOp fightOp;

    public static ActOp actOp;

    public static ItemOp itemOp;

    public static MercyOp mercyOp;

    public static BoxHeart boxHeart;

    private OrthographicCamera camera;

    float beginTime;

    float endTime;

    private Texture image, fightImg, actImg, itemImg, mercyImg;
    public static SpriteBatch batch;



    public void loadImages() {
        image = new Texture(Gdx.files.internal("images/heart.png"));
        fightImg = new Texture(Gdx.files.internal("images/FightButton.jpg"));
        actImg = new Texture(Gdx.files.internal("images/ActButton.jpg"));
        itemImg = new Texture(Gdx.files.internal("images/ItemButton.jpg"));
        mercyImg = new Texture(Gdx.files.internal("images/MercyButton.jpg"));
    }

    public void loadObject() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        fightOp = new FightOp(fightImg, batch, 20, 20);
        actOp = new ActOp(actImg, batch, fightOp.getX() + (fightImg.getWidth() * 0.2f) + 5, 20);
        itemOp = new ItemOp(itemImg, batch, actOp.getX() + actImg.getWidth() * 0.2f + 5, 20);
        mercyOp = new MercyOp(mercyImg, batch, itemOp.getX() + itemImg.getWidth() * 0.2f  + 5, 20);
        boxHeart = new BoxHeart(1000, 250);

        float heartX = boxHeart.getX() + (boxHeart.getWidth() - image.getWidth() * 0.5f) * 0.5f;
        float heartY = boxHeart.getY() + (boxHeart.getHeight() - image.getHeight() * 0.5f) * 0.5f;
        heart = new Heart(image, heartX, heartY);
        heart.setPositionFightOp();
        heart.updateHitBox();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void show() {
        loadImages();
        loadObject();


        Sounds.fightSound();

    }

    @Override
    public void render(float delta) {
        deltaTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(KeyListener.get());
        if (heart.getHp() < 0) {
            Gdx.app.exit();
        }
        update();


        boxHeart.draw(batch, 1);
        fightOp.draw(); actOp.draw(); itemOp.draw(); mercyOp.draw();
        heart.draw(batch, 1);
    }

    static void drawBlackGround() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, boxHeart.getY()+boxHeart.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-boxHeart.getY()-boxHeart.getHeight());
        shapeRenderer.rect(0, 0, boxHeart.getX(), Gdx.graphics.getHeight());
        shapeRenderer.rect(boxHeart.getX() + boxHeart.getWidth() , 0, Gdx.graphics.getWidth() - (boxHeart.getX()+ boxHeart.getWidth()), Gdx.graphics.getHeight());
        shapeRenderer.rect(0,0, Gdx.graphics.getWidth(), boxHeart.getY());
        shapeRenderer.end();
    }

    void update() {
        if ( !heart.isTurn ) {
            generateAct();

        } else {
            boxHeart.changeDimensionsMax();
            Heart.canMoveLeft = !Gdx.input.isKeyPressed(Input.Keys.LEFT) || Heart.canMoveLeft;
            Heart.canMoveRight = !Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Heart.canMoveRight;
            for (ObjetsItems objetsItem: objectItems) {
                objetsItem.draw();
            }
            detectEvent();
        }











        /*
        *
        * if (boxHeart.getModeBox() == 0) {
            boxHeart.changeDimensionsMinSquare();
        }
        else if( boxHeart.getModeBox() == 2)
        {
            boxHeart.changeDimensionsMin();
        } else if (boxHeart.getModeBox() == 1 )
        {
            boxHeart.changeDimensionsMax();
        }*/
        beginTime = endTime;

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Método llamado cuando la pantalla cambia de tamaño.
    }

    @Override
    public void pause() {
        // Método llamado cuando la aplicación se pausa.
    }

    @Override
    public void resume() {
        // Método llamado cuando la aplicación se reanuda.
    }

    @Override
    public void hide() {
        // Método llamado cuando la pantalla se oculta.
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        fightImg.dispose();
        actImg.dispose();
        itemImg.dispose();
        mercyImg.dispose();
        image.dispose(); // Libera la memoria de la imagen
        batch.dispose(); // Libera la memoria del SpriteBatch
    }
}
