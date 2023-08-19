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
import ec.edu.uce.pa.renders.RenderFiguras;

public class Trabajo_Figuras extends AppCompatActivity {
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


                RenderFiguras.giraMundo = true;//util para saber con que 'modo debe girar'

                Toast toast = Toast.makeText(Trabajo_Figuras.this , "MOVIENDO EL MUNDO", Toast.LENGTH_SHORT);
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

                RenderFiguras.giraMundo = false;//util para saber con que 'modo debe girar'

                Toast toast = Toast.makeText(Trabajo_Figuras.this , "MOVIENDO LA CAMARA", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

            }
        });


    }

    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Trabajo_Figuras.this, Trabajo_Figuras.class );
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {


            Toast.makeText(this, "DERECHA ", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;
            return true; // consume el evento
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            Toast.makeText(this, "IZQUIERDA ", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;
            return true; // consume el evento
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            Toast.makeText(this, "ABAJO ", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;
            return true; // consume el evento
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            Toast.makeText(this, "ARRIBA ", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;
            return true; // consume el evento
        }

        return super.onKeyDown(keyCode, event);
    }

}