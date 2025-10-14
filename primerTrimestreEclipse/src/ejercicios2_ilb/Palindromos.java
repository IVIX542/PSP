package ejercicios2_ilb;

public class Palindromos {

	public static void main(String[] args) {
		/**
		 * @author Iván López Benítez
		 * Realiza un programa que lea una cadena pasada como argumento al main() y
		 * visualice en pantalla si la cadena es o no palíndromo (palabra que es igual
		 * si se lee de izquierda a derecha que de derecha a izquierda), en este caso 
		 * será una salida normal. Si no se proporciona parámetro a la entrada se
		 * indicará este hecho con un mensaje y la salida del programa será con un -1
		 * para indicar un error aunque el programa no genera ninguna excepción.
		 * 
		 * Para sinplificar el ejercicio, convierte los caracteres a minúsculas antes
		 * de efectuar la comparación.
		 * */
		
		// Nos aseguramos de que el usuario ha introducido una palabra.
        if (args.length == 0) {
            System.out.println("Por favor, introduce una palabra como argumento.");
            return; // Salimos del método main si no hay argumentos.
        }

        String palabra = args[0].toLowerCase(); // Convertimos a minúsculas para no distinguir mayúsculas/minúsculas.
        boolean esPalindromo = true; // Asumimos que la palabra SÍ es un palíndromo hasta que se demuestre lo contrario.

        // 1. Creamos dos índices: uno al principio (i) y otro al final (j).
        int i = 0;
        int j = palabra.length() - 1;

        // 2. Mientras el índice del principio sea menor que el del final, seguimos comparando.
        while (i < j) {
            
            if (palabra.charAt(i) != palabra.charAt(j)) {
                esPalindromo = false; // NO es un palíndromo.
            }
            
            i++;
            j--;
        }

        // 5. FUERA DEL BUCLE, tomamos la decisión final.
        if (esPalindromo) {
            System.out.println(args[0] + " es un palíndromo.");
            System.exit(0);
        } else {
            System.out.println(args[0] + " no es un palíndromo.");
            System.exit(-1);
        }
    
		
		
	}

}
