package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderDepthTest implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;
    private Triangulo triangulo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //gl.glDisable(GL10.GL_DEPTH_TEST);
        cubo = new Cubo();
        triangulo = new Triangulo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);// es la mas usada

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();


//        int puntosRueda = 100;
//        float RADIUS = 6.0f;
//
//        float x = 0;
//        float y= 0;
//
//        theta += 0.05;

//        x = RADIUS * (float) Math.cos(theta);
//        y = RADIUS * (float) Math.sin(theta);
//
//        GLU.gluLookAt(gl,
//                y , 0, x,
//                0,0 ,0,
//                0,1,0
//        );

        gl.glTranslatef(0,0,-5f);
        gl.glRotatef(vIncremento, 1, 0 ,0);
        gl.glPushMatrix();
        gl.glRotatef(vIncremento, 0, 1 ,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();

        gl.glTranslatef(0, -2, 2f);
        gl.glScalef(.5f,.5f,.5f);
        triangulo.dibujar(gl);


    }
}