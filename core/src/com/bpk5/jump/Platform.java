package com.bpk5.jump;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bpk on 21.06.17.
 */

public class Platform extends Rectangle {

    public Texture texture;

    public Platform(Texture texture) {
        this.texture = texture;
        this.height = texture.getHeight();
        this.width = texture.getWidth();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
}
