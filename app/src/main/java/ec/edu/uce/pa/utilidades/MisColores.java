package ec.edu.uce.pa.utilidades;

import java.util.ArrayList;

public class MisColores {
    //private float[] colores;

    public static float[] random(int caras){
        ArrayList<Float> tmp = new ArrayList<>();
        for (int i = 0; i < caras; i++) {
            tmp.add((float)Math.random());tmp.add((float)Math.random());tmp.add((float)Math.random());tmp.add((float)Math.random());
        }
        float[] colores = new float[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            colores[i] = tmp.get(i);
        }

        return colores;
    }
    public static float[] blancoYnegroSeis(){
        float[] colores={
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
                (float)Math.random(),(float)Math.random(),(float)Math.random(),1f,
        };
        return colores;
    }

}
