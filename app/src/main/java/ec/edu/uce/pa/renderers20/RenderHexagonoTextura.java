package ec.edu.uce.pa.renderers20;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias20.HexagonoTextura;
import ec.edu.uce.pa.utilidades.Funciones20;


public class RenderHexagonoTextura implements GLSurfaceView.Renderer {

    private HexagonoTextura hexagono;
    private float vIncremento = 0;
    private float relacionAspecto = 0, rotacion = 0;

    private Context contexto;
    private int[] arrTextura = new int[1];

    private  float[] matrizProyeccion = new float[16];

    private  float[] matrizModelo = new float[16];

    private  float[] matrizTemporal = new float[16];


    public RenderHexagonoTextura(Context contexto){
        this.contexto = contexto;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.07059f, 0.07059f, 0.07059f, 1.0f);

        hexagono = new HexagonoTextura(contexto,matrizProyeccion);
        arrTextura = Funciones20.habilitarTexturas(new GLES20(),1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        relacionAspecto = (float) width/height;

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Funciones20.cargarImagenesTexturas(new GLES20(),contexto, R.drawable.cara3,0,arrTextura);
        posicionarObjeto(0,0.5f,-2.5f,rotacion);
        hexagono.dibujar(new GLES20());

        rotacion+= 0.5f;
    }
    public void posicionarObjeto(float x, float y, float z, float anguloRot){
        Matrix.frustumM(matrizProyeccion,0,-relacionAspecto,relacionAspecto,-1,1,1,30);
        Matrix.setIdentityM(matrizModelo,0);
        Matrix.translateM(matrizModelo,0,x,y,z);
        Matrix.rotateM(matrizModelo,0,anguloRot,0,1,0);
        Matrix.multiplyMM(matrizTemporal,0,matrizProyeccion,0,matrizModelo,0);
        System.arraycopy(matrizTemporal,0,matrizProyeccion,0,matrizTemporal.length);
    }
}
