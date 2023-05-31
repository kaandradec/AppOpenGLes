package ec.edu.uce.pa.renders;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Linea;
import ec.edu.uce.pa.geometrias.Punto;

public class RenderLinea implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Linea linea;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
        linea = new Linea();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-5,5,-5,5,1,30);// left, right, bottom, top, Znear, Zfar
        //gl.glOrthof(-5,5,-5,5,1,30); //Otra proyeccion ortogonal
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.2F;
        gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Limpiar buffer de colores
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,0.0f,-2.0f);//x,y,z
        gl.glScalef(1,2,1);//Escalar un punto en X,Y,Z
        gl.glRotatef(vIncremento,0,0,1); //Rotar en el eje X,Y,Z
        linea.dibujar(gl);

        gl.glTranslatef(0.0f,0.0f,0.0f); //Vamos a trasladar
        linea.dibujar(gl);//Si copio este mismo otra vez cambia el sentido
    }
}
