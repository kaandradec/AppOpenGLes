package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT1;
import static android.opengl.GLES10.GL_LIGHT2;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cilindro;
import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometriasIluminacion.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderLuzLampara implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;
    private Context context;
    private PlanoIluminacion planoIluminacion;
    private Cono conoLuz, conoCamara;
    private Cilindro palo;

    public RenderLuzLampara(Context context) {
        this.context = context;
    }

    float[] posicionLuz0 = {
            0.0f, 0.0f, -3f, 1.0f
    };
    float[] posicionLuz2 = {
            0.6f, 0.5f, -3.0f, 1.0f
    };

    float[] luzAmarilla = {
            0.3f, 0.3f, 0.0f, 1.0f
    };
    float[] luzAzulado = {
            0.298f, 0.329f, 0.823f, 1.0f
    };

    float[] luzBlanca = {
            1.0f, 1.0f, 1.0f, 1.0f
    };
    float[] sinLuz = {
            0.0f, 0.0f, 0.0f, 1.0f
    };
    private float[] spotDir1, spotDir2;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        planoIluminacion = new PlanoIluminacion();
        conoLuz = new Cono(0.7f, 2f, 10, new double[]{1.0f, 1.0f, 1.0f, 1f});
        conoCamara = new Cono(0.3f, 0.3f, 4, new double[]{0.0f, 0.0f, 0.0f, 1.0f});
        palo = new Cilindro(0.03f, 1.8f, 4, new double[]{0, 0, 0, 1});
        gl.glEnable(gl.GL_LIGHTING);
        //gl.glEnable(GL10.GL_BLEND);


        gl.glEnable(LUZ0);
        gl.glEnable(LUZ1);
        gl.glEnable(LUZ2);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.5f, 30f);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();


        gl.glTranslatef(0, -0.5f, -3.0f);

        //LUZ DE LA ESCENA
        gl.glLightfv(LUZ0, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0, gl.GL_DIFFUSE, Funciones.generarBuffer(luzAzulado));
        gl.glLightfv(LUZ0, gl.GL_SPECULAR, Funciones.generarBuffer(luzAzulado));


        gl.glPushMatrix();
        {//Planos cuarto
            gl.glLightfv(LUZ2, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz2));
            gl.glLightfv(LUZ2, gl.GL_DIFFUSE, Funciones.generarBuffer(luzBlanca));

            spotDir2 = new float[]{-1, -0.25f, 0};
            gl.glLightfv(LUZ2, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir2));
            gl.glLightf(LUZ2, gl.GL_SPOT_CUTOFF, 20f);
            gl.glLightf(LUZ2, gl.GL_SPOT_EXPONENT, 10);


            gl.glPushMatrix();
            {
                planoIluminacion.dibujar(gl);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {//Plano pared izq
                gl.glTranslatef(0, 0.5f, 0);
                gl.glScalef(1, 1.5f, 1);
                gl.glRotatef(90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);

                planoIluminacion.dibujar(gl);
            }
            gl.glPopMatrix();
            gl.glPushMatrix();
            {//Plano pared atras
                gl.glTranslatef(0, 0.5f, 0);
                gl.glScalef(1, 1.5f, 1);
                gl.glRotatef(180, 1, 0, 0);
                planoIluminacion.dibujar(gl);
            }
            gl.glPopMatrix();
            gl.glPushMatrix();
            {//Plano pared der
                gl.glTranslatef(0, 0.5f, 0);
                gl.glScalef(1, 1.5f, 1);
                gl.glRotatef(-90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);
                planoIluminacion.dibujar(gl);
            }
            gl.glPopMatrix();
            gl.glPushMatrix();
            {//Plano pared arriba
                gl.glTranslatef(0, 0.5f, 0);
                gl.glScalef(1, 1.5f, 1);
                gl.glRotatef(90, 1, 0, 0);
                planoIluminacion.dibujar(gl);
            }
            gl.glPopMatrix();


        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {//Cono de luz
            gl.glEnable(GL10.GL_BLEND);
            gl.glLightfv(LUZ2, gl.GL_DIFFUSE, Funciones.generarBuffer(sinLuz));
            gl.glLightfv(LUZ0, gl.GL_DIFFUSE, Funciones.generarBuffer(sinLuz));

            gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_DST_ALPHA);
            gl.glTranslatef(-1.3f, 0.5f, -.8f);
            gl.glRotatef(-75, 0, 0, 1);
            conoLuz.dibujar(gl);
            gl.glDisable(GL10.GL_BLEND);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {//Cono Camara
            //gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE);
            gl.glTranslatef(0.3f, 0.8f, -0);
            gl.glRotatef(-75, 0, 0, 1);
            conoCamara.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {//Palo camara
            gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE);
            gl.glTranslatef(0.5f, 0f, 0);
            palo.dibujar(gl);
        }
        gl.glPopMatrix();


    }
}