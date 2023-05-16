package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderColores implements GLSurfaceView.Renderer {
    private float vIncremento= 0f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        //Esto es lo qye noi va a cambiar en nsetro programa
        //Inicializa lo que queremos inicializar por primera vez
        //TOD0 lo estatico deberia estar aqui
        //RGBA (Toma valores de 0 a 1)
        gl.glClearColor(0.2f,  0.2f, 0.2f, 1);//limpia la pantalla de un color

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int i, int i1) {
        //Cambios en la posicion de la pantalla
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //se genera cada vez que dibujemos algo en pantalla
        gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Antes de dibujar limpia la pantalla de todo lo que estaba antes
        vIncremento +=0.001;
        gl.glClearColor(vIncremento,  0.2f, 0.2f, 1);
    }
}
