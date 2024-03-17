package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public class Sounds {

    public static void fightSound() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sound/SansFight.mp3"));
        music.setLooping(true);
        music.play();
    }

    public static void selectSound() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sound/Select_Sound.mp3"));
        music.play();
    }

    public static void soulDamaged() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sound/Soul_Damaged.mp3"));
        music.play();
    }

    public static void alertSound() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sound/Alert_Sound.mp3"));
        music.play();
    }

}
