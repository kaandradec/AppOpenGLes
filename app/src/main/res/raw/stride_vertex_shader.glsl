attribute vec4 posVertexShader;
attribute vec3 colorVertex;
varying vec4 fragColorvertex;

void main() {
    gl_Position = posVertexShader;
    gl_PointSize = 80.0;
    fragColorvertex = vec4(colorVertex,1.0);
}
