package ejercicios2_ilb;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class LlamarPalindromos {

	/**
	 * @author Iván López Benítez
	 * Realiza un segundo programa Java. Desde el programa principal, el fichero de entrada, inputPalinEj08.txt debe ser leído línea a línea
	 * y por cada una de ellas, se debe mandar ejecutar un proceso que lance la clase creada en el apartado anterior. La salida de esta
	 * ejecución debe mostrarse por pantalla e incluir información sobre la palabra estudiada. También debe mostrar una línea en la que se
	 * informe sobre el fin de la comprobación del fichero estudiado
	 * */
    public static void main(String[] args) {
        String nombreFichero = ".//src//ejercicios2_ilb//inputPalinEj08.txt";
        System.out.println("Iniciando la comprobación de palíndromos del fichero: " + nombreFichero);
        System.out.println("----------------------------------------------------------");

        // Usamos try-with-resources para asegurar que el lector del fichero se cierre automáticamente
        try (BufferedReader br = Files.newBufferedReader(Paths.get(nombreFichero))) {
            String palabra;
            // Leer el fichero línea a línea
            while ((palabra = br.readLine()) != null) {
                // Ignorar líneas vacías
                if (palabra.trim().isEmpty()) {
                    continue;
                }

                System.out.println("Estudiando la palabra: '" + palabra + "'");

                // Construir el comando para lanzar el proceso hijo
                // Comando: java VerificadorPalindromo "la palabra leída"
                ProcessBuilder pb = new ProcessBuilder("java", "VerificadorPalindromo", palabra);

                // Iniciar el proceso
                Process procesoHijo = pb.start();

                // Capturar y mostrar la salida del proceso hijo
                try (BufferedReader readerProceso = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()))) {
                    String lineaSalida;
                    while ((lineaSalida = readerProceso.readLine()) != null) {
                        System.out.println("   Resultado: " + lineaSalida);
                    }
                }

                // Es una buena práctica esperar a que el proceso hijo termine
                int exitCode = procesoHijo.waitFor();
                if (exitCode != 0) {
                     System.out.println("   El proceso hijo terminó con un código de error: " + exitCode);
                }
                 System.out.println(); // Añadir un salto de línea para mayor claridad
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de entrada: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido: " + e.getMessage());
        } finally {
            // Este mensaje se muestra siempre al final
            System.out.println("----------------------------------------------------------");
            System.out.println(" Fin de la comprobación del fichero estudiado.");
        }
    }
}