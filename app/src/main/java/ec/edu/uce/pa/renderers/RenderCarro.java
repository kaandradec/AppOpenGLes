package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Carro;

public class RenderCarro implements GLSurfaceView.Renderer {

    private static final int NUM_VERTICES = 8; // Número de vértices alrededor del punto
    private static final float RADIUS = 0.5f; // Radio de los vértices alrededor del punto
    private FloatBuffer vertexBuffer;
    private int vertexCount;
    private int program;
    private int positionHandle;
    private int colorHandle;




    private Carro carro;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        carro = new Carro();

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glViewport(0,0,ancho,alto);
        //gl.glViewport(0,ancho/2,ancho,ancho);//viewPort para centrar vista
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-5,5,-5,5,1f,30);// left, right, bottom, top, Znear, Zfar
        //gl.glOrthof(-5,5,-5,5,1,30); //Otra proyeccion ortogonal
    }
    @Override
    public void onDrawFrame(GL10 gl) {

        // Limpiar el búfer de color y el búfer de profundidad
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Limpiar buffer de colores
//
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f,0.0f,-1.5f);//x,y,z
        gl.glScalef(1,0.5f,1);//Escalar un punto en X,Y,Z





        carro.dibujar(gl);

    }
}
