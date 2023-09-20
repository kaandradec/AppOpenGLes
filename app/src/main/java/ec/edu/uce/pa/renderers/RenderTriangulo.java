package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderTriangulo implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Triangulo triangulo;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.07059f,0.07059f,0.07059f,1.0f);
        triangulo = new Triangulo();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        //RELACION DE ASPECTO------------------------------------
        float relacionAspecto = (float)ancho/(float)alto;

        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        //Usamos la relacion de aspecto en el frustrum
        gl.glFrustumf(-5,5,-5,5,1,10);// left, right, bottom, top, Znear, Zfar
        //gl.glOrthof(-5,5,-5,5,1,30); //Otra proyeccion ortogonal
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.01F;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Limpiar buffer de colores
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

//        gl.glTranslatef((float)Math.sin(vIncremento),0.0f,-6.0f);//x,y,z
//        gl.glTranslatef(-0.5f,0.0f,-4.0f);//x,y,z
//        triangulo.dibujar(gl);

        gl.glTranslatef(0,-2f,-1.5f);//x,y,z
        gl.glScalef(1f,0.5f,1f);//Escalar un punto en X,Y,Z
        triangulo.dibujar(gl);

    }
}