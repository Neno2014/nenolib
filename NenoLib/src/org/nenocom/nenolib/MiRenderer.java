package org.nenocom.nenolib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import static android.opengl.GLES20.*;

public class MiRenderer implements Renderer {

	@Override
	public void onDrawFrame(GL10 arg0) {
		glClear(GL_COLOR_BUFFER_BIT);
		
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		glViewport(0, 0, width, height);
		
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		glClearColor(0f, 1f, 0f, 1f);
		
	}

}
