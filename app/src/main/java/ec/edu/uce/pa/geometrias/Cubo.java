package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cubo {
    private FloatBuffer bufferVertices;
    //private FloatBuffer bufferVertices2;//2
    private FloatBuffer bufferColores;
    private FloatBuffer bufferColores2;//2

    private ByteBuffer bufferIndices;
    private ByteBuffer bufferIndices2; //2
    private ByteBuffer bufferIndices3; //2
    private ByteBuffer bufferIndices4; //2
    private ByteBuffer bufferIndices5; //2
    private ByteBuffer bufferIndices6; //2
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
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo
                0.89f, 0.1f, 0.14f, 1.0f,//Rojo

                0.32f, 0.73f, 0.0f, 0.28f,//Verde
                0.32f, 0.73f, 0.0f, 0.28f,//Verde
                0.32f, 0.73f, 0.0f, 0.28f,//Verde
                0.32f, 0.73f, 0.0f, 0.28f,//Verde
                0.32f, 0.73f, 0.0f, 0.28f,//Verde
                0.32f, 0.73f, 0.0f, 0.28f,//Verde

                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul
                0.0f, 0.36f, 0.66f, 1.0f,//Azul

                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo
                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo
                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo
                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo
                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo
                1.0f, 0.93f, 0.0f, 1.0f,//Amarillo


                1.0f, 1.0f, 0.8f, 1.0f,//Blanco
                1.0f, 1.0f, 0.8f, 1.0f,//Blanco
                1.0f, 1.0f, 0.8f, 1.0f,//Blanco
                1.0f, 1.0f, 0.8f, 1.0f,//Blanco
                1.0f, 1.0f, 0.8f, 1.0f,//Blanco
                1.0f, 1.0f, 0.8f, 1.0f,//Blanco

                0.57f, 0.16f, 0.57f, 1.0f,//Morado
                0.57f, 0.16f, 0.57f, 1.0f,//Morado
                0.57f, 0.16f, 0.57f, 1.0f,//Morado
                0.57f, 0.16f, 0.57f, 1.0f,//Morado
                0.57f, 0.16f, 0.57f, 1.0f,//Morado
                0.57f, 0.16f, 0.57f, 1.0f,//Morado


                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris
                0.1f, 0.1f, 0.1f, 1.0f,//Gris

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



        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        //1------------------------------------------------------------------
        bufferIndices= ByteBuffer.allocateDirect(indices.length);
        //bufferIndices= ByteBuffer.allocateDirect(indices.length*4);////Ya no tengo que multiplicar por 4 porque ya es byte
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

        //2------------------------------------------------------------------
        bufferIndices2= ByteBuffer.allocateDirect(indices.length);
        bufferIndices2.order(ByteOrder.nativeOrder());
        bufferIndices2.put(indices);
        bufferIndices2.position(6);

        //------------------------------------------------------------------
        //3------------------------------------------------------------------
        bufferIndices3= ByteBuffer.allocateDirect(indices.length);
        bufferIndices3.order(ByteOrder.nativeOrder());
        bufferIndices3.put(indices);
        bufferIndices3.position(12);
        //------------------------------------------------------------------
        //4------------------------------------------------------------------
        bufferIndices4= ByteBuffer.allocateDirect(indices.length);
        bufferIndices4.order(ByteOrder.nativeOrder());
        bufferIndices4.put(indices);
        bufferIndices4.position(18);
        //------------------------------------------------------------------
        //5------------------------------------------------------------------
        bufferIndices5= ByteBuffer.allocateDirect(indices.length);
        bufferIndices5.order(ByteOrder.nativeOrder());
        bufferIndices5.put(indices);
        bufferIndices5.position(24);
        //------------------------------------------------------------------
        //6------------------------------------------------------------------
        bufferIndices6= ByteBuffer.allocateDirect(indices.length);
        bufferIndices6.order(ByteOrder.nativeOrder());
        bufferIndices6.put(indices);
        bufferIndices6.position(30);
        //------------------------------------------------------------------

    }

    //Frontal ROJO
    //Top VERDE
    //Izquierda AZUL
    //Atras MORADO
    //Derecha BLANCO
    //Abajo AMARILLO
    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);//(elementos del float[] que se toman para formar los colores(4) , FLOAT)
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        //ROJO
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices);

        //VERDE
        bufferColores.clear();
        bufferColores.position(24);//24

        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices2);

        //AZUL
        bufferColores.clear();
        bufferColores.position(48);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices3);

        //AMARILLO
        bufferColores.clear();
        bufferColores.position(72);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices4);

        //BLANCO
        bufferColores.clear();
        bufferColores.position(100);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices5);

        //MORADO
        bufferColores.clear();
        bufferColores.position(120);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE,bufferIndices6);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}