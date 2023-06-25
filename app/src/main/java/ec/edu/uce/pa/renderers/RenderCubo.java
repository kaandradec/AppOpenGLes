package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.*;
import ec.edu.uce.pa.utilidades.MisColores;


public class RenderCubo implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;

    private Cilindro cilindro;
    private Cono cono, conoPlano, conoPlano2;
    private Circulo circulo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glEnable(gl.GL_CULL_FACE);
//        gl.glCullFace(gl.GL_BACK);
        cubo = new Cubo();
        cilindro = new Cilindro(1.0f, 1.0f,6, new double[]{1,1,0,1});
        cono = new Cono(1.0f, 2.0f, 6 , new double[]{1,1,0,1});
        conoPlano = new Cono(1.0f, 0.0f, 6,new double[]{1,1,0,1});
        conoPlano2 = new Cono(1.0f, 0.0f, 21,new double[]{1,1,1,1});
        //conoPlano2 = new Cono(1.0f, 0.0f, 21,null);

        //circulo = new Circulo(1.0f,6, new double[]{1,0,0,1});
        circulo = new Circulo(1.0f,6, MisColores.random(6));


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15);// es la mas usada
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


    float ex = 0, ey= 0, ez= 0;
    float ux= 0, uy= 1, uz= 0;
    float theta = 0;
    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.22f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        /*
        int puntosRueda = 100;
        float RADIUS = 6.0f;

        float x = 0;
        float y= 0;

        theta += 0.05;
        //float theta = (float) ((2.0f * Math.PI )/ puntosRueda);
        x = RADIUS * (float) Math.cos(theta);
        y = RADIUS * (float) Math.sin(theta);

        GLU.gluLookAt(gl,
                y , 0, x,
                0,0 ,0,
                0,1,0
        );
        gl.glTranslatef(0.0f, 0.0f, -4.0f);//Con GLU lookat no es necesadio transladar
        cubo.dibujar(gl);

         */

        gl.glTranslatef(0.0f, 0.0f, -4.5f);

        gl.glPushMatrix();
        gl.glRotatef(vIncremento*2, 0, 1,0);
        cono.dibujar(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0,-0.5f,0);
            cilindro.dibujar(gl);
            gl.glPopMatrix();
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0,-1f,0);
        gl.glScalef(2,2,2);
        conoPlano2.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0,-1f,0);
        gl.glScalef(2.5f,2.5f,2.5f);
        gl.glRotatef(90, 1,0,0);
        circulo.dibujar(gl);
        gl.glPopMatrix();
    }
}