package practica_runnable;

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

    public synchronized void atenderCliente(Cliente cliente) {
        cliente.setCaja(this);
        Thread hiloCliente = new Thread(cliente);
        hiloCliente.start();
        
        try {
            hiloCliente.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error al atender al cliente " + cliente.getId());
            return;
        }
        
        clientesAtendidos++;
        tiempoTotal += cliente.getTiempoTotal();
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
