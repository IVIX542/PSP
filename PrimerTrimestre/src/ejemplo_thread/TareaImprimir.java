package src.ejemplo_thread;
public class TareaImprimir implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String nombreHilo = Thread.currentThread().getName();
            System.out.println("Hilo: " + nombreHilo + " contador: " + i);

            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error de interrupción:" + e.getMessage());
            }
        }

        System.out.println("---Hilo " + Thread.currentThread().getName() + " finalizado---");
    }

    
}
