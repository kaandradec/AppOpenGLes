package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;

import android.opengl.GLSurfaceView;

import java.io.FileInputStream;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometriasIluminacion.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;
import ec.edu.uce.pa.utilidades.ObjModel;

public class RenderIluminacion2 implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT0;
    private PlanoIluminacion planoIluminacion;
    private ObjModel mModel;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
        gl.glEnable(gl.GL_LIGHTING);
        planoIluminacion = new PlanoIluminacion();

        String ruta = "esfera.txt";
        try{
            mModel = ObjModel.loadFromStream(new FileInputStream(ruta), "mat1_dice.jpg");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("ERROR CARGANDO EL ARCHIVO");
        }

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio,aspectRatio,-aspectRatio*2,aspectRatio*2,2,30);

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT );
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        vIncremento += 0.05F;
        // float[] posicion = {
        //        (float) Math.cos(vIncremento),-(float) Math.sin(vIncremento),-3.0f,1.0f
        // };

        float[] posicion = {
                (float) (Math.cos(vIncremento)),-0.8f,-1.5f,1.0f
        };
        float[] posicion2 = {
                0, (float) ( Math.cos(vIncremento)),-2.0f,0.7f
        };
        float[] posicion3 = {
                0.9f, 0f,-2.0f,1f
        };
        float[] color = {
                1.0f,0.2f,0.2f,1.0f
        };
        float[] colorAmbient = {
                0.2f,0.2f,0.2f, 1.0f
        };
        float[] colorDifuso = {
                0.3f,0.8f,0.3f, 1.0f
        };

        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorAmbient));
        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicion));
//        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(new float[]{0f, .5f, 0f, 1f}));
//        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(new float[]{0f,0f,0f,1f}));


//        gl.glLightfv(LUZ0,gl.GL_AMBIENT, Funciones.generarBuffer(colorAmbient));
//        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(color));
//        gl.glLightfv(LUZ0,gl.GL_SPECULAR, Funciones.generarBuffer(colorDifuso));
        gl.glEnable(LUZ0);


        //AFECTA A TODA LA ESCENA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glTranslatef(0.0f,0f,-2.0f);
        gl.glScalef(1.5f,1.5f,1.5f);

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        gl.glPushMatrix();{//Plano1
            planoIluminacion.dibujar(gl);
        }gl.glPopMatrix();







    }
}