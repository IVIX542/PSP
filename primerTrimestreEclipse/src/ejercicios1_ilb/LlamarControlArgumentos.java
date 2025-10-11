package ejercicios1_ilb;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author Iván López Benítez
 * Ejercicio3: ControlArgumentos
 * */

public class LlamarControlArgumentos {

 public static void main(String[] args) {
     System.out.println("Iniciando las pruebas para ControlArgumentos.java...");

     // Definimos una lista con los casos de prueba que queremos enviar.
     // Cada caso es un array de Strings.
     String[][] casosDePrueba = {
         {},                 // 1. Sin argumentos
         {"hola"},           // 2. Con una cadena de texto
         {"-50"},            // 3. Con un entero negativo
         {"25"},             // 4. Con un entero positivo o cero
         {"-10", "extra"}    // 5. Con demasiados argumentos
     };

     // Recorremos cada caso de prueba y lo ejecutamos.
     for (String[] prueba : casosDePrueba) {
         llamarProceso(prueba);
     }
 }

 /**
  * Método que ejecuta el programa ControlArgumentos.java como un proceso externo,
  * le pasa los argumentos indicados y analiza el código de salida que devuelve.
  * @param argumentos Los argumentos a pasar al programa.
  */
 public static void llamarProceso(String[] argumentos) {
     // Creamos una lista para construir el comando completo que se ejecutará en la terminal.
     // El comando será: java -cp bin ControlArgumentos [argumentos...]
     ArrayList<String> comando = new ArrayList<>();
     comando.add("java");
     comando.add("-cp");
     comando.add("bin"); // Asumimos que los .class están en la carpeta 'bin'
     comando.add("ControlArgumentos");
     comando.addAll(Arrays.asList(argumentos));

     System.out.println("\n--- Ejecutando con argumentos: " + Arrays.toString(argumentos) + " ---");

     try {
         // Usamos ProcessBuilder para preparar el comando.
         ProcessBuilder pb = new ProcessBuilder(comando);
         
         // pb.inheritIO(); // Descomenta esta línea si quieres ver los mensajes "DEBUG" del otro programa.

         // Iniciamos el proceso. Esto es como dar "Enter" en la terminal.
         Process proceso = pb.start();

         // Esperamos a que el proceso termine y guardamos el código que devuelve. ¡Esta es la parte clave!
         int codigoSalida = proceso.waitFor();

         System.out.print("Proceso terminado. Código de salida: " + codigoSalida + ". -> ");

         // Usamos un 'switch' para interpretar el código de salida y mostrar un mensaje claro.
         switch (codigoSalida) {
             case 1:
                 System.out.println("Resultado: Devolvió 1, el número de argumentos era < 1.");
                 break;
             case 2:
                 System.out.println("Resultado: Devolvió 2, el argumento es una cadena.");
                 break;
             case 3:
                 System.out.println("Resultado: Devolvió 3, el argumento es un número entero menor que 0.");
                 break;
             case 4:
                 System.out.println("Resultado: Devolvió 4, el argumento es un número entero >= 0.");
                 break;
             case -1:
                 System.out.println("Resultado: Devolvió -1, situación no controlada (más de 1 argumento).");
                 break;
             default:
                 System.out.println("Resultado: Código de salida inesperado.");
                 break;
         }

     } catch (IOException | InterruptedException e) {
         // Manejo de posibles errores al ejecutar el proceso.
         e.printStackTrace();
     }
 }
}
