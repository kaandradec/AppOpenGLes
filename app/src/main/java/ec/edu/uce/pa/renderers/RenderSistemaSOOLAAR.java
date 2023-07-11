//Clase Render;
package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Esfera;

public class RenderSistemaSOOLAAR implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Esfera esfera;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        esfera = new Esfera(20,20,1,1.0f, new float[]{1,0,0,1,  1,1,0,1});
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {

        // Calcular la relación de aspecto
        float aspectRatio = (float) ancho / alto;

        // Definir las dimensiones del frustrum
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        float near = 1.0f;
        float far = 10.0f;

        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(left, right, bottom, top, near, far);



    }
    private float cameraX = 0.0f; // Posición inicial de la cámara en el eje X
    private float cameraZ = 0.0f; // Posición inicial de la cámara en el eje Z
    private float velocidadEnX = 0.006f; // Velocidad de camara en X
    private float velocidadEnZ = 0.005f; // Velocidad de camara en Z
    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(-0,0,-4);

        cameraX += velocidadEnX ; // Mover la cámara en el eje X
        cameraZ += velocidadEnZ; // Mover la cámara en el eje Z
                GLU.gluLookAt(gl,
                cameraX , 0, (0),
                cameraX,0 ,-1,
                0,1,0
        );

       if(cameraX>4) System.out.println("DISTANCIA: " + cameraX);


        gl.glPushMatrix();{
            gl.glTranslatef(0,0,-1f);
            gl.glScalef(1,1,1);
            esfera.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(4,0,-1.5f);
            gl.glScalef(1.5f,1.5f,1.5f);
            esfera.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(10,0,-3f);
            gl.glScalef(3,3,3);
            esfera.dibujar(gl);
        }gl.glPopMatrix();


        vIncremento += 0.5f;
    }
}