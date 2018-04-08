package com.jeffwhenderson.engineTestDrive;
//import com.jeffwhenderson.renderEngine.*; // import everything from  renderEngine

import org.lwjgl.opengl.Display;

import com.jeffwhenderson.models.RawModel;
import com.jeffwhenderson.renderEngine.DisplayManager;
import com.jeffwhenderson.renderEngine.Loader;
import com.jeffwhenderson.renderEngine.Renderer;
import com.jeffwhenderson.shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
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
		
		RawModel model = loader.loadToVAO(vertices, indices);

		
		while(!Display.isCloseRequested()) {
			// game logic
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
