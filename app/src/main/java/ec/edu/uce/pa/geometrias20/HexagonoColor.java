package ec.edu.uce.pa.geometrias20;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones20;

public class HexagonoColor {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private ByteBuffer bufferIndice;

    private final static int byteFlotante = 4; //son 4 por que en  un flotante hay 4 bytes
    private final static int compPorVertice = 2;
    private final static int compPorColores = 3;

    private Context contexto;
    public HexagonoColor(Context contexto) {
        this.contexto = contexto;

        float[] vertices = {
                0.0f, 0.0f,
                0.6f, 1.0f,
                1.0f, 0.0f,
                0.6f, -1.0f,
                -0.6f, -1.0f,
                -1.0f, 0.0f,
                -0.6f, 1.0f
        };
        byte[] indices = {
                0, 1, 2,
                0, 2, 3,
                0, 3, 4,
                0, 4, 5,
                0, 5, 6,
                0, 6, 1
        };
        float[] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,//rojo se le asigna al vertice 0
                0.0f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 1
                0.0f, 0.0f, 1.0f, 1.0f,//azul se le asigna al vertice 2
                1.0f, 1.0f, 0.0f, 1.0f,//amarillo se le asigna al vertice 3
                0.0f, 1.0f, 0.0f, 1.0f,//verde se le asigna al vertice 4
                0.0f, 0.0f, 1.0f, 1.0f,//azul se le asigna al vertice 5
                1.0f, 1.0f, 0.0f, 1.0f,//amarillo se le asigna al vertice 6
        };

        bufferVertices = Funciones20.generarFloatBuffer(vertices);
        bufferColores = Funciones20.generarFloatBuffer(colores);

        bufferIndice=Funciones20.generarByteBuffer(indices);

    }

    public void dibujar(GLES20 gl) {
        //config vertex shader
        //1.Crear vertex Shader
        int vertexShader = 0;

        String sourceVS = null;
        String sourceFS = null;

        sourceVS = Funciones20.leerArchivo(R.raw.color_vertex_shader, contexto);
        vertexShader = Funciones20.crearShader(gl.GL_VERTEX_SHADER, sourceVS, gl);

        int fragmentShader = 0;

        sourceFS = Funciones20.leerArchivo(R.raw.color_fragment_shader, contexto);
        fragmentShader = Funciones20.crearShader(gl.GL_FRAGMENT_SHADER, sourceFS, gl);

        //7. Crear programa
        int programa = Funciones20.crearPrograma(vertexShader, fragmentShader, gl);

        //10. Usar programa en el proceso de renderizacion
        gl.glUseProgram(programa);

        //11. Lectura de parámetros desde el renderer vertexShader
        int idVertexShader = gl.glGetAttribLocation(programa, "posVertexShader");
        gl.glVertexAttribPointer(idVertexShader,
                compPorVertice, gl.GL_FLOAT,
                false, 0, bufferVertices);
        gl.glEnableVertexAttribArray(idVertexShader);
        //12. Lectura de parámetros desde el renderer FragmentShader
        int idFragmentShader = gl.glGetAttribLocation(programa, "colorVertex");
        gl.glVertexAttribPointer(idFragmentShader,
                compPorColores, gl.GL_FLOAT,
                false, 0, bufferColores);
        gl.glEnableVertexAttribArray(idFragmentShader);

        gl.glFrontFace(gl.GL_CW);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 3 * 6, gl.GL_UNSIGNED_BYTE, bufferIndice);
        gl.glFrontFace(gl.GL_CW);
        gl.glDisableVertexAttribArray(idVertexShader);
        gl.glDisableVertexAttribArray(idFragmentShader);

    }
}

