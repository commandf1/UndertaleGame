package com.game;

import com.badlogic.gdx.physics.box2d.*;

public class BodyFabric {

    public static Body getRectangleBody(float xPos, float yPos, World world, float widthInMeters, float heightInMeters) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xPos, yPos);

        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(widthInMeters, heightInMeters);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;

        // Configurar filtros de colisión para el fixture
        fixtureDef.filter.categoryBits = 0x0001;  // Categoría de colisión del cuerpo
        fixtureDef.filter.maskBits = 0x0002;      // Máscara de colisión que indica con qué categorías el cuerpo puede colisionar

        body.createFixture(fixtureDef);

        // Establecer UserData para el cuerpo
        body.setUserData("Rectangle"); // Puedes establecer cualquier objeto como UserData para identificar el tipo de cuerpo

        polygonShape.dispose();
        return body;
    }

    public static Body getCircleBody(float xPos, float yPos, World world, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xPos, yPos);

        Body body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;

        // Configurar filtros de colisión para el fixture
        fixtureDef.filter.categoryBits = 0x0002;  // Categoría de colisión del cuerpo
        fixtureDef.filter.maskBits = 0x0001;      // Máscara de colisión que indica con qué categorías el cuerpo puede colisionar

        body.createFixture(fixtureDef);

        // Establecer UserData para el cuerpo
        body.setUserData("Circle"); // Puedes establecer cualquier objeto como UserData para identificar el tipo de cuerpo

        circleShape.dispose();
        return body;
    }
}
