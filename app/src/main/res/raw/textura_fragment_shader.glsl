precision mediump float;
varying vec2 fragTexturavertex;
uniform sampler2D imgTextura;

void main() {
    gl_FragColor = texture2D(imgTextura,fragTexturavertex);

}