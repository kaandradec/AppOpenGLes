package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndice;//TRABAJAR CON INDICES
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;


    //private int numPuntos = Activity_Figuras.numPrimitivas;//input Numero de primitavas

    public Triangulo(){
        float[] vertices ={
                //
//                1.0f, -1.0f,//0
//                -1.0f, -1.0f,//1
//                -1.0f, 1.0f,//2
//                1.0f, 1.0f//3

                //CASA

                -2.0f,7f,
                2.0f,7.0f,
                4.0f,5.0f,
                4.0f,0.0f,
                -4.0f,0.0f,
                -4.0f,5.0f,




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
        byte[] indices = {
//                3,0,1,
//                3,1,2

                //CASA
                0,1,2,
                0,2,5,
                5,2,3,
                5,3,4


        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0); //Desde donde posicion de arrays comienza a dibujar

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        bufferIndice = ByteBuffer.allocateDirect(indices.length); //Ya no tengo que multiplicar por 4 porque ya es byte
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(25);
        gl.glDrawElements(gl.GL_TRIANGLES,12,gl.GL_UNSIGNED_BYTE, bufferIndice); //DRAW ELEMENTS



        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}