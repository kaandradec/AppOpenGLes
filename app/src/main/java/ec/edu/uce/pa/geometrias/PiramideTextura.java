package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class PiramideTextura {
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;

    FloatBuffer[] floatBuffersVertices = new FloatBuffer[5];
    FloatBuffer[] floatBuffersTexturas = new FloatBuffer[5];
    public PiramideTextura(){

//        float[] vertices ={
//                0,1,0,//0
//                1,-1,-1,//1
//                1,-1,1,//2
//                -1,-1,1,//3
//                -1,-1,-1//4
//        };
//        byte[] indicesPiramide = {
//                0,1,2,
//                0,2,3,
//                0,3,4,
//                0,1,4
//        };

        float[] triangulo0 ={
                0,1,0,//0
                1,-1,-1,//1
                1,-1,1,//2
        };
        float[] triangulo1 ={
                0,1,0,//0
                1,-1,1,//2
                -1,-1,1,//3
        };
        float[] triangulo2 ={
                0,1,0,//0
                -1,-1,1,//3
                -1,-1,-1//4
        };
        float[] triangulo3 ={
                0,1,0,//0
                1,-1,-1,//1
                -1,-1,-1//4
        };
        float[] verticeBase ={
                1,-1,-1,//1
                1,-1,1,//2
                -1,-1,-1,//4
                -1,-1,1,//3
        };

        float[] texturas0 = {//silueta
                0.5f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f
        };
        float[] texturas1 = {//Iluminati
                0.5f, 0.0f,
                1f, 1f,
                0f, 1f,
        };
        float[] texturas2 = {//luna
                0.65f, 0.0f,
                1.0f, 1.0f,
                0.5f, 1.0f
        };

        float[] texturas3 = {//Gato PIZZA
                0.5f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f,
        };

        float[] texturas4 = {//Companion Cube
                1.0f, 0.0f,
                1.0f, 1.0f,
                0f, 0f,
                0f, 1,0f,

        };

        floatBuffersVertices[0]= Funciones.generarBuffer(triangulo0);
        floatBuffersVertices[1]= Funciones.generarBuffer(triangulo1);
        floatBuffersVertices[2]= Funciones.generarBuffer(triangulo2);
        floatBuffersVertices[3]= Funciones.generarBuffer(triangulo3);
        floatBuffersVertices[4]= Funciones.generarBuffer(verticeBase);//base


        floatBuffersTexturas[0]= Funciones.generarBuffer(texturas0);
        floatBuffersTexturas[1]= Funciones.generarBuffer(texturas1);
        floatBuffersTexturas[2]= Funciones.generarBuffer(texturas2);
        floatBuffersTexturas[3]= Funciones.generarBuffer(texturas3);
        floatBuffersTexturas[4]= Funciones.generarBuffer(texturas4);//base


    }
    public void dibujarCara(GL10 gl, int cara){//CARA: triangulos ->(0,1,2,3), base -> 4
        cara = (cara>4)?4:cara;

        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,floatBuffersVertices[cara]);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,floatBuffersTexturas[cara]);
        if(cara ==4){
            gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,0,4);//Dibuja Cuadrados
        }else {
            gl.glDrawArrays(gl.GL_TRIANGLES,0,3);//Dibuja Triangulos
        }
    }

}