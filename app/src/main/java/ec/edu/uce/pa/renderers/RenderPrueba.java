package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cilindro;
import ec.edu.uce.pa.geometrias.Circulo;
import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Plano;
import ec.edu.uce.pa.geometrias.Prisma;
import ec.edu.uce.pa.geometrias.Tablero;
import ec.edu.uce.pa.utilidades.MisColores;

public class RenderPrueba implements GLSurfaceView.Renderer {
    private float vIncremento;
    private float vIncremento2;
    public static float anguloSigno = 1, rx = 0 ,ry = 1,rz = 0;
    private Cubo cubo;
    private Circulo circulo;
    private Plano plano;
    private Cono conoPlano, conoPlano2,conoPlano8seg, conoPiso;
    private Cilindro cilindro, cilindro2, cajaBlanca;
    private Prisma prisma, prismaTablero;

    private Tablero tablero;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        cilindro = new Cilindro(1,1,26, MisColores.random(26));
        conoPlano = new Cono(1,0,26, MisColores.random(26));

        conoPlano8seg = new Cono(1,0,8, new double[]{0.8, 0.56, 0.84, 1});
        cilindro2 = new Cilindro(1,1,8, MisColores.random(8));
        conoPlano2 = new Cono(1,0,20, MisColores.blancoYnegro(20));
        conoPiso = new Cono(1,2.5f,20, MisColores.blancoYnegro(20));

        cubo = new Cubo();
        prisma = new Prisma(1,2,3, new float[] {1, 0.5f, 0, 1});

        circulo = new Circulo(1,40,new double[]{1,1,1,1});


        tablero = new Tablero(MisColores.blancoYnegro(16+8+1)); //Por cada fila 2 triangulos degenerados
        cajaBlanca = new Cilindro(2.5f,0.5f,4,new double[]{0.65,0.58,0.35,1});
        prismaTablero = new Prisma(3,0.5f,4, new float[]{0.65f,0.58f,0.35f,1});

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio*2, aspectRatio*2, 2f, 15*2);
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.5f;
        if(anguloSigno == 1){
            vIncremento2 += 0.3f;
        }
        if(anguloSigno == -1){
            vIncremento2 -= 0.3f;
        }
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        //MOVIMIENTO PARA TODA LA ESCENA con TECLADO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        gl.glTranslatef(0,-1f,-4.5f);//AFECTA A TODOS LOS OBJETOS
        gl.glRotatef(vIncremento2, rx, ry, rz);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        //CILINDRO CON 2 TAPAS-----------------
        gl.glPushMatrix();
        gl.glTranslatef(0,4,0);
        gl.glScalef(0.4f,0.4f,0.4f);

            gl.glPushMatrix();
                cilindro.dibujar(gl);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(0,0.5f,0);
            conoPlano.dibujar(gl);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(0,-0.5f,0);
            conoPlano.dibujar(gl);
            gl.glPopMatrix();

        gl.glPopMatrix();
        //-------------------------------------


        //CONOs + Cilindro---------------
//        gl.glPushMatrix();
//            gl.glPushMatrix();
//            gl.glTranslatef(0,0.5f,0);
//            conoPlano8seg.dibujar(gl);
//            gl.glPopMatrix();
//
//            gl.glPushMatrix();
//            cilindro2.dibujar(gl);
//            gl.glPopMatrix();
//
//            gl.glPushMatrix();
//            gl.glTranslatef(0,-0.5f,0);
//            gl.glScalef(2,1,2);
//            conoPlano2.dibujar(gl);
//            gl.glPopMatrix();
//
//            gl.glPushMatrix();
//            gl.glTranslatef(0,-0.5f,0);
//            gl.glScalef(2,1,2);
//            gl.glRotatef(180,1,0,0);
//            conoPiso.dibujar(gl);
//            gl.glPopMatrix();
//
//        gl.glPopMatrix();

        //-------------------------------------
        //CUBO---------------------------------
        gl.glPushMatrix();
            gl.glTranslatef(0,2,0);
            gl.glRotatef(vIncremento, 1,0,0.5f);
            gl.glScalef(0.3f,0.3f,0.3f);
            cubo.dibujar(gl);
        gl.glPopMatrix();
        //-------------------------------------
        //PRISMA---------------------------------
        gl.glPushMatrix();
        gl.glTranslatef(0,6,0);
        gl.glRotatef(-vIncremento, 1,0,0.5f);
        gl.glScalef(0.3f,0.3f,0.3f);
        prisma.dibujar(gl);
        gl.glPopMatrix();
        //-------------------------------------
        //CIRCULO---------------------------------
//        gl.glPushMatrix();
//        gl.glTranslatef(0,-0.5f,0);
//        gl.glScalef(2.1f,1,2.1f);
//        gl.glRotatef(90, 1,0,0);
//        circulo.dibujar(gl);
//        gl.glPopMatrix();
        //-------------------------------------




        //TABLERO ENTERO>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        gl.glPushMatrix();
        gl.glScalef(0.4f,0.4f,0.4f);

        gl.glPushMatrix();
        gl.glTranslatef(-2,0,2);
        tablero.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2,0,2);
        tablero.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(-2,0,-2);
        tablero.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2,0,-2);
        tablero.dibujar(gl);
        gl.glPopMatrix();

        gl.glPopMatrix();

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        //BORDE de TABLERO-----------------------------
        gl.glPushMatrix();
        gl.glScalef(0.8f,0.5f,0.8f);
        gl.glTranslatef(0,-.25f,0);
        gl.glRotatef(45, 0,1,0);
        prismaTablero.dibujar(gl);
        gl.glPopMatrix();
        //-------------------------------------








    }
}