package ec.edu.uce.pa.grupalAstros20;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones20;

public class RenderSistemaSolar implements GLSurfaceView.Renderer {
    private Astro astro;
    private EstrellasFondo estrellas;
    private Anillo anillo, anillo2, anillo3, anillo4, anillo5, anillo6, anillo7, anillo8;
    private FondoTextura nebulosa;

    private Context context;


    private float[] matrizProyeccion = new float[16];
    private float[] matrizModelo = new float[16];

    private float[] matrizVista = new float[16];

    private float[] matrizTemp = new float[16];
    private float relacionAspecto, rotacion = 0.0f, vIncremento = 0.0f;

    int[] arrayTextura = new int[1];

    public RenderSistemaSolar(Context contexto) {
        this.context = contexto;
        astro = new Astro(30, 30, 1.0f, 1.0f, contexto, matrizProyeccion, matrizVista, matrizModelo);
        estrellas = new EstrellasFondo(160);
        nebulosa = new FondoTextura(context, matrizProyeccion, matrizVista, matrizModelo);


        anillo = new Anillo(1.25f, 1.22f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo2 = new Anillo(1.5f, 1.48f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo3 = new Anillo(2f, 1.98f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo4 = new Anillo(2.5f, 2.48f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo5 = new Anillo(3.7f, 3.68f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo6 = new Anillo(4.5f, 4.48f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo7 = new Anillo(5.0f, 4.98f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
        anillo8 = new Anillo(5.4f, 5.38f, 100,
                contexto, matrizProyeccion, matrizVista,
                matrizModelo);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.00f, 0.00f, 0.00f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        arrayTextura = Funciones20.habilitarTexturas(new GLES20(), 10);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.sol, 0, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.mercurio, 1, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.venus, 2, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.tierra, 3, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.luna, 4, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.marte, 5, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.jupiter, 6, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.saturno, 7, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.urano, 8, arrayTextura);
        Funciones20.cargarImagenesTexturas(new GLES20(), context, R.drawable.neptuno, 9, arrayTextura);


        nebulosa.habilitarTexturasFondo(new GLES20(), 4);
        nebulosa.cargarImagenesTexturaFondo(new GLES20(), context, R.drawable.nebulosa, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        gl.glViewport(0, 0, ancho, alto);
        relacionAspecto = (float) ancho / (float) alto;
        Matrix.frustumM(matrizProyeccion, 0, -relacionAspecto, relacionAspecto, -1, 1, 1, 20);

        Matrix.setLookAtM(matrizVista, 0,
                0, 3, 5,
                0, 0, 1,
                0, 1, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);


        //ESTRELLAS---------------------------------------------------------------------------------
        posicionarObjeto();
        translate(0, 0, -20);
        estrellas.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------
        //NEBULOSA------------------------------------------------------------
        posicionarObjeto();
        translate(-3.5f, 2.5f, -5);
        scalef(0.09f, 0.09f, 0.09f);
        nebulosa.dibujar(new GLES20(), 0);
        //--------------------------------------------------------------------


        //ANILLOS:
        posicionarObjeto();
        anillo.dibujar(new GLES20());
        posicionarObjeto();
        anillo2.dibujar(new GLES20());
        posicionarObjeto();
        anillo3.dibujar(new GLES20());
        posicionarObjeto();
        anillo4.dibujar(new GLES20());
        posicionarObjeto();
        anillo5.dibujar(new GLES20());
        posicionarObjeto();
        anillo6.dibujar(new GLES20());
        posicionarObjeto();
        anillo7.dibujar(new GLES20());
        posicionarObjeto();
        anillo8.dibujar(new GLES20());
        //SOL---------------------------------------------------------------------------------------
        posicionarObjeto();
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 1);
        rotar(0, 1, 0, rotacion);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //MERCURIO----------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia = 1.2f;
        translate(distancia * (float) Math.cos(vIncremento), 0, distancia * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.1f, 0.1f, 0.1f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 2);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //VENUS-------------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia2 = 1.5f;
        translate(distancia2 * (float) Math.cos(vIncremento), 0, distancia2 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia2*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.12f, 0.12f, 0.12f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 3);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //TIERRA----------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia3 = 2f;
        translate(distancia3 * (float) Math.cos(vIncremento), 0, distancia3 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia3*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.15f, 0.15f, 0.15f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 4);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        // LUNA--------------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia4 = 0.2f;
        translate(distancia4, 0, distancia4);
        translate(distancia3 * (float) Math.cos(vIncremento), 0, distancia3 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia3*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.06f, 0.06f, 0.06f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 5);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //MARTE-------------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia5 = 2.5f;
        translate(distancia5 * (float) Math.cos(vIncremento), 0, distancia5 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia5*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.14f, 0.14f, 0.14f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 6);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //JUPITER-----------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia6 = 3.5f;
        translate(distancia6 * (float) Math.cos(vIncremento), 0, distancia6 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia6*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.4f, 0.4f, 0.4f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 7);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //SATURNO-----------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia7 = 4.5f;
        translate(distancia7 * (float) Math.cos(vIncremento), 0, distancia7 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia7*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.25f, 0.25f, 0.25f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 8);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //URANO-------------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia8 = 5f;
        translate(distancia8 * (float) Math.cos(vIncremento), 0, distancia8 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia8*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.15f, 0.15f, 0.15f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 9);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------

        //NEPTUNO-----------------------------------------------------------------------------------
        posicionarObjeto();
        float distancia9 = 5.5f;
        translate(distancia9 * (float) Math.cos(vIncremento), 0, distancia9 * (float) Math.sin(vIncremento));//rota en forma circular
        //translate(distancia9*(float)Math.cos(vIncremento),0,(float)Math.sin(vIncremento));//rota en forma eliptica (Eje Mayor en X)
        rotar(0, 1, 0, rotacion);
        scalef(0.15f, 0.15f, 0.15f);
        gl.glBindTexture(GLES20.GL_TEXTURE_2D, 10);
        astro.dibujar(new GLES20());
        //------------------------------------------------------------------------------------------


        rotacion -= 1f;//rotacion sobre su eje
        vIncremento += 0.005;//rotacion especto al origen

    }

    private void posicionarObjeto() {
        Matrix.setIdentityM(matrizModelo, 0);
        Matrix.multiplyMM(matrizTemp, 0, matrizProyeccion, 0, matrizModelo, 0);
        System.arraycopy(matrizTemp, 0, matrizProyeccion, 0, matrizTemp.length);
    }

    private void rotar(float x, float y, float z, float anguloRot) {

        Matrix.rotateM(matrizModelo, 0, anguloRot, x, y, z);
    }

    private void translate(float x, float y, float z) {
        float[] tempMatrix = new float[16];
        Matrix.setIdentityM(tempMatrix, 0);
        Matrix.translateM(tempMatrix, 0, x, y, z);
        Matrix.multiplyMM(matrizModelo, 0, tempMatrix, 0, matrizModelo, 0);
    }

    private void scalef(float x, float y, float z) {
        Matrix.scaleM(matrizModelo, 0, x, y, z);
    }
}
