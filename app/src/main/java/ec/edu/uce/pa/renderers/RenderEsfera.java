package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Esfera;

public class RenderEsfera implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Esfera esfera, esferaTierra;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        esfera = new Esfera(30, 30, 1.5f, 1.0f, new float[]{1, 0, 0, 1, 1, 1, 0, 1});
        esferaTierra = new Esfera(30, 30, 1.5f, 1.0f, new float[]{0, 0, 1, 1, 0.3f, 1, 1, 1});
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.5f, 30f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -4f);
        //PRIMERA ESFERA
        gl.glPushMatrix();
        {
            gl.glPushMatrix();
            {
                gl.glRotatef(vIncremento / 4, 0.5f, 0.5f, 1f);
                esfera.dibujar(gl);
            }
            gl.glPopMatrix();

            //SEGUNDA ESFERA
            gl.glPushMatrix();
            {
                gl.glRotatef(vIncremento / 2, 0.5f, 0.5f, 1f);
                gl.glTranslatef(0, 2.2f, 0);
                gl.glScalef(0.2f, 0.2f, 0.2f);
                gl.glRotatef(vIncremento * 2, 0, 1, 1);
                esferaTierra.dibujar(gl);

                //TERCERA ESFERA
                gl.glPushMatrix();
                {
                    gl.glRotatef(vIncremento * 6, 0.5f, 0.5f, 1f);
                    gl.glTranslatef(0, 2.2f, 0);
                    gl.glScalef(0.2f, 0.2f, 0.2f);
                    esfera.dibujar(gl);

                    //CUARTA ESFERA-------------
                    gl.glPushMatrix();
                    {
                        gl.glRotatef(vIncremento * 6, 0.2f, 0.9f, 0.2f);
                        gl.glTranslatef(2f, -2f, 0);
                        gl.glScalef(0.2f, 0.2f, 0.2f);
                        esfera.dibujar(gl);
                    }
                    gl.glPopMatrix();

                }
                gl.glPopMatrix();

            }
            gl.glPopMatrix();


        }
        gl.glPopMatrix();

        vIncremento += 0.5f;
    }
}