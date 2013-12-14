package mdesl.test;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class SimpleGame extends Game {
	

	@Override
	protected void resize() throws LWJGLException {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	@Override
	protected void render() throws LWJGLException {
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	protected void create() throws LWJGLException {
		// 2D games generally won't require depth testing 
		glDisable(GL_DEPTH_TEST);
		
		// Enable blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		// Set clear to transparent black
		glClearColor(0f, 0f, 0f, 0f);
		
	}

	@Override
	protected void dispose() throws LWJGLException {
		
	}
}
