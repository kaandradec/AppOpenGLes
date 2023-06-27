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
    public static float[] blancoYnegro(int caras){
        ArrayList<Float> tmp = new ArrayList<>();


        if(caras%2 !=0) tmp.add(1f);tmp.add(1f);tmp.add(1f);tmp.add(1f);//para caras impares agrego un color mas

        for (int i = 0; i < caras/2; i++) {
            tmp.add(0f);tmp.add(0f);tmp.add(0f);tmp.add(0f);//NEGRO
            tmp.add(1f);tmp.add(1f);tmp.add(1f);tmp.add(1f);//BLANCO
        }





        float[] colores = new float[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            colores[i] = tmp.get(i);
        }

        return colores;
    }


    public static float[] seisColores(){

        return new float[]{
                1,0,0,1,
                0,1,0,1,
                0,0,1,1,
                1,1,0,1,
                1,0,1,1,
                0,1,1,1,
        };
    }

}
