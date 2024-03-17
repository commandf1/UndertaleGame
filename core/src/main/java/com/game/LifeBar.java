package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.game.Undertale.shapeRenderer;

public class LifeBar extends Actor {
    private float currentValue; // Valor actual de la vida


    public LifeBar(float x, float y, float width, float height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void setCurrentValue(float value) {
        this.currentValue = Math.max(0, Math.min(value, 90)); // Aseguramos que el valor esté dentro del rango
    }

    public void draw() {
        shapeRenderer.begin(ShapeType.Filled);

        // Dibujar el rectángulo contenedor
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());

        // Dibujar la barra de vida
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(getX(), getY(), getWidth() * (currentValue / 90), getHeight());


        shapeRenderer.end();
    }
}
