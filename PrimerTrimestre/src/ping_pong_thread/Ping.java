import java.util.Random;

/**
 * Clase que representa un hilo de tipo Ping.
 * Hereda de Thread y ejecuta acciones de tipo "ping" sobre el recurso compartido Pelota.
 * Utiliza tiempos de espera aleatorios para simular diferentes duraciones de ejecución.
 * 
 * @author Iván López Benítez
 * @version 1.0
 */
public class Ping extends Thread{
    /** Recurso compartido Pelota para sincronización con hilos Pong */
    private Pelota pelota;
    /** Identificador único del hilo */
    private String id;
    
    /**
     * Constructor de la clase Ping.
     * 
     * @param pelota Recurso compartido para sincronización
     * @param id Identificador único del hilo
     */
    public Ping(Pelota pelota, String id) {
        this.pelota = pelota;
        this.id = id;
    }
    
    /**
     * Método principal de ejecución del hilo.
     * Ejecuta 5 iteraciones llamando al método ping de Pelota con tiempos de espera aleatorios.
     */
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            pelota.ping(getName());
            try {
                Random rand = new Random();
                int sleepTime = 1000; // 500-1500ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
