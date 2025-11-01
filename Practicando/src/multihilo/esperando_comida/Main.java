package multihilo.esperando_comida;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        //Creación de los hilos
        Thread hiloSopa = new Thread(new Cocinero(), "sopa");
        Thread hiloFilete = new Thread(new Cocinero(), "filete");
        Thread hiloPostre = new Thread(new Cocinero(), "postre");

        //Iniciamos los hilos
        hiloSopa.start();
        hiloFilete.start();
        hiloPostre.start();

        /*
         * NUEVO: El hilo main debe esperar a que los tres terminen
         * Para ello, se debe utilizar el método .join() con cada hilo
        */
        hiloSopa.join();
        hiloFilete.join();
        hiloPostre.join();

        System.out.println("¡El menú está listo para servir!");
    }
}
