package com.jeffwhenderson.engineTestDrive;
//import com.jeffwhenderson.renderEngine.*; // import everything from  renderEngine


import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.jeffwhenderson.entities.Camera;
import com.jeffwhenderson.entities.Entity;
import com.jeffwhenderson.entities.Light;
import com.jeffwhenderson.models.RawModel;
import com.jeffwhenderson.models.TexturedModel;
import com.jeffwhenderson.renderEngine.DisplayManager;
import com.jeffwhenderson.renderEngine.Loader;
import com.jeffwhenderson.renderEngine.OBJLoader;
import com.jeffwhenderson.renderEngine.Renderer;
import com.jeffwhenderson.shaders.StaticShader;
import com.jeffwhenderson.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
//		//////////////////////   STALL   ////////////////////
//		RawModel model = OBJLoader.loadOBJModel("stall", loader);
//		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
//		texture.setShineDamper(50);
//		texture.setReflectivity(.5f);
//		TexturedModel texturedModel = new TexturedModel(model, texture);
//		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,150,0,1);
		//////////////DRAGON ////////////////////////
	
		RawModel dragonModel = OBJLoader.loadOBJModel("dragon", loader);
		
		TexturedModel texturedDragonModel = new TexturedModel(dragonModel, new ModelTexture(loader.loadTexture("wood")));
		ModelTexture dragonTexture = texturedDragonModel.getModelTexture();
		dragonTexture.setShineDamper(10);
		dragonTexture.setReflectivity(1);
		Entity dragonEntity = new Entity(texturedDragonModel, new Vector3f(0,-10,-40),0,0,0,2);

		Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// game logic
			dragonEntity.increaseRotation(0, -1f, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
//			renderer.render(entity, shader);
			renderer.render(dragonEntity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
