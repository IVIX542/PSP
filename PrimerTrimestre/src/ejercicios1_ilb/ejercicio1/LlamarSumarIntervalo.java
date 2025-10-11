package ejercicios1_ilb.ejercicio1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Iván López Benítez
 */
public class LlamarSumarIntervalo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("java", "SumarIntervaloNumeros.java", "1", "10");
        pb.directory(new File("." + File.separator + "PSP" + File.separator + "PrimerTrimestre" + File.separator + "src" + File.separator + "ejercicios1_ilb" + File.separator + "ejercicio1"));
        Process p = pb.start();
        pb.redirectErrorStream(true);
        InputStream stream = p.getInputStream();
        int c;
        while ((c = stream.read()) != -1) {
            System.out.print((char) c);
        }
        stream.close();
        int exitcode = p.waitFor();
        System.out.println("El proceso ha terminado con código: " + exitcode);
    }
}
