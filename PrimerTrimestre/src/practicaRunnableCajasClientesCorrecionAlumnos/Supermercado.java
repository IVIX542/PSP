package practicaRunnableCajasClientesCorrecionAlumnos;

/**
 * @author Iván López Benítez
 */

import java.util.Scanner;

public class Supermercado {

    //Variables globales
    static final String errorStringDatos="ERROR: Número fuera del límite";
    static final String errorExcepcionCajas="Error al esperar a que terminen las cajas";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Variables para cajas
        int numCajas = 0;
        boolean entradaValida = false;
        
        //Variables para clientes
        int numClientes = 0;
        entradaValida = false;
        //Array de clientes
        Cliente[] clientes = new Cliente[numClientes];
        
        // Introducimos el número de cajas por teclado
        while (!entradaValida) {
            System.out.print("Introduce el número de cajas (1-5): ");
            int inputCajas = scanner.nextInt();
            if (inputCajas >= 1 && inputCajas <= 5) {
                numCajas = inputCajas;
                entradaValida = true;
            } else {
                System.out.println(errorStringDatos);
            }
        }
        
        // Introducimos el número de clientes por teclado
        while (!entradaValida) {
            System.out.print("Introduce el número de clientes (1-7): ");
            int inputClientes = scanner.nextInt();
            if (inputClientes >= 1 && inputClientes <= 7) {
                numClientes = inputClientes;
                entradaValida = true;
            } else {
                System.out.println(errorStringDatos);
            }
        }
        
        // Creamos las cajas
        Caja[] cajas = new Caja[numCajas];
        for (int i = 0; i < numCajas; i++) {
            cajas[i] = new Caja(i);
        }
        
        // Creamos los clientes, introduciendo los productos de cada cliente por teclado
        for (int i = 0; i < numClientes; i++) {
            int numProductos;
            do {
                System.out.print("Número de artículos del cliente " + (i + 1) + " (1-6): ");
                numProductos = scanner.nextInt();
                if (numProductos < 1 || numProductos > 6) {
                    System.out.println(errorStringDatos);
                }
            } while (numProductos < 1 || numProductos > 6);
            
            clientes[i] = new Cliente(i + 1, numProductos, null);
        }
        
        //Iniciamos el supermercado
        long tiempoInicioTotal = System.currentTimeMillis();
        
        // Crear una copia (final) de los arrays para usar en los hilos
        final Caja[] cajasFinal = cajas;
        final Cliente[] clientesFinal = clientes;
        final int numCajasFinal = numCajas;
        final int numClientesFinal = numClientes;
        
        // Crear un array para almacenar los hilos de las cajas
        Thread[] hilosCajas = new Thread[numCajasFinal];
        
        // Iniciar un hilo (cliente) por cada caja
        for (int i = 0; i < numCajasFinal; i++) {

            final int cajaIndex = i;
            hilosCajas[i] = new Thread(() -> {
                // Metemos los clientes en la caja
                for (int j = cajaIndex; j < numClientesFinal; j += numCajasFinal) {
                    cajasFinal[cajaIndex].atenderCliente(clientesFinal[j]);
                }
            });

            //Iniciamos el hilo de cada caja
            hilosCajas[i].start();
        }
        
        // Esperar a que todas las cajas terminen
        for (Thread hilo : hilosCajas) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(errorExcepcionCajas);
            }
        }
        
        // Calculamos el  tiempo total
        long tiempoFinTotal = System.currentTimeMillis();
        long tiempoTotal = (tiempoFinTotal - tiempoInicioTotal) / 1000;
        
        // Mostramos el tiempo total por pantalla
        System.out.println("\nTiempo total global para atender a todos los clientes: " + tiempoTotal + " segundos\n");
        
        //Cerramos el scanner
        scanner.close();
    }
}
