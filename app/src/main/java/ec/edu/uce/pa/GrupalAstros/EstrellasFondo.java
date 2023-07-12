package ec.edu.uce.pa.GrupalAstros;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class EstrellasFondo {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;
    private float rango; // Rango para dispersar las estrellas

    public EstrellasFondo(int numEstrellas, float dispersion) {
        this.rango = dispersion;

        float[] vertices = generarVerticesAleatorios(numEstrellas);
        float[] colores = generarColores(numEstrellas);

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);
    }

    private float[] generarVerticesAleatorios(int numEstrellas) {
        float[] vertices = new float[numEstrellas * 2];
        Random random = new Random();

        for (int i = 0; i < numEstrellas; i++) {
            vertices[i * 2] = generarCoordenadaAleatoria(random);
            vertices[i * 2 + 1] = generarCoordenadaAleatoria(random);
        }

        return vertices;
    }

    private float generarCoordenadaAleatoria(Random random) {
        // Generar una coordenada aleatoria entre -rango/2 y rango/2
        return random.nextFloat() * rango - rango / 2;
    }

    private float[] generarColores(int numEstrellas) {
        float[] colores = new float[numEstrellas * 4];
        // Colocar el color blanco (1.0, 1.0, 1.0, 1.0) en cada componente de color
        for (int i = 0; i < numEstrellas * 4; i += 4) {
            colores[i] = 1.0f;
            colores[i + 1] = 1.0f;
            colores[i + 2] = 1.0f;
            colores[i + 3] = 1.0f;
        }

        return colores;
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(comPorVertices, GL10.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        bufferColores.position(0);
        gl.glColorPointer(comPorColor, GL10.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glPointSize(5);
        gl.glDrawArrays(GL10.GL_POINTS, 0, bufferVertices.capacity() / 2);
        gl.glFrontFace(GL10.GL_CW);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}

