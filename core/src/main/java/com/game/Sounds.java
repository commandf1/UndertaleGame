package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import java.util.List;

public class Sounds {
    private static Music dialogueSound, healSound, musicBackground, soulDamagedSound, selectSound, alertSound, gameOverTheme, gasterBlasterSound, soulShatterSound, dogSound;

    public static void fightSound() {
        stopAllSounds();
        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("sound/SansFight.mp3"));
        musicBackground.setLooping(true);
        musicBackground.setVolume(0.7f);
        musicBackground.play();
    }

    public static boolean isGameOverPlaying() {
        return gameOverTheme != null && gameOverTheme.isPlaying();
    }

    public static boolean isDialoguePlaying() {
        return dialogueSound != null && dialogueSound.isPlaying();
    }


    public static void dialogueSound() {
        dialogueSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Dialogue_effect_sound.mp3"));
        dialogueSound.play();
        if (!dialogueSound.isPlaying()) {
            dialogueSound.dispose();
        }
    }

    public static void soundHeal() {
        healSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Heal_Sound.mp3"));
        healSound.play();
        if (!healSound.isPlaying()) {
            healSound.dispose();
        }
    }

    public static void selectSound() {
        selectSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Select_Sound.mp3"));
        selectSound.play();
        if (!selectSound.isPlaying()) {
            selectSound.dispose();
        }
    }

    public static void soulDamaged() {
        if (soulDamagedSound == null ) {
            soulDamagedSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Soul_Damaged.mp3"));
        }
        if (!soulDamagedSound.isPlaying()) {
            soulDamagedSound.play();
        }
    }

    public static void alertSound() {
        alertSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Alert_Sound.mp3"));
        alertSound.play();
        if (!alertSound.isPlaying()) {
            alertSound.dispose();
        }
    }

    public static void introSound() {
        stopAllSounds();
        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("sound/Once Upon A Time.mp3"));
        musicBackground.play();
        musicBackground.setLooping(true);
    }

    public static void creditsSound() {
        stopAllSounds();
        dogSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Dogsong.mp3"));
        dogSound.play();
        dogSound.setLooping(true);
    }

    public static void menuSound() {
        stopAllSounds();
        musicBackground = Gdx.audio.newMusic(Gdx.files.internal("sound/Start_Menu.mp3"));
        musicBackground.setLooping(true);
        musicBackground.play();
    }

    public static void gasterBlasterAttackSound() {
        gasterBlasterSound = Gdx.audio.newMusic(Gdx.files.internal("sound/gaster_blaster_sound_effect.mp3"));
        gasterBlasterSound.setVolume(0.5f);
        gasterBlasterSound.play();
        if (!gasterBlasterSound.isPlaying() && gasterBlasterSound != null) {
            gasterBlasterSound.dispose();
        }
    }

    public static void soulShatterSound() {
        soulShatterSound = Gdx.audio.newMusic(Gdx.files.internal("sound/Soul_Shatter.mp3"));
        soulShatterSound.play();
        if (!soulShatterSound.isPlaying() && soulShatterSound !=null) {
            soulShatterSound.dispose();
        }
    }

    public static void gameOverTheme() {
        gameOverTheme = Gdx.audio.newMusic(Gdx.files.internal("sound/Game_Over_Theme.mp3"));
        gameOverTheme.play();
        gameOverTheme.setLooping(true);
        if (!gameOverTheme.isPlaying() && gameOverTheme !=null) {
            gameOverTheme.dispose();
        }
    }
    public static void stopAllSounds()   {
        if (selectSound != null) {
            selectSound.stop();
            selectSound.dispose();
        }
        if (gasterBlasterSound != null) {
            gasterBlasterSound.stop();
            gasterBlasterSound.dispose();
        }
        if (musicBackground!=null) {
            musicBackground.stop();
            musicBackground.dispose();
        }
        if (soulDamagedSound!=null) {
            soulDamagedSound.stop();
            soulDamagedSound.dispose();
        }
        if (alertSound!=null) {
            alertSound.stop();
            alertSound.dispose();
        }
        if (soulShatterSound != null) {
            soulShatterSound.stop();
            soulShatterSound.dispose();
        }
        if (gameOverTheme != null) {
            gameOverTheme.stop();
            gameOverTheme.dispose();
        }
        if (dogSound != null) {
            dogSound.stop();
            dogSound.dispose();
        }
        if (dialogueSound != null) {
            dialogueSound.stop();
            dialogueSound.dispose();
        }
        if (healSound != null) {
            healSound.stop();
            healSound.dispose();
        }
    }
}
