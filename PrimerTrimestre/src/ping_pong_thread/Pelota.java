/**
 * Clase que representa el recurso compartido Pelota.
 * Contiene un booleano que controla el turno de ejecución entre hilos Ping y Pong.
 * Utiliza mecanismos de sincronización para garantizar la alternancia correcta.
 * 
 * @author Iván López Benítez
 * @version 1.0
 */
public class Pelota {
    /** Booleano que indica si es el turno de Ping (true) o Pong (false) */
    private boolean pingTurn = true;
    
    /**
     * Método sincronizado que permite a un hilo Ping ejecutar su acción.
 * Espera si no es su turno y notifica a los demás hilos cuando termina.
     * 
     * @param threadName Nombre del hilo que está ejecutando la acción
     */
    public synchronized void ping(String threadName) {
        while (!pingTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("ping (" + threadName + ")");
        pingTurn = false;
        notifyAll();
    }
    
    /**
     * Método sincronizado que permite a un hilo Pong ejecutar su acción.
 * Espera si no es su turno y notifica a los demás hilos cuando termina.
     * 
     * @param threadName Nombre del hilo que está ejecutando la acción
     */
    public synchronized void pong(String threadName) {
        while (pingTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("PONG (" + threadName + ")");
        pingTurn = true;
        notifyAll();
    }
}
