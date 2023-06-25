package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Cono {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;


    private int segmentos;

    private ArrayList<Float> verticesAL = new ArrayList<>();
    ArrayList<Float> coloresAL = new ArrayList<>();


    double[] unicoColor;


    public Cono(float RADIUS, float height, int segmentos, double[] unicoColor){
        this.unicoColor = unicoColor;

        this.segmentos = segmentos;

        float posInicialx =0;
        float posInicialy =0;
        float posInicialz =0;

        //ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float x = 0;
        float z= 0;
        float y= 0; //Altura del cono

        float tmpx = 0, tmpy = 0, tmpz = 0;

        boolean aux = false;
        byte paso = 0;

        // 1 PUNTO CENTRAL  (VERTICE)
        verticesAL.add(0f); verticesAL.add(height);verticesAL.add(0f);
        // Cicunferencia
        for (int i = 0; i <= segmentos; i++) {

            if(paso ==2) aux =true;
            if(aux){
                verticesAL.add(0f); verticesAL.add(height);verticesAL.add(0f);
                System.out.print("CERO: " + 0);System.out.print(" "+2 );System.out.println(" "+ 0);
                verticesAL.add(tmpx);  verticesAL.add(0f);verticesAL.add(tmpz);
                System.out.print("Vertice: " + tmpx);System.out.print(" "+ 0f);System.out.println(" "+ tmpz);
                paso =1;
                aux =false;
            }
            paso ++;

            float theta = (float) ((2.0f * Math.PI * i )/ segmentos);
            x = RADIUS * (float) Math.cos(theta);
            y = 0;
            z = RADIUS * (float) Math.sin(theta);

            tmpx = x; tmpy = y; tmpz =z;
            verticesAL.add(x + posInicialx);  verticesAL.add(0f);verticesAL.add(z + posInicialz);
            System.out.print("Vertice: " + x);System.out.print(" "+y);System.out.println(" "+ z);
        }

         //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }

//---------------------------------------------------------------------------------------

        //COLORES PARA RUEDAS
        for (int i = 0; i <= segmentos; i++) {

            //coloresAL.add(1.0f);coloresAL.add(0.4f);coloresAL.add(0.0f);coloresAL.add(1.0f);//NARANJA
            coloresAL.add(0.6f);coloresAL.add(0f);coloresAL.add(0.082f);coloresAL.add(1.0f);//Morado
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


//        bufferColores.position(0);
//        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
//        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

//        //COLORES PARA CADA CARA
        gl.glColor4f(0f, 1, 0f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,  3);

        gl.glColor4f(1f, 0, 0f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,3,  3);

        gl.glColor4f(0f, 0, 1f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,6,  3);

        gl.glColor4f(1f, 1, 0f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,9,  3);

        gl.glColor4f(1f, 0, 1f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,12,  3);

        gl.glColor4f(0f, 1f, 1f,1);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,15,  3);

        //COLORES PARA T0DO EL CONO
        gl.glColor4f((float)unicoColor[0], (float)unicoColor[1], (float)unicoColor[2],(float)unicoColor[3]);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0, segmentos*3 );



//        gl.glColor4f(0.6f, 0, 1,1);
//        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,  verticesAL.size()/2 +1);//size()/2 Porque cada vertice tiene dos numeros float // +1 de vertice Central





        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}
