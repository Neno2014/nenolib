package org.nenocom.objects;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.opengl.Matrix;

public abstract class GlObject {
	
	protected static final String A_POSITION = "a_Position";
	protected static final String U_MATRIX = "u_Matrix";
	protected static final int BYTES_PER_FLOAT = 4;
	protected static final int COMPONENTES_POR_VERTICE = 3;
	
	protected int aPositionLocation;
	protected int shaderProgram;
	protected int uMatrixLocation;
	protected final FloatBuffer vertexData;
	protected Context context;
	
	protected float[] modelMatrix = new float[16];
	protected float[] projectionMatrix;
	protected float[] modelProjectionMatrix = new float[16];
	
	public GlObject(Context context, float[] vertices){
		this.context = context;
		vertexData = ByteBuffer
				.allocateDirect(vertices.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(vertices);
		Matrix.setIdentityM(modelMatrix, 0);
	}
	
	public void onSurfaceCreated(){
		
	}

	public void onDrawFrame() {
		glUseProgram(shaderProgram);
		uMatrixLocation = glGetUniformLocation(shaderProgram, U_MATRIX);
		multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0);
		glUniformMatrix4fv(uMatrixLocation, 1, false, modelProjectionMatrix, 0);
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, COMPONENTES_POR_VERTICE, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPositionLocation);
	}
	
	public void translate(float x, float y, float z){
		Matrix.translateM(modelMatrix, 0, x, y, z);
	}
	
	public void rotate(float angle, float x, float y, float z){
		Matrix.rotateM(modelMatrix, 0, angle, x, y, z);
	}
	
	public void setProjectionMatrix(float[] projectionMatrix){
		this.projectionMatrix = projectionMatrix;
	}
}
