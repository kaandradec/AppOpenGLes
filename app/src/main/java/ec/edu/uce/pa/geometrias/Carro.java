package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class Carro {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;


    //private int puntosRueda = OpenGL10Activity.numPrimitivas;//input Numero de primitavas
    private int puntosRueda = 15;
    private int verticesTotales = 0;
    //int NUM_VERTICES = 20;

    private ArrayList<Float> segmentoEntrePuntos(String direccion, float estatico, float inicio, float fin, float espaciado){
        ArrayList<Float> listSegmentos = new ArrayList<>();
        if(direccion.toLowerCase().equals("derecha")){
            while (inicio < fin){
                listSegmentos.add(inicio + espaciado);
                listSegmentos.add(estatico);

                inicio += espaciado;
            }
        }
        if(direccion.toLowerCase().equals("arriba")){
            while (inicio < fin){
                listSegmentos.add(estatico);
                listSegmentos.add(inicio + espaciado);

                inicio += espaciado;
            }
        }
        if(direccion.toLowerCase().equals("izquierda")){
            while (inicio > fin){
                listSegmentos.add(inicio - espaciado);
                listSegmentos.add(estatico);

                inicio -= espaciado;
            }
        }
        if(direccion.toLowerCase().equals("abajo")){
            while (inicio > fin){
                listSegmentos.add(estatico);
                listSegmentos.add(inicio - espaciado);

                inicio -= espaciado;
            }
        }
        return listSegmentos;
    }
    public Carro(){
        ArrayList<Float> verticesAL = new ArrayList<>();
        float espaciado = 0.2f;

        verticesAL.add(3.0f); verticesAL.add(0.0f);
        verticesAL.addAll(segmentoEntrePuntos("derecha",0.0f, 3.0f, 6.0f, espaciado  ));

        verticesAL.add(6.0f); verticesAL.add(0.0f);
        verticesAL.addAll(segmentoEntrePuntos("arriba",6.0f, 0.0f, 2.0f,espaciado  ));

        verticesAL.add(6.0f); verticesAL.add(2.0f);
        verticesAL.addAll(segmentoEntrePuntos("izquierda",2.0f, 6.0f, 3.0f,espaciado  ));

        verticesAL.add(3.0f); verticesAL.add(2.0f);
        verticesAL.addAll(segmentoEntrePuntos("abajo",3.0f, 2.0f, 0.0f,espaciado  ));


        verticesAL.add(3.0f); verticesAL.add(0.0f);
        verticesAL.addAll(segmentoEntrePuntos("izquierda",0.0f, 3.0f, -6.0f,espaciado  ));


        verticesAL.add(-6.0f); verticesAL.add(0.0f);
        verticesAL.addAll(segmentoEntrePuntos("arriba",-6.0f, 0.0f, 5.0f,espaciado  ));

        verticesAL.add(-6.0f); verticesAL.add(5.0f);
        verticesAL.addAll(segmentoEntrePuntos("derecha",5.0f, -6.0f, 3.0f,espaciado  ));

        verticesAL.add(3.0f); verticesAL.add(5.0f);
        verticesAL.addAll(segmentoEntrePuntos("abajo",3.0f, 5.0f, 2.0f,espaciado  ));

        verticesAL.add(3.0f); verticesAL.add(2.0f);

//ALGORITMO RUEDA---------------------------------------------------------------
        //DATOS DE VERTICES
        float RADIUS = 1.0f;
        float xO = -5.0f;
        float yO = -1.0f;
        float x = 0;
        float y= 0;

        //PARA RUEDA 1
        for (int i = 0; i < puntosRueda; i++) {
            float theta = (float) ((2.0f * Math.PI * i )/ puntosRueda);
            System.out.println("Angulo Theta "  + theta);
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            verticesAL.add(x + xO); verticesAL.add(y + yO);
        }
        //PARA RUEDA 2
         xO = 2.0f;
         yO = -1.0f;

        for (int i = 0; i < puntosRueda; i++) {
            float theta = (float) ((2.0f * Math.PI * i )/ puntosRueda);
            System.out.println("Angulo Theta "  + theta);
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            verticesAL.add(x + xO); verticesAL.add(y + yO);
        }

        float [] vertices = new float[verticesAL.size()];
        verticesTotales = vertices.length;//usado para segmentos entre lineas

        for (int i=0;i<verticesAL.size();i++) {
            vertices[i] =  verticesAL.get(i);

        }
//---------------------------------------------------------------------------------------
        //COLORES para 'Cabina de carro'
        ArrayList<Float> coloresAL = new ArrayList<>();

        for (int i = 0; i < ((verticesTotales/2) - puntosRueda*2); i++) {

            coloresAL.add(0.0f);coloresAL.add(0.5f);coloresAL.add(1.0f);coloresAL.add(1.0f);
        }
        //COLORES PARA RUEDAS
        for (int i = 0; i < puntosRueda*2; i++) {

            coloresAL.add(1.0f);coloresAL.add(0.4f);coloresAL.add(0.0f);coloresAL.add(1.0f);
        }

        //Asignar TODOS los vertices de colores
        float[] colores = new float[coloresAL.size()];
        for (int i=0;i<colores.length;i++) {
            colores[i] =  coloresAL.get(i);

        }


//        float[] colores2 ={
//                0.0f,0.5f,1.0f,1.0f,//AZUL CELESTE
//                0.5f,1.0f,0.0f,1.0f,//VERDE (verde + rojo)
//                //Color rueda 1
//                1.0f,0.0f,0.0f,1.0f,//ROJO
//        };




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


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(8);//ancho de linea


        int verticesLineas = ((verticesTotales/2) - puntosRueda*2);//total de vertices para la 'cabina'
        //gl.glDrawArrays(gl.GL_LINE_STRIP,0,9);
        gl.glDrawArrays(gl.GL_LINES,0,verticesLineas);

        //gl.glDrawArrays(gl.GL_POINTS,9,NUM_VERTICES);
        gl.glDrawArrays(gl.GL_POINTS,verticesLineas, puntosRueda*2);
        gl.glPointSize(12);
        gl.glFrontFace(gl.GL_CW);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);


    }
}
