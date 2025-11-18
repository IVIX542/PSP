/**
 * Clase principal que coordina la ejecución del programa Ping-Pong.
 * Crea 10 hilos Ping y 10 hilos Pong, todos compartiendo el mismo recurso Pelota.
 * Espera a que todos los hilos finalicen su ejecución antes de mostrar el mensaje de fin.
 * 
 * @author Iván López Benítez
 * @version 1.0
 */
public class Main {
    /**
     * Método principal de entrada del programa.
     * Crea los hilos Ping y Pong, los inicia y espera su finalización.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        /** Recurso compartido Pelota para sincronización entre hilos */
        Pelota pelota = new Pelota();

        /** Array de hilos de tipo Ping */
        Ping[] ping = new Ping[10];
        /** Array de hilos de tipo Pong */
        Pong[] pong = new Pong[10];

        for(int i = 0; i < 10; i++){
            ping[i] = new Ping(pelota, "" + (i+1));
            pong[i] = new Pong(pelota, "" + (i+1));

            ping[i].setName("Thread-" + (i+1));
            pong[i].setName("Thread-" + (i+1));
            
            ping[i].start();
            pong[i].start();
        }

        // Wait for all threads to complete
        for(int i = 0; i < 10; i++){
            try {
                ping[i].join();
                pong[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("********GAME OVER********");
    }
}
