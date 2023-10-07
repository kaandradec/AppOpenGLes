package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Circulo {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;

    private int segmentos;
    private ArrayList<Float> verticesAL = new ArrayList<>();
    ArrayList<Float> coloresAL = new ArrayList<>();


    public Circulo(float RADIUS, int segmentos, double [] unicoColor){
        this.segmentos = segmentos;
        float posInicialx =0, posInicialy=0;

        //ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float x = 0;
        float y= 0;

        for (int i = 0; i < segmentos; i++) {
            float theta = (float) ((2.0f * Math.PI * i )/ segmentos);
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            verticesAL.add(x + posInicialx); verticesAL.add(y + posInicialy);

        }

         //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }

//---------------------------------------------------------------------------------------

        //COLORES PARA CIRCULO
        for (int i = 0; i < segmentos; i++) {
            //coloresAL.add(0.08f);coloresAL.add(0.082f);coloresAL.add(0.082f);coloresAL.add(1.0f);//GRIS
            //COLOR PARA T0DO EL CIRCULO
             coloresAL.add((float)unicoColor[0]);coloresAL.add((float)unicoColor[1]);coloresAL.add((float)unicoColor[2]);coloresAL.add((float)unicoColor[3]);
        }

        //Asignar TODOS los vertices de colores
        float[] colores = new float[coloresAL.size()];
        for (int i=0;i<colores.length;i++) {//<= En el caso de tener el punto central
            colores[i] =  coloresAL.get(i);
        }

        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);

    }
    public Circulo(float RADIUS, int segmentos, float [] coloresPorCara){
        this.segmentos = segmentos;
        float posInicialx =0, posInicialy=0;

        //ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float x = 0;
        float y= 0;
        for (int i = 0; i < segmentos; i++) {
            float theta = (float) ((2.0f * Math.PI * i )/ segmentos);
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);
            verticesAL.add(x + posInicialx); verticesAL.add(y + posInicialy);

        }
        //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }
        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(coloresPorCara);
    }



    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);


        gl.glPointSize(12);
        gl.glLineWidth(12);
        gl.glDrawArrays(gl.GL_LINE_LOOP,0,  verticesAL.size()/2);//size()/2 Porque cada vertice tiene dos numeros float // +1 de vertice Central





        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}
