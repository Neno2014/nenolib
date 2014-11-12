package org.nenocom.objects;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;

public abstract class GlObject {
	
	protected static final String A_POSITION = "a_Position";
	protected static final int BYTES_PER_FLOAT = 4;
	protected static final int COMPONENTES_POR_VERTICE = 3;
	
	protected int aPositionLocation;
	protected int shaderProgram;
	
	protected final FloatBuffer vertexData;
	protected Context context;
	
	public GlObject(Context context, float[] vertices){
		this.context = context;
		vertexData = ByteBuffer
				.allocateDirect(vertices.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(vertices);
	}
	
	public void onSurfaceCreated(){
		
	}

	public void onDrawFrame() {
		glUseProgram(shaderProgram);
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, COMPONENTES_POR_VERTICE, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPositionLocation);
	}
}
