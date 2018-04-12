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
		////////////////////////////////// OLD CODE TO MAKE THE 'FACEBOOK CUBE' //////////////
//		float[] vertices = {			
//				-0.5f,0.5f,-0.5f,	
//				-0.5f,-0.5f,-0.5f,	
//				0.5f,-0.5f,-0.5f,	
//				0.5f,0.5f,-0.5f,		
//				
//				-0.5f,0.5f,0.5f,	
//				-0.5f,-0.5f,0.5f,	
//				0.5f,-0.5f,0.5f,	
//				0.5f,0.5f,0.5f,
//				
//				0.5f,0.5f,-0.5f,	
//				0.5f,-0.5f,-0.5f,	
//				0.5f,-0.5f,0.5f,	
//				0.5f,0.5f,0.5f,
//				
//				-0.5f,0.5f,-0.5f,	
//				-0.5f,-0.5f,-0.5f,	
//				-0.5f,-0.5f,0.5f,	
//				-0.5f,0.5f,0.5f,
//				
//				-0.5f,0.5f,0.5f,
//				-0.5f,0.5f,-0.5f,
//				0.5f,0.5f,-0.5f,
//				0.5f,0.5f,0.5f,
//				
//				-0.5f,-0.5f,0.5f,
//				-0.5f,-0.5f,-0.5f,
//				0.5f,-0.5f,-0.5f,
//				0.5f,-0.5f,0.5f
//				
//		};
//		
//		float[] textureCoords = {
//				
//				0,0,
//				0,1,
//				1,1,
//				1,0,			
//				0,0,
//				0,1,
//				1,1,
//				1,0,			
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0
//
//				
//		};
//		
//		int[] indices = {
//				0,1,3,	
//				3,1,2,	
//				4,5,7,
//				7,5,6,
//				8,9,11,
//				11,9,10,
//				12,13,15,
//				15,13,14,	
//				16,17,19,
//				19,17,18,
//				20,21,23,
//				23,21,22
//
//		};
		// CREATE MODEL HERE
//		ModelTexture texture = new ModelTexture(loader.loadTexture("facebook-256px"));
		//CREATE TEXTURED MODEL HERE
		//CREATE ENTITY HERE
////////////////////////////////// OLD CODE TO MAKE THE 'FACEBOOK CUBE' //////////////
		
		//////////////////////   STALL   ////////////////////
		RawModel model = OBJLoader.loadOBJModel("stall", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		//////////////DRAGON ////////////////////////
		RawModel dragonModel = OBJLoader.loadOBJModel("dragon", loader);
		TexturedModel texturedDragonModel = new TexturedModel(dragonModel, new ModelTexture(loader.loadTexture("wood")));
		Entity dragonEntity = new Entity(texturedDragonModel, new Vector3f(0,-20,-60),0,0,0,2);

		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// game logic
			entity.increaseRotation(0, 0.2f, 0);;
			dragonEntity.increaseRotation(0, -0.2f, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			renderer.render(dragonEntity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
