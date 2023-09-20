package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT1;
import static android.opengl.GLES10.GL_LIGHT2;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometriasIluminacion.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;

public
class RenderSpotLight implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;

    private PlanoIluminacion planoIluminacion;

    float[] posicionLuz0 = {-0.8f, 1.8f, 0.0f, 1.0f};
    float[] posicionLuz1 = {0f, 0f, 0.0f, 1.0f};
    float[] posicionLuz2 = {0f, 0f, 0.0f, 1.0f};

    float[] luzAmarilla = {
            0.9f, 0.9f, 0.0f, 1.0f
    };
    float[] luzVerde = {
            0f, 0.8f, 0.0f, 1.0f
    };
    float[] luzRoja = {
            0.8f, 0.0f, 0.0f, 1.0f
    };
    private float[] spotDir1, spotDir2;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);

        planoIluminacion = new PlanoIluminacion();

        //Luz 0---------------------------------------------------------------------
        gl.glLightfv(LUZ0, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0, gl.GL_DIFFUSE, Funciones.generarBuffer(luzAmarilla));

        //Luz 1---------------------------------------------------------------------
        gl.glLightfv(LUZ1, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz1));
        gl.glLightfv(LUZ1, gl.GL_DIFFUSE, Funciones.generarBuffer(luzVerde));

        //LUZ2---------------------------------------------------------------------
        gl.glLightfv(LUZ2, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz2));
        gl.glLightfv(LUZ2, gl.GL_DIFFUSE, Funciones.generarBuffer(luzRoja));

        gl.glEnable(gl.GL_LIGHTING);
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
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.5f, 30f);

        //VISTA FRONTAL
//        GLU.gluLookAt(gl,
//                0, 0, 3, //Posicion de la camara
//                0, 0, 0, //a donde mira la camara
//                0, 1, 0 //que eje se considera arriba para la camara
//        );
        //VISTA DERECHA HACIA EL CENTRO
        GLU.gluLookAt(gl,
                2.5f, 0, 2, //Posicion de la camara
                0, 0, 0, //a donde mira la camara
                0, 1, 0 //que eje se considera arriba para la camara
        );

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        vIncremento += 0.05F;

        //AFECTA A TODA LA ESCENA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glScalef(1.2f, 1.2f, 1.2f);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //Cambio de posición de luz verde
        float[] nuevaPosicionLuz1 = {0f, (float) Math.cos(vIncremento), 0.0f, 1.0f};
        gl.glLightfv(LUZ1, gl.GL_POSITION, FloatBuffer.wrap(nuevaPosicionLuz1));
        //-----------------------

        gl.glPushMatrix();
        {//Plano1
            spotDir1 = new float[]{0, 0, -1};//VERDE
            gl.glLightfv(LUZ1, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir1));
            gl.glLightf(LUZ1, gl.GL_SPOT_CUTOFF, 10);
            gl.glLightf(LUZ1, gl.GL_SPOT_EXPONENT, 1);

            planoIluminacion.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {//Plano2
            spotDir2 = new float[]{0, -1, 0};//ROJA
            gl.glLightfv(LUZ2, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir2));
            gl.glLightf(LUZ2, gl.GL_SPOT_CUTOFF, 50);
            gl.glLightf(LUZ2, gl.GL_SPOT_EXPONENT, 10);

            gl.glRotatef(180, 0, 0, 1);
            gl.glRotatef(90, 1, 0, 0);
            planoIluminacion.dibujar(gl);
        }
        gl.glPopMatrix();

    }
}