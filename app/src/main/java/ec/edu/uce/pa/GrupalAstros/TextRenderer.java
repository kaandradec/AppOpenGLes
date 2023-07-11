package ec.edu.uce.pa.GrupalAstros;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TextRenderer {
    private int textureId;
    private int textureWidth;
    private int textureHeight;
    private FloatBuffer verticesBuffer;
    private FloatBuffer texCoordsBuffer;

    public TextRenderer(GL10 gl) {
        // Configura la fuente y el tamaño del texto
        int fontSize = 5;
        String fontFamily = "Arial";

        // Crea un objeto Paint para dibujar el texto
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(fontFamily, Typeface.BOLD));

        // Calcula el tamaño de la textura en función del texto
        Rect textBounds = new Rect();
        paint.getTextBounds("Europa", 0, 6, textBounds);
        textureWidth = textBounds.width();
        textureHeight = textBounds.height();

        // Crea un Bitmap para renderizar el texto
        Bitmap bitmap = Bitmap.createBitmap(textureWidth, textureHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText("[ Europa ]", 0, textureHeight, paint);

        // Crea las coordenadas de vértice y de textura
        float[] vertices = {
                0f, 0f, 0f,
                0f, textureHeight, 0f,
                textureWidth, textureHeight, 0f,
                textureWidth, 0f, 0f
        };

        float[] texCoords = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f
        };

        // Crea los buffers de vértices y coordenadas de textura
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        texCoordsBuffer = tbb.asFloatBuffer();
        texCoordsBuffer.put(texCoords);
        texCoordsBuffer.position(0);

        // Genera un ID de textura OpenGL ES
        int[] textureIds = new int[1];
        gl.glGenTextures(1, textureIds, 0);
        textureId = textureIds[0];

        // Configura la textura OpenGL ES a partir del Bitmap
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        // Convierte el Bitmap en un arreglo de píxeles
        int[] pixels = new int[textureWidth * textureHeight];
        bitmap.getPixels(pixels, 0, textureWidth, 0, 0, textureWidth, textureHeight);

        // Convierte el arreglo de píxeles a un arreglo de bytes
        ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        buffer.asIntBuffer().put(pixels);
        buffer.position(0);

        // Carga los datos de la textura
        gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, textureWidth, textureHeight, 0,
                GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, buffer);

        // Libera los recursos del Bitmap
        bitmap.recycle();
    }

    public void draw(GL10 gl) {
        // Activa la textura
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);

        // Configura las coordenadas de vértice y textura
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordsBuffer);

        // Dibuja el texto
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        // Desactiva los estados y la textura
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
