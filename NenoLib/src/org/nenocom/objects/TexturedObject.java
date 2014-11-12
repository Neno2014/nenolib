package org.nenocom.objects;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.nenocom.nenolib.R;
import org.nenocom.utils.LectorDeRecursos;
import org.nenocom.utils.ShaderHelper;
import org.nenocom.utils.TextureHelper;

import android.content.Context;

public class TexturedObject extends GlObject {
	
	protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
	protected static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
	protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
	
	protected int uTextureUnitLocation;
	protected int aTextureCoordinatesLocation;
	protected final FloatBuffer textureCoordsData;
	
	private int textureId;
	
	public TexturedObject(Context context, float[] vertices, float[] texCoords, int textId) {
		super(context, vertices);
		textureCoordsData = ByteBuffer
				.allocateDirect(texCoords.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		textureCoordsData.put(texCoords);
		textureId=textId;
	}
	
	@Override
	public void onSurfaceCreated(){
		String vertexShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.texture_vertex_shader);
		String fragmentShaderSource = LectorDeRecursos
				.leerTxtDeRecursos(context, R.raw.texture_fragment_shader);
		
		shaderProgram = ShaderHelper.buildProgram(vertexShaderSource, fragmentShaderSource);
		glUseProgram(shaderProgram);
		
	}
	
	@Override
	public void onDrawFrame(){
		super.onDrawFrame();
		glUseProgram(shaderProgram);
		uTextureUnitLocation = glGetUniformLocation(shaderProgram, U_TEXTURE_UNIT);
		aPositionLocation = glGetAttribLocation(shaderProgram, A_POSITION);
		aTextureCoordinatesLocation = glGetAttribLocation(shaderProgram, A_TEXTURE_COORDINATES);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureId);
		glUniform1i(uTextureUnitLocation, 0);
		
		textureCoordsData.position(0);
		glVertexAttribPointer(aTextureCoordinatesLocation, TEXTURE_COORDINATES_COMPONENT_COUNT, GL_FLOAT, false, 0, textureCoordsData);
		glEnableVertexAttribArray(aTextureCoordinatesLocation);
		
		glDrawArrays(GL_TRIANGLES, 0, vertexData.capacity()/COMPONENTES_POR_VERTICE);
	}
}
