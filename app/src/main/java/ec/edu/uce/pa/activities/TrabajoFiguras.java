package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;

import ec.edu.uce.pa.renderers.RenderFiguras;

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
                renderer = new RenderFiguras();
                view.setRenderer(renderer);
                setContentView(view);

                //Controlador: nos dice si gira o no nuestro mundo.
                RenderFiguras.giraMundo = true;

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
                renderer = new RenderFiguras();
                view.setRenderer(renderer);
                setContentView(view);

                RenderFiguras.giraMundo = false;

                Toast toast = Toast.makeText(TrabajoFiguras.this , "MOVIENDO LA CAMARA", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        });


    }

    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrabajoFiguras.this, TrabajoFiguras.class );
        startActivity(intent);
        finish();

    }
    //Metodo para detectar las teclas y manejar los movimientos de camara,mundo:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Tecla derecha:
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            Toast.makeText(this, "[Girando derecha]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;

            RenderFiguras.ejex =1;
            RenderFiguras.ejey =0;
            RenderFiguras.ejez =1;
            return true;
        }
        //Tecla izquierda:
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;

            RenderFiguras.ejex =1;
            RenderFiguras.ejey =0;
            RenderFiguras.ejez =1;
            return true;
        }
        //Tecla abajo:
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;

            RenderFiguras.ejex =0;
            RenderFiguras.ejey =1;
            RenderFiguras.ejez =1;
            return true;
        }
        //Tecla arriba:
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;

            RenderFiguras.ejex =0;
            RenderFiguras.ejey =1;
            RenderFiguras.ejez =1;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}