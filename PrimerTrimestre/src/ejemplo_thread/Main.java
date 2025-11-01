package src.ejemplo_thread;
public class Main {
    /**
     * @author Iván López Benítez
     * Realiza un programa que cree tres hilos y que cada uno de ellos imprima 5 veces el mensaje:
     * "Hilo: " + nombre del hilo + " contador: " + número
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Comienza el programa...");

        // 1. Crear la instacia de la tarea
        // Los hilos comparten la misma tarea (lógica)
        TareaImprimir tarea = new TareaImprimir();

        // 2. Creamos los hilos
        //Al crearlos, les pasamos la tarea y el nombre Thread(tarea, nombreHilo)
        Thread hilo1 = new Thread(tarea, "Hilo1");
        Thread hilo2 = new Thread(tarea, "Hilo2");
        Thread hilo3 = new Thread(tarea, "Hilo3");

        // 3. Iniciamos los hilos
        /*
         * NOTE: Usamos .start() para que se ejecuten en un hilo nuevo.
         * Si usáramos .run(), se ejecutarían uno después de otro en el hilo principal.
        */
        hilo1.start();
        hilo2.start();
        hilo3.start();

        //Apartado de prueba mientras los hilos están en ejecución
        System.out.println("Los hilos se están ejecutando...");
        for (int i = 1; i<=5; i++){
            System.out.println("Ejecución en el main: " + i);
        }
        
        System.out.println("Finaliza el main...");
    }
}
