package ec.edu.uce.pa.utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Funciones {

    public static FloatBuffer generarBuffer(float[] arrayVertices ){
        int byteFlotante = 4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result = buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);

        return result;

    }

    public static int[] habilitarTexturas (GL10 gl, int numeroTexturas){
        int[] arrayTexturas = new int[numeroTexturas];
        gl.glEnable(gl.GL_TEXTURE_2D);
        arrayTexturas = new int[numeroTexturas];
        gl.glGenTextures(1,arrayTexturas,0);//Genera una textura en OpenGL y guarda su identificación en el array arrayTexturas.

        return arrayTexturas;
    }
    public static void cargarImagenesTextura (GL10 gl, Context context, int idImagen, int indice, int[] arrayTexturas){
        gl.glEnable(gl.GL_TEXTURE_2D);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
    }
}
