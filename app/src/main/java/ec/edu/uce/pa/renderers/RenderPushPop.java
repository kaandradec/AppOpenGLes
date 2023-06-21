package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderPushPop implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Triangulo triangulo;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
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
        vIncremento += 0.8f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();



        gl.glTranslatef(0.0f,-2.0f,-15.0f);
        gl.glPushMatrix();

            gl.glRotatef(vIncremento,0,0,1);
            triangulo.dibujar(gl);

            gl.glPushMatrix();

                gl.glRotatef(-vIncremento-vIncremento,0,0,1);
                gl.glTranslatef(0.0f,9.0f,0.0f);
                gl.glScalef(0.5f,0.5f,5f);
                triangulo.dibujar(gl);

                gl.glPushMatrix();

                    gl.glRotatef(vIncremento*2,0,0,1);
                    gl.glTranslatef(0.0f,9.0f,0.0f);
                    gl.glScalef(0.5f,0.5f,5f);
                    triangulo.dibujar(gl);

                gl.glPopMatrix();

            gl.glPopMatrix();

        gl.glPopMatrix();



    }
}