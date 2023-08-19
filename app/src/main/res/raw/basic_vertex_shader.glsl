attribute vec2 posVertexShader;
void main(){
gl_Position = vec4(posVertexShader,0.0,1.0);
    gl_PointSize = 80.0;
}