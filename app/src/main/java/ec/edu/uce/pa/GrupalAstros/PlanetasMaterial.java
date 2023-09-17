package ec.edu.uce.pa.GrupalAstros;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class PlanetasMaterial {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private FloatBuffer bufferNormales;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;

    private int franjas, cortes;

    public PlanetasMaterial(int franjas, int cortes, float radio, float ejePolar){

        this.franjas = franjas;
        this.cortes = cortes;

        float [] vertices ;
        float [] colores;
        float [] normales;

        int iVertice = 0;
        int iColor = 0;
        int iNormal = 0;

        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];
        colores = new float[4 * ((cortes * 2 + 2) * franjas)];
        normales = new float[3 * ((cortes * 2 + 2) * franjas)];
        int i, j;

        for(i = 0; i < franjas; i++)  {
            float phi0 = (float)Math.PI * ((i + 0) * (1.0f/(franjas)) - 0.5f);
            float cosPhi0 = (float)Math.cos(phi0);
            float sinPhi0 = (float)Math.sin(phi0);

            float phi1 = (float)Math.PI * ((i + 1 ) * (1.0f/(franjas)) - 0.5f); //i + 1
            float cosPhi1 = (float)Math.cos(phi1);
            float sinPhi1 = (float)Math.sin(phi1);

            float cosTheta, sinTheta;
            for(j = 0; j < cortes; j++) {
                float theta = (float)(-2.0f * Math.PI * j * (1.0/(cortes -1)));
                cosTheta = (float)Math.cos(theta);
                sinTheta = (float)Math.sin(theta);

                vertices[iVertice+0] = radio * cosPhi0 * cosTheta;
                vertices[iVertice+1] = radio * (sinPhi0 * ejePolar);
                vertices[iVertice+2] = radio * (cosPhi0 * sinTheta);

                vertices[iVertice+3] = radio * cosPhi1 * cosTheta;
                vertices[iVertice+4] = radio * (sinPhi1 * ejePolar);
                vertices[iVertice+5] = radio * (cosPhi1 * sinTheta);
//------------------Normales----------------------------------------
                normales[iVertice+0] = cosPhi0 * cosTheta;
                normales[iVertice+1] = sinPhi0 ;
                normales[iVertice+2] = cosPhi0 * sinTheta;

                normales[iVertice+3] = cosPhi0 * cosTheta;
                normales[iVertice+4] = sinPhi0;
                normales[iVertice+5] = cosPhi0 * sinTheta;
//----------------------------------------------------------------
                colores[iColor+0] = 1.0f;
                colores[iColor+1] = 0.5f;
                colores[iColor+2] = 0.25f;
                colores[iColor+3] = 1.0f;

                colores[iColor+4] = 0.25f;
                colores[iColor+5] = 0.5f;
                colores[iColor+6] = 1.0f;
                colores[iColor+7] = 1.0f;

                iColor += 2*4;
                iVertice += 2*3;
                iNormal+=2*3;
            }

            vertices[iVertice+0] = vertices[iVertice+3];
            vertices[iVertice+3] = vertices[iVertice-3];
            vertices[iVertice+1] = vertices[iVertice+4];
            vertices[iVertice+4] = vertices[iVertice-2];
            vertices[iVertice+2] = vertices[iVertice+5];
            vertices[iVertice+5] = vertices[iVertice-1];
        }
        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);
        bufferNormales = Funciones.generarBuffer(normales);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);


        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, franjas * cortes *2);


        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}