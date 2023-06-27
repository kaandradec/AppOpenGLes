package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Tablero {
    private FloatBuffer bufferVertices;
    private ByteBuffer bufferIndices;

    private final static int byteFlotante =4;
    private final static int compPorVertice=3;
    //Creamos los Vertices de nuestra Figura 2:

    float[] colorPorCara;
    public Tablero(float[] colorPorCara) {
        this.colorPorCara = colorPorCara;
        float[] vertices = {

                -2.0f,0.0f,2.0f, //0
                -2.0f,0.0f,1.0f, //5
                -1.0f,0.0f,2.0f, //1
                -1.0f,0.0f,1.0f, //6
                -0.0f,0.0f,2.0f, //2
                -0.0f,0.0f,1.0f, //7
                 1.0f,0.0f,2.0f, //3
                1.0f,0.0f,1.0f, //8
                 2.0f,0.0f,2.0f, //4
                2.0f,0.0f,1.0f, //9

                2.0f,0.0f,1.0f, //9------------------------
                -2.0f,0.0f,1.0f, //5 2Triangulos Degenerados por Fila
                2.0f,0.0f,1.0f, //9
                -2.0f,0.0f,1.0f, //5-----------------------

                -2.0f,0.0f,1.0f, //5
                -2.0f,0.0f,0.0f, //10
                -1.0f,0.0f,1.0f, //6
                -1.0f,0.0f,0.0f, //11
                -0.0f,0.0f,1.0f, //7
                -0.0f,0.0f,0.0f, //12
                1.0f,0.0f,1.0f, //8
                1.0f,0.0f,0.0f, //13
                2.0f,0.0f,1.0f, //9
                2.0f,0.0f,0.0f, //14


                2.0f,0.0f,0.0f, //14------------------------
                -2.0f,0.0f,0.0f, //10  2Triangulos Degenerados por Fila
                2.0f,0.0f,0.0f, //14
                -2.0f,0.0f,0.0f, //10-----------------------



                -2.0f,0.0f,0.0f, //10
                -2.0f,0.0f,-1.0f, //15
                -1.0f,0.0f,0.0f, //11
                -1.0f,0.0f,-1.0f, //16
                -0.0f,0.0f,0.0f, //12
                -0.0f,0.0f,-1.0f, //17
                1.0f,0.0f,0.0f, //13
                1.0f,0.0f,-1.0f, //18
                2.0f,0.0f,0.0f, //14
                2.0f,0.0f,-1.0f, //19


                2.0f,0.0f,-1.0f, //19-----------------------
                -2.0f,0.0f,-1.0f, //15  2Triangulos Degenerados por Fila
                2.0f,0.0f,-1.0f, //19
                -2.0f,0.0f,-1.0f, //15----------------------

                -2.0f,0.0f,-1.0f, //15
                -2.0f,0.0f,-2.0f, //20
                -1.0f,0.0f,-1.0f, //16
                -1.0f,0.0f,-2.0f, //21
                -0.0f,0.0f,-1.0f, //17
                -0.0f,0.0f,-2.0f, //22
                1.0f,0.0f,-1.0f, //18
                1.0f,0.0f,-2.0f, //23
                2.0f,0.0f,-1.0f, //19
                2.0f,0.0f,-2.0f, //24
        };
        byte[] indices = {
                0,5,1,6,2,7,3,8,4,9
//                0,5,1, 1,5,6,
//
//                1,6,2, 2,6,7


        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        bufferIndices= ByteBuffer.allocateDirect(indices.length);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);


    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


//        bufferIndices.position(0);
//        gl.glColor4f(0.24f,0.28f,0.80f,1);
//        gl.glDrawElements(gl.GL_TRIANGLE_STRIP,10,gl.GL_UNSIGNED_BYTE,bufferIndices);


        //PINTAR T0DO EL CILINDRO DE COLOR X:

        int paso =0;
        int poss=0;


        if(colorPorCara != null){
            for (int i = 0; i < (16 + 8 +1); i++) { //16CARAS +2porCadaFila +1Para cerrar
                gl.glColor4f(colorPorCara[poss], colorPorCara[poss+1], colorPorCara[poss+2],colorPorCara[poss+3]);
                gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,paso,  4);
                poss +=4;
                paso +=2;

            }

        }else{
            gl.glColor4f(1,0,0,1);
            gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,0,  2*(16+8+2));
        }






        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }

}