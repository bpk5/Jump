package com.bpk5.jump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;

public class Jump extends ApplicationAdapter {
	private Assets assets;
	private Music music;
	private Sound jumpSound;
	private Texture playerTexture, platformTexture, backgroundTexture;
	private JumpPlayer player;
	private Background background;
	private Array<Platform> platformsArray;
	private OrthographicCamera camera;

	private float gravity = -20;

	SpriteBatch batch;
	
	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();

		// If everything files is loading.
		if (assets.manager.update()) {
			loadDate();
			initial();
		}
	}

	private void initial() {
		background = new Background(backgroundTexture);
		batch = new SpriteBatch();
		music.play();
		camera = new OrthographicCamera(480, 700);

		player = new JumpPlayer(playerTexture, jumpSound);
		platformsArray = new Array<Platform>();

		for(int i = 1; i < 20; i++) {
			Platform p = new Platform(platformTexture);
			p.x = MathUtils.random(480);
			p.y = 200 * i;
			platformsArray.add(p);
		}
	}

	private void loadDate() {
		playerTexture = assets.manager.get("sheep.png", Texture.class);
		platformTexture = assets.manager.get("rectagle-green.png", Texture.class);
		backgroundTexture = assets.manager.get("tlo.jpg", Texture.class);
		music = assets.manager.get("music.mp3", Music.class);
		jumpSound = assets.manager.get("Boing-sound-effect.mp3", Sound.class);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.draw(batch);
		batch.setProjectionMatrix(camera.combined);
		for(Platform p : platformsArray) {
			p.draw(batch);
		}
		player.draw(batch);
		batch.end();
	}

	private void update() {
		handleInput();
		camera.update();
		camera.position.set(player.x + player.width / 2, player.y + 300, 0);

		player.y += player.jumpVelocity * Gdx.graphics.getDeltaTime();

		if(player.y > 0) {
			player.jumpVelocity += gravity;
		}
		else {
			player.y = 0;
			player.canJump = true;
			player.jumpVelocity = 0;
		}

		for (Platform p : platformsArray) {
			if (isPlayerOnPlatform(p)) {
				player.canJump = true;
				player.jumpVelocity = 0;
				player.y = p.y + p.height;
			}
		}

		background.x = background.xBackground + player.x / 5;
		background.y = background.yBackground + player.y / 5;
	}

	private boolean isPlayerOnPlatform(Platform p) {
		return player.jumpVelocity <= 0 && player.overlaps(p) && !(player.y <= p.y);
	}

	private void handleInput() {
		// Desktop
		if(Gdx.input.isKeyPressed(Input.Keys.A) && (player.x > -1260)) {
			player.x -= 300 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) && (player.x < 1170)) {
			player.x += 300 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.jump();
		}


		// Android
		if(Gdx.input.justTouched()) {
			player.jump();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
