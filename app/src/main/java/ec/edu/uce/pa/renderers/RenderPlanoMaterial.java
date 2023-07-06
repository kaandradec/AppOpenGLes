package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT1;
import static android.opengl.GLES10.GL_LIGHT2;

import android.opengl.GLSurfaceView;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometriasIluminacion.PlanoMaterial;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderPlanoMaterial implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private PlanoMaterial plano;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;

    float[] posicionLuz0= {0,0,-3f,1.0f};
    float[] posicionLuz1= {0f,0f,-3.0f,1.0f};
    float[] posicionLuz2= {0f,0f,-3.0f,1.0f};

    private float [] spotDir1, spotDir2;

    //COLORES_DE_MATERIALES--------------------------------------------

    private float[] materialBlanco = {1,1,1,1};
    private float[] materialVerde = {0,1,0,1};
    private float[] materialRojo = {0,0,1,1};
    private float[] materialAzul = {0,0,1,1};
    private float[] materialAmarillo = {1,1,0,1};
    private float[] materialBlancoMed = {0.5f,0.5f,0.5f,1};
    private float[] materialVerdeMed = {0,0.5f,0,1};
    private float[] materialRojoMed = {0.5f,0,0,1};
    private float[] materialAzulMed = {0,0,0.5f,1};
    private float[] materialAmarilloMed = {0.5f,0.5f,0,1};

    //COLORES_LUCES
    float[] luzBlanco = {0.9f,0.9f,0.0f, 1.0f};
    float[] luzAmarilla = {0.9f,0.9f,0.0f, 1.0f};
    float[] luzRoja = {0.8f,0.0f,0.0f, 1.0f};
    float[] luzVerde = {0f,0.8f,0.0f, 1.0f};
    float[] luzAzul = {0.0f,0.0f,1.0f, 1.0f};

    float[] luzBlancoMed = {0.5f,0.5f,0.5f, 1.0f};
    float[] luzAmarillaMed = {0.5f,0.5f,0.0f, 1.0f};
    float[] luzRojaMed = {0.5f,0.0f,0.0f, 1.0f};
    float[] luzVerdeMed = {0f,0.5f,0.0f, 1.0f};
    float[] luzAzulMed = {0.0f,0.0f,0.5f, 1.0f};






    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        plano = new PlanoMaterial();

        //Luz 0---------------------------------------------------------------------
        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0,gl.GL_AMBIENT, Funciones.generarBuffer(luzBlancoMed));
        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(luzVerdeMed));
        gl.glLightfv(LUZ0,gl.GL_SHININESS, Funciones.generarBuffer(luzVerdeMed));


        gl.glLightf(LUZ0,gl.GL_LINEAR_ATTENUATION, 2f);
//        gl.glLightf(LUZ0,gl.GL_CONSTANT_ATTENUATION,15 );
//        gl.glLightf(LUZ0,gl.GL_QUADRATIC_ATTENUATION,50 );


//        //Luz 1---------------------------------------------------------------------
//        gl.glLightfv(LUZ1,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz1));
//        gl.glLightfv(LUZ1,gl.GL_DIFFUSE, Funciones.generarBuffer(luzVerde));
//
//        //LUZ2---------------------------------------------------------------------
//        gl.glLightfv(LUZ2,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz2));
//        gl.glLightfv(LUZ2,gl.GL_DIFFUSE, Funciones.generarBuffer(luzRoja));


        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);
//        gl.glEnable(LUZ1);
//        gl.glEnable(LUZ2);

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

        //AFECTA A TODA LA ESCENA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glTranslatef(0.0f,0f,-3f);
        //gl.glScalef(1.2f,1.2f,1.2f);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        gl.glPushMatrix();{//Plano1_ACOSTADO
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialRojoMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE, materialRojoMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR, materialRojoMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, materialBlanco, 0);
            plano.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{//Plano2
            gl.glRotatef(90,1,0,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialVerdeMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE, materialRojoMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR, materialRojoMed, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, materialVerde, 0);
            plano.dibujar(gl);
        }gl.glPopMatrix();

    }
}