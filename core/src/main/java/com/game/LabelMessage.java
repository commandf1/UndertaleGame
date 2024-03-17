package com.game;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LabelMessage extends Label {
    private StateMessage state;

    public LabelMessage(Label label, StateMessage state) {
        super(label.getText(), label.getStyle());
        this.state = state;
    }

    public StateMessage getState() {
        return state;
    }

    public void setState(StateMessage state) {
        this.state = state;
    }
}
