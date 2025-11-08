package practica_runnable;

/**
 * @author Iván López Benítez
 */

import java.util.Random;

public class Cliente implements Runnable {
    private int id;
    private Caja caja;
    private int numProductos;
    private long tiempoInicio;
    private long tiempoFin;
    private long tiempoTotal;
    private long[] tiemposProductos;
    private Random random = new Random();

    public Cliente(int id, int numProductos, Caja caja) {
        this.id = id;
        this.numProductos = numProductos;
        this.caja = caja;
        this.tiemposProductos = new long[numProductos];
    }

    @Override
    public void run() {
        System.out.println("Comienzo Cliente " + id + ", tiempo 0 en " + caja.getNombre());
        tiempoInicio = System.currentTimeMillis();
        
        for (int i = 0; i < numProductos; i++) {
            int tiempoProcesamiento = random.nextInt(5) + 1; // 1-5 segundos
            tiemposProductos[i] = tiempoProcesamiento;

            tiempoFin = System.currentTimeMillis();
            tiempoTotal = (tiempoFin - tiempoInicio) / 1000;
            
            System.out.println("Vendido producto " + (i + 1) + " del Cliente " + id + " en " + caja.getNombre() + ", tiempo total del cliente: " + tiempoTotal + " segundos.");
            
            try {
                Thread.sleep(tiempoProcesamiento * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en el procesamiento del cliente " + id);
                return;
            }
        }
        
        tiempoFin = System.currentTimeMillis();
        tiempoTotal = (tiempoFin - tiempoInicio) / 1000;
        System.out.println("*** El Cliente " + id + " ha terminado su compra en " + caja.getNombre() + ", tiempo total del cliente: " + tiempoTotal + " segundos.");
    }

    public long getTiempoTotal() {
        return (tiempoFin - tiempoInicio) / 1000;
    }
    
    public int getNumProductos() {
        return numProductos;
    }
    
    public int getId() {
        return id;
    }
    
    public void setCaja(Caja caja) {
        this.caja = caja;
    }
}
