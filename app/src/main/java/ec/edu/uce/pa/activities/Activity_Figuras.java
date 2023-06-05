package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.renderers.RenderCarro;
import ec.edu.uce.pa.renderers.RenderColores;
import ec.edu.uce.pa.renderers.RenderLinea;
import ec.edu.uce.pa.renderers.RenderPunto;

public class Activity_Figuras extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;


    public static int numPrimitivas = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_figuras);

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

                if(optionSel>0){

                    if(optionSel ==  R.id.rbPantalla){
                        renderer = new RenderColores();
                    }
                    if(optionSel ==  R.id.rbPuntos){
                        renderer = new RenderPunto();
                    }
                    if(optionSel ==  R.id.rbLineas){
                        renderer = new RenderLinea();
                    }
                    if(optionSel ==  R.id.rbCarro){
                        renderer = new RenderCarro();
                    }
                    if(optionSel ==  R.id.rbTriangulos){
                        renderer = new RenderColores();
                    }
                    if(optionSel ==  R.id.rbPoligonos){
                        renderer = new RenderColores();
                    }

                    view.setRenderer(renderer);
                    setContentView(view);

                }else {
                    Toast.makeText(Activity_Figuras.this, "Seleccione una figura", Toast.LENGTH_SHORT).show();
                }

                //input numero de primitivas a dibujar -> int
                try{
                    numPrimitivas = Integer.parseInt(inputNumPrimitivas.getText().toString());
                }catch (NumberFormatException e){
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

        RadioButton rbPantalla = findViewById(R.id.rbPantalla);
        rbPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });


        RadioButton rbPunto = findViewById(R.id.rbPuntos);
        rbPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputNumPrimitivas.setHint("Cantidad a dibujar");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.VISIBLE);
            }
        });

        RadioButton rbLinea = findViewById(R.id.rbLineas);
        rbLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputNumPrimitivas.setHint("Cantidad a dibujar");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.VISIBLE);
            }
        });


        RadioButton rbCarro = findViewById(R.id.rbCarro);
        rbCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputNumPrimitivas.setHint("Puntos para cada rueda");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.VISIBLE);
            }
        });





    }
    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Figuras.this, EjerciciosActivity.class );
        startActivity(intent);
        finish();

    }
}
