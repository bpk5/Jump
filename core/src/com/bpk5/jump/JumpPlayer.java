package com.bpk5.jump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bpk on 21.06.17.
 */

public class JumpPlayer extends Rectangle {
    private Sound jumpSound;
    private Texture texture;
    public boolean canJump = true;

    public float jumpVelocity;

    public  JumpPlayer(Texture texture, Sound jumpSound) {
        this.texture = texture;
        this.height = texture.getHeight();
        this.width = texture.getWidth();
        this.jumpSound = jumpSound;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void jump(){
        if(canJump && jumpVelocity >= -100){
            jumpVelocity += 800;
            canJump = false;
            jumpSound.play();
        }
    }
}
