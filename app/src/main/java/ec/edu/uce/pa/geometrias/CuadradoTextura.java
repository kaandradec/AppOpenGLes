package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class CuadradoTextura {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private FloatBuffer bufferTexturas;//TEXTURA

    private ByteBuffer bufferIndice;//TRABAJAR CON INDICES
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;


    //private int numPuntos = Activity_Figuras.numPrimitivas;//input Numero de primitavas

    public CuadradoTextura(){
        float[] vertices ={//TEXTURA
                1,1,//0
                1,-1,//1
                -1,-1,//2
                -1,1//3
        };
        float[] textura ={//TEXTURA
//                1,1,
//                1,0,
//                0,0,
//                0,1
                1,0,
                1,1,
                0,1,
                0,0
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
                0,1,2,
                0,2,3
//
        };

        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);
        bufferTexturas = Funciones.generarBuffer(textura);//TEXTURA


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

        gl.glTexCoordPointer(2, gl.GL_FLOAT, 0, bufferTexturas);//TEXTURA
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);//TEXTURA

        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE, bufferIndice); //DRAW ELEMENTS


        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);//TEXTURA


    }
}