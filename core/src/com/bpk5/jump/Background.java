package com.bpk5.jump;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bpk on 23.06.17.
 */

public class Background extends Rectangle {

    private Texture texture;
    public final float xBackground;
    public final float yBackground;

    public Background(Texture texture) {
        this.texture = texture;
        this.xBackground = -texture.getWidth() / 2;
        this.yBackground = -150;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

}
