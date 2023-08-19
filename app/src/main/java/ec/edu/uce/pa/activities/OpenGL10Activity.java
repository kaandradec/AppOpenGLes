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

public class OpenGL10Activity extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;


    public static int numPrimitivas = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opengl_10);

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

                    if(optionSel ==  R.id.rbHexagonoColorFijo){
                        renderer = new RenderColores();
                    }
                    if(optionSel ==  R.id.rbHexagonoColorStride){
                        renderer = new RenderPunto();//REVISAR
                    }
                    if(optionSel ==  R.id.rbHexagonoProyeccion){
                        renderer = new RenderTriangulo();//REVISAR
                    }
                    if(optionSel ==  R.id.rbHexagonoTextura){
                        renderer = new RenderCirculo();//REVISAR
                    }
                    if(optionSel ==  R.id.rbCarro){
                        renderer = new RenderCarro();
                    }
                    if(optionSel ==  R.id.rbPushPop){
                        renderer = new RenderPushPop();
                    }
                    if(optionSel ==  R.id.rbCubo){
                        renderer = new RenderCubo();
                    }
                    if(optionSel ==  R.id.rbDeptTest){
                        renderer = new RenderDepthTest();
                    }
                    if(optionSel ==  R.id.rbCuboMovTeclado){
                        //renderer = new RenderFiguras();//FALTAA!!!
                        Intent intent = new Intent(view.getContext(), TrabajoFiguras.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    if(optionSel ==  R.id.rbEsfera){
                        renderer = new RenderEsfera();
                    }
                    if(optionSel ==  R.id.rbPlanosIluminacion){
                        renderer = new RenderPlanoMaterial();//REVISAR
                    }
                    if(optionSel ==  R.id.rbFiguras3d){//12
                        renderer = new RenderPrueba();//FALTA
                    }
                    if(optionSel ==  R.id.rbSpotLightAnimada){//13
                        renderer = new RenderSpotLight();
                    }
                    if(optionSel ==  R.id.rbUniversoEscalaMateriales){//14
                        renderer = new RenderAstroLuces(getApplicationContext());//REVISAR
                    }
                    if(optionSel ==  R.id.rbUniversoEscalaTexturas){//15
                        renderer = new RenderSistemaSolar(getApplicationContext());//REVISAR
                    }
                    if(optionSel ==  R.id.rbBlending){//16
                        renderer = new RenderCuadradoBlend(getApplicationContext());
                    }
                    if(optionSel ==  R.id.rbPiramideTextura){//17
                        renderer = new RenderPiramideTextura(getApplicationContext());
                    }
                    if(optionSel ==  R.id.rbMipMap){//18
                        renderer = new RenderCuadradoMipMap(getApplicationContext());
                    }
                    if(optionSel ==  R.id.rbNeblina){//19
                        renderer = new RenderCuboNeblina();//REVISAR
                    }
                    if(optionSel ==  R.id.rbLinternaPlano){//20
                        renderer = new RenderLuzLampara(getApplicationContext());
                    }



//                    if(optionSel ==  R.id.rbSpotLightAnimada){
//                        renderer = new RenderDepthTest();
//                    }
//                    if(optionSel ==  R.id.rbUniversoEscalaMateriales){
//                        //renderer = new RenderIcosfera();
//                        Intent intent = new Intent(view.getContext(), TrabajoFiguras.class);
//                        startActivity(intent);
//                        finish();
//                        return;
//
//                    }

                    view.setRenderer(renderer);
                    setContentView(view);

                }else {
                    Toast.makeText(OpenGL10Activity.this, "Seleccione una figura", Toast.LENGTH_SHORT).show();
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

        RadioButton rbPantalla = findViewById(R.id.rbHexagonoColorFijo);
        rbPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });


        RadioButton rbPunto = findViewById(R.id.rbHexagonoColorStride);
        rbPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputNumPrimitivas.setHint("Puntos a dibujar| Max:12");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.VISIBLE);
            }
        });

        RadioButton rbCasa = findViewById(R.id.rbHexagonoProyeccion);
        rbCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inputNumPrimitivas.setHint("Cantidad a dibujar");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });


        RadioButton rbCarro = findViewById(R.id.rbHexagonoTextura);
        rbCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inputNumPrimitivas.setHint("Segmentos para el circulo");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });

        RadioButton rbTriangulo = findViewById(R.id.rbCarro);
        rbTriangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inputNumPrimitivas.setHint("Puntos para cada rueda");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });

        RadioButton rbCubo = findViewById(R.id.rbCubo);
        rbCubo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // inputNumPrimitivas.setHint("Puntos para cada rueda");
                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });

        RadioButton rbObjeto = findViewById(R.id.rbUniversoEscalaMateriales);
        rbObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
            }
        });


    }

    //Metodo para detectar las teclas y manejar los movimientos de camara,mundo:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Tecla derecha:
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            //Toast.makeText(this, "[Girando derecha]", Toast.LENGTH_SHORT).show();
            RenderPrueba.anguloSigno = 1;
            RenderPrueba.rx = 0f;
            RenderPrueba.ry = 1f;
            RenderPrueba.rz = 0f;

            RenderFigurasAntiguo.ejex =1;
            RenderFigurasAntiguo.ejey =0;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla izquierda:
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            //Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
            RenderPrueba.anguloSigno = -1;
            RenderPrueba.rx = 0f;
            RenderPrueba.ry = 1f;
            RenderPrueba.rz = 0f;

            RenderFigurasAntiguo.ejex =1;
            RenderFigurasAntiguo.ejey =0;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla abajo:
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            //Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
            RenderPrueba.anguloSigno = 1;
            RenderPrueba.rx = 1f;
            RenderPrueba.ry = 0f;
            RenderPrueba.rz = 0f;

            RenderFigurasAntiguo.ejex =0;
            RenderFigurasAntiguo.ejey =1;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }
        //Tecla arriba:
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            //Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
            RenderPrueba.anguloSigno = -1;
            RenderPrueba.rx = 1f;
            RenderPrueba.ry = 0f;
            RenderPrueba.rz = 0f;

            RenderFigurasAntiguo.ejex =0;
            RenderFigurasAntiguo.ejey =1;
            RenderFigurasAntiguo.ejez =1;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;
    //Metodo para detectar las GESTOS en pantalla y manejar los movimientos de camara,mundo:
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
                            RenderPrueba.anguloSigno = 1;
                            RenderPrueba.rx = 0f;
                            RenderPrueba.ry = 1f;
                            RenderPrueba.rz = 0f;

                        } else {
                            // Deslizamiento hacia la izquierda detectado
                            //Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
                            RenderPrueba.anguloSigno = -1;
                            RenderPrueba.rx = 0f;
                            RenderPrueba.ry = 1f;
                            RenderPrueba.rz = 0f;

                        }
                    }
                } else {
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        if (deltaY > 0) {
                            // Deslizamiento hacia abajo detectado
                            //Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
                            RenderPrueba.anguloSigno = 1;
                            RenderPrueba.rx = 1f;
                            RenderPrueba.ry = 0f;
                            RenderPrueba.rz = 0f;

                        } else {
                            // Deslizamiento hacia arriba detectado
                            //Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
                            RenderPrueba.anguloSigno = -1;
                            RenderPrueba.rx = 1f;
                            RenderPrueba.ry = 0f;
                            RenderPrueba.rz = 0f;

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
        Intent intent = new Intent(OpenGL10Activity.this, OpenGL10Activity.class );
        startActivity(intent);
        finish();
    }
}
