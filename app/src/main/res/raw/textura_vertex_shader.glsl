attribute vec4 posVertexShader;
attribute vec2 texturaVertex;
varying vec2 fragTexturaVertex;

uniform mat4 matrizModel;      // Matriz de transformación del modelo
uniform mat4 matrizView;       // Matriz de vista (cámara)
uniform mat4 matrizProjection; // Matriz de proyección
void main(){
    // Calcula la matriz MVP
    mat4 mvp = matrizProjection * matrizView * matrizModel;

    // Transforma la posición del vértice utilizando la matriz MVP
    gl_Position = mvp * posVertexShader;

    gl_PointSize = 80.0;
    fragTexturaVertex = texturaVertex;
}
