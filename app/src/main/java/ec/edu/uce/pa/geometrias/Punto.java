package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.OpenGL10Activity;

public class Punto {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;

    //private int numPuntos = RenderPunto.numPuntos;

    private int numPuntos = OpenGL10Activity.numPrimitivas;//input Numero de primitavas


    public Punto(){
        if(numPuntos ==0 ){
            numPuntos = 12;
        }


        float[] vertices ={
                4.0f, 4.0f,  //0
                4.0f, -4.0f, //1
                -4.0f, -4.0f,//2
                -4.0f, 4.0f, //3

                4.0f, 0.0f,
                0.0f, -4.0f,
                -4.0f, 0.0f,
                0.0f, 4.0f,

                1.0f, 1.0f,
                1.0f, -1.0f,
                -1.0f, -1.0f,
                -1.0f, 1.0f,

                0,0

        };

        float[] colores ={
                1.0f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                1.0f,1.0f,0.0f,1.0f,

                0.5f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,0.5f,0.0f,1.0f,
                0.0f,0.0f,0.5f,1.0f,
                0.5f,0.5f,0.0f,1.0f,

                0.25f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,0.25f,0.0f,1.0f,
                0.0f,0.0f,0.25f,1.0f,
                0.25f,0.25f,0.0f,1.0f
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

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        System.out.println(numPuntos);



        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        //gl.glColor4f(1.0f,0.0f,0.0f,1.0f);
        //gl.glDrawArrays(gl.GL_POINTS,0,4); //especifico desde donde comienza y cuantos dibuja

//          Podemos cambiar los puntos de color y redireccionar los puntos
//        gl.glColor4f(0.0f,0.0f,0.0f,1.0f);
//        gl.glDrawArrays(gl.GL_POINTS,1,1);
        gl.glPointSize(40);
        gl.glDrawArrays(gl.GL_POINTS,0,numPuntos);//numero de vertices a dibujar
        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);


    }
}
