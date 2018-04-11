package com.jeffwhenderson.engineTestDrive;
//import com.jeffwhenderson.renderEngine.*; // import everything from  renderEngine


import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.jeffwhenderson.entities.Camera;
import com.jeffwhenderson.entities.Entity;
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
		RawModel model = OBJLoader.loadOBJModel("stall", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,0,0,1);
		Entity entity2 = new Entity(texturedModel, new Vector3f(6,-3,-10),0,150,0,.7f); ///// REMOVE THIS TEST CODE
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// game logic
			entity.increaseRotation(0, 0.4f, 0);;
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			renderer.render(entity2, shader); ///////////// REMOVE THIS TEST CODE
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
