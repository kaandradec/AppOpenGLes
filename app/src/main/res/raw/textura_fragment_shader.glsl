precision mediump float;
varying vec2 fragTexturaVertex;
uniform sampler2D imgTextura;

void main() {
    gl_FragColor = texture2D(imgTextura, fragTexturaVertex);

}