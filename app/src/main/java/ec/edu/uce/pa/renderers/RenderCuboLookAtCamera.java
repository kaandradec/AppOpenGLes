package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Cubo;


public class RenderCuboLookAtCamera implements GLSurfaceView.Renderer {
    private float vIncremento;
    private Cubo cubo;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
//        gl.glEnable(gl.GL_CULL_FACE);
//        gl.glCullFace(gl.GL_BACK);
        cubo = new Cubo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.5f, 30f); // es la mas usada

        //FRONTAL
        GLU.gluLookAt(gl,
                0, 0, 5, //Posicion de la camara
                0, 0, 0, //a donde mira la camara
                0, 1, 0 //que eje se considera arriba para la camara
        );
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
//                0,0,-1 //al estar mirando desde arriba ahora 'arriba' será el eje -z en lugar de y
//        );
        //DONW
//        GLU.gluLookAt(gl,
//                0, -5, 0,
//                0, 0, 0,
//                0, 0, 1
//        );

        //Frontal ROJO
        //Top VERDE
        //Izquierda AZUL
        //Atras MORADO
        //Derecha BLANCO
        //Abajo AMARILLO

    }


    float ex = 0, ey = 0, ez = 0;
    float ux = 0, uy = 1, uz = 0;
    float theta = 0;
    float alfa = 0;
    float ALGULO360;
    float gamma = 0;

    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 1f;

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
        //CUBO ORBITANDO ELIPTICAMANTE
        gl.glPushMatrix();
        {
            ALGULO360 += 1;//VELOCIDAD con la que gira en la trayectoria de elipse
            if (ALGULO360 == 360) ALGULO360 = 0;
            alfa = (float) (ALGULO360 * (Math.PI / 180));
            // Aplica la rotación alrededor del eje Z
            gl.glRotatef(gamma, 0.0f, 0.0f, 1f);
            // Calcula la posición actual de la elipse
            float radioMayor = 3f;
            float radioMenor = 1f;
            float x = (float) (radioMayor * Math.cos(alfa));
            float y = (float) (radioMenor * Math.sin(alfa));

            // Traslada al objeto a la posición de la elipse
            gl.glTranslatef(x, y, 0);
            gl.glScalef(0.3f, 0.3f, 0.3f);
            cubo.dibujar(gl);

        }
        gl.glPopMatrix();

        //CUBO CENTRAL
        gl.glPopMatrix();
        {
            gl.glRotatef(vIncremento, 1,1,0.5f);
            gl.glScalef(.5f,.5f, .5f);
            cubo.dibujar(gl);
        }
        gl.glPopMatrix();


    }
}