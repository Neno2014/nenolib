package org.nenocom.objects;

import android.content.Context;
import android.graphics.Color;

public class ShadedRectangle extends ShadedObject{
	
	
	
	public ShadedRectangle(Context context, float ancho, float alto, int c1, int c2, int c3, int c4) {
		super(context, new float[]{
				//x y z
				0f, 0f, 0f,
				ancho, alto, 0f,
				0f, alto, 0f,
				
				0f, 0f, 0f,
				ancho, 0f, 0f,
				ancho, alto, 0f
		}, new float[]{
				//r g b
				Color.red(c1)/255f, Color.green(c1)/255f, Color.blue(c1)/255f,
				Color.red(c3)/255f, Color.green(c3)/255f, Color.blue(c3)/255f,
				Color.red(c4)/255f, Color.green(c4)/255f, Color.blue(c4)/255f,
				
				Color.red(c1)/255f, Color.green(c1)/255f, Color.blue(c1)/255f,
				Color.red(c2)/255f, Color.green(c2)/255f, Color.blue(c2)/255f,
				Color.red(c3)/255f, Color.green(c3)/255f, Color.blue(c3)/255f,
		});
		
	}

}
