package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Icosfera;
import ec.edu.uce.pa.geometrias.Rectangulo;


public class RenderGrupalCamaras implements GLSurfaceView.Renderer {
    private Icosfera icosfera;
    private Rectangulo rectangulo;
    private Cubo cubo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        icosfera = new Icosfera();
        rectangulo = new Rectangulo();
        cubo = new Cubo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.5f, 30f);

        GLU.gluLookAt(gl,
                0, 0, 1, //Posicion de la camara
                0, 0, 0, //a donde mira la camara
                0, 1, 0 //que eje se considera arriba para la camara
        );

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        if (anguloSigno == 1) {
            vIncremento += 0.6f;
        }
        if (anguloSigno == -1) {
            vIncremento -= 0.6f;
        }
        //GIRAR MUNDO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        if (giraMundo) {
            gl.glTranslatef(0.0f, 0.0f, -2.0f);
            gl.glRotatef(vIncremento, rx, ry, rz);
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


        //GIRAR CAMARA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if (!giraMundo) {
            //GIRO DERECHA-----------------------------------------------
            if (anguloSigno == 1) {
                theta -= 0.05;
            }
            if (anguloSigno == -1) {
                theta += 0.05;
            }
            float RADIUS = 2.0f;
            float x = 0;
            float y = 0;
            //theta += 0.05;
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            GLU.gluLookAt(gl,
                    y, 0, x,
                    0, 0, 0,
                    0, 1, 0
            );
            //-----------------------------------------------
        }

        icosfera.dibujar(gl);
        gl.glPushMatrix();
        gl.glScalef(0.2f, 0.2f, 0.2f);
        gl.glTranslatef(-1f, 0f, 0f);
        rectangulo.dibujar(gl);
        gl.glPopMatrix();

    }

    private float vIncremento = 0;
    private float theta = 0;

    public static float anguloSigno = 1, rx = 0, ry = 0, rz = 0;

    public static boolean giraMundo;


}