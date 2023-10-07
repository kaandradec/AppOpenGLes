package ec.edu.uce.pa.renderers;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT2;
import static javax.microedition.khronos.opengles.GL10.GL_LIGHT1;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.PlanoMaterial;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderPlanoMaterial implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;
    private PlanoMaterial planoMaterial;

    private Cono cono;

    private float [] materialBlanco = {1.0f,1.0f,1.0f,1.0f};
    private float [] materialVerde= {.0f,1.0f,0.0f,1.0f};
    private float [] materialRojo = {1.0f,0.0f,0.0f,1.0f};
    private float [] materialAzul = {0.0f,0.0f,1.0f,1.0f};
    private float [] materialAmarillo= {1.0f,1.0f,0.0f,1.0f};

    private float [] materialBlancoMed = {0.50f,0.50f,0.50f,1.0f};
    private float [] materialVerdeMed= {.0f,0.50f,0.0f,1.0f};
    private float [] materialRojoMed = {0.50f,0.0f,0.0f,1.0f};
    private float [] materialAzulMed = {0.0f,0.0f,0.50f,1.0f};
    private float [] materialAmarilloMed= {0.50f,0.50f,0.0f,1.0f};

    private float[] spotDir1 ;
    private float[] spotDir2 ;
    float[] posicionLuz0 = {
            0f,0f,-3f,1.0f
    };
    float[] posicionLuz1 = {
            0, 0,-3.0f,1.0f
    };
    float[] posicionLuz2 = {
            0f, 0.0f,-3.0f,1f
    };
    float[] LuzBlanco = {
            0.8f,0.8f,0.8f,1.0f
    };
    float[] LuzRojo = {
            1.0f,0.0f,0.0f,1.0f
    };
    float[] LuzVerde = {
            0.0f,1.0f,0.0f,1.0f
    };
    float[] LuzAmarillo = {
            1.0f,1.0f,0.0f,1.0f
    };
    float[] LuzAzul = {
            0.0f,0.0f,1.0f,1.0f
    };
    private float [] LuzBlancoMed = {0.50f,0.50f,0.50f,1.0f};
    private float [] LuzVerdeMed= {.0f,0.50f,0.0f,1.0f};
    private float [] LuzRojoMed = {0.50f,0.0f,0.0f,1.0f};
    private float [] LuzAzulMed = {0.0f,0.0f,0.50f,1.0f};
    private float [] LuzAmarilloMed= {0.50f,0.50f,0.0f,1.0f};


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(gl.GL_LIGHTING);
        planoMaterial = new PlanoMaterial();

        cono = new Cono(1,0,8, new double[]{0.8, 0.56, 0.84, 1});


        spotDir1 = new float []{
                0,0,-1
        };
        spotDir2 = new float []{
                0,-1,0
        };


    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0,0,ancho,alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio,aspectRatio,-aspectRatio*2,aspectRatio*2,1,15);

    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_DEPTH_BUFFER_BIT );
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzRojoMed));
//        gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzBlancoMed));
        // gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
//        gl.glLightf(LUZ0, gl.GL_LINEAR_ATTENUATION,0);
//        gl.glLightf(LUZ0, gl.GL_CONSTANT_ATTENUATION,0);
//        gl.glLightf(LUZ0, gl.GL_QUADRATIC_ATTENUATION,0);



        gl.glLightfv(LUZ1,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ1,gl.GL_DIFFUSE, Funciones.generarBuffer(LuzVerde));
        // gl.glLightfv(LUZ0,gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));

        gl.glEnable(gl.GL_LIGHTING);

        gl.glEnable(LUZ1);
        gl.glEnable(LUZ0);

        gl.glTranslatef(0.0f,0f,-3.0f);
        gl.glPushMatrix();
        {

            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT,materialBlancoMed,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE,materialVerdeMed,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR,materialVerdeMed,0);
            gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, (float) (60*Math.sin(vIncremento)));
            gl.glRotatef(90,1,0,0);
            planoMaterial.dibujar(gl);

        }
        gl.glPopMatrix();
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE,materialRojoMed,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT,materialBlancoMed,0);
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR,materialRojoMed,0);
        gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS,1);

        planoMaterial.dibujar(gl);

        vIncremento += 0.05F;


    }
}