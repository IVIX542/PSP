package ejercicios1_ilb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Iván López Benítez
 * Ejercicio1: SumarIntervaloNumeros
 */
public class LlamarSumarIntervalo {
    public static void main(String[] args) throws IOException, InterruptedException {
    	try {

            File directorio = new File(".\\src\\ejercicios1_ilb"); // Establecemos el directorio de trabajo

            ProcessBuilder pb = new ProcessBuilder("java", "SumaIntervaloNumeros.java", "1", "5"); /* proceso,
            comando, argumentos*/

            pb.directory(directorio); // establecemos el directorio como por defecto de pb

            System.out.println("El directorio es : " + pb.directory());

            Process p = pb.start(); // empezamos el proceso

            //Imprimimos lo que devuelve el programa
            InputStream is = p.getInputStream();
            int c;
            while((c = is.read()) != -1){
                System.out.print((char) c);

            }
            
            //Imprimimos el código de salida del proceso (0=bien, !0=mal)
            int codigoSalida = p.waitFor();
            is.close();
            System.out.print("Proceso finalizado, codigo de salida: " + codigoSalida);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
