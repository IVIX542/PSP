package ejercicios_iniciales_ficheros.ejercicio1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
    //1. Crea un  fichero llamado "ejercicio01.txt" que contenga la fecha y hora  actual.
    //Muestra  una línea en pantalla que confirme  que  se ha creado  correctamente.
    public static void main(String[] args) throws IOException {
        File fichero = new File("ejercicio01.txt");
        FileWriter fich = new FileWriter("ejercicios_iniciales_ficheros\\ejercicio1\\" + fichero.getName());
        String cadena = java.time.LocalDateTime.now().toString();

        fich.write(cadena);
        fich.close();
        
        System.out.println("Fichero creado correctamente");
    }
}
