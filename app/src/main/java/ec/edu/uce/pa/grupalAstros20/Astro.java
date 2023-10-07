package ec.edu.uce.pa.grupalAstros20;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones20;


public class Astro {

    private FloatBuffer bufferVertices;

    private FloatBuffer bufferNormales;
    private FloatBuffer bufferTexturas;
    private static final int byteFlotante = 4;
    private static final int compPorVertice = 3;
    private static final int compPorText = 2;
    private final static int STRIDE = (compPorVertice + compPorText) * byteFlotante;

    private int cortes, franjas;
    private float[] matrizProyeccion,matrizVista, matrizModelo;
    private Context contexto;

    public Astro(int franjas, int cortes, float radio, float ejePolar, Context contexto, float[] matrizProyeccion, float[] matrizVista, float[] matrizModelo) {
        this.cortes = cortes;
        this.franjas = franjas;
        this.matrizVista = matrizVista;
        this.matrizModelo = matrizModelo;
        this.matrizProyeccion = matrizProyeccion;
        this.contexto = contexto;

        float[] vertices;
        float[] normales;
        float[] texturas;

        int iVertice = 0;
        int iNormal = 0;
        int iTextura = 0;

        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];
        normales = new float[3 * ((cortes * 2 + 2) * franjas)];
        texturas = new float[2 * ((cortes * 2 + 2) * franjas)];

        int i, j;

        // Bucle para construir las franjas de la esfera
        // Latitudes
        for (i = 0; i < franjas; i++) {
            //empieza en -90 grados (-1.57 radianes) incrementa hasta +90 grados  (o +1.57 radianes)
            //Phi   --> angulo de latitud
            //Theta --> angulo de longitud

            //Valor del angulo para el primer cìrculo
            float phi0 = (float) Math.PI * ((i + 0) * (1.0f / (franjas)) - 0.5f);
            float cosPhi0 = (float) Math.cos(phi0);
            float sinPhi0 = (float) Math.sin(phi0);

            //Valor del angulo para el segundo cìrculo
            float phi1 = (float) Math.PI * ((i + 1) * (1.0f / (franjas)) - 0.5f);
            float cosPhi1 = (float) Math.cos(phi1);
            float sinPhi1 = (float) Math.sin(phi1);

            float cosTheta, sinTheta;
            //Bucle para construir los cortes de la esfera
            //Longitudes
            for (j = 0; j < cortes; j++) {
                float theta = (float) (-2.0f * Math.PI * j * (1.0 / (cortes - 1)));
                cosTheta = (float) Math.cos(theta);
                sinTheta = (float) Math.sin(theta);

                // Dibujar la esfera en duplas, pares de puntos
                vertices[iVertice + 0] = radio * cosPhi0 * cosTheta;          //x
                vertices[iVertice + 1] = radio * (sinPhi0 * ejePolar);    //y
                vertices[iVertice + 2] = (radio * (cosPhi0 * sinTheta));        //z

                vertices[iVertice + 3] = radio * cosPhi1 * cosTheta;          //x'
                vertices[iVertice + 4] = radio * (sinPhi1 * ejePolar);    //y'
                vertices[iVertice + 5] = (radio * (cosPhi1 * sinTheta));        //z'

                normales[iNormal + 0] = cosPhi0 * cosTheta;          //x
                normales[iNormal + 1] = (sinPhi0);    //y
                normales[iNormal + 2] = (cosPhi0 * sinTheta);        //z

                normales[iNormal + 3] = cosPhi1 * cosTheta;          //x'
                normales[iNormal + 4] = (sinPhi1);    //y'
                normales[iNormal + 5] = (cosPhi1 * sinTheta);        //z'

                texturas[iTextura + 0] = j*1.0f/(cortes-1);          //x
                texturas[iTextura + 1] = (i+0)*1.0f/(franjas-1)*-1;   //y

                texturas[iTextura + 2] = j*1.0f/(cortes-1);      //z
                texturas[iTextura + 3] = (i+1)*1.0f/(franjas-1)*-1;          //x'

                iVertice += 2 * 3;
                iNormal += 2 * 3;
                iTextura += 2 * 2;
            }

            vertices[iVertice + 0] = vertices[iVertice + 3];
            vertices[iVertice + 3] = vertices[iVertice - 3];
            vertices[iVertice + 1] = vertices[iVertice + 4];
            vertices[iVertice + 4] = vertices[iVertice - 2];
            vertices[iVertice + 2] = vertices[iVertice + 5];
            vertices[iVertice + 5] = vertices[iVertice - 1];
        }

        bufferVertices = Funciones20.generarFloatBuffer(vertices);
        bufferNormales = Funciones20.generarFloatBuffer(normales);
        bufferTexturas = Funciones20.generarFloatBuffer(texturas);
    }

    public void dibujar(GLES20 gl) {
        int vertexShader = 0;
        int fragmentShader = 0;

        String sourceVS = null;
        String sourceFS = null;

        sourceVS = Funciones20.leerArchivo(R.raw.textura_vertex_shader, contexto);
        vertexShader = Funciones20.crearShader(gl.GL_VERTEX_SHADER, sourceVS, gl);

        sourceFS = Funciones20.leerArchivo(R.raw.textura_fragment_shader, contexto);
        fragmentShader = Funciones20.crearShader(gl.GL_FRAGMENT_SHADER, sourceFS, gl);

        int programa = Funciones20.crearPrograma(vertexShader, fragmentShader, gl);
        gl.glUseProgram(programa);

        bufferVertices.position(0);
        int idVertexShader = gl.glGetAttribLocation(programa, "posVertexShader");
        gl.glVertexAttribPointer(idVertexShader,
                compPorVertice,
                gl.GL_FLOAT,
                false,
                0,
                bufferVertices);
        gl.glEnableVertexAttribArray(idVertexShader);

        bufferTexturas.position(0);
        int idFragmentShader = gl.glGetAttribLocation(programa, "texturaVertex");
        gl.glVertexAttribPointer(idFragmentShader,
                compPorText,
                gl.GL_FLOAT,
                false,
                0,
                bufferTexturas);
        gl.glEnableVertexAttribArray(idFragmentShader);



        int idPosMatrizProy = gl.glGetUniformLocation(programa, "matrizProjection");
        gl.glUniformMatrix4fv(idPosMatrizProy, 1, false, matrizProyeccion, 0);

        int idPosMatrizView = gl.glGetUniformLocation(programa, "matrizView");
        gl.glUniformMatrix4fv(idPosMatrizView, 1, false, matrizVista, 0);

        int idPosMatrizModel = gl.glGetUniformLocation(programa, "matrizModel");
        gl.glUniformMatrix4fv(idPosMatrizModel, 1, false, matrizModelo, 0);


        gl.glFrontFace(gl.GL_CW);

        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, franjas * cortes * 2);

        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableVertexAttribArray(idVertexShader);
        gl.glDisableVertexAttribArray(idFragmentShader);

        Funciones20.liberarShader(programa, vertexShader, fragmentShader);
    }

}