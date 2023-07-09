package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.ObjetoBlender;
import ec.edu.uce.pa.geometrias.Rectangulo;
import ec.edu.uce.pa.utilidades.MisColores;


public class RenderBlender implements GLSurfaceView.Renderer {
    private float vIncremento;
    private ObjetoBlender obj;
    Rectangulo rec;


    private Context context;
    public RenderBlender (Context context){
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //gl.glEnable(GL10.GL_BLEND);
        //obj = new ObjetoBlender(MisColores.random(967));
        obj = new ObjetoBlender("monaBlender.obj",MisColores.random(967), this.context);

        //obj = new ObjetoBlender(new float[]{1f,1f,0f,1f});
        rec = new Rectangulo();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15);// es la mas usada

    }


    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        //TODA LA ESCENA--------------------------------------------------
        gl.glTranslatef(0.0f, 0.0f, -4f);
        gl.glRotatef(vIncremento*2, 0f,1,0);
        //----------------------------------------------------------------

        gl.glPushMatrix();{
            gl.glScalef(1.5f,1.5f,1.5f);
            obj.dibujar(gl);
        }gl.glPopMatrix();

    }
}