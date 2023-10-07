package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.grupalAstros20.RenderSistemaSolar;
import ec.edu.uce.pa.renderers20.RenderHexagonoColor;
import ec.edu.uce.pa.renderers20.RenderHexagonoProyFP;
import ec.edu.uce.pa.renderers20.RenderHexagonoStride;
import ec.edu.uce.pa.renderers20.RenderHexagonoTextura;

public class OpenGL20Activity extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;


    public static int numPrimitivas = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opengl_20);

        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(2);

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

                    HashMap<Integer, GLSurfaceView.Renderer> map = new HashMap<>();

                    map.put(R.id.rbColorFijo, new RenderHexagonoColor(getApplicationContext()));
                    map.put(R.id.rbPuntos, new RenderHexagonoStride(getApplicationContext()));
                    map.put(R.id.rbCasa, new RenderHexagonoProyFP(getApplicationContext()));
                    map.put(R.id.rbCirculo, new RenderHexagonoTextura(getApplicationContext()));
                    map.put(R.id.rbSistemaSolar, new RenderSistemaSolar(getApplicationContext()));

                    view.setRenderer(map.get(optionSel));
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
                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });

        RadioButton rbPantalla = findViewById(R.id.rbColorFijo);
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
