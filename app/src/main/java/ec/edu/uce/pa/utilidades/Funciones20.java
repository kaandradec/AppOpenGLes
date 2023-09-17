package ec.edu.uce.pa.utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Funciones20 {
    public static FloatBuffer generarFloatBuffer(float[] arrayVertices) {
        int byteFlotante = 4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result = buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);
        return result;
    }

    public static ByteBuffer generarByteBuffer(byte[] arrayIndices) {
        ByteBuffer result;
        result = ByteBuffer.allocateDirect(arrayIndices.length);
        result.order(ByteOrder.nativeOrder());
        result.put(arrayIndices);
        result.position(0);
        return result;
    }

    public static int crearShader(int tipoShader, String SourceShader, GLES20 gl) {

        int[] infoCompiler = new int[1];
        int Shader = gl.glCreateShader(tipoShader);
        if (Shader == 0) {
            Log.w("Shader", "Error al crear vertex shader" + tipoShader); // mandamos a consola el error
            return 0;
        }


        gl.glShaderSource(Shader, SourceShader);
        GLES20.glCompileShader(Shader);
        GLES20.glGetShaderiv(Shader, gl.GL_COMPILE_STATUS, infoCompiler, 0);
        if (infoCompiler[0] == 0) {
            gl.glDeleteShader(Shader);
            Log.w("Shader", "Error al compilar el Shader" + tipoShader); // mandamos a consola el error
            return 0;
        }
        return Shader;
    }

    public static int crearPrograma(int vertexShader, int fragmentShader, GLES20 gl) {
        int[] infoLink = new int[1];
        int programa = gl.glCreateProgram();
        if (programa == 0) {
            Log.w("Programa", "Error al crear el programa GLSL "); // mandamos a consola el error
            return 0;
        }
        // 8 Attach shaders al programa
        gl.glAttachShader(programa, vertexShader);
        gl.glAttachShader(programa, fragmentShader);

        // 9 Vincular programa
        gl.glLinkProgram(programa);
        gl.glGetProgramiv(programa, gl.GL_LINK_STATUS, infoLink, 0);

        if (infoLink[0] == 0) {
            gl.glDeleteShader(programa);
            Log.w("Programa", "Error al vincular programa"); // mandamos a consola el error
            return 0;
        }

        return programa;
    }

    public static String leerArchivo(int idArchivo, Context contexto) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = contexto.getResources().openRawResource(idArchivo);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            String linea = bufferedReader.readLine();
            while (linea != null) {
                sb.append(linea);
                sb.append("\n");
                linea = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error al abrir archivo" + idArchivo, ex);

        }

        return String.valueOf(sb);
    }
    public static int[] habilitarTexturas (GLES20 gl, int numeroTexturas){
        int[] arrayTexturas = new int[numeroTexturas];
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(numeroTexturas, arrayTexturas, 0);
        return arrayTexturas;

    }
    public static void cargarImagenesTexturas(GLES20 gl, Context context, int idImagen, int indice, int[] arrayTexturas){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
    }

    public static  void liberarShader (int programa, int vertexShader, int fragmentShader){
        GLES20.glDeleteProgram(programa);
        GLES20.glDeleteShader(vertexShader);
        GLES20.glDeleteShader(fragmentShader);
    }

}
