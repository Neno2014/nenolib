package org.nenocom.objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class GlObject {
	
	private static final int BYTES_PER_FLOAT = 4;
	private final FloatBuffer vertexData;
	
	public GlObject(float[] vertices){
		vertexData = ByteBuffer
				.allocateDirect(vertices.length * BYTES_PER_FLOAT) .order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(vertices);
	}
}
