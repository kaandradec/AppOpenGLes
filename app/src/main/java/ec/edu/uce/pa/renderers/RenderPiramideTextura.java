package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.PiramideTextura;


public class RenderPiramideTextura implements GLSurfaceView.Renderer {
    private float vIncremento;
    private PiramideTextura triangulo;

    private int[] arrayTexturas = new int[5];//Identificadores de texturas
    private Context context;

    public RenderPiramideTextura(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glEnable(gl.GL_TEXTURE_2D);
        triangulo = new PiramideTextura();

        gl.glGenTextures(5, arrayTexturas, 0);

        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara1);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara2);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[1]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara3);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[2]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara4);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[3]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara5);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[4]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);

        bitmap.recycle();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1, 1, bottom, top, 1, 10);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);//Estado de las coordenadas de textura

        //AFECTA TODA LA ESCENA--------------------------
        gl.glTranslatef(0, 0, -3);
        gl.glRotatef(-vIncremento * 2, 1f, 0f, 0f);
        gl.glRotatef(-vIncremento, 0f, 1f, 0f);
        //-----------------------------------------------

        gl.glRotatef(180, 1,0,0);

        // Triangulo0
        gl.glBindTexture(GL10.GL_TEXTURE_2D, arrayTexturas[0]);//Textura a usar
        triangulo.dibujarCara(gl, 0);

        // Triangulo1
        gl.glBindTexture(GL10.GL_TEXTURE_2D, arrayTexturas[1]);
        triangulo.dibujarCara(gl, 1);

        // Triangulo2
        gl.glBindTexture(GL10.GL_TEXTURE_2D, arrayTexturas[2]);
        triangulo.dibujarCara(gl, 2);


        // Triangulo3
        gl.glBindTexture(GL10.GL_TEXTURE_2D, arrayTexturas[3]);
        triangulo.dibujarCara(gl, 3);


        // Base
        gl.glBindTexture(GL10.GL_TEXTURE_2D, arrayTexturas[4]);
        triangulo.dibujarCara(gl, 4);


        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);


    }
}