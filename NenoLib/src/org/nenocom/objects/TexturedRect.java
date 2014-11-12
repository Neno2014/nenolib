package org.nenocom.objects;

import android.content.Context;

public class TexturedRect extends TexturedObject {

	public TexturedRect(Context context, float ancho, float alto, int texId) {
		super(context, new float[]{
				//x y z
				0f, 0f, 0f,
				ancho, alto, 0f,
				0f, alto, 0f,
				
				0f, 0f, 0f,
				ancho, 0f, 0f,
				ancho, alto, 0f
		}, new float[]{
				//s t
				0f, 0f,
				1f, 1f,
				0f, 1f,
				
				0f, 0f,
				1f, 0f,
				1f, 1f
		},texId);
	}

}
