attribute vec4 posVertexShader;
attribute vec3 colorVertex;
varying vec4 fragColorvertex;

uniform mat4 matrizProyeccion;

void main() {
    gl_Position = matrizProyeccion*posVertexShader; // multipliecamos por la matriz de proyeccion
    //gl_PointSize = 80.0;
    fragColorvertex = vec4(colorVertex,1.0);
}


