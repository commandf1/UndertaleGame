package com.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyListener implements InputProcessor {
    private static KeyListener keyListener;

    private KeyListener() {

    }

    public static KeyListener get() {
        if ( KeyListener.keyListener == null ) {
            KeyListener.keyListener = new KeyListener();
        }
        return KeyListener.keyListener;
    }


    @Override
    public boolean keyDown(int i) {
        return i == Input.Keys.DOWN;
    }

    @Override
    public boolean keyUp(int i) {
        return i == Input.Keys.UP;
    }

    public boolean keyLeft(int i) {
        return i == Input.Keys.LEFT;
    }

    public boolean keyRight(int i) {
        return i == Input.Keys.RIGHT;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
