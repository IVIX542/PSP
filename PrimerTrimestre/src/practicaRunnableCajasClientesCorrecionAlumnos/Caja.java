package practicaRunnableCajasClientesCorrecionAlumnos;

/**
 * @author Iván López Benítez
 */

public class Caja {
    private String nombre;
    private int numero;
    private int clientesAtendidos;
    private long tiempoTotal;

    public Caja(int numero) {
        this.numero = numero + 1;
        this.nombre = "Caja " + this.numero;
        this.clientesAtendidos = 0;
        this.tiempoTotal = 0;
    }

    public void atenderCliente(Cliente cliente) {
        cliente.setCaja(this);
        Thread hiloCliente = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    // Ejecutamos el cliente
                    cliente.run();
                    
                    // Actualizamos los contadores
                    clientesAtendidos++;
                    tiempoTotal += cliente.getTiempoTotal();
                    
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Error al atender al cliente " + cliente.getId());
                }
            }
        });
        
        // Iniciamos el hilo del cliente sin esperar a que termine
        hiloCliente.start();
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getNumero() {
        return numero;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }
}
