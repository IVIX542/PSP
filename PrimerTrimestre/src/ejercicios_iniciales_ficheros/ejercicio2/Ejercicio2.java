package  ejercicios_iniciales_ficheros.ejercicio2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException {
        //2. Crea un  fichero llamado "ejercicio02.txt" que contenga la fecha y hora  actual en un directorio 'txt' que  no existe previamente.  Muestra una línea en pantalla que confirme la creación del directorio  o la existencia previa de éste.

        File dir = new File("ejercicios_iniciales_ficheros\\ejercicio2\\txt");
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("Directorio creado correctamente");
        } else {
            System.out.println("El directorio ya existe");
        }

        File fichero = new File("ejercicio02.txt");
        FileWriter fich = new FileWriter("ejercicios_iniciales_ficheros\\ejercicio2\\txt\\ejercicio02.txt");
        String cadena = java.time.LocalDateTime.now().toString();

        fich.write(cadena);
        fich.close();
        System.out.println("Fichero creado correctamente");
    }
}
