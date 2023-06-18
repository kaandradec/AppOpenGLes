package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.opengles.GL10;

public class Icosfera {
    private FloatBuffer bufferVertices;

    private FloatBuffer bufferColores;


    private ByteBuffer bufferIndices;
    private float colorRandom, colorRandom2, colorRandom3 ;

    private final static int byteFlotante =4;
    private final static int compPorVertice=3;
    private final static int compPorColores=4;

    //Creamos los vertices de nuestra Figura 1:
    public Icosfera() {
        float[] vertices = {
                0.0000f, 0.0000f, -1.0000f,
                0.7236f, -0.5257f, -0.4472f,
                -0.2764f, -0.8506f, -0.4472f,
                -0.8944f, 0.0000f, -0.4472f,
                -0.2764f, 0.8506f, -0.4472f,
                0.7236f, 0.5257f, -0.4472f,
                0.2764f, -0.8506f, 0.4472f,
                -0.7236f, -0.5257f, 0.4472f,
                -0.7236f, 0.5257f, 0.4472f,
                0.2764f, 0.8506f, 0.4472f,
                0.8944f, 0.0000f, 0.4472f,
                0.0000f, 0.0000f, 1.0000f,
        };
        //Haremos uso de un Array de Colores para asignarlo a cada primitiva (OpenGL):
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
        //Indexacion de Vertices:
        byte[] indices = {
                0, 1, 2,
                0, 2, 3,
                0, 3, 4,
                0, 4, 5,
                0, 5, 1,

                5,10,9,
                5,9,4,
                4,9,8,
                4,8,3,
                3,8,7,
                3,7,2,
                2,7,6,
                2,6,1,
                1,6,10,
                1,10,5,

                11,6,7,
                11,7,8,
                11,8,9,
                11,9,10,
                11,10,6
        };
        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        bufferIndices= ByteBuffer.allocateDirect(indices.length);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);
        //Temporizador:
        Timer timer  = new Timer();
        //Para un "n" determinado tiempo cambiara de color:
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {

                colorRandom = (float)Math.random();
                colorRandom2 = (float)Math.random();
                colorRandom2 = (float)Math.random();
            }
        };
        timer.schedule(tarea, 0,1000L);
    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferIndices.position(0);
        gl.glColor4f(colorRandom,colorRandom2,colorRandom3,1);
        gl.glDrawElements(gl.GL_LINE_STRIP,60,gl.GL_UNSIGNED_BYTE,bufferIndices);

        gl.glLineWidth(12);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }


}