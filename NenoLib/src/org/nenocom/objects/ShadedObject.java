package org.nenocom.objects;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.nenocom.nenolib.R;
import org.nenocom.utils.LectorDeRecursos;
import org.nenocom.utils.ShaderHelper;

import android.content.Context;

public class ShadedObject extends GlObject {

	protected static final String A_COLOR = "a_Color";
	protected static final int COLOR_COMPONENT_COUNT = 3;
	protected int aColorLocation;
	
	protected final FloatBuffer colorData;
	
	public ShadedObject(Context context, float[] vertices, float[] colors) {
		super(context, vertices);
		colorData = ByteBuffer
				.allocateDirect(colors.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		colorData.put(colors);
	}
	
	@Override
	public void onSurfaceCreated(){
		String vertexShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.shaded_vertex_shader);
		String fragmentShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.shaded_fragment_shader);
		
		super.shaderProgram = ShaderHelper.buildProgram(vertexShaderSource, fragmentShaderSource);
		glUseProgram(shaderProgram);
		super.aPositionLocation = glGetAttribLocation(shaderProgram, A_POSITION);
		aColorLocation = glGetAttribLocation(shaderProgram, A_COLOR);
	}
	
	@Override
	public void onDrawFrame() {
		super.onDrawFrame();
		
		colorData.position(0);
		glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT, false, 0, colorData);
		glEnableVertexAttribArray(aColorLocation);
		
		glDrawArrays(GL_TRIANGLES, 0, vertexData.capacity()/COMPONENTES_POR_VERTICE);
	}
	
}
