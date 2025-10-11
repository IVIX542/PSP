package ejercicios1_ilb;

/**
 * @author Iván López Benítez
 * Ejercicio3: ControlArgumentos
 * */

public class ControlArgumentos {

	public static void main(String[] args) {

    // Caso 1: Si no hay argumentos (la longitud del array es 0)
    if (args.length < 1) {
         System.out.println("DEBUG: No se recibieron argumentos.");
         System.exit(1);
     }

     // Caso "cualquier otra situación": más de 1 argumento
     if (args.length > 1) {
         System.exit(-1);
     }

     // Si llegamos aquí, es porque hay exactamente 1 argumento.
     String argumento = args[0];

     try {
         // Intentamos convertir el argumento a un número entero.
         int numero = Integer.parseInt(argumento);

         // Si se pudo convertir, comprobamos si es negativo o no.
         if (numero < 0) {
             // Caso 3: Es un entero negativo.
             System.exit(3);
         } else {
             // Caso 4: Es un entero mayor o igual a 0.
             System.exit(4);
         }

     } catch (NumberFormatException e) {
         // Si Integer.parseInt() falla, es porque el argumento no es un número.
         // Caso 2: Es una cadena de texto.
         System.exit(2);
     }
 }
}