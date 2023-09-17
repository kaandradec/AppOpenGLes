attribute vec4 posVertexShader;
attribute vec2 texturaVertex;
varying vec2 fragTexturavertex;
uniform mat4 matrizProyeccion;

void main() {
    //gl_Position = vec4(posVertexShader,0.0,1.0); //La componente w  se encarga de la proyeccion

    gl_Position = matrizProyeccion * posVertexShader;
    fragTexturavertex = texturaVertex;
}
