package ec.edu.uce.pa.geometrias;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.ObjFileReader;

public class ObjetoBlender {
    private FloatBuffer bufferVertices;
    //private ByteBuffer bufferIndices;
    private ShortBuffer bufferIndices;
    private final static int byteFlotante =4;
    private final static int compPorVertice=3;

    private float [] colorPorTriangulo;

    float[] vertices;
    short[] indices;
    public ObjetoBlender(String archivoOBJ,float[] colorPorTriangulo, Context context) {
        this.colorPorTriangulo = colorPorTriangulo;

            ObjFileReader cubo = new ObjFileReader(archivoOBJ, context);
            this.vertices = cubo.getVertices();
            this.indices = cubo.getIndices();


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        bufferIndices = ShortBuffer.allocate(indices.length);
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        int paso =0;//cambiar indices a dibujar, cada 3 valores int
        int possColor=0;//cambiar de color cada 4 valores float

        if(colorPorTriangulo.length >4){//si se envia MAS DE UN COLOR float[]{}

            for (int i = 0; i < (indices.length)/3; i++) {//Por triangulo asigna el color { a, b, c, d }:
                gl.glColor4f(colorPorTriangulo[possColor], colorPorTriangulo[possColor+1], colorPorTriangulo[possColor+2],colorPorTriangulo[possColor+3]);
                //gl.glColor4f((float)Math.random(), (float)Math.random(),(float)Math.random(),1);
                bufferIndices.position(paso);
                //gl.glDrawElements(gl.GL_TRIANGLES,3,gl.GL_UNSIGNED_BYTE,bufferIndices);
                gl.glDrawElements(gl.GL_TRIANGLES, 3, gl.GL_UNSIGNED_SHORT, bufferIndices);
                possColor +=4;
                paso +=3;
            }

        }else{//si se envia UN SOLO COLOR float[]{}
            gl.glColor4f(colorPorTriangulo[possColor], colorPorTriangulo[possColor+1], colorPorTriangulo[possColor+2],colorPorTriangulo[possColor+3]);
            bufferIndices.position(0);
            //gl.glDrawElements(gl.GL_TRIANGLES,(indices.length),gl.GL_UNSIGNED_BYTE,bufferIndices);
            gl.glDrawElements(gl.GL_TRIANGLES,(indices.length),gl.GL_UNSIGNED_SHORT,bufferIndices);

        }

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}