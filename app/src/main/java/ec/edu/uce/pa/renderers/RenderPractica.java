package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;

public class RenderPractica implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        cubo = new Cubo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);
        //FRONTAL
//        GLU.gluLookAt(gl,
//                0 , 0, 5,
//                0,0 ,0,
//                0,1,0
//        );
        //BACK
//        GLU.gluLookAt(gl,
//                0 , 0, -5,
//                0,0 ,0,
//                0,1,0
//        );

        //LEFT
//        GLU.gluLookAt(gl,
//                -5 , 0, 0,
//                0,0 ,0,
//                0,1,0
//        );
//        //TOP
//        GLU.gluLookAt(gl,
//                0 , 5, 0,
//                0,0 ,0,
//                1,0,0 //Mi camara enfoca al eje X
//        );
        //DONW
//        GLU.gluLookAt(gl,
//                0 , -5, 0,
//                0,0 ,0,
//                1,0,0 //Mi camara enfoca al eje X
//        );


    }

    //Frontal ROJO
    //Top VERDE
    //Izquierda AZUL
    //Atras MORADO
    //Derecha BLANCO
    //Abajo AMARILLO


    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0,0,-9f);
        gl.glRotatef(vIncremento, 0.2f,1,0.5f);


        gl.glPushMatrix();
        //========================================================================CUBO RUBIK

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>PRIMER CAPA
        //---------------------------------------Amarillo abajo (1,1)
        gl.glPushMatrix();
            gl.glTranslatef(-2.1f, 2.1f, 0);
            gl.glRotatef(-90,1,0,0);
            cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Azul izquierda (2,1)
        gl.glPushMatrix();
        gl.glTranslatef(0f, 2.1f, 0);
        gl.glRotatef(90,0,1,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Verde arriba (3,1)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Morado atras (1,2)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 0f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        cubo.dibujar(gl);//Rojo frontal (2,2)

        //---------------------------------------//Verde arriba(3,2)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 0f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Verde arriba (1,3)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, -2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Morado atras (2,3)
        gl.glPushMatrix();
        gl.glTranslatef(0f, -2.1f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Amarillo abajo (3,3)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, -2.1f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>






        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEGUNDA CAPA
        //---------------------------------------Amarillo abajo (1,1)
        gl.glPushMatrix();
        gl.glTranslatef(0,0,-2.1f);
        gl.glRotatef(180,0,0,1);

        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 2.1f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Azul izquierda (2,1)
        gl.glPushMatrix();
        gl.glTranslatef(0f, 2.1f, 0);
        gl.glRotatef(90,0,1,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Verde arriba (3,1)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Morado atras (1,2)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 0f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        cubo.dibujar(gl);//Rojo frontal (2,2)

        //---------------------------------------//Verde arriba(3,2)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 0f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Verde arriba (1,3)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, -2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Morado atras (2,3)
        gl.glPushMatrix();
        gl.glTranslatef(0f, -2.1f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Amarillo abajo (3,3)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, -2.1f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        gl.glPopMatrix();
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TERCER CAPA
        //---------------------------------------Amarillo abajo (1,1)
        gl.glPushMatrix();
        gl.glTranslatef(0,0,2.1f);
        gl.glRotatef(90,0,0,1);

        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 2.1f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Azul izquierda (2,1)
        gl.glPushMatrix();
        gl.glTranslatef(0f, 2.1f, 0);
        gl.glRotatef(90,0,1,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Verde arriba (3,1)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Morado atras (1,2)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 0f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        cubo.dibujar(gl);//Rojo frontal (2,2)

        //---------------------------------------//Verde arriba(3,2)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 0f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Verde arriba (1,3)
        gl.glPushMatrix();

        gl.glTranslatef(-2.1f, -2.1f, 0);
        gl.glRotatef(90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Morado atras (2,3)
        gl.glPushMatrix();
        gl.glTranslatef(0f, -2.1f, 0);
        gl.glRotatef(180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Amarillo abajo (3,3)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, -2.1f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        gl.glPopMatrix();
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //================================================================================

        gl.glPopMatrix();


    }
}