package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.CuboNeblina;

public class RenderCuboNeblina implements GLSurfaceView.Renderer {
    private CuboNeblina prisma;
        float vIncremento;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        prisma = new CuboNeblina();

        float[] LuzBlanco = {
                0.8f, 0.8f, 0.8f, 1.0f
        };
        float[] LuzRojo = {
                1.0f, 0.0f, 0.0f, 1.0f
        };
        float[] LuzVerde = {
                0.0f, 1.0f, 0.0f, 1.0f
        };
        float[] LuzAmarillo = {
                1.0f, 0.5f, 0.0f, 1.0f
        };

        gl.glFogfv(gl.GL_FOG_COLOR, FloatBuffer.wrap(LuzRojo));
        gl.glFogf(gl.GL_FOG_MODE, gl.GL_EXP);
        gl.glFogf(gl.GL_FOG_DENSITY,0.4f);
        gl.glFogf(gl.GL_FOG_START,1f);
        gl.glFogf(gl.GL_FOG_END,8f);
        gl.glEnable(gl.GL_FOG);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 1.0f, 15.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef((float) 0, 0, (float) (-5+2*Math.cos(vIncremento)));
        prisma.dibujar(gl);
        vIncremento+=0.01f;
    }
}