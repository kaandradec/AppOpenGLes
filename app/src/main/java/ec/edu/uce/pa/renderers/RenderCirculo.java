package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.OpenGL10Activity;
import ec.edu.uce.pa.geometrias.Circulo;

public class RenderCirculo implements GLSurfaceView.Renderer {
    private float vIncremento;

    private Circulo circulo;
    private int segmentos = OpenGL10Activity.numPrimitivas;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.07059f,0.07059f,0.07059f,1.0f);gl.glClearColor(0.07059f,0.07059f,0.07059f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        circulo = new Circulo(3,30,new double[]{1,1,1,1});

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15*2);
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        //MOVIMIENTO PARA TODA LA ESCENA
        gl.glTranslatef(0,0f,-4.5f);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        circulo.dibujar(gl);

    }
}