package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import ec.edu.uce.pa.GrupalAstros.RenderAstrosMaterial;
import ec.edu.uce.pa.R;
import ec.edu.uce.pa.grupalAstros20.RenderSistemaSolar;
import ec.edu.uce.pa.renderers.RenderCarro;
import ec.edu.uce.pa.renderers.RenderCirculo;
import ec.edu.uce.pa.renderers.RenderColores;
import ec.edu.uce.pa.renderers.RenderCuadradoBlend;
import ec.edu.uce.pa.renderers.RenderCuadradoMipMap;
import ec.edu.uce.pa.renderers.RenderCuboLookAtCamera;
import ec.edu.uce.pa.renderers.RenderCuboNeblina;
import ec.edu.uce.pa.renderers.RenderCuboRubik;
import ec.edu.uce.pa.renderers.RenderDepthTest;
import ec.edu.uce.pa.renderers.RenderEsfera;
import ec.edu.uce.pa.renderers.RenderFiguras;
import ec.edu.uce.pa.renderers.RenderGrupalCamarasAntiguo;
import ec.edu.uce.pa.renderers.RenderLuzLampara;
import ec.edu.uce.pa.renderers.RenderObjModel;
import ec.edu.uce.pa.renderers.RenderPiramideTextura;
import ec.edu.uce.pa.renderers.RenderPlanoMaterial;
import ec.edu.uce.pa.renderers.RenderPunto;
import ec.edu.uce.pa.renderers.RenderPushPop;
import ec.edu.uce.pa.renderers.RenderSpotLight;
import ec.edu.uce.pa.renderers.RenderTierraLuces;

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


                if (optionSel > 0) {
                    if (optionSel == R.id.rbCuboMovTeclado) {
                        Intent intent = new Intent(view.getContext(), TrabajoFiguras.class);
                        startActivity(intent);
                        finish();
                        return;
                    }

                    HashMap<Integer, GLSurfaceView.Renderer> map = new HashMap<>();

                    map.put(R.id.rbColorFijo, new RenderColores());
                    map.put(R.id.rbPuntos, new RenderPunto());
                    map.put(R.id.rbCasa, new RenderCarro());
                    map.put(R.id.rbCirculo, new RenderCirculo());
                    map.put(R.id.rbCarro, new RenderCarro());
                    map.put(R.id.rbPushPop, new RenderPushPop());
                    map.put(R.id.rbCubo, new RenderCuboLookAtCamera());
                    map.put(R.id.rbCuboRubik, new RenderCuboRubik());
                    map.put(R.id.rbDeptTest, new RenderDepthTest());
                    map.put(R.id.rbEsfera, new RenderEsfera());
                    map.put(R.id.rbPlanosIluminacion, new RenderPlanoMaterial());
                    map.put(R.id.rbFiguras3d, new RenderFiguras());
                    map.put(R.id.rbObjModel, new RenderObjModel(getApplicationContext()));
                    map.put(R.id.rbSpotLightAnimada, new RenderTierraLuces(getApplicationContext()));
                    map.put(R.id.rbSpotLightAnimada, new RenderSpotLight());
                    map.put(R.id.rbUniversoEscalaMateriales, new RenderAstrosMaterial());
                    map.put(R.id.rbUniversoEscalaTexturas, new RenderSistemaSolar(getApplicationContext()));
                    map.put(R.id.rbBlending, new RenderCuadradoBlend(getApplicationContext()));
                    map.put(R.id.rbPiramideTextura, new RenderPiramideTextura(getApplicationContext()));
                    map.put(R.id.rbMipMap, new RenderCuadradoMipMap(getApplicationContext()));
                    map.put(R.id.rbNeblina, new RenderCuboNeblina());
                    map.put(R.id.rbLinternaPlano, new RenderLuzLampara(getApplicationContext()));


                    view.setRenderer(map.get(optionSel));
                    setContentView(view);

                } else {
                    Toast.makeText(OpenGL10Activity.this, "Seleccione una figura", Toast.LENGTH_SHORT).show();
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
        btnSalir.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MenuActivity.class);
            startActivity(intent);
            finish();

        });

        RadioButton rbPunto = findViewById(R.id.rbPuntos);
        rbPunto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputNumPrimitivas.setHint("Puntos a dibujar| Max:12");
                    findViewById(R.id.inputNumPrimitivas).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.inputNumPrimitivas).setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    //Metodo para detectar las teclas y manejar los movimientos de camara,mundo:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Tecla derecha:
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            //Toast.makeText(this, "[Girando derecha]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;

            RenderGrupalCamarasAntiguo.ejex = 1;
            RenderGrupalCamarasAntiguo.ejey = 0;
            RenderGrupalCamarasAntiguo.ejez = 1;
            return true;
        }
        //Tecla izquierda:
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            //Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 0f;
            RenderFiguras.ry = 1f;
            RenderFiguras.rz = 0f;

            RenderGrupalCamarasAntiguo.ejex = 1;
            RenderGrupalCamarasAntiguo.ejey = 0;
            RenderGrupalCamarasAntiguo.ejez = 1;
            return true;
        }
        //Tecla abajo:
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            //Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = 1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;

            RenderGrupalCamarasAntiguo.ejex = 0;
            RenderGrupalCamarasAntiguo.ejey = 1;
            RenderGrupalCamarasAntiguo.ejez = 1;
            return true;
        }
        //Tecla arriba:
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            //Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
            RenderFiguras.anguloSigno = -1;
            RenderFiguras.rx = 1f;
            RenderFiguras.ry = 0f;
            RenderFiguras.rz = 0f;

            RenderGrupalCamarasAntiguo.ejex = 0;
            RenderGrupalCamarasAntiguo.ejey = 1;
            RenderGrupalCamarasAntiguo.ejez = 1;
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
                            RenderFiguras.anguloSigno = 1;
                            RenderFiguras.rx = 0f;
                            RenderFiguras.ry = 1f;
                            RenderFiguras.rz = 0f;

                        } else {
                            // Deslizamiento hacia la izquierda detectado
                            //Toast.makeText(this, "[Girando izquierda]", Toast.LENGTH_SHORT).show();
                            RenderFiguras.anguloSigno = -1;
                            RenderFiguras.rx = 0f;
                            RenderFiguras.ry = 1f;
                            RenderFiguras.rz = 0f;

                        }
                    }
                } else {
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        if (deltaY > 0) {
                            // Deslizamiento hacia abajo detectado
                            //Toast.makeText(this, "[Girando abajo]", Toast.LENGTH_SHORT).show();
                            RenderFiguras.anguloSigno = 1;
                            RenderFiguras.rx = 1f;
                            RenderFiguras.ry = 0f;
                            RenderFiguras.rz = 0f;

                        } else {
                            // Deslizamiento hacia arriba detectado
                            //Toast.makeText(this, "[Girando arriba]", Toast.LENGTH_SHORT).show();
                            RenderFiguras.anguloSigno = -1;
                            RenderFiguras.rx = 1f;
                            RenderFiguras.ry = 0f;
                            RenderFiguras.rz = 0f;

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
        Intent intent = new Intent(OpenGL10Activity.this, OpenGL10Activity.class);
        startActivity(intent);
        finish();
    }
}
