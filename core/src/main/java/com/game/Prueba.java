package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Prueba extends ApplicationAdapter {

    float dt;
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    int regionX = 398; // Coordenada x de la región 12
    int regionY = 693; // Coordenada y de la región
    int regionWidth = 12; // Ancho de la región
    int regionHeight = 42; // Alto de la región

    int x = 1000 , y = 300;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // half body
    // up normal : Region ( 14 , 23 ) w: 54 h: 26
    // down normal : Region ( 5 , 81 ) w: 44 h: 23

    // Hands up - down
    // body -> hands over : Region ( 0 , 200 ) w : 70 h : 70
    // body -> hands over : Region ( 70 , 200 ) w : 70 h : 70
    // body -> hands over : Region ( 140 , 200 ) w : 70 h : 70
    // body -> hands over : Region ( 210 , 200 ) w : 70 h : 70
    // body -> hands over : Region ( 280 , 200 ) w : 70 h : 70

    // Hands down - up
    // body -> hands over : Region ( 0 , 295 ) w : 70 h : 70
    // body -> hands over : Region ( 70 , 295 ) w : 70 h : 70
    // body -> hands over : Region ( 140 , 295 ) w : 70 h : 70
    // body -> hands over : Region ( 210 , 295 ) w : 70 h : 70
    // body -> hands over : Region ( 280 , 295 ) w : 70 h : 70
    // body -> hands over : Region ( 350 , 295 ) w : 70 h : 70

    // Hands down - right
    // body -> hands over : Region ( 0 , 370 ) w : 83 h : 70
    // body -> hands over : Region ( 110 , 370 ) w : 83 h : 70   // 12 pixel since the beginning until the image
    // body -> hands over : Region ( 212 , 370 ) w : 83 h : 70
    // body -> hands over : Region ( 314 , 370 ) w : 83 h : 70
    // body -> hands over : Region ( 416 , 370 ) w : 83 h : 70
    // body -> hands over : Region ( 518 , 370 ) w : 83 h : 70

    // Head
    // normal : Region ( 4 , 467 ) w : 32 h : 30
    // smile : Region ( 39 , 467 ) w : 32 h : 30   // 12 pixel since the beginning until the image
    // scared  : Region ( 74 , 467 ) w : 32 h : 30
    // one eye  : Region ( 109 , 467 ) w : 32 h : 30
    // eyes closed  : Region ( 144 , 467 ) w : 32 h : 30
    // looking corner right-up  : Region ( 179 , 467 ) w : 32 h : 30
    // one eye looking corner right-up  : Region ( 214 , 467 ) w : 32 h : 30
    // without eyes : Region ( 214 , 467 ) w : 32 h : 30

    // blue eye : Region ( 6 , 557 ) w : 32 h : 30
    // orange eye : Region ( 45 , 557 ) w : 32 h : 30

    // bone long : Region ( 278 , 573 ) w : 12 h : 202
    // bone small : Region ( 278 , 780 ) w : 12 h : 8
    // bone small 360° : Region ( 278 , 791 ) w : 12 h : 8
    // bone medium vertical : Region ( 293 , 573 ) w : 102 h : 12
    // bone Big vertical : Region ( 398 , 573 ) w : 12 h : 102
    // bone medium horizontal : Region ( 398 , 678 ) w : 202 h : 12
    // bone normal vertical : Region ( 398 , 693 ) w : 12 h : 52
    // bone normal vertical : Region ( 415 , 695 ) w : 12 h : 42
    // bone small vertical : Region ( 432 , 693 ) w : 12 h : 42

    // bone long horizontal : Region ( 398 , 573 ) w : 219 h : 102







    // body -> hands over : ( 80 , 20 ) w : 75  h : 30
    // legs -> hands over : ( 5 , 80 ) w : 45 h : 25
    // body -> body : ( 5, 127 ) w: 54 h : 49 full body normal
    // body -> body : ( 5, 200 ) w: 60 h : 68 hand up
    // body -> body : ( 76, 200 ) w: 60 h : 68 hand up
    // body -> body : ( 145, 200 ) w: 60 h : 63 hand down movement

    @Override
    public void create () {
        batch = new SpriteBatch();
        texture = new Texture("images/SansSprite.png"); // Cargar la imagen
        TextureRegion region = new TextureRegion(texture, regionX, regionY, regionWidth, regionHeight);
        sprite = new Sprite(region);
        sprite.setPosition(x, y);
    }

    @Override
    public void render () {
        dt += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if (getX() > 20) {
            setX(getX() - 2);
            batch.draw(sprite, getX(), getY(), sprite.getWidth() * 4, sprite.getHeight()*4);// Dibujar el sprite
            System.out.println("Delta: " + dt);
        }
        batch.end();

    }

    @Override
    public void dispose () {
        batch.dispose();
        texture.dispose();
    }

}
