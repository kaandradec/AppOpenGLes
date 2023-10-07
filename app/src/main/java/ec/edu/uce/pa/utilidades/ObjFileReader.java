package ec.edu.uce.pa.utilidades;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ObjFileReader {
    private float[] vertices;
    private short[] indices;

    public ObjFileReader(String archivoObj, Context context) {
        try {
            InputStream ruta = context.getAssets().open(archivoObj);
            Scanner scanner = new Scanner(ruta);

            int numVertices = 0;
            int numIndices = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.matches("^[mous].*")) {
                    continue;
                }

                String[] words = line.trim().split("\\s+");
                if (words[0].equals("v")) {
                    numVertices++;
                } else if (words[0].equals("f")) {
                    numIndices += words.length - 1;
                }
            }

            vertices = new float[numVertices * 3];
            indices = new short[numIndices];

            int vertexIndex = 0;
            int indexIndex = 0;

            scanner = new Scanner(context.getAssets().open(archivoObj));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.matches("^[mous].*")) {
                    continue;
                }

                String[] words = line.trim().split("\\s+");
                if (words[0].equals("v")) {
                    for (int i = 1; i < 4; i++) {
                        vertices[vertexIndex++] = Float.parseFloat(words[i]);
                    }
                } else if (words[0].equals("f")) {
                    for (int i = 1; i < words.length; i++) {
                        String[] vertexData = words[i].split("/");
                        indices[indexIndex++] = (short) (Short.parseShort(vertexData[0]) - 1);
                    }
                }
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