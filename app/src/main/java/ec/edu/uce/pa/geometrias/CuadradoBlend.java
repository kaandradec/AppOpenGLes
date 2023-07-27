package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CuadradoBlend {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndice;//TRABAJAR CON INDICES
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;
    private float[] color;


    //private int numPuntos = Activity_Figuras.numPrimitivas;//input Numero de primitavas

    public CuadradoBlend(float[] color){
        this.color = color;
        float[] vertices ={
                1.0f, -1.0f,//0
                -1.0f, -1.0f,//1
                -1.0f, 1.0f,//2
                1.0f, 1.0f//3
        };
        byte[] indices = {
                0,1,3,
                3,1,2
        };

        float[] colores ={
                1.0f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                1.0f,1.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                1.0f,1.0f,0.0f,1.0f,

        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0); //Desde donde posicion de arrays comienza a dibujar


        bufferIndice = ByteBuffer.allocateDirect(indices.length); //Ya no tengo que multiplicar por 4 porque ya es byte
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        gl.glColor4f(color[0],color[1],color[2],color[3]);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE, bufferIndice);



        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}