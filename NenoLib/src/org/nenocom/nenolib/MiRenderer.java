package org.nenocom.nenolib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nenocom.objects.UniformColorRect;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView.Renderer;
import static android.opengl.GLES20.*;

public class MiRenderer implements Renderer {

	private UniformColorRect miRect;
	private UniformColorRect miRect2;

	public MiRenderer(Context context) {
		miRect = new UniformColorRect(context, 0.4f, 0.4f, Color.RED);
		miRect2 = new UniformColorRect(context, -0.4f, -0.4f, Color.BLUE);
		
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		glClear(GL_COLOR_BUFFER_BIT);
		miRect.onDrawFrame();
		miRect2.onDrawFrame();
		
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		glViewport(0, 0, width, height);
		
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		glClearColor(0f, 1f, 0f, 1f);
		miRect.onSurfaceCreated();
		miRect2.onSurfaceCreated();
		
	}

}
