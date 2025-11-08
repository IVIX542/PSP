package practica_runnable;

/**
 * @author Iván López Benítez
 */

import java.util.Scanner;
import java.util.Random;

public class Supermercado {

    static final String errorStringDatos="ERROR: Número fuera del límite";
    static final String errorExcepcionCajas="Error al esperar a que terminen las cajas";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar número de cajas (1-5)
        int numCajas = 0;
        boolean entradaValida = false;
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
        
        // Solicitar número de clientes (1-7)
        int numClientes = 0;
        entradaValida = false;
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
        
        // Crear cajas
        Caja[] cajas = new Caja[numCajas];
        for (int i = 0; i < numCajas; i++) {
            cajas[i] = new Caja(i);
        }
        
        // Crear clientes con productos especificados por el usuario (1-6 productos por cliente)
        Cliente[] clientes = new Cliente[numClientes];
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
        
        //Inicio simulación
        long tiempoInicioTotal = System.currentTimeMillis();
        
        // Crear una copia final de los arrays para usar en los hilos
        final Caja[] cajasFinal = cajas;
        final Cliente[] clientesFinal = clientes;
        final int numCajasFinal = numCajas;
        final int numClientesFinal = numClientes;
        
        // Crear un array para almacenar los hilos de las cajas
        Thread[] hilosCajas = new Thread[numCajasFinal];
        
        // Iniciar un hilo por cada caja
        for (int i = 0; i < numCajasFinal; i++) {
            final int cajaIndex = i;
            hilosCajas[i] = new Thread(() -> {
                // Asignar clientes a esta caja
                for (int j = cajaIndex; j < numClientesFinal; j += numCajasFinal) {
                    cajasFinal[cajaIndex].atenderCliente(clientesFinal[j]);
                }
            });
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
        
        // Calcular tiempo total
        long tiempoFinTotal = System.currentTimeMillis();
        long tiempoTotal = (tiempoFinTotal - tiempoInicioTotal) / 1000;
        
        // Mostrar resumen
        System.out.println("\nRESUMEN");
        System.out.println("=======\n");
        
        // Mostrar tiempo total de la simulación
        System.out.println("Tiempo total de la simulación: " + tiempoTotal + " segundos\n");
        
        // Mostrar estadísticas por caja
        for (Caja caja : cajas) {
            System.out.println(caja.getNombre() + " - Clientes atendidos: " + caja.getClientesAtendidos() + " - Tiempo total: " + caja.getTiempoTotal() + " segundos");
        }
        
        // Mostrar tiempo de cada cliente
        System.out.println("\nTiempo por cliente:");
        for (Cliente cliente : clientes) {
            System.out.println("Cliente " + cliente.getId() + ": " + cliente.getTiempoTotal() + " segundos");
        }
        
        scanner.close();
    }
}
