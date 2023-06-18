package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.geometrias.Icosfera;
import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderIcosfera implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Icosfera icosfera;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        icosfera = new Icosfera();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) alto / (float) ancho);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -aspectRatio, aspectRatio, 1f, 10f);// es la mas usada
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //vIncremento += 0.6f;;

        if(anguloSigno == 1){
            vIncremento += 0.6f;
        }
        if(anguloSigno == -1){
            vIncremento -= 0.6f;
        }


//        if(estadox ==0){//Horizontal
//
//        }



        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();


//        //GIRO DERECHA-----------------------------------------------
//        int puntosRueda = 100;
//        float RADIUS = 2.0f;
//
//        float x = 0;
//        float y= 0;
//
//        theta += 0.05;
//        //float theta = (float) ((2.0f * Math.PI )/ puntosRueda);
//        x = RADIUS * (float) Math.cos(theta);
//        y = RADIUS * (float) Math.sin(theta);
//
//        GLU.gluLookAt(gl,
//                y , 0, x,
//                0,0 ,0,
//                0,1,0
//        );
//        //-----------------------------------------------

        //GIRO ABAJO-----------------------------------------------
        float RADIUS = 2.0f;

        float x = 0;
        float y= 0;

        theta += 0.05;
        //float theta = (float) ((2.0f * Math.PI )/ puntosRueda);
        if(Math.cos(theta) <0){
            x = -RADIUS * (float) Math.cos(theta);
        }else{
            x = RADIUS * (float) Math.cos(theta);
        }

        if(Math.sin(theta) <0){
            y = -RADIUS * (float) Math.sin(theta);
        }else{
            y = RADIUS * (float) Math.sin(theta);
        }


        System.out.println(x);

        GLU.gluLookAt(gl,
                0 , x, y,
                0,0 ,0,
                0,1,0
        );
        //-----------------------------------------------





        //gl.glTranslatef(0.0f, 0.0f, -2.0f);
//        gl.glRotatef(vIncremento, 0f, 0.5f, 0f);//ROTA DERECHA
//        gl.glRotatef(-vIncremento, 0f, 0.5f, 0f);//ROTA IZQUIERDA
//        gl.glRotatef(vIncremento, 0.5f, 0f, 0f);//ROTA ABAJO
        //gl.glRotatef(-vIncremento, 0.5, 0f, 0f);//ROTA ARRIBA

        //gl.glRotatef(vIncremento, rx, ry, rz);



        icosfera.dibujar(gl);


//        estadox =rx;
//        estadoy = ry;

    }
    private float theta = 0;

    public static float anguloSigno = 1, rx = 0 ,ry = 0,rz = 0;


}