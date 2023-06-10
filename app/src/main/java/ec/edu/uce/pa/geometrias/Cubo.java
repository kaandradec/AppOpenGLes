package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cubo {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferVertices2;//2
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndices;
    private ByteBuffer bufferIndices2; //2
    private final static int byteFlotante =4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice=3;
    private final static int compPorColores=4;
    public Cubo() {
        float[] vertices = {
                -1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,   //0
                1.0f,-1.0f,1.0f,   //0
                -1.0f,-1.0f,1.0f,   //0

                -1.0f,1.0f,-1.0f,   //0
                1.0f,1.0f,-1.0f,   //0
                1.0f,-1.0f,-1.0f,   //0
                -1.0f,-1.0f,-1.0f   //0
        };
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,//Rojo
                1.0f, 0.0f, 0.0f, 1.0f,//Rojo
                1.0f, 0.0f, 0.0f, 1.0f,//Rojo
                1.0f, 0.0f, 0.0f, 1.0f,//Rojo
                0.0f, 1.0f, 0.0f, 1.0f,//Verde
                0.0f, 1.0f, 0.0f, 1.0f,//Verde
                0.0f, 1.0f, 0.0f, 1.0f,//Verde
                0.0f, 1.0f, 0.0f, 1.0f,//Verde

                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris






                //0.0f, 0.0f, 1.0f, 1.0f,//Azul

//                0.5f, 1.0f, 0.0f, 0.8f,
//                0.0f, 0.5f, 0.5f, 1.0f,
//                0.0f, 0.5f, 0.5f, 1.0f,
//                0.5f, 1.0f, 0.0f, 0.8f,
//                0.5f, 1.0f, 0.0f, 0.8f,

        };
        byte[] indices = {
                0,2,3,
                0,1,2,
                0,5,1,
                0,4,5,
                0,7,3,
                0,4,7,

                6,2,3,
                6,3,7,
                6,2,1,
                6,1,5,
                6,7,4,
                6,4,5
        };
        byte[] indices2 = {
                0,2,3,
                0,1,2,
                0,5,1,
                0,4,5,
                0,7,3,
                0,4,7,

                6,2,3,
                6,3,7,
                6,2,1,
                6,1,5,
                6,7,4,
                6,4,5
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        bufferIndices= ByteBuffer.allocateDirect(indices.length*4);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);//Desde que posicion de byte[] indices = {} empieza a tomar valores  0 1 2 3 ...

        //2
//        bufferVertices2 = buffer.asFloatBuffer();
//        bufferVertices2.put(vertices);
//
        bufferIndices2= ByteBuffer.allocateDirect(indices.length*4);
        bufferIndices2.order(ByteOrder.nativeOrder());
        bufferIndices2.put(indices2);
        bufferIndices2.position(6);

    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);//(elementos del float[] que se toman para formar los colores(4) , FLOAT)
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices);
        //bufferIndices.position(6);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices2);

        gl.glLineWidth(300);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}