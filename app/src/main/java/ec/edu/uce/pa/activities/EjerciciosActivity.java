package ec.edu.uce.pa.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;

public class EjerciciosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ejercicios);

        Button btnColorPantalla = findViewById(R.id.btnColorPantalla);
        btnColorPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EjerciciosActivity.this, "Presiono boton \"Color Pantalla\"", Toast.LENGTH_SHORT).show();
            }
        });


        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }

    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EjerciciosActivity.this, PrincipalActivity.class );
        startActivity(intent);
        finish();

    }
}
