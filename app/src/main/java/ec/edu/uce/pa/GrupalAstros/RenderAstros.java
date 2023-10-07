package ec.edu.uce.pa.GrupalAstros;

import static android.opengl.GLES10.GL_LIGHT0;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderAstros implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private Context context;

    private final static int LUZ0 = GL_LIGHT0;

    private Planetas [] planetas = new Planetas[22];
    private EstrellasFondo estrellas, estrellas2;
    private AnillosPluton anillosUrano, anillosSturno;

    float[] posicionLuz0= {0.0f, -10.0f,-4f,1.0f};

    public RenderAstros(Context context){
        this.context = context;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)  {
        gl.glClearColor(0f,0f,0f,1.0f);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glEnable(gl.GL_DEPTH_TEST);

        estrellas = new EstrellasFondo(1100, 6250);
        estrellas2 = new EstrellasFondo(1100, 25000);
        anillosUrano = new AnillosPluton(50, 1.36f, 1.5f);
        anillosSturno = new AnillosPluton(50,1.15f,1.8f);
        planetas[0] = new Planetas(50,50,1f,1f);

        //CLONAR INSTACIAS (22 planetas)
        try {
            for (int i = 0; i < planetas.length; i++) {
                planetas[i] = planetas[0].clone();
            }
        }catch (Exception e){
            e.printStackTrace();
        }//16 vs  3 milisegundos

        //HABILITAR TEXTURAS DE 22 PLANETAS
        for (int i = 0; i < planetas.length; i++) {
            planetas[i].habilitarTexturas(gl,1);
        }

        //Habilitar textura para cada planeta:
        planetas[0].cargarImagenesTextura(gl, this.context, R.drawable.ceres, 0);
        planetas[1].cargarImagenesTextura(gl, this.context, R.drawable.makemake, 0);
        planetas[2].cargarImagenesTextura(gl, this.context, R.drawable.pluto, 0);
        planetas[3].cargarImagenesTextura(gl, this.context, R.drawable.europa, 0);
        planetas[4].cargarImagenesTextura(gl, this.context, R.drawable.luna, 0);
        planetas[5].cargarImagenesTextura(gl, this.context, R.drawable.callisto, 0);
        planetas[6].cargarImagenesTextura(gl, this.context, R.drawable.mercurio, 0);
        planetas[7].cargarImagenesTextura(gl, this.context, R.drawable.titan, 0);
        planetas[8].cargarImagenesTextura(gl, this.context, R.drawable.ganymede, 0);
        planetas[9].cargarImagenesTextura(gl, this.context, R.drawable.marte, 0);
        planetas[10].cargarImagenesTextura(gl, this.context, R.drawable.venus, 0);
        planetas[11].cargarImagenesTextura(gl, this.context, R.drawable.tierra, 0);
        planetas[12].cargarImagenesTextura(gl, this.context, R.drawable.kepler22b, 0);
        planetas[13].cargarImagenesTextura(gl, this.context, R.drawable.neptuno, 0);
        planetas[14].cargarImagenesTextura(gl, this.context, R.drawable.urano, 0);
        planetas[15].cargarImagenesTextura(gl, this.context, R.drawable.saturno, 0);
        planetas[16].cargarImagenesTextura(gl, this.context, R.drawable.jupiter, 0);
        planetas[17].cargarImagenesTextura(gl, this.context, R.drawable.sol, 0);
        planetas[18].cargarImagenesTextura(gl, this.context, R.drawable.siriusa, 0);
        planetas[19].cargarImagenesTextura(gl, this.context, R.drawable.elnath, 0);
        planetas[20].cargarImagenesTextura(gl, this.context, R.drawable.pollux, 0);
        planetas[21].cargarImagenesTextura(gl, this.context, R.drawable.arcturus, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1.0f, 1.0f, bottom, top, 1.0f, 9000.0f);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }

    private float cameraX = 0.0f; // Posición inicial camara eje x
    private float cameraZ = 0.0f; // Posición inicial camara eje z
    private float velocidadEnX = 0.01f; // Velocidad de camara en X
    private float velocidadEnZ = 0.015f; // Velocidad de camara en Z

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glLightfv(LUZ0, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0, gl.GL_SPECULAR, Funciones.generarBuffer(LuzColor.luzAmarilla));

        vIncremento+=0.3f;

        //-----------------------MOVIMIENTO DE CAMARA-----------------------------
        cameraX += velocidadEnX ; //Camara eje x
        cameraZ += velocidadEnZ; //Camara eje z
        GLU.gluLookAt(gl,
                cameraX , 0, (cameraZ),
                cameraX,0 ,-1,
                0,1,0
        );
        //Velocidad de la camara eje X y Z
        if(cameraX>6){velocidadEnX=0.018f;velocidadEnZ=0.015f;}
        if(cameraX>20){velocidadEnX=0.03f;velocidadEnZ=0.015f;}
        if(cameraX>48){velocidadEnX=0.05f;velocidadEnZ=0.005f;}
        if(cameraX>107){velocidadEnX=0.1f; velocidadEnZ=0.05f;}
        if(cameraX>187){velocidadEnX=0.1f;velocidadEnZ=0f; }
        if(cameraX>257){velocidadEnX=0.4f;velocidadEnZ=0.5f;}
        if(cameraX>637){velocidadEnX=0.6f;velocidadEnZ=0f;}
        if(cameraX>950){velocidadEnX=1f;velocidadEnZ=0.8f;}
        if(cameraX>1837){velocidadEnX=1.5f;velocidadEnZ=0f;}
        if(cameraX>3037){velocidadEnX=5f;velocidadEnZ=9f;}
        if(cameraX>9037){velocidadEnX=15;velocidadEnZ=1.2f;}
        if(cameraX>20000){velocidadEnX=1.5f;velocidadEnZ=0.8f;}
        if(cameraX>21200){velocidadEnX=3.5f;velocidadEnZ=2f;}
        if(cameraX>23200){velocidadEnX=4f;velocidadEnZ=3.5f;}
        if(cameraX>28200){velocidadEnX=0;velocidadEnZ=0;}
        //-----------------------CAMARA FINALIZA-----------------------------

        //ESTRELLAS:--------------------------------------------------------------------------------
        gl.glPushMatrix();{
            gl.glPushMatrix();{
                gl.glTranslatef(0, 0, -600);
                gl.glTranslatef(cameraX, 0, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialEstrellas, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION,LuzColor.luzEstrellas , 0);
                if(cameraX<3800){
                    estrellas.dibujar(gl);
                }
            }gl.glPopMatrix();

            //Estrellas intermedias:
            gl.glPushMatrix();{
                if(cameraX>3800){//Cuando llegue a un punto x empieza a moverse
                    gl.glTranslatef(cameraX, 0, 0);
                }
                gl.glTranslatef(1300, 0, 4000);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialEstrellas, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION,LuzColor.luzEstrellas , 0);
                estrellas2.dibujar(gl);
            }gl.glPopMatrix();

            //Estrellas referentes en la posicion del sol:
            gl.glPushMatrix();{
                if(cameraX>6000){//Cuando llegue a un punto x empieza a moverse
                    gl.glTranslatef(cameraX, 0, 0);
                }
                gl.glTranslatef(1500, 0, 10500);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialEstrellas, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION,LuzColor.luzEstrellas , 0);
                estrellas2.dibujar(gl);
            }gl.glPopMatrix();
        }gl.glPopMatrix();//------------------------------------------------------------------------

        //Todos los planetas + una traslacion (Escena):---------------------------------------------
        gl.glPushMatrix();{
            gl.glTranslatef(0f, 0, -8);

            //CERES:
            gl.glPushMatrix();
            {
                gl.glTranslatef(0.0f, 0.0f, -1f);
                gl.glScalef(1.0f, 1.0f, 1.0f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialCere, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION,LuzColor.luzCeres , 0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[0].dibujar(gl,0);
            }
            gl.glPopMatrix();

            //MAKE MAKE
            gl.glPushMatrix();
            {
                gl.glTranslatef(4.0f, 0.0f, -1.5f);
                gl.glScalef(1.5f, 1.5f, 1.5f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialMakeMake, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzMakeMake,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[1].dibujar(gl,0);

            }gl.glPopMatrix();

            //PLUTO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(10.0f, 0.0f, -3f);
                gl.glScalef(3.0f, 3.0f, 3.0f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialPluto, 0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzPluto,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[2].dibujar(gl,0);
            }gl.glPopMatrix();


            //EUROPA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(20.0f,0.0f,-3.9f);
                gl.glScalef(3.9f,3.9f,3.9f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialEuropa,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzEuropa,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[3].dibujar(gl,0);
            }gl.glPopMatrix();

            //LUNA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(32.0f,0.0f,-4.68f);
                gl.glScalef(4.68f,4.68f,4.68f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialLuna,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzLuna,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[4].dibujar(gl,0);
            }gl.glPopMatrix();

            //CALLISTO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(48.0f,0.0f,-7.95f);
                gl.glScalef(7.95f,7.95f,7.95f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialCallisto,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzCallisto,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[5].dibujar(gl,0);
            }gl.glPopMatrix();

            //MERCURIO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(66.0f,0.0f,-7.95f);
                gl.glScalef(7.95f,7.95f,7.95f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialMercurio,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzMercurio,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[6].dibujar(gl,0);
            }gl.glPopMatrix();

            //TITAN:
            gl.glPushMatrix();
            {
                gl.glTranslatef(85.0f,0.0f,-8.75f);
                gl.glScalef(8.75f,8.75f,8.75f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialTitan,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzTitan,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[7].dibujar(gl,0);
            } gl.glPopMatrix();

            //GANYMEDE:
            gl.glPushMatrix();
            {
                gl.glTranslatef(107.0f,0.0f,-9.62f);
                gl.glScalef(9.62f,9.62f,9.62f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialGanymede,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzGanymede,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[8].dibujar(gl,0);
            }gl.glPopMatrix();

            //MARTE:
            gl.glPushMatrix();
            {
                gl.glTranslatef(137.0f,0.0f,-14.44f);
                gl.glScalef(14.44f,14.44f,14.44f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialMarte,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzMarte,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[9].dibujar(gl,0);
            }gl.glPopMatrix();

            //VENUS:
            gl.glPushMatrix();
            {
                gl.glTranslatef(187.0f,0.0f,-28.88f);
                gl.glScalef(28.88f,28.88f,28.88f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialVenus,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzVenus,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[10].dibujar(gl,0);
            } gl.glPopMatrix();

            //TIERRA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(257.0f,0.0f,-31.76f);
                gl.glScalef(31.76f,31.76f,31.76f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialTierra,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzTierra,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[11].dibujar(gl,0);
            }gl.glPopMatrix();

            //KEPLER-22B:
            gl.glPushMatrix();
            {
                gl.glTranslatef(387.0f,0.0f,-76.24f);
                gl.glScalef(76.24f,76.24f,76.24f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialKepler22b,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzKepler22b,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[12].dibujar(gl,0);
            }gl.glPopMatrix();

            //NEPTUNO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(637.0f,0.0f,-137.23f);
                gl.glScalef(137.23f,137.23f,137.23f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialNeptuno,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzNeptuno,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[13].dibujar(gl,0);
            }gl.glPopMatrix();

            //URANO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(937.0f+80f,0.0f,-137.23f);
                gl.glScalef(137.23f,137.23f,137.23f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialUrano,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzUrano,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[14].dibujar(gl,0);
                gl.glPushMatrix();
                {
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialAnillosUrano, 0);
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, LuzColor.luzAnillosUrano, 0);
                    gl.glRotatef(-120, 0,1,0);
                    gl.glRotatef(-6, 0,0,1);
                    anillosUrano.dibujar(gl);
                }gl.glPopMatrix();
            }gl.glPopMatrix();

            //SATURNO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(1837.0f,0.0f,-343.09f);
                gl.glScalef(343.09f,343.09f,343.09f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialSaturno,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzSaturno,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[15].dibujar(gl,0);
                gl.glPushMatrix();
                {
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, LuzColor.materialAnillosSaturno, 0);
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, LuzColor.luzAnillosSaturno, 0);
                    gl.glRotatef(-10,0,0,1);
                    gl.glRotatef(-80,1,0,0);
                    anillosSturno.dibujar(gl);
                }gl.glPopMatrix();
            }gl.glPopMatrix();

            //JUPITER:
            gl.glPushMatrix();
            {
                gl.glTranslatef(3037.0f,0.0f,-446.02f);
                gl.glScalef(446.02f,446.02f,446.02f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialJupiter,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzJupiter,0);
                gl.glRotatef(vIncremento,0,1,0);
                planetas[16].dibujar(gl,0);
            }gl.glPopMatrix();

            //SOL:
            gl.glPushMatrix();
            {
                if(cameraX>25000) {gl.glTranslatef(0,0,-10000);}
                gl.glTranslatef(9037.0f,0.0f,+0f);
                gl.glScalef(4460.27f,4460.27f,4460.27f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialSol,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzSol,0);
                gl.glRotatef(vIncremento/2,0,1,0);
                planetas[17].dibujar(gl,0);
            } gl.glPopMatrix();

            //SIRIUS A:
            gl.glPushMatrix();
            {
                gl.glTranslatef(20000,0.0f,12500);
                gl.glScalef(400f/2,400f/2,400f/2);//NUEVA ESCALA APLICADA
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialSiriusA,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzSiriusA,0);
                gl.glRotatef(vIncremento/3,0,1,0);
                planetas[18].dibujar(gl,0);
            } gl.glPopMatrix();

            //ELNATH:
            gl.glPushMatrix();
            {
                gl.glTranslatef(21200, 0 , 12000);
                gl.glScalef(1000/2,1000/2,1000/2 );
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialElnathAzul,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzElnathAzul,0);
                gl.glRotatef(vIncremento/4,0,1,0);
                planetas[19].dibujar(gl,0);
            }gl.glPopMatrix();

            //POLLUX:
            gl.glPushMatrix();
            {
                gl.glTranslatef(23200, 0 , 12000);
                gl.glScalef(2000/2,2000/2,2000/2 );
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialPollux,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzPollux,0);
                gl.glRotatef(vIncremento/5,0,1,0);
                planetas[20].dibujar(gl,0);
            }gl.glPopMatrix();

            //ARCTURUS:
            gl.glPushMatrix();
            {
                gl.glTranslatef(28200, 0 , 12000);
                gl.glScalef(5000/2,5000/2,5000/2 );
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,LuzColor.materialArcturus,0);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,LuzColor.luzArcturus,0);
                gl.glRotatef(vIncremento/6,0,1,0);
                planetas[21].dibujar(gl,0);
            }gl.glPopMatrix();
        }gl.glPopMatrix();//------------------------------------------------------------------------
    }
}