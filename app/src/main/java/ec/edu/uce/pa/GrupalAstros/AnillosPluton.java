package ec.edu.uce.pa.GrupalAstros;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class AnillosPluton {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private ArrayList<Float> listaVertices;
    private final static float PI = 3.141516f;
    private final static int byteFlotante = 4;
    private final static int comporVertice = 2;
    private final static int comPorColor = 4;
    private int numSegmentos;
    private float radioInterno;
    private float radioExterno;

    public AnillosPluton(int numSegmentos, float radioInterno, float radioExterno) {
        this.numSegmentos = numSegmentos;
        this.radioInterno = radioInterno;
        this.radioExterno = radioExterno;

        listaVertices = new ArrayList<>();

        for (int i = 0; i < numSegmentos; i++) {
            float theta = 2.0f * PI * i / numSegmentos;
            float x = (float) Math.cos(theta);
            float y = (float) Math.sin(theta);

            listaVertices.add(radioInterno * x);
            listaVertices.add(radioInterno * y);
            listaVertices.add(radioExterno * x);
            listaVertices.add(radioExterno * y);
        }

        // Agregar el primer vértice nuevamente para cerrar el anillo
        listaVertices.add(listaVertices.get(0));
        listaVertices.add(listaVertices.get(1));
        listaVertices.add(listaVertices.get(2));
        listaVertices.add(listaVertices.get(3));

        float[] vertices = new float[listaVertices.size()];
        for (int i = 0; i < listaVertices.size(); i++) {
            vertices[i] = listaVertices.get(i);
        }

        listaVertices = new ArrayList<>();

        for (int j = 0; j < numSegmentos; j++) {
            listaVertices.add(0.0f);
            listaVertices.add(0.0f);
            listaVertices.add(0.0f);
            listaVertices.add(1.0f);
        }

        float[] colores = new float[listaVertices.size()];
        for (int i = 0; i < listaVertices.size(); i++) {
            colores[i] = listaVertices.get(i);
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(comporVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(comPorColor, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(10);
        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, numSegmentos * 2 + 2); // +2 para cerrar completamente el anillo

        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
