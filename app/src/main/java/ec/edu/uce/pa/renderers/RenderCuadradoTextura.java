package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.Cilindro;
import ec.edu.uce.pa.geometrias.Circulo;
import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.CuadradoTextura;
import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.utilidades.MisColores;


public class RenderCuadradoTextura implements GLSurfaceView.Renderer {
    private float vIncremento;
    private CuadradoTextura cuadrado;

    private int[] arrayTexturas = new int[1];//Textura
    private Context context;

    public RenderCuadradoTextura(Context context){
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glEnable(gl.GL_TEXTURE_2D);
        cuadrado = new CuadradoTextura();
        gl.glGenTextures(1,arrayTexturas,0);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cubo_textura);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        float near = 1.0f;
        float far = 10.0f;
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(left, right, bottom, top, near, far);

        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.22f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0,0,-4);
        gl.glRotatef(vIncremento*2,0,1,0);
        cuadrado.dibujar(gl);

    }
}