package org.nenocom.objects;

import static android.opengl.GLES20.*;

import org.nenocom.nenolib.R;
import org.nenocom.utils.LectorDeRecursos;
import org.nenocom.utils.ShaderHelper;

import android.content.Context;
import android.graphics.Color;

public class UniformColorRect extends Rectangle {
	
	private static final String U_COLOR = "u_Color";
	
	private int uColorLocation;
	
	private float red;
	private float green;
	private float blue;
	
	public UniformColorRect(Context context, float ancho, float alto, int color){
		super(context, new float[]{
				//x y z
				0f, 0f, 0f,
				ancho, alto, 0f,
				0f, alto, 0f,
				
				0f, 0f, 0f,
				ancho, 0f, 0f,
				ancho, alto, 0f
		});
		this.red = Color.red(color)/255f;
		this.green = Color.green(color)/255f;
		this.blue = Color.blue(color)/255f;
		
	}
	
	@Override
	public void onSurfaceCreated(){
		String vertexShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.vertex_shader);
		String fragmentShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.fragment_shader);
		
		super.shaderProgram = ShaderHelper.buildProgram(vertexShaderSource, fragmentShaderSource);
		glUseProgram(shaderProgram);
		uColorLocation = glGetUniformLocation(shaderProgram, U_COLOR);
		
		super.aPositionLocation = glGetAttribLocation(shaderProgram, A_POSITION);
		
	}
	
	@Override
	public void onDrawFrame(){
		super.onDrawFrame();
		
		glUniform4f(uColorLocation, red, green, blue, 1.0f);
		glDrawArrays(GL_TRIANGLES, 0, vertexData.capacity()/COMPONENTES_POR_VERTICE);
	}
}
