package ec.edu.uce.pa.grupalAstros20;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.FloatBuffer;

import ec.edu.uce.pa.utilidades.Funciones20;

public class EstrellasFondo {

    private FloatBuffer bufferVertices;

    private static final int compPorVertice = 2;


    int numEstrellas; // Número de estrellas
    float[] vertices;
    float[] colores;

    public EstrellasFondo(int numEstrellas){
        this.numEstrellas = numEstrellas;

        vertices = new float[numEstrellas * 2];
        colores = new float[numEstrellas * 4];

        for (int i = 0; i < numEstrellas; i++) {
            float x = (float) Math.random() * 2.0f - 1.0f;
            float y = (float) Math.random() * 2.0f - 1.0f;
            vertices[i * 2] = x;
            vertices[i * 2 + 1] = y;

            colores[i * 4] = 1.0f;
            colores[i * 4 + 1] = 1.0f;
            colores[i * 4 + 2] = 1.0f;
            colores[i * 4 + 3] = 1.0f;
        }
        bufferVertices = Funciones20.generarFloatBuffer(vertices);
    }

    public void dibujar(GLES20 gl){

        //Configuracion Vertex Shader.
        int [] infoCompiler= new int[1];
        String source;
        StringBuilder sb = new StringBuilder();
        //1) Crear el shader:
        int vertexShader = gl.glCreateShader(GL_VERTEX_SHADER);

        if (vertexShader ==0){
            Log.w("Vertex Shader", "Error al crear vertex shader");
            return;
        }
        //2) Crear source del Vertex shader:
        sb.append("attribute vec4 posVertexShader;");
        sb.append("\n");
        sb.append("void main() {");
        sb.append("\n");
        sb.append("gl_Position = posVertexShader;");
        sb.append("\n");
        sb.append("gl_PointSize = 4.0;");
        sb.append("\n");
        sb.append("}");
        sb.append("\n");

        //3) Compilar el vertexShader
        source=String.valueOf(sb);
        gl.glShaderSource(vertexShader,source);
        GLES20.glCompileShader(vertexShader);
        GLES20.glGetShaderiv(vertexShader, gl.GL_COMPILE_STATUS, infoCompiler,0);
        if (infoCompiler[0] == 0){
            gl.glDeleteShader(vertexShader);
            Log.w("Vertex Shader", "Error al compilar el vertexShader");
            return;
        }
        //4) Configuracion  Fragment Shader

        source = null;
        sb = new StringBuilder();
        int fragmentShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

        if (fragmentShader == 0){
            Log.w("Fragmen Shader", "Error al crear fragmen shader"); // mandamos a consola el error
            return;
        }

        //5) Crear source del fragment shader
        sb.append("precision mediump float;");
        sb.append("\n");
        sb.append("uniform vec4 posColor;");
        sb.append("\n");
        sb.append("void main() {");
        sb.append("\n");
        sb.append("gl_FragColor = posColor;");
        sb.append("\n");
        sb.append("}");
        sb.append("\n");
        source=String.valueOf(sb);

        //6) Compilar el FragmenShader
        gl.glShaderSource(fragmentShader,source);
        GLES20.glCompileShader(fragmentShader);
        GLES20.glGetShaderiv(fragmentShader, gl.GL_COMPILE_STATUS, infoCompiler,0);
        if (infoCompiler[0] == 0){
            gl.glDeleteShader(fragmentShader);
            Log.w("Fragment Shader", "Error al compilar ek fragmentShader");
            return;
        }

        //7) Crear el Programa
        int [] infoLink = new int[1];
        int programa = gl.glCreateProgram();
        if (programa == 0 ) {
            Log.w("Programa", "Error al crear el programa ");
            return;
        }
        //8) Definir el Attach shaders para el programa
        gl.glAttachShader(programa,vertexShader);
        gl.glAttachShader(programa,fragmentShader);

        //9) Vincular programa
        gl.glLinkProgram(programa);
        gl.glGetProgramiv(programa, gl.GL_LINK_STATUS, infoLink,0);

        if (infoLink[0] == 0){
            gl.glDeleteShader(programa);
            Log.w("Programa", "Error al vincular programa"); // mandamos a consola el error
            return;
        }
        //10) usar programa para la renderización
        gl.glUseProgram(programa);

        //Lectura de parametros desde el renderer vertex shader:
        int idVertexShader = gl.glGetAttribLocation(programa,"posVertexShader");
        gl.glVertexAttribPointer(idVertexShader,compPorVertice, gl.GL_FLOAT,false,0,bufferVertices);
        gl.glEnableVertexAttribArray(idVertexShader);


        gl.glGetUniformLocation(programa, "posColor");
        int idFragmentShader = gl.glGetUniformLocation(programa,"posColor");

        //Definir el color blanco para las estrellas
        gl.glUniform4f(idFragmentShader,1.0f,1.0f,1.0f,1.0f);

        gl.glFrontFace(gl.GL_CW);
        gl.glDrawArrays(gl.GL_POINTS,0,numEstrellas);
        gl.glFrontFace(gl.GL_CCW);

    }

}