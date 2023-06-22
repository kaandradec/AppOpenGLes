package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.Activity_Figuras;
import ec.edu.uce.pa.utilidades.Funciones;

public class Circulo {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;


    private int puntosRueda;
    //private int verticesTotales = 0;
    private ArrayList<Float> verticesAL = new ArrayList<>();
    ArrayList<Float> coloresAL = new ArrayList<>();


    public Circulo(float RADIUS, float posInicialx, float posInicialy, int puntosRueda){
        this.puntosRueda = puntosRueda;


        //ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float x = 0;
        float y= 0;

        //PARA RUEDA 1

        //verticesAL.add(posInicialx); verticesAL.add(posInicialy);//PUNTO CENTRAL  (VERTICE)

        for (int i = 0; i < puntosRueda; i++) {
            float theta = (float) ((2.0f * Math.PI * i )/ puntosRueda);

            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            verticesAL.add(x + posInicialx); verticesAL.add(y + posInicialy);

        }

         //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

            System.out.println("X: "+ vertices[i]);
        }

        System.out.println("ARRLIST: " + verticesAL);
        System.out.println("ARRLIST: " + verticesAL.size());
//---------------------------------------------------------------------------------------

        //COLORES PARA RUEDAS
        for (int i = 0; i < puntosRueda; i++) {

            //coloresAL.add(1.0f);coloresAL.add(0.4f);coloresAL.add(0.0f);coloresAL.add(1.0f);//NARANJA
            coloresAL.add(0.08f);coloresAL.add(0.082f);coloresAL.add(0.082f);coloresAL.add(1.0f);//GRIS
        }

        //Asignar TODOS los vertices de colores
        float[] colores = new float[coloresAL.size()];
        for (int i=0;i<colores.length;i++) {//<= En el caso de tener el punto central
            colores[i] =  coloresAL.get(i);
        }



        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);

    }



    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);



        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,  verticesAL.size()/2);//size()/2 Porque cada vertice tiene dos numeros float // +1 de vertice Central

        gl.glPointSize(12);



        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}
