package com.jeffwhenderson.engineTestDrive;
//import com.jeffwhenderson.renderEngine.*; // import everything from  renderEngine

import org.lwjgl.opengl.Display;

import com.jeffwhenderson.renderEngine.DisplayManager;
import com.jeffwhenderson.renderEngine.Loader;
import com.jeffwhenderson.renderEngine.RawModel;
import com.jeffwhenderson.renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = { /// <-------------------------------- define triangles counter clockwise by default 4 OpenGL
				//left bottom Triangle
			 -0.5f,  0.5f, 0f,
			 -0.5f, -0.5f, 0f,
			  0.5f, -0.5f, 0f,
			  	// Right Top Triangle
			  0.5f, -0.5f, 0f,
			  0.5f,  0.5f, 0f,
			 -0.5f,  0.5f, 0f
		};
		
		RawModel model = loader.loadToVAO(vertices);

		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			// game logic
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
