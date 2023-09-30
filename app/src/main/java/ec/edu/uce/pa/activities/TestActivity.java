package ec.edu.uce.pa.activities;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.GrupalAstros.RenderSistemaSolarMaterial;
import ec.edu.uce.pa.renderers.RenderFiguras;
public class TestActivity extends AppCompatActivity {
    private GLSurfaceView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);//OpenGL version
        //Seleccionar clase render
        view.setRenderer(new RenderSistemaSolarMaterial());//Render

        setContentView(view);
    }
}
