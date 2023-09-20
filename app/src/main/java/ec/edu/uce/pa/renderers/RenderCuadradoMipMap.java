package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoMipMap;


public class RenderCuadradoMipMap implements GLSurfaceView.Renderer {
    private float vIncremento, vIntervalo = -0.01f;
    private CuadradoMipMap cuadrado;

    private Context context;
    private float deltaZ = -2.0f;

    public RenderCuadradoMipMap(Context context){
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glEnable(gl.GL_TEXTURE_2D);
        cuadrado = new CuadradoMipMap(true);

        cuadrado.habilitarTexturas(gl, 1);

        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel0, 0, 0);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel1, 0, 1);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel2, 0, 2);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel3, 0, 3);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel4, 0, 4);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel5, 0, 5);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel6, 0, 6);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel7, 0, 7);
        cuadrado.cargarImagenesTextura(gl, context, R.drawable.nivel8, 0, 8);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        float near = 1.0f;
        float far = 80.0f;
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(left, right, bottom, top, near, far);

        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);//Configura el modo de mezcla de textura. Aqui usado para que no se mezcle con los colores definidos en la geometria.
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 5f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        if(deltaZ<-20.0f|| deltaZ>-2)
            vIntervalo*=-1;
        deltaZ +=vIntervalo;

        gl.glTranslatef(0.0f, 0.0f, deltaZ);
        cuadrado.dibujar(gl);

    }
}