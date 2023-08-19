package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.AstroTextura;

public class RenderAstroTextura implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private AstroTextura astro;
    private Context context;

    public RenderAstroTextura(Context context){
        this.context = context;
        astro = new AstroTextura(30,30,1,1);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        astro.habilitarTexturas(gl, 2);
        astro.cargarImagenesTextura(gl, this.context, R.drawable.tierra, 0);

        astro.cargarImagenesTextura(gl, this.context, R.drawable.arcturus, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1, 1, bottom, top, 1, 10);
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        vIncremento += 0.5F;

        //AFECTA A TODA LA ESCENA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glTranslatef(0.0f, 0f, -2f);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        gl.glPushMatrix();{
            gl.glRotatef(vIncremento * 2, 0, 1, 0);
            gl.glTranslatef(0, 1f, 0);
            astro.dibujar(gl, 0);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glRotatef(vIncremento * 2, 0, 1, 0);
            gl.glTranslatef(0, -1f, 0);
            astro.dibujar(gl, 1);
        }gl.glPopMatrix();

    }
}