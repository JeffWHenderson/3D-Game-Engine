package com.jeffwhenderson.renderEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeffwhenderson.entities.Camera;
import com.jeffwhenderson.entities.Entity;
import com.jeffwhenderson.entities.Light;
import com.jeffwhenderson.models.TexturedModel;
import com.jeffwhenderson.shaders.StaticShader;

public class MasterRenderer {
	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	public void render(Light sun, Camera camera) {
		renderer.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		
		shader.stop();
		entities.clear();
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
