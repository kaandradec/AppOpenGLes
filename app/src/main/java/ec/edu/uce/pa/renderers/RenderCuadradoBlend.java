package ec.edu.uce.pa.renderers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoBlend;
import ec.edu.uce.pa.geometrias.CuadradoTextura;
import ec.edu.uce.pa.utilidades.Funciones;


public class RenderCuadradoBlend implements GLSurfaceView.Renderer {
    private float vIncremento;
    private CuadradoBlend cuadrado, cuadrado2, cuadrado3;
    private CuadradoTextura cuadradoTextura;

    private int[] arrayTexturas ;//Se utilizará para almacenar las identificaciones de textura generadas por OpenGL.
    private Context context;

    public RenderCuadradoBlend(Context context){

        this.context = context;
        cuadradoTextura = new CuadradoTextura();

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        //gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        gl.glEnable(gl.GL_TEXTURE_2D);


        arrayTexturas = Funciones.habilitarTexturas(gl,1);
        Funciones.cargarImagenesTextura(gl,context, R.drawable.cubo_textura,0, arrayTexturas);


        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_DST_ALPHA);

        cuadrado = new CuadradoBlend(new float[]{0.5f, 0.5f, 0.5f , 0.5f});
        cuadrado2 = new CuadradoBlend(new float[]{0.0f, 0.5f, 0.0f , 0.5f});
        cuadrado3 = new CuadradoBlend(new float[]{0.0f, 0.0f, 0.5f , 1f});

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cubo_textura);//Decodifica un recurso de imagen en forma de Bitmap.
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);//Vincula la textura generada anteriormente para su uso en OpenGL.
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);//Carga la imagen en la textura vinculada.
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);//Configura el filtro de ampliación de la textura.
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);//Configura el filtro de reducción de la textura.
        bitmap.recycle();//Libera la memoria utilizada por el Bitmap ya que la imagen ya está cargada en OpenGL.

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
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

        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);//Configura el modo de mezcla de textura. Aqui usado para que no se mezcle con los colores definidos en la geometria.
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        vIncremento += 0.05f;

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        //gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_DST_ALPHA);

        gl.glColorMask(true, true, true, true);


        gl.glPushMatrix();{
            gl.glTranslatef((float)Math.cos(vIncremento),0,-3);
            cuadrado.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(0,(float)Math.cos(vIncremento),-3);
            cuadrado2.dibujar(gl);
        }gl.glPopMatrix();

        gl.glPushMatrix();{
            gl.glTranslatef(0,-(float)Math.sin(vIncremento),-3);
            //cuadrado3.dibujar(gl);
            gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
            cuadradoTextura.dibujar(gl);
        }gl.glPopMatrix();




    }
}