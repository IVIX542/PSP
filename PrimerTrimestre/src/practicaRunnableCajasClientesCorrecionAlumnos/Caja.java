package practicaRunnableCajasClientesCorrecionAlumnos;

/**
 * @author Iván López Benítez
 */

public class Caja {

    //Variables globales
    private final String nombre;
    private final int numero;
    private int clientesAtendidos;
    private long tiempoTotal;

    /**
     * Constructor de la clase Caja
     * @param numero
     */
    public Caja(int numero) {
        this.numero = numero + 1;
        this.nombre = "Caja " + this.numero;
        this.clientesAtendidos = 0;
        this.tiempoTotal = 0;
    }

    /**
     * Método para atender a un cliente
     * @param cliente
     */
    public void atenderCliente(Cliente cliente) {
        //Establecemos la caja en la que está el cliente
        cliente.setCaja(this);

        //Creamos el hilo del cliente
        Thread hiloCliente = new Thread(() -> {
            try {
                // Runeamos el cliente
                cliente.run();
                
                // Actualizamos los contadores
                clientesAtendidos++;

                //Sumamos el tiempo total por cada iteración
                tiempoTotal += cliente.getTiempoTotal();
                
            } catch (Exception e) {//Si ocurre cualquier excepción

                //Interrumpimos el hilo actual
                Thread.currentThread().interrupt();
                //Mostramos el mensaje de error con el id del cliente
                System.out.println("Error al atender al cliente " + cliente.getId());
            }
        });
        
        // Iniciamos el hilo del cliente sin esperar a que termine
        hiloCliente.start();
        // Esperar a que el hilo del cliente termine antes de devolver el control
        try {
            hiloCliente.join();
        } catch (InterruptedException e) {//Si ocurre una excepción de interrupción
            //Interrumpimos el hilo actual
            Thread.currentThread().interrupt();
            //Mostramos el mensaje de error con el id del cliente
            System.out.println("La espera del hilo del cliente fue interrumpida para el cliente " + cliente.getId());
        }
    }

    /**
     * Método getNombre
     * @return nombre de la caja
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método getNumero
     * @return numero de la caja
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Método getClientesAtendidos
     * @return clientesAtendidos en la caja
     */
    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    /**
     * Método getTiempoTotal
     * @return tiempoTotal de atención en la caja
     */
    public long getTiempoTotal() {
        return tiempoTotal;
    }
}
