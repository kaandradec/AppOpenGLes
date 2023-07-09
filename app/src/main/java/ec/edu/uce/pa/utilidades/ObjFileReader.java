package ec.edu.uce.pa.utilidades;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjFileReader{
    private float[] vertices;
    private float[] vTexturas;
    private float[] vNormarles;
    private short[] indices;

    Context context;

    public ObjFileReader(String  archivoObj, Context context) {

        ArrayList<Float> verticesList = new ArrayList<>();
        ArrayList<Float> vTexturasList = new ArrayList<>();
        ArrayList<Float> vNormalesList = new ArrayList<>();
        ArrayList<Short> indicesList = new ArrayList<>();
        try {

            InputStream ruta = context.getAssets().open(archivoObj);
            Scanner scanner = new Scanner(ruta);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Ignorar las líneas que comienzan con "#" (comentarios)
                if (line.startsWith("#")||line.startsWith("m")||line.startsWith("o")||line.startsWith("u")||line.startsWith("s")) {
                    continue;
                }

                // Dividir la línea en palabras
                String[] words = line.trim().split("\\s+");

                // Procesar las líneas que contienen vértices
                if (words[0].equals("v")) {
                    verticesList.add(Float.parseFloat(words[1]));
                    verticesList.add(Float.parseFloat(words[2]));
                    verticesList.add(Float.parseFloat(words[3]));
                }
                // Procesar las líneas que contienen coordenadas de textura
                else if (words[0].equals("vt")) {
                    vTexturasList.add(Float.parseFloat(words[1]));
                    vTexturasList.add(Float.parseFloat(words[2]));
                }
                // Procesar las líneas que contienen normales
                else if (words[0].equals("vn")) {
                    vNormalesList.add(Float.parseFloat(words[1]));
                    vNormalesList.add(Float.parseFloat(words[2]));
                    vNormalesList.add(Float.parseFloat(words[3]));
                }
                // Procesar las líneas que contienen índices
                else if (words[0].equals("f")) {
                    // Iterar sobre las palabras que representan los índices
                    for (int i = 1; i < words.length; i++) {
                        String[] vertexData = words[i].split("/");

                        //int vertexIndex = Integer.parseInt(vertexData[0]);
                        //int textureCoordIndex = Integer.parseInt(vertexData[1]);
                        //int normalIndex = Integer.parseInt(vertexData[2]);
                        short indx = (short) (Short.parseShort(vertexData[0])-1);
                        indicesList.add(indx);
                    }
                }
            }

            vertices = new float[verticesList.size()];
            for (int i=0;i<vertices.length;i++) {
                vertices[i] =  verticesList.get(i);
                System.out.println(vertices[i]+ " ");
            }

            indices = new short[indicesList.size()];
            for (int i=0;i<indices.length;i++) {
                indices[i] =  indicesList.get(i);
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndices() {
        return indices;
    }
}

