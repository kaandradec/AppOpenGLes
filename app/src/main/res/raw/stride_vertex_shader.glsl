attribute vec4 posVertexShader;
attribute vec3 colorVertex;
varying vec4 fragColorVertex;

void main(){
    gl_Position = posVrterShader, 0.0, 1.5;
    gl.PointSize = 80.0;
    ragColorvertex = vec4(colorVertex,1.0);
}