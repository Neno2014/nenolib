package org.nenocom.nenolib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nenocom.objects.ShadedRectangle;
import org.nenocom.objects.TexturedRect;
import org.nenocom.objects.UniformColorRect;
import org.nenocom.utils.MatrixHelper;
import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView.Renderer;
import static android.opengl.GLES20.*;

public class MiRenderer implements Renderer {

	private UniformColorRect miRect;
	private ShadedRectangle miRect2;
	private ShadedRectangle miRect3;
	private float[] projectionMatrix = new float[16];
	private TexturedRect miTexturedRect;
	private Context context;

	public MiRenderer(Context context) {
		miRect = new UniformColorRect(context, 0.4f, 0.4f, Color.RED);
		miRect2 = new ShadedRectangle(context, -0.4f, -0.4f, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
		miRect3 = new ShadedRectangle(context, -0.4f, 0.4f, Color.BLUE, Color.YELLOW, Color.BLUE, Color.YELLOW);
		miRect3.translate(0, 0, -2);
		miRect2.translate(0, 0, -2);
		miRect.translate(0, 0, -2);
		
		this.context = context;
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		miRect.translate(0.003f, 0, 0);
		miRect2.translate(0, 0.003f, 0);
		
		miRect3.rotate(2f, 1f, 0, 0);
		miTexturedRect.rotate(2f, 0, 0, 1);
		miRect3.onDrawFrame();
		miRect.onDrawFrame();
		miRect2.onDrawFrame();
		miTexturedRect.onDrawFrame();
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		glViewport(0, 0, width, height);
		MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 100f);
		miRect3.setProjectionMatrix(projectionMatrix);
		miRect2.setProjectionMatrix(projectionMatrix);
		miRect.setProjectionMatrix(projectionMatrix);
		miTexturedRect.setProjectionMatrix(projectionMatrix);
		
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		glClearColor(0f, 1f, 0f, 1f);
		
		miTexturedRect = new TexturedRect(context, 0.4f, -0.4f);
		miTexturedRect.translate(0, 0, -2);
		miRect.onSurfaceCreated();
		miRect2.onSurfaceCreated();
		miRect3.onSurfaceCreated();
		miTexturedRect.onSurfaceCreated();
	}

}
