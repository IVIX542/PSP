package practicaRunnableCajasClientesCorrecionAlumnos;

/**
 * @author Iván López Benítez
 */

import java.util.Random;

public class Cliente implements Runnable {

    //Variables globales
    private final int id;
    private Caja caja;
    private final int numProductos;
    private long tiempoInicio;
    private long tiempoFin;
    private long tiempoTotal;
    private final long[] tiemposProductos;
    private final Random random = new Random();

    /**
     * Constructor de la clase Cliente
     * @param id
     * @param numProductos
     * @param caja
     */
    public Cliente(int id, int numProductos, Caja caja) {
        this.id = id;
        this.numProductos = numProductos;
        this.caja = caja;
        this.tiemposProductos = new long[numProductos];
    }

    /**
     * Método run de la clase Cliente
     */
    @Override
    public void run() {

        System.out.println("Comienzo Cliente " + id + ", tiempo 0 en " + caja.getNombre());
        tiempoInicio = System.currentTimeMillis();
        
        for (int i = 0; i < numProductos; i++) { //Por cada producto
            //Asimulamos el tiempo de procesamiento aleatorio entre 1 y 5 segundos
            int tiempoProcesamiento = random.nextInt(5) + 1;
            tiemposProductos[i] = tiempoProcesamiento; //Guardamos el tiempo del producto

            //Actualizamos el tiempo de finalización y el tiempo total
            tiempoFin = System.currentTimeMillis();
            tiempoTotal = (tiempoFin - tiempoInicio) / 1000;
            
            //Mostramos el mensaje de cada producto vendido
            System.out.println("Vendido producto " + (i + 1) + " del Cliente " + id + " en " + caja.getNombre() + ", tiempo total del cliente: " + (tiempoTotal + tiempoProcesamiento) + " segundos.");
            
            try {
                Thread.sleep(tiempoProcesamiento * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en el procesamiento del cliente " + id);
            }
        }
        
        // Actualizamos el tiempo de finalización y el tiempo total
        tiempoFin = System.currentTimeMillis();
        tiempoTotal = (tiempoFin - tiempoInicio) / 1000;

        //Mostramos el mensaje de fin de compra
        System.out.println("\n*** El Cliente " + id + " ha terminado su compra en " + caja.getNombre() + ", tiempo total del cliente: " + tiempoTotal + " segundos.");
    }

    /**
     * Método que devuelve el tiempo total del cliente
     * @return tiempoTotal del cliente
     */
    public long getTiempoTotal() {
        return (tiempoFin - tiempoInicio) / 1000;
    }
    
    /**
     * Método que devuelve el numero de productos del cliente
     * @return numProductos del cliente
     */
    public int getNumProductos() {
        return numProductos;
    }
    
    /**
     * Método que devuelve el ID del cliente
     * @return ID del cliente
     */
    public int getId() {
        return id;
    }
    
    /**
     * Método que asigna una caja al cliente
     * @param caja
     */
    public void setCaja(Caja caja) {
        this.caja = caja;
    }
}
