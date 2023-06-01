package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Punto;

public class RenderPunto implements GLSurfaceView.Renderer {
//    public static int numPuntos;
//    public RenderPunto(int numPuntos){
//        this.numPuntos = numPuntos;
//    }
    private float vIncremento = 0f;
    private Punto punto;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        punto = new Punto();
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
        vIncremento += 2F;
        gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Limpiar buffer de colores
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,0.0f,-1.5f);//x,y,z
        gl.glScalef(1,0.5f,1);//Escalar un punto en X,Y,Z
        gl.glRotatef(vIncremento,0,0,1); //Rotar en el eje X,Y,Z
        punto.dibujar(gl);

        //gl.glTranslatef(0.0f,0.0f,-0.0f); //Vamos a trasladar
        //punto.dibujar(gl);//Si copio este mismo otra vez cambia el sentido
    }


}
