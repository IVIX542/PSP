package practica_runnable;

import java.util.Random;

public class Cliente implements Runnable {
    private int id;
    private Caja caja;
    private int numProductos;
    private long tiempoInicio;
    private long tiempoFin;
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
        tiempoInicio = System.currentTimeMillis();
        System.out.println("[" + getTiempoActual() + "] Cliente " + id + " empieza a ser atendido en " + 
                         caja.getNombre() + " con " + numProductos + " artículos");
        
        for (int i = 0; i < numProductos; i++) {
            int tiempoProcesamiento = random.nextInt(5) + 1; // 1-5 segundos
            tiemposProductos[i] = tiempoProcesamiento;
            
            System.out.println("[" + getTiempoActual() + "] Caja " + caja.getNumero() + ": Artículo " + (i + 1) + 
                             " del cliente " + id + " cobrado en " + tiempoProcesamiento + " segundos.");
            
            try {
                Thread.sleep(tiempoProcesamiento * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en el procesamiento del cliente " + id);
                return;
            }
        }
        
        tiempoFin = System.currentTimeMillis();
        long tiempoTotal = (tiempoFin - tiempoInicio) / 1000;
        System.out.println("[" + getTiempoActual() + "] Cliente " + id + " ha terminado su compra en " + 
                         caja.getNombre() + ". Tiempo total: " + tiempoTotal + " segundos");
    }

    private String getTiempoActual() {
        return String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
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
