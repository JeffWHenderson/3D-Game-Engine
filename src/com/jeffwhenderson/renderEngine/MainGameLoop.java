package com.jeffwhenderson.renderEngine;

import org.lwjgl.opengl.Display;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		while(!Display.isCloseRequested()) {
			// game logic
			// render
			
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}

}
