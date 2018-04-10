package com.jeffwhenderson.engineTestDrive;
//import com.jeffwhenderson.renderEngine.*; // import everything from  renderEngine


import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.jeffwhenderson.entities.Entity;
import com.jeffwhenderson.models.RawModel;
import com.jeffwhenderson.models.TexturedModel;
import com.jeffwhenderson.renderEngine.DisplayManager;
import com.jeffwhenderson.renderEngine.Loader;
import com.jeffwhenderson.renderEngine.Renderer;
import com.jeffwhenderson.shaders.StaticShader;
import com.jeffwhenderson.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] vertices = { /// <-------------------------------- define triangles counter clockwise by default 4 OpenGL
				//left bottom Triangle
			 -0.5f,  0.5f, 0, // 	VO
			 -0.5f, -0.5f, 0, //	V1
			  0.5f, -0.5f, 0, //	V2	
			  0.5f,  0.5f, 0 //		V3
		};
		
		int[] indices = {
				0,1,3, // TOP LEFT TRIANGLE -----> VO, V1, V3
				3,1,2 // BOTTOM RIGHT TRINAGLE --> V3, V1, V2
		};
		
		float[] textureCoords = {
				0,0,	//V0
				0,1,	//V1
				1,1,	//V2
				1,0		//V3
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Coke"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-1),0,0,0,1);
		
		while(!Display.isCloseRequested()) {
			// game logic
			entity.increasePosition(0, 0, -0.01f); //<----------- this should push the image back into the screen as if it were farther away.
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
