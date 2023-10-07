package ec.edu.uce.pa.grupalAstros20;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.FloatBuffer;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones20;

public class Anillo {
    private GLES20 gl;
    private Context contexto;
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferTexturas;

    private static final int byteFlotante= 4;
    private static final int compPorVertice= 3;
    private static final int compPorTextura= 2;
    private final static int STRIDE= (compPorVertice+compPorTextura) *byteFlotante;
    private float[] matrizProyeccion;
    private float[] matrizVista;
    private float[] matrizModelo;

    public Anillo(float radioExterno, float radioInterno, int segmentos, Context contexto,
                  float[] matrizProyeccion, float[] matrizVista, float[] matrizModelo) {
        this.contexto = contexto;
        this.matrizProyeccion = matrizProyeccion;
        this.matrizVista = matrizVista;
        this.matrizModelo = matrizModelo;

        int cantidadVertices = (segmentos + 1) * 2; // 2 vértices por segmento

        float[] vertices = new float[cantidadVertices *compPorVertice];
        float[] texturas = new float[cantidadVertices *compPorTextura];

        int indiceVertice = 0;
        for (int i = 0; i <= segmentos; i++) {
            float angulo = (float) (2.0 * Math.PI* i / segmentos);

            float xExterno = radioExterno * (float) Math.cos(angulo);
            float yExterno = radioExterno * (float) Math.sin(angulo);

            float xInterno = radioInterno * (float) Math.cos(angulo);
            float yInterno = radioInterno * (float) Math.sin(angulo);

            vertices[indiceVertice++] = xExterno;
            vertices[indiceVertice++] = 0.0f; // coordenada y
            vertices[indiceVertice++] = yExterno;

            vertices[indiceVertice++] = xInterno;
            vertices[indiceVertice++] = 0.0f; // coordenada y
            vertices[indiceVertice++] = yInterno;

            texturas[i * 4] = (float) i / segmentos;
            texturas[i * 4 + 1] = 1.0f;

            texturas[i * 4 + 2] = (float) i / segmentos;
            texturas[i * 4 + 3] = 0.0f;
        }

        bufferVertices = Funciones20.generarFloatBuffer(vertices);
        bufferTexturas = Funciones20.generarFloatBuffer(texturas);
    }

    public void dibujar(GLES20 gl) {
        int vertexShader = 0;
        int fragmentShader = 0;

        String sourceVS = Funciones20.leerArchivo(R.raw.textura_vertex_shader, contexto);
        vertexShader = Funciones20.crearShader(gl.GL_VERTEX_SHADER, sourceVS, gl);

        String sourceFS = Funciones20.leerArchivo(R.raw.textura_fragment_shader, contexto);
        fragmentShader = Funciones20.crearShader(gl.GL_FRAGMENT_SHADER, sourceFS, gl);

        int programa = Funciones20.crearPrograma(vertexShader, fragmentShader, gl);
        gl.glUseProgram(programa);

        bufferVertices.position(0);
        int idPosVertexShader = gl.glGetAttribLocation(programa, "posVertexShader");
        gl.glVertexAttribPointer(idPosVertexShader,compPorVertice, gl.GL_FLOAT, false, 0, bufferVertices);
        gl.glEnableVertexAttribArray(idPosVertexShader);

        bufferTexturas.position(0);
        int idPosTextura = gl.glGetAttribLocation(programa, "texturaVertex");
        gl.glVertexAttribPointer(idPosTextura,compPorTextura, gl.GL_FLOAT, false, 0, bufferTexturas);
        gl.glEnableVertexAttribArray(idPosTextura);

        int idPosMatrizProy = gl.glGetUniformLocation(programa, "matrizProjection");
        gl.glUniformMatrix4fv(idPosMatrizProy, 1, false, matrizProyeccion, 0);

        int idPosMatrizView = gl.glGetUniformLocation(programa, "matrizView");
        gl.glUniformMatrix4fv(idPosMatrizView, 1, false, matrizVista, 0);

        int idPosMatrizModel = gl.glGetUniformLocation(programa, "matrizModel");
        gl.glUniformMatrix4fv(idPosMatrizModel, 1, false, matrizModelo, 0);

        gl.glFrontFace(gl.GL_CW);

        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, bufferVertices.capacity() /compPorVertice);

        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableVertexAttribArray(idPosVertexShader);
        gl.glDisableVertexAttribArray(idPosTextura);

        Funciones20.liberarShader(programa, vertexShader, fragmentShader);
    }

}
