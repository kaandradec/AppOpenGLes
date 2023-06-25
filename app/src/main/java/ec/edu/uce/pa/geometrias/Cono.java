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
    float[] colorPorCara;


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
                verticesAL.add(tmpx);  verticesAL.add(0f);verticesAL.add(tmpz);
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
        }

         //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }

//---------------------------------------------------------------------------------------

        //COLORES PARA CIRCULO
        for (int i = 0; i < segmentos*3; i++) {
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

    public Cono(float RADIUS, float height, int segmentos, float[] colorPorCara){
        this.colorPorCara = colorPorCara;

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
                verticesAL.add(tmpx);  verticesAL.add(0f);verticesAL.add(tmpz);
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
        }

        //Asignar ArrayList a vertices []
        float [] vertices = new float[verticesAL.size()];
        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }

//---------------------------------------------------------------------------------------

        bufferVertices = Funciones.generarBuffer(vertices);
        //bufferColores = Funciones.generarBuffer(colorPorCara);


    }



    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

//        bufferColores.position(0);
//        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
//        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        int paso =0;
        int poss=0;

        if(colorPorCara != null){

            for (int i = 0; i < (colorPorCara.length)/4; i++) {//Por cada asigna el color { a, b, c, d }:
                 gl.glColor4f(colorPorCara[poss], colorPorCara[poss+1], colorPorCara[poss+2],colorPorCara[poss+3]);
                 gl.glDrawArrays(gl.GL_TRIANGLE_FAN,paso,  3);

                 poss +=4;
                 paso +=3;
            }

        } else{

            gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0, segmentos*3 );
        }


        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);


    }
}
