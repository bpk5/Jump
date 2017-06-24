package com.bpk5.jump;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by bpk on 23.06.17.
 */

public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void load() {
        manager.load("sheep.png", Texture.class);
        manager.load("rectagle-green.png", Texture.class);
        manager.load("tlo.jpg", Texture.class);
        manager.load("music.mp3", Music.class);
        manager.load("Boing-sound-effect.mp3", Sound.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
