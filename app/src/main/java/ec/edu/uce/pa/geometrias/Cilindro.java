package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Cilindro {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;


    private int numSlices;
    private float height;
    private float radius;

    private float [] vertices;


    double[] unicoColor;
    float[] colorPorCara;

    public Cilindro(float radius, float height, int numSlices, double[] unicoColor){

        this.unicoColor = unicoColor;
        this.numSlices = numSlices;
        this.radius = radius;
        this.height = height;

        ArrayList<Float> verticesArrl = new ArrayList<>();

        float angleStep = (float)(2.0 * Math.PI / numSlices);

        for (int i = 0; i <= numSlices; i++) {
            float angle = i * angleStep;
            float x = (float)(radius * Math.cos(angle));
            float z = (float)(radius * Math.sin(angle));

            // Vertices superiores del circulo del cilindro
            verticesArrl.add(x); //x
            verticesArrl.add(height / 2.0f); //y
            verticesArrl.add(z);//z


            // Vertices inferiores del circulo2 del cilindro
            verticesArrl.add(x); //x
            verticesArrl.add(-height / 2.0f); //y
            verticesArrl.add(z); //z

        }

        vertices = new float[verticesArrl.size()];
        for (int i=0;i<verticesArrl.size();i++) {
            vertices[i] =  verticesArrl.get(i);
        }

        bufferVertices = Funciones.generarBuffer(vertices);
        //bufferColores = Funciones.generarBuffer(colores);


    }
    public Cilindro(float radius, float height, int numSlices, float[] colorPorCara){

        this.colorPorCara = colorPorCara;
        this.numSlices = numSlices;
        this.radius = radius;
        this.height = height;

        ArrayList<Float> verticesArrl = new ArrayList<>();

        float angleStep = (float)(2.0 * Math.PI / numSlices);

        for (int i = 0; i <= numSlices; i++) {
            float angle = i * angleStep;
            float x = (float)(radius * Math.cos(angle));
            float z = (float)(radius * Math.sin(angle));

            // Vertices superiores del circulo del cilindro
            verticesArrl.add(x); //x
            verticesArrl.add(height / 2.0f); //y
            verticesArrl.add(z);//z


            // Vertices inferiores del circulo2 del cilindro
            verticesArrl.add(x); //x
            verticesArrl.add(-height / 2.0f); //y
            verticesArrl.add(z); //z

        }

        vertices = new float[verticesArrl.size()];
        for (int i=0;i<verticesArrl.size();i++) {
            vertices[i] =  verticesArrl.get(i);
        }

        bufferVertices = Funciones.generarBuffer(vertices);
        //bufferColores = Funciones.generarBuffer(colores);


    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


//        //COLORES PARA CADA CARA 6 caras
//        gl.glColor4f(1f,1f,1f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, 4);
//
//        gl.glColor4f(0f,0f,0f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 2, 4);
//
//        gl.glColor4f(1f,1f,1f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 4, 4);
//
//        gl.glColor4f(0f,0f,0f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 6, 4);
//
//        gl.glColor4f(1f,1f,1f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 8, 4);
//
//        gl.glColor4f(0f,0f,0f,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 10, 4);



        //PINTAR T0DO EL CILINDRO DE COLOR X:

        int paso =0;
        int poss=0;


        if(colorPorCara != null){

            for (int i = 0; i < (numSlices); i++) {//Por cada asigna el color { a, b, c, d }:
                gl.glColor4f(colorPorCara[poss], colorPorCara[poss+1], colorPorCara[poss+2],colorPorCara[poss+3]);
                gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,paso,  4);

                poss +=4;
                paso +=2;
            }

        }else{
            gl.glColor4f((float)unicoColor[0],(float)unicoColor[1],(float)unicoColor[2],(float)unicoColor[3]);
            gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, (numSlices *2 + 2)); //+2 para cerrar paredes de cilindro

        }

//        gl.glColor4f(1,0,0,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, 4); //+2 para cerrar paredes de cilindro
//        gl.glColor4f(0,1,0,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 2, 4); //+2 para cerrar paredes de cilindro
//        gl.glColor4f(0,0,1,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 4, 4); //+2 para cerrar paredes de cilindro
//        gl.glColor4f(1,1,0,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 6, 4); //+2 para cerrar paredes de cilindro
//
//        gl.glColor4f(1,0,1,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 8, 4); //+2 para cerrar paredes de cilindro
//        gl.glColor4f(0,1,1,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 10, 4); //+2 para cerrar paredes de cilindro




        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}