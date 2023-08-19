package ec.edu.uce.pa.renderers;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RenderColores implements GLSurfaceView.Renderer {
    private float vIncremento= 0f;
//    private static boolean interruptor= false;
//    private float[] arrColor = {1f, 0f ,0f ,0f};


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        //Se llama cuando la 'superficie es creada o recreada'
        //TOD0 lo estatico deberia estar aqui

        //RGBA (Toma valores de 0 a 1)
        //gl.glClearColor(arrColor[0], arrColor[1],arrColor[2],arrColor[3]);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //Cambios que se ejecutan al cambiar la posicion de la pantalla

        //cambio entre colores random
//        arrColor[0] = (float)Math.random();
//        arrColor[1] = (float)Math.random();
//        arrColor[2] = (float)Math.random();

        //Cambio entre colores fijos
//        if(interruptor){
//            arrColor[0] = 1f;
//            arrColor[1] = 0f;
//            arrColor[2] = 0f;
//            interruptor = false;
//        }else{
//            arrColor[0] = 0f;
//            arrColor[1] = 1f;
//            arrColor[2] = 0f;
//            interruptor = true;
//
//        }

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //se genera cada vez que dibujemos algo en pantalla
        gl.glClear(gl.GL_COLOR_BUFFER_BIT); //Antes de dibujar limpia la pantalla de tod0 lo que estaba antes
        vIncremento +=0.002;
////        gl.glClearColor(vIncremento,  0.2f, 0.2f, 1);
//
//        gl.glClearColor(ColorPantallaActivity.red,
//                ColorPantallaActivity.green,
//                ColorPantallaActivity.blue,
//                ColorPantallaActivity.alpha);

        //gl.glClearColor(arrColor[0], arrColor[1],arrColor[2],arrColor[3]);
        gl.glClearColor((float) Math.cos(2*vIncremento), (float) Math.cos(vIncremento), (float) Math.cos(vIncremento/5), 0.1f);
        //gl.glClearColor(vIncremento, 0, vIncremento, 0.5f);

    }
}
