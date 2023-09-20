package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT1;
import static android.opengl.GLES10.GL_LIGHT2;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.AstroTextura;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderTierraLuces implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private AstroTextura astro;
    private Context context;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;

    float[] posicionLuz0= {0,0,0,1f};
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


    public RenderTierraLuces(Context context){
        this.context = context;
        astro = new AstroTextura(30,30,1,1);

    }



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glEnable(gl.GL_DEPTH_TEST);
        astro.habilitarTexturas(gl, 1);
        astro.cargarImagenesTextura(gl, this.context, R.drawable.tierra, 0);

        //Luz 0---------------------------------------------------------------------
        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0,gl.GL_AMBIENT, Funciones.generarBuffer(luzBlancoMed));
        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(luzBlancoMed));
//        gl.glLightfv(LUZ0,gl.GL_SHININESS, Funciones.generarBuffer(luzVerdeMed));


//        gl.glLightf(LUZ0,gl.GL_LINEAR_ATTENUATION, 2f);
//        gl.glLightf(LUZ0,gl.GL_CONSTANT_ATTENUATION,15 );
//        gl.glLightf(LUZ0,gl.GL_QUADRATIC_ATTENUATION,50 );

        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);


    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1, 1, bottom, top, 1, 10);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT );
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        vIncremento += 0.5F;


        //AFECTA A TODA LA ESCENA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glTranslatef(0.0f,0f,-2f);
        //gl.glScalef(1.2f,1.2f,1.2f);
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        gl.glPushMatrix();{
            gl.glRotatef(vIncremento*2, 0,1,0);
            astro.dibujar(gl, 0);
        }gl.glPopMatrix();


    }
}