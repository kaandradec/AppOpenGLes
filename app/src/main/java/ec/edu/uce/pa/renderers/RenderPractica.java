package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Circulo;
import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Plano;

public class RenderPractica implements GLSurfaceView.Renderer {
    private float vIncremento;
    private float vIncremento2;
    public static float anguloSigno = 1, rx = 0 ,ry = 1,rz = 0;
    private Cubo cubo;
    private Circulo circulo;
    private Plano plano;
    private Cono conoPlano;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        cubo = new Cubo();
        circulo = new Circulo( 3f,  30, new double[]{0,0,0,1});
        conoPlano = new Cono(3f,  0,30, new double[]{0.1, 0.1 ,0.1, 1});
        plano = new Plano(new float[] {.88f, 0.88f, 0.88f, 1.0f});



    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);


        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        //gl.glFrustumf(-aspectRatio, aspectRatio, -3, 3, 2f, 15);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15*2);

        //FRONTAL
//        GLU.gluLookAt(gl,
//                0 , 0, 5,
//                0,0 ,0,
//                0,1,0
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
        if(anguloSigno == 1){
            vIncremento2 += 0.1f;
        }
        if(anguloSigno == -1){
            vIncremento2 -= 0.1f;
        }

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        //MOVIMIENTO PARA TODA LA ESCENA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        gl.glTranslatef(0,0,-10f);//AFECTA A TODOS LOS OBJETOS

        gl.glRotatef(vIncremento2, rx, ry, rz);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //PLANO.................................
        gl.glPushMatrix();
        gl.glTranslatef(0,-6.01f,0f);
        gl.glRotatef(90, 1,0,0);
        gl.glScalef(6,6,1);
        plano.dibujar(gl);
        gl.glPopMatrix();
        //......................................

        //CIRCULO.........................
        gl.glPushMatrix();
        gl.glTranslatef(0,-6,0f);
        gl.glRotatef(90, 1,0,0);
        circulo.dibujar(gl);
        gl.glPopMatrix();
        //......................................

        //CONO PLANO.........................
        gl.glPushMatrix();
        gl.glTranslatef(0,-6,0f);
        gl.glScalef(0.9f,0.9f,0.9f);
        conoPlano.dibujar(gl);
        gl.glPopMatrix();
        //......................................





        //========================================================================CUBO RUBIK

        gl.glPushMatrix();
        if(anguloSigno == -1){
            vIncremento += 0.1f;//cancela la rotacion de la escena para el cuboRubik

        }
        gl.glRotatef(vIncremento, -0.5f,1,0f);
        //gl.glRotatef(vIncremento, 0, 1, 0);

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

        gl.glPushMatrix();
        gl.glTranslatef(0,0,-2.1f);
        gl.glRotatef(180,0,0,1);
        //---------------------------------------Amarillo abajo (1,1)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 2.1f, 0);
        gl.glRotatef(-180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Azul izquierda (2,1)
        gl.glPushMatrix();
        gl.glTranslatef(0f, 2.1f, 0);
        gl.glRotatef(-180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Verde arriba (3,1)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 2.1f, 0);
        gl.glRotatef(90,0,1,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Morado atras (1,2)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 0f, 0);
        gl.glRotatef(180,0,0,1);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        cubo.dibujar(gl);//Rojo frontal (2,2)

        //---------------------------------------//Verde arriba(3,2)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 0f, 0);
        gl.glRotatef(-90,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Verde arriba (1,3)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, -2.1f, 0);
        gl.glRotatef(-180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Morado atras (2,3)
        gl.glPushMatrix();
        gl.glTranslatef(0f, -2.1f, 0);
        gl.glRotatef(180,0,1,0);
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
        gl.glRotatef(90,0,0,1);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Azul izquierda (2,1)
        gl.glPushMatrix();
        gl.glTranslatef(0f, 2.1f, 0);
        gl.glRotatef(-90,0,1,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------//Verde arriba (3,1)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 2.1f, 0);
        gl.glRotatef(90,0,0,1);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------


        //---------------------------------------//Morado atras (1,2)
        gl.glPushMatrix();
        gl.glTranslatef(-2.1f, 0f, 0);
        gl.glRotatef(-180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        cubo.dibujar(gl);//Rojo frontal (2,2)

        //---------------------------------------//Verde arriba(3,2)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, 0f, 0);
        gl.glRotatef(-180,0,1,0);
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
        gl.glRotatef(0,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------
        //---------------------------------------Amarillo abajo (3,3)
        gl.glPushMatrix();
        gl.glTranslatef(2.1f, -2.1f, 0);
        gl.glRotatef(-180,1,0,0);
        cubo.dibujar(gl);
        gl.glPopMatrix();
        //---------------------------------------

        gl.glPopMatrix();
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        gl.glPopMatrix();
        //================================================================================








    }
}