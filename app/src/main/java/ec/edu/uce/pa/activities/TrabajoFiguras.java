package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renderers.RenderFigurasAntiguo;
import ec.edu.uce.pa.renders.RenderFiguras;


public class TrabajoFiguras extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trabajo_pagina);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);

        //Boton para girar mundo (gl.glRotate)
        Button btnMundo = findViewById(R.id.btnMundo);
        btnMundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                renderer = null;
                renderer = new RenderFigurasAntiguo();
                view.setRenderer(renderer);
                setContentView(view);

                //Controlador: nos dice si gira o no nuestro mundo.
                RenderFigurasAntiguo.giraMundo = true;

                Toast toast = Toast.makeText(TrabajoFiguras.this , "MOVIENDO EL MUNDO", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        });

        //Boton para girar con camara (GLU.gluLookAt)
        Button btnCamara = findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderer = null;
                renderer = new RenderFigurasAntiguo();
                view.setRenderer(renderer);
                setContentView(view);

                RenderFigurasAntiguo.giraMundo = false;

                Toast toast = Toast.makeText(TrabajoFiguras.this , "MOVIENDO LA CAMARA", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                Toast toast2 = Toast.makeText(TrabajoFiguras.this , "PRESIONE UNA FLECHA PARA GIRAR", Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_VERTICAL, 0, 0);
                toast2.show();

            }
        });


    }

    //Metodo para detectar las teclas y manejar los movimientos de camara,mundo:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Tecla derecha:
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            Toast.makeText(this, "[Girando derecha]", Toast.LENGTH_SHORT).show();
            RenderFigurasAntiguo.anguloSigno = 1;
            RenderFigurasAntiguo.rx = 0f;
            RenderFigurasAntiguo.ry = 1f;
            RenderFigurasAntiguo.rz = 0f;

            RenderFigurasAntiguo.ejex =1;
            RenderFigurasAntiguo.ejey =0;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla izquierda:
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
            RenderFigurasAntiguo.anguloSigno = -1;
            RenderFigurasAntiguo.rx = 0f;
            RenderFigurasAntiguo.ry = 1f;
            RenderFigurasAntiguo.rz = 0f;

            RenderFigurasAntiguo.ejex =1;
            RenderFigurasAntiguo.ejey =0;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla abajo:
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
            RenderFigurasAntiguo.anguloSigno = 1;
            RenderFigurasAntiguo.rx = 1f;
            RenderFigurasAntiguo.ry = 0f;
            RenderFigurasAntiguo.rz = 0f;

            RenderFigurasAntiguo.ejex =0;
            RenderFigurasAntiguo.ejey =1;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla arriba:
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
            RenderFigurasAntiguo.anguloSigno = -1;
            RenderFigurasAntiguo.rx = 1f;
            RenderFigurasAntiguo.ry = 0f;
            RenderFigurasAntiguo.rz = 0f;

            RenderFigurasAntiguo.ejex =0;
            RenderFigurasAntiguo.ejey =1;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        if (deltaX > 0) {
                            // Deslizamiento hacia la derecha detectado
                            //Toast.makeText(this, "[Girando derecha]", Toast.LENGTH_SHORT).show();
                            RenderFigurasAntiguo.anguloSigno = 1;
                            RenderFigurasAntiguo.rx = 0f;
                            RenderFigurasAntiguo.ry = 1f;
                            RenderFigurasAntiguo.rz = 0f;

                            RenderFigurasAntiguo.ejex =1;
                            RenderFigurasAntiguo.ejey =0;
                            RenderFigurasAntiguo.ejez =1;
                        } else {
                            // Deslizamiento hacia la izquierda detectado
                            //Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
                            RenderFigurasAntiguo.anguloSigno = -1;
                            RenderFigurasAntiguo.rx = 0f;
                            RenderFigurasAntiguo.ry = 1f;
                            RenderFigurasAntiguo.rz = 0f;

                            RenderFigurasAntiguo.ejex =1;
                            RenderFigurasAntiguo.ejey =0;
                            RenderFigurasAntiguo.ejez =1;
                        }
                    }
                } else {
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        if (deltaY > 0) {
                            // Deslizamiento hacia abajo detectado
                            //Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
                            RenderFigurasAntiguo.anguloSigno = 1;
                            RenderFigurasAntiguo.rx = 1f;
                            RenderFigurasAntiguo.ry = 0f;
                            RenderFigurasAntiguo.rz = 0f;

                            RenderFigurasAntiguo.ejex =0;
                            RenderFigurasAntiguo.ejey =1;
                            RenderFigurasAntiguo.ejez =1;
                        } else {
                            // Deslizamiento hacia arriba detectado
                            //Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
                            RenderFigurasAntiguo.anguloSigno = -1;
                            RenderFigurasAntiguo.rx = 1f;
                            RenderFigurasAntiguo.ry = 0f;
                            RenderFigurasAntiguo.rz = 0f;

                            RenderFigurasAntiguo.ejex =0;
                            RenderFigurasAntiguo.ejey =1;
                            RenderFigurasAntiguo.ejez =1;
                        }
                    }
                }
                break;
        }
        return true;
    }

    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrabajoFiguras.this, OpenGL10Activity.class );
        startActivity(intent);
        finish();
    }



}