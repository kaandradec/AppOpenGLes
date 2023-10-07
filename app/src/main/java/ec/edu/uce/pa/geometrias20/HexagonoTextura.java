package ec.edu.uce.pa.geometrias20;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones20;

public class HexagonoTextura {

    private float w;
    private FloatBuffer bufferVertices;

    private ByteBuffer bufferIndice;

    private final static int byteFlotante = 4;
    private final static int compPorVertice = 4;
    private final static int compPorTextura = 2;
    private final static int STRIDE = (compPorVertice + compPorTextura) * byteFlotante;

    private float [] matrizProyeccion;


    private Context contexto;

    public HexagonoTextura(Context contexto, float[]matrizProyeccion) {
        this.matrizProyeccion=matrizProyeccion;
        this.contexto = contexto;
        w = 1.0f;


        float[] vertices = {

                -0.6f, 1.0f, 0.0f, w, 0.3f, 0.0f,
                -0.6f, -1.0f, 0.0f, w, 0.3f, 1.0f,
                -1.0f, 0.0f, 0.0f, w, 0.0f, 0.5f,

                -0.6f, 1.0f, 0.0f, w, 0.3f, 0.0f,
                0.6f, 1.0f, 0.0f, w, 0.6f, 0.0f,
                0.6f, -1.0f, 0.0f, w, 0.6f, 1.0f,
                -0.6f, -1.0f, 0.0f, w, 0.3f, 1.0f,

                0.6f, 1.0f, 0.0f, w, 0.6f, 0.0f,
                1.0f, 0.0f, 0.0f, w, 1.0f, 0.5f,
                0.6f, -1.0f, 0.0f, w, 0.6f, 1.0f


        };
        byte[] indices = {
                0, 1, 2,
                3,4,5,
                3,5,6,
                7,8,9
        };


        bufferVertices = Funciones20.generarFloatBuffer(vertices);


        bufferIndice = Funciones20.generarByteBuffer(indices);

    }

    public void dibujar(GLES20 gl) {
        int vertexShader = 0;
        int fragmentShader = 0;

        String sourceVS = null;
        String sourceFS = null;

        sourceVS = Funciones20.leerArchivo(R.raw.hexagono_textura_vertex_shader, contexto);
        vertexShader = Funciones20.crearShader(gl.GL_VERTEX_SHADER, sourceVS, gl);


        sourceFS = Funciones20.leerArchivo(R.raw.textura_fragment_shader, contexto);
        fragmentShader = Funciones20.crearShader(gl.GL_FRAGMENT_SHADER, sourceFS, gl);

        int programa = Funciones20.crearPrograma(vertexShader, fragmentShader, gl);

        gl.glUseProgram(programa);

        bufferVertices.position(0);

        int idVertexShader = gl.glGetAttribLocation(programa, "posVertexShader");
        gl.glVertexAttribPointer(idVertexShader,
                compPorVertice, gl.GL_FLOAT,
                false, STRIDE, bufferVertices);
        gl.glEnableVertexAttribArray(idVertexShader);

        bufferVertices.position(4);
        int idFragmentShader = gl.glGetAttribLocation(programa, "texturaVertex");
        gl.glVertexAttribPointer(idFragmentShader,
                compPorTextura, gl.GL_FLOAT,
                false, STRIDE, bufferVertices);
        gl.glEnableVertexAttribArray(idFragmentShader);


        int idPosMatrizProy = gl.glGetUniformLocation(programa,"matrizProyeccion");
        gl.glUniformMatrix4fv(idPosMatrizProy,1,false, matrizProyeccion,0);

        gl.glFrontFace(gl.GL_CW);
        bufferIndice.position(0);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndice);
        bufferIndice.position(3);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3*2, gl.GL_UNSIGNED_BYTE, bufferIndice);
        bufferIndice.position(9);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3, gl.GL_UNSIGNED_BYTE, bufferIndice);
        gl.glFrontFace(gl.GL_CW);
        gl.glDisableVertexAttribArray(idVertexShader);
        gl.glDisableVertexAttribArray(idFragmentShader);

        Funciones20.liberarShader(programa,vertexShader,fragmentShader);

    }
}

