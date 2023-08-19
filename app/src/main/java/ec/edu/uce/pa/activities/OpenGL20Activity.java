package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.GrupalAstros.RenderSistemaSolar;
import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renderers.RenderAstroLuces;
import ec.edu.uce.pa.renderers.RenderCarro;
import ec.edu.uce.pa.renderers.RenderCirculo;
import ec.edu.uce.pa.renderers.RenderColores;
import ec.edu.uce.pa.renderers.RenderCuadradoBlend;
import ec.edu.uce.pa.renderers.RenderCuadradoMipMap;
import ec.edu.uce.pa.renderers.RenderCubo;
import ec.edu.uce.pa.renderers.RenderCuboNeblina;
import ec.edu.uce.pa.renderers.RenderDepthTest;
import ec.edu.uce.pa.renderers.RenderEsfera;
import ec.edu.uce.pa.renderers.RenderFigurasAntiguo;
import ec.edu.uce.pa.renderers.RenderLuzLampara;
import ec.edu.uce.pa.renderers.RenderPiramideTextura;
import ec.edu.uce.pa.renderers.RenderPlanoMaterial;
import ec.edu.uce.pa.renderers.RenderPrueba;
import ec.edu.uce.pa.renderers.RenderPunto;
import ec.edu.uce.pa.renderers.RenderPushPop;
import ec.edu.uce.pa.renderers.RenderSpotLight;
import ec.edu.uce.pa.renderers.RenderTriangulo;

public class OpenGL20Activity extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;


    public static int numPrimitivas = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opengl_20);

        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);

        renderer = null;

        //Hacer invisible la input
        findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);


        //input numero de primitivas a dibujar
        EditText inputNumPrimitivas = (EditText) findViewById(R.id.inputNumPrimitivas);

        Button btnDibujar = findViewById(R.id.btnDibujar);
        btnDibujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//SE CAMBIO View view por View a
                renderer = null;

                int optionSel;
                RadioGroup rgOpciones = findViewById(R.id.rgOpciones);
                optionSel = rgOpciones.getCheckedRadioButtonId();

                if (optionSel > 0) {

                    if (optionSel == R.id.rbHexagonoColorFijo) {
                        renderer = new RenderColores();
                    }
                    if (optionSel == R.id.rbHexagonoColorStride) {
                        renderer = new RenderPunto();//REVISAR
                    }
                    if (optionSel == R.id.rbHexagonoProyeccion) {
                        renderer = new RenderTriangulo();//REVISAR
                    }
                    if (optionSel == R.id.rbHexagonoTextura) {
                        renderer = new RenderCirculo();//REVISAR
                    }

                    view.setRenderer(renderer);
                    setContentView(view);

                } else {
                    Toast.makeText(OpenGL20Activity.this, "Seleccione una figura", Toast.LENGTH_SHORT).show();
                }

                //input numero de primitivas a dibujar -> int
                try {
                    numPrimitivas = Integer.parseInt(inputNumPrimitivas.getText().toString());
                } catch (NumberFormatException e) {
                    numPrimitivas = 0;
                }


            }
        });

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EjerciciosActivity.class);
                startActivity(intent);
                finish();

            }
        });

        RadioButton rbPantalla = findViewById(R.id.rbHexagonoColorFijo);
        rbPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });



    }
    //Boton Back de Android
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(OpenGL20Activity.this, OpenGL20Activity.class);
        startActivity(intent);
        finish();
    }
}
