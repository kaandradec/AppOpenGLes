package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Icosfera;
import ec.edu.uce.pa.geometrias.Rectangulo;


public class RenderFigurasAntiguo implements GLSurfaceView.Renderer{
    //Figura 1:
    private Icosfera icosfera;
    //Figura 2:
    private Rectangulo rectangulo;
    //Incremento Movimiento:
    private float vIncremento = 0;
    private float theta = 0;
    //Angulo corrdenadas para mover el mundo (objeto):
    public static float anguloSigno = 1, rx = 0 ,ry = 1,rz = 0;
    //Coordenadas para mover la camara (nosotros):
    public static float ejex = 0 ,ejey = 0,ejez = 0;
    //Saber si el mundo esta girando:
    public static boolean giraMundo;//true-> gira el mundo / false -> gira la camara
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        icosfera = new Icosfera();
        rectangulo = new Rectangulo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio, aspectRatio, 1f, 10f);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        if(anguloSigno == 1){
            vIncremento += 3f;
        }
        if(anguloSigno == -1){
            vIncremento -= 3f;
        }
        //GIRAR MUNDO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        if(giraMundo){
            gl.glTranslatef(0.0f, 0.0f, -2.0f);
            gl.glRotatef(vIncremento, rx, ry, rz);
        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


        //GIRAR CAMARA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if(!giraMundo){
//            //-----------------------------------------------
            if(anguloSigno == 1){
                theta -= 0.05;
            }
            if(anguloSigno == -1){
                theta += 0.05;
            }
            float RADIUS = 2.0f;
            float x = 0;
            float y= 0;
            //theta += 0.05;
            x = RADIUS * (float) Math.cos(theta);
            y = RADIUS * (float) Math.sin(theta);

            GLU.gluLookAt(gl,
                    y*ejex , y*ejey, x*ejez,
                    0,0 ,0,
                    0,1,0
            );

        }
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        icosfera.dibujar(gl);

        gl.glPushMatrix();
            gl.glScalef(0.2f,0.2f, 0.2f);
            gl.glTranslatef(-1f, 0f, 0f);
            rectangulo.dibujar(gl);
        gl.glPopMatrix();



    }




}
