package org.nenocom.objects;

public class Rectangle extends GlObject{
	
	public Rectangle(){
		super(new float[]{
				//x y z
				// Triangle 1
				0f, 0f, 0f,
				9f, 14f, 0f,
				0f, 14f, 0f,
				
				// Triangle 2
				0f, 0f, 0f,
				9f, 0f, 0f,
				9f, 14f, 0f
		});
	}
}
