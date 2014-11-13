package org.nenocom.nenolib;

import static android.opengl.GLES20.*;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.nenocom.objects.TexturedRect;
import org.nenocom.utils.MatrixHelper;
import org.nenocom.utils.TextureHelper;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class MiRenderer implements Renderer {

	
	private float[] projectionMatrix = new float[16];
	
	private ArrayList<TexturedRect> rectangulos = new ArrayList<TexturedRect>();;
	private Context context;

	public MiRenderer(Context context) {
		
		
		this.context = context;
		
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		glClear(GL_COLOR_BUFFER_BIT);
		for(TexturedRect tr : rectangulos){
			tr.rotate(2f, 0.785426f, -(1), -1);
			tr.onDrawFrame();
		}
	}

	@Override
	public void onSurfaceChanged(GL10 arg0, int width, int height) {
		glViewport(0, 0, width, height);
		MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1f, 100f);
		for(TexturedRect tr : rectangulos){
			tr.setProjectionMatrix(projectionMatrix);
			
		}
		
	}

	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		glClearColor(0f, 1f, 0f, 1f);
		int textureId = TextureHelper.loadTexture(context, R.drawable.textura);
		for(int i=0;i<100;i++){
			TexturedRect miTexturedRect;
			
			miTexturedRect = new TexturedRect(context, (float)Math.random()*1-0.5f, (float)Math.random()*1-0.5f, textureId);
			miTexturedRect.translate((float)Math.random()-0.5f, (float)Math.random()-0.5f, -2);
			miTexturedRect.onSurfaceCreated();
			rectangulos.add(miTexturedRect);
		}
		
	}

}
