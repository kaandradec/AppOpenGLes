package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Plano {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndices;//TRABAJAR CON INDICES
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;

    private float[] colorRGBA;


    public Plano(float[] colorRGBA){
        this.colorRGBA = colorRGBA;

        float[] vertices ={
                -1f, 1f,//0
                1f, 1f,//1
                1f, -1f,//2
                -1f, -1f//3

        };


        byte[] indices = {
                0,1,3,
                3,1,2
        };

        bufferVertices = Funciones.generarBuffer(vertices);

        bufferIndices= ByteBuffer.allocateDirect(indices.length);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferIndices.position(0);
        gl.glColor4f(colorRGBA[0],colorRGBA[1], colorRGBA[2], colorRGBA[3]);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE, bufferIndices);


        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}