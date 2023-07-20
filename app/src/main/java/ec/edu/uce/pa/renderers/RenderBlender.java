package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.ObjetoBlender;
import ec.edu.uce.pa.utilidades.MisColores;


public class RenderBlender implements GLSurfaceView.Renderer {
    private float vIncremento;
    private ObjetoBlender mona, dona, langosta,casco;

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
        mona = new ObjetoBlender("monaBlender.obj",MisColores.random(967), this.context);
        dona = new ObjetoBlender("donaBlender.obj",MisColores.blancoYnegro(1000), this.context);
        langosta = new ObjetoBlender("langosta.obj",new float[]{0,0.2f, 0.9f,1}, this.context);//10 000 poligonos/vertices
        casco = new ObjetoBlender("casco.obj",MisColores.random(2496), this.context);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {

        // Calcular la relación de aspecto
        float aspectRatio = (float) ancho / alto;

        // Definir las dimensiones del frustrum
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        float near = 1.0f;
        float far = 10.0f;

        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(left, right, bottom, top, near, far);

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
            gl.glTranslatef(0,5,0);
            //gl.glScalef(1.2f,1.2f,1.2f);
            gl.glRotatef(vIncremento*2, 1,1,1);
            dona.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(0,2f,0);
            gl.glScalef(1.2f,1.2f,1.2f);
            mona.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(0,-0.5f,0);
            gl.glScalef(1.5f,1.5f,1.5f);
            casco.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(0,-5f,-0);
            gl.glScalef(0.4f,0.4f,0.4f);
            gl.glRotatef(90, 1,0,0);
            gl.glRotatef(180, 0,0,1);
            gl.glRotatef(180, 0,1,0);
            langosta.dibujar(gl);
        }gl.glPopMatrix();

    }
}