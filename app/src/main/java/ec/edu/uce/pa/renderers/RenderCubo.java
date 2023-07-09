package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cilindro;
import ec.edu.uce.pa.geometrias.Circulo;
import ec.edu.uce.pa.geometrias.Cono;
import ec.edu.uce.pa.geometrias.Cubo;
import ec.edu.uce.pa.utilidades.MisColores;


public class RenderCubo implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;

    private Cilindro cilindro;
    private Cono conoTecho, conoPlano, conoPlano2;
    private Circulo circulo;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glEnable(gl.GL_CULL_FACE);
//        gl.glEnable(gl.GL_CULL_FACE);
//        gl.glEnable(gl.GL_CULL_FACE);
//        gl.glCullFace(gl.GL_BACK);
        cubo = new Cubo();
        //cilindro = new Cilindro(1.0f, 1.0f,6, new double[]{0.3,0.8,1,1});
        cilindro = new Cilindro(1.0f, 1.0f,6, MisColores.seisColores());

        //conoTecho = new Cono(1.0f, 2.0f, 6 , new double[]{1,1,0,1});
        conoTecho = new Cono(1.0f, 2.0f, 6 , MisColores.seisColores());
        conoPlano = new Cono(1.0f, 0.0f, 6,new double[]{1,1,0,1});
        //conoPlano2 = new Cono(1.0f, 0.0f, 21,new double[]{1,0,0,1});
        conoPlano2 = new Cono(1.0f, 0.0f, 50,MisColores.blancoYnegro(50));
        //conoPlano2 = new Cono(1.0f, 0.0f, 21,null);

        //circulo = new Circulo(1.0f,6, new double[]{1,0,0,1});
        circulo = new Circulo(1.0f,25, MisColores.random(25));


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
    float alfa = 0;
    float ALGULO360;
    float gamma = 0;
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

        gl.glTranslatef(0.0f, 0.0f, -4f);
        //gl.glRotatef(vIncremento*2,0,1,0);
        //gl.glRotatef(90, 1,0,0);

        gl.glPushMatrix();

        ALGULO360+=5;//VELOCIDAD con la que gira en la trayectoria de elipse
        if (ALGULO360 ==360) ALGULO360=0;
        alfa=(float)(ALGULO360*(Math.PI/180));
        // Aplica la rotación alrededor del eje Z
        gl.glRotatef(gamma, 0.0f, 0.0f, 1f);
        // Calcula la posición actual de la elipse
        float radioMayor = 3f;
        float radioMenor = 1f;
        float x = (float) (radioMayor * Math.cos(alfa));
        float y = (float)(radioMenor * Math.sin(alfa));

        // Traslada al objeto a la posición de la elipse
        gl.glTranslatef(x, y, 0);
        gl.glScalef(0.3f, 0.3f, 0.3f);
        cubo.dibujar(gl);
        gl.glPopMatrix();


//        gl.glPushMatrix();
//        //gl.glRotatef(vIncremento*2,0,1,0);
//        conoTecho.dibujar(gl);
//            gl.glPushMatrix();
//            gl.glTranslatef(0,-0.5f,0);
//            cilindro.dibujar(gl);
//            gl.glPopMatrix();
//        gl.glPopMatrix();

//        gl.glPushMatrix();
//        gl.glTranslatef(0,-1f,0);
//        gl.glScalef(2,1,2);
//        conoPlano2.dibujar(gl);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//        gl.glTranslatef(0,-1f,0);
//        gl.glScalef(2.5f,2.5f,2.5f);
//        gl.glRotatef(90, 1,0,0);
//        circulo.dibujar(gl);
//        gl.glPopMatrix();
    }
}